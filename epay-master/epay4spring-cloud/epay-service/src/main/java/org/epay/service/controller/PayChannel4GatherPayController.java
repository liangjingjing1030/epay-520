package org.epay.service.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.epay.common.util.EPayUtil;
import org.epay.common.util.MyBase64;
import org.epay.common.util.MyLog;
import org.epay.dal.dao.model.PayOrder;
import org.epay.service.service.GatherPayService;
import org.epay.service.service.PayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 名称: 支付渠道接口:接聚合支付
 * 作者: HappyDan
 * 时间: 2019年4月5日
 * 版本: V1.0
 */
@RestController
public class PayChannel4GatherPayController{

    private final MyLog _log = MyLog.getLog(PayChannel4GatherPayController.class);

    @Autowired
    private PayOrderService payOrderService;

    @Resource
    private GatherPayService gatherPayService;

    /**
     * 方法名: 发起支付，连接聚合支付
     * 作    者: HappyDan
     * 时    间: 2019年4月5日
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/pay/channel/gather_pay")
    public String sendPayRequest(@RequestParam String jsonParam) {
        JSONObject response = new JSONObject();//响应报文response
        JSONObject retHeader = new JSONObject();//响应报文头map对象
        JSONObject retBody = new JSONObject();//响应报文体map对象
        try{
            String logPrefix = "【发送支付订单请求到支付平台】";
            retHeader.put("retCode", "0000");
            if(StringUtils.isBlank(jsonParam)) {
            	retHeader.put("retCode", "0001");
            	retHeader.put("retMsg", "缺少参数");
            	response.put("response_header", retHeader);
                return response.toJSONString();
            }
            _log.info("{}发送支付订单请求到支付平台, params:{}", logPrefix, jsonParam);
        	JSONObject paramObj = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        	_log.info("{}发送支付订单请求到支付平台, params:{}", logPrefix, paramObj.toString());
        	
        	paramObj = paramObj.getJSONObject("payOrder");
        	String res_key = "";
        	if("MGR".equals(paramObj.getString("channel_id"))) {
        		res_key = paramObj.getString("res_key");
            }
        	//组装发送往支付平台数据====================================Start
            Map<String, Object> buildPayOrderRequest = buildUnifiedPayOrderRequest(paramObj);
            String code = (String) buildPayOrderRequest.get("code");
            if(!"0000".equals(code)) {
            	String msg = (String) buildPayOrderRequest.get("msg");
            	retHeader.put("request_channel", paramObj.getString("channel_id"));
            	retHeader.put("retCode", code);
            	retHeader.put("retMsg", msg);
            	response.put("response_header", retHeader);
            	_log.info("返回错误结果", response.toString());
                return response.toJSONString();
            }
            buildPayOrderRequest.remove("code");
            String requestParam = JSON.toJSONString(buildPayOrderRequest);
            //组装发送往支付平台数据====================================End
            
            //发送数据到支付平台支付====================================Start
            try {
            	String result = gatherPayService.sendPayRequest(requestParam);
            	if(!"SUCCESS".equals(result)) {
            		retHeader.put("request_channel", paramObj.getString("channel_id"));
                	retHeader.put("retCode", "0007");
                	retHeader.put("retMsg", "订单支付系统调用处理异常");
                	response.put("response_header", retHeader);
                    return response.toJSONString();
            	}
			} catch (Exception e) {
				retHeader.put("request_channel", paramObj.getString("channel_id"));
            	retHeader.put("retCode", "0007");
            	retHeader.put("retMsg", "订单支付系统调用处理异常");
            	response.put("response_header", retHeader);
                return response.toJSONString();
			}
            //发送数据到支付平台支付====================================End
            
            //更新支付结果到数据库表====================================Start
            PayOrder payOlder = JSON.parseObject(paramObj.toJSONString(), PayOrder.class);
            payOlder.setPay_order_id(paramObj.getString("pay_order_id"));
            payOlder.setStatus((byte)4);
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        	String myDate = format.format(new Date());
            payOlder.setUpdate_time(myDate);
            int result = payOrderService.updateStatus(payOlder);
            if(result <= 0) {
            	retHeader.put("request_channel", paramObj.getString("channel_id"));
            	retHeader.put("retCode", "0118");
            	retHeader.put("retMsg", "更新支付状态DB错误");
            	response.put("response_header", retHeader);
                return response.toJSONString();
            }
            
            retHeader.put("request_channel", paramObj.getString("channel_id"));
        	retHeader.put("retCode", "SUCCESS");
        	retHeader.put("retMsg", "订单正在支付中");
        	response.put("response_header", retHeader);
        	retBody.put("pay_order_id", paramObj.getString("pay_order_id"));
        	response.put("response_body", retBody);
        	
            _log.info("{} >>> 支付订单更新数量", result);
            
            if("MGR".equals(paramObj.getString("channel_id"))) {
            	return EPayUtil.makeRetData(response, res_key);
            }else {
            	return EPayUtil.makeRetData(response);
            }
        }catch (Exception e) {
            _log.error(e, "聚合支付统一下单异常");
        	retHeader.put("retCode", "FAIL");
        	retHeader.put("retMsg", "聚合支付统一下单异常");
        	response.put("response_header", retHeader);
            return response.toJSONString();
        }
    }

    /**
     * 构建发送支付订单信息到支付平台请求数据
     * @param payOrder
     * @param wxPayConfig
     * @return
     */
    Map<String, Object> buildUnifiedPayOrderRequest(JSONObject payOrder) {
    	Map<String, Object> request = new HashMap<String, Object>();
    	String pay_order_id = "";//订单号
        String channel_id = "";//请求渠道
        String user_channel_account = "";//用户渠道账号
        String amount = "";//金额（分）
        request.put("code", "0000");
        try {
        	pay_order_id = payOrder.getString("pay_order_id").trim();//订单号
		} catch (Exception e) {
			request.put("code", "0001");
			request.put("msg", "系统获取订单号失败");
			return request;
		}
        try {
        	channel_id = payOrder.getString("channel_id").trim();//请求渠道
		} catch (Exception e) {
			request.put("code", "0001");
			request.put("msg", "系统获取请求渠道失败");
			return request;
		}
        try {
        	user_channel_account = payOrder.getString("user_channel_account").trim();//用户渠道账号
		} catch (Exception e) {
			request.put("code", "0001");
			request.put("msg", "系统获取用户支付账号失败");
			return request;
		}
        try {
        	amount = payOrder.getString("amount").trim();//金额（分）
        	amount = String.valueOf(Integer.valueOf(amount)/100) + ".00";
		} catch (Exception e) {
			request.put("code", "0001");
			request.put("msg", "系统获取订单金额失败");
			return request;
		}
        
        // 聚合支付统一下单请求对象
        Map<String, Object> channel = new HashMap<String, Object>();
        Map<String, Object> requester = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(new Date());
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
		String format1 = sdf1.format(new Date());
		
        channel.put("channelCode", channel_id);
        request.put("channel", channel);
        
        requester.put("tellerId", "1");//TODO 
        requester.put("reqTransDate", format);
        requester.put("reqJournalNo", user_channel_account);//TODO
        requester.put("orgNo", "7001");//TODO
        requester.put("reqTransTime", format1);
        request.put("requester", requester);
        
        request.put("requestType", "N");			//TODO 请求类型
        request.put("merNo", "1234567890");			//TODO 商户号
        request.put("amount", amount);				// 金额
        request.put("merOrderNo", pay_order_id);	// 商户订单号
        request.put("remark", user_channel_account);// 商户备注
        request.put("buttChannel", "cloudOrder");	// 标识cloudOrder
        request.put("callbackUrl", "http://127.0.0.1:3020/epay/pay_result_notify");//TODO 回调地址
        
        return request;
    }
}
