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
import org.epay.dal.dao.model.RefundOrder;
import org.epay.service.service.GatherPayService;
import org.epay.service.service.RefundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 类名: 退款接口:接聚合支付
 * 作者: HappyDan
 * 时间: 2019年4月29日
 * 版本: V1.0
 */
@RestController
public class RefundChannel4GatherPayController{

    private final MyLog _log = MyLog.getLog(RefundChannel4GatherPayController.class);

    @Autowired
    private RefundOrderService refundOrderService;

    @Resource
    private GatherPayService gatherPayService;

    /**
     * 发起退款请求，连接聚合支付
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/refund/channel/gather_pay")
    public String sendRefundRequest(@RequestParam String jsonParam) {
        JSONObject response = new JSONObject();//响应报文response
        JSONObject retHeader = new JSONObject();//响应报文头map对象
        JSONObject retBody = new JSONObject();//响应报文体map对象
        try{
            String logPrefix = "【发送退款请求到支付平台】";
            retHeader.put("retCode", "SUCCESS");
            if(StringUtils.isBlank(jsonParam)) {
            	retHeader.put("retCode", "FAIL");
            	retHeader.put("retMsg", "缺少参数");
            	response.put("response_header", retHeader);
                return response.toJSONString();
            }
            _log.info("{}MyBase64》》发送退款请求到支付平台, params:{}", logPrefix, jsonParam);
        	JSONObject paramObj = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        	_log.info("{}发送退款请求到支付平台, params:{}", logPrefix, paramObj);
        	
            JSONObject refundObject = paramObj.getJSONObject("refundOrder");
        	String res_key = "";
        	if("MGR".equals(refundObject.getString("channel_id"))) {
        		res_key = refundObject.getString("res_key");
            }
        	//组装发送往支付平台数据====================================Start
            Map<String, Object> buildRefundOrderRequest = buildUnifiedRefundOrderRequest(paramObj);
            _log.info("组装发送往支付平台数据", buildRefundOrderRequest.toString());
            
            String code = (String) buildRefundOrderRequest.get("code");
            if(!"0000".equals(code)) {
            	String msg = (String) buildRefundOrderRequest.get("msg");
            	retHeader.put("request_channel", refundObject.getString("channel_id"));
            	retHeader.put("retCode", "FAIL");
            	retHeader.put("retMsg", msg);
            	response.put("response_header", retHeader);
                return response.toJSONString();
            }
            buildRefundOrderRequest.remove("code");
            String requestParam = JSON.toJSONString(buildRefundOrderRequest);
            //组装发送往支付平台数据====================================End
            
            //发送数据到支付平台支付====================================Start
            String mweb_url = "";
            try {
            	String result = gatherPayService.sendRefundRequest(requestParam);
            	JSONObject object = JSONObject.parseObject(result);
        		String retCode = object.getString("retCode");// 支付结果代码
        		mweb_url = object.getString("mweb_url");// 支付结果代码
            	if(!"SUCCESS".equals(retCode)) {
            		int i = refundOrderService.deleteRefundOrderByPrimaryKey(refundObject.getString("refund_order_id"));
            		System.out.println("============删除退款订单失败的数据条数：" + i);
            		retHeader.put("request_channel", paramObj.getString("channel_id"));
                	retHeader.put("retCode", "0007");
                	retHeader.put("retMsg", "订单支付系统调用处理异常");
                	response.put("response_header", retHeader);
                    return response.toJSONString();
            	}
			} catch (Exception e) {
				retHeader.put("request_channel", refundObject.getString("channel_id"));
            	retHeader.put("retCode", "0007");
            	retHeader.put("retMsg", "订单支付系统调用处理异常");
            	response.put("response_header", retHeader);
                return response.toJSONString();
			}
            //发送数据到支付平台支付====================================End
            
            //更新支付结果到数据库表====================================Start
            RefundOrder refundOrder = JSON.parseObject(refundObject.toJSONString(), RefundOrder.class);
            refundOrder.setRefund_order_id(refundObject.getString("refund_order_id"));
            refundOrder.setStatus((byte)1);
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String myDate = format.format(new Date());
            refundOrder.setUpdate_time(myDate);
            refundOrder.setMch_refund_no(refundObject.getString("mch_refund_no"));
            refundOrder.setPay_amount(Long.valueOf(refundObject.getString("pay_amount")));
            refundOrder.setCurrency(refundObject.getString("currency"));
            refundOrder.setPay_orderid(refundObject.getString("pay_orderid"));
            int result = refundOrderService.updateRefundOrder(refundOrder);
            if(result <= 0) {
            	retHeader.put("request_channel", refundObject.getString("channel_id"));
            	retHeader.put("retCode", "FAIL");
            	retHeader.put("retMsg", "更新退款状态DB错误");
            	response.put("response_header", retHeader);
                return response.toJSONString();
            }
            
            retHeader.put("request_channel", refundObject.getString("channel_id"));
        	retHeader.put("retCode", "SUCCESS");
        	retHeader.put("retMsg", "退款正在进行，请稍后查看");
        	response.put("response_header", retHeader);
        	// update by GaoLiang
            retBody.put("refund_order_id", refundObject.getString("refund_order_id"));
            retBody.put("pay_order_id", refundObject.getString("pay_orderid"));
            retBody.put("status", refundOrder.getStatus());
            retBody.put("mweb_url", mweb_url);
        	response.put("response_body", retBody);
        	
            _log.info("{} >>> 退款申请更新数量", result);
            
            if("MGR".equals(refundObject.getString("channel_id"))) {
            	return EPayUtil.makeRetData(response, res_key);
            }else {
            	return EPayUtil.makeRetData(response);
            }
        }catch (Exception e) {
            _log.error(e, "聚合支付退款异常");
        	retHeader.put("retCode", "FAIL");
        	retHeader.put("retMsg", "聚合支付退款异常");
        	response.put("response_header", retHeader);
            return response.toJSONString();
        }
    }

    /**
     * 构建发送退款申请信息到支付平台请求数据
     * @param payOrder
     * @param payOrder
     * @return
     */
    Map<String, Object> buildUnifiedRefundOrderRequest(JSONObject payOrder) {
    	Map<String, Object> request = new HashMap<String, Object>();
    	Long pay_amount;//订单支付金额
    	String refund_order_id = "";//订单号
        String channel_id = "";//请求渠道
        String user_channel_account = "";//用户渠道账号
        Long refund_amount;//金额（分）
        request.put("code", "0000");
        RefundOrder refundOrder = null;
        try {
            // update by GaoLiang
//        	pay_amount = payOrder.getString("pay_amount").trim();//订单支付金额
            JSONObject refundOrderJSONObject = payOrder.getJSONObject("refundOrder");
            refundOrder = JSON.parseObject(refundOrderJSONObject.toJSONString(), RefundOrder.class);
            pay_amount = refundOrder.getPay_amount();
        } catch (Exception e) {
			request.put("code", "0001");
			request.put("msg", "系统获取订单支付金额失败");
			return request;
		}
        try {
            // update by GaoLiang
        	refund_order_id = refundOrder.getRefund_order_id();//订单号
		} catch (Exception e) {
			request.put("code", "0001");
			request.put("msg", "系统获取订单号失败");
			return request;
		}
        try {
            // update by GaoLiang
        	channel_id = refundOrder.getChannel_id();//请求渠道
		} catch (Exception e) {
			request.put("code", "0001");
			request.put("msg", "系统获取请求渠道失败");
			return request;
		}
        try {
            // update by GaoLiang
        	user_channel_account = refundOrder.getUser_channel_account();//用户渠道账号
		} catch (Exception e) {
			request.put("code", "0001");
			request.put("msg", "系统获取用户支付账号失败");
			return request;
		}
        try {
            // update by GaoLiang
        	refund_amount = refundOrder.getRefund_amount();//金额（分）
//        	refund_amount = String.valueOf(Integer.valueOf(refund_amount)/100) + ".00";
		} catch (Exception e) {
			request.put("code", "0001");
			request.put("msg", "系统获取订单金额失败");
			return request;
		}
        Long payAmount = pay_amount;
        Long refundAmount = refund_amount;
        if(payAmount < refundAmount) {
        	request.put("code", "FAIL");
			request.put("msg", "退款金额大于该订单的支付金额");
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
        
        request.put("requestType", "N");					//TODO 请求类型
        request.put("merNo", "1234567890");					//TODO 商户号
        request.put("refundAmount", refund_amount);			// 金额
        request.put("merRefundOrderNo", refund_order_id);	// 商户订单号
        request.put("remark", user_channel_account);		// 商户备注
        request.put("buttChannel", "cloudOrder");			// 标识cloudOrder
        request.put("callbackUrl", "http://127.0.0.1:3020/epay/pay_result_notify");//TODO 回调地址
        
        return request;
    }
}
