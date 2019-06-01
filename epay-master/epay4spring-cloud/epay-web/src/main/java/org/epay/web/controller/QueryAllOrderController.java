package org.epay.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.epay.common.constant.PayConstant;
import org.epay.common.util.EPayUtil;
import org.epay.common.util.MyLog;
import org.epay.web.service.MchInfoServiceClient;
import org.epay.web.service.MchQueryOrdersServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 类名: 商户当日流水查询
 * 作者: HappyDan
 * 时间: 2019年4月18日
 * 版本: V1.0
 */
@RestController
public class QueryAllOrderController {

    private final MyLog _log = MyLog.getLog(QueryAllOrderController.class);

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private MchQueryOrdersServiceClient mchQueryOrdersServiceClient;

    @Autowired
    private MchInfoServiceClient mchInfoServiceClient;

    /**
     * 商户当日流水查询接口：(mch_query_order)
     * 1)查询商户信息是否正确
     * 2）查询支付订单信息
     * 3）根据条件查询退款订单信息
     * 4）整合支付订单和退款订单返回商户信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/mch_query_order")
    public String mchQueryOrders(@RequestParam String params) {
        _log.info("###### 开始接收商户查询订单流水请求 ######");
        JSONObject object;
        String logPrefix = "【商户查询订单流水】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/mch_query_order, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
            object = JSONObject.parseObject(params);
            JSONObject channelObj = object.getJSONObject("request_header");
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject payContext = new JSONObject();
            // 验证参数有效性
            String errorMessage = validateParams(object, payContext);
            if (!"success".equalsIgnoreCase(errorMessage)) {
                _log.warn(errorMessage);
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, errorMessage, null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            _log.debug("请求参数及签名校验通过");
            //查询满足条件的数据
            String retStr = mchQueryOrdersServiceClient.mchQueryOrders(payContext.toJSONString());
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}商户查询订单流水,结果:{}", logPrefix, retObj);
            if(!"0000".equals(retObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            //获取查询到的对象内容
            JSONArray orderMap = retObj.getJSONArray("result");
            String total_num = retObj.getString("total_num");
            if (orderMap == null) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付订单不存在", null, null);
            	response.put("response_header", retHeader);
            	return EPayUtil.makeRetFail(response);
            }
            
            //组装返回报文response
            retBody.put("total_num", total_num);
            retBody.put("result", orderMap);
            response.put("response_body", retBody);
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "商户查询流水成功，共------" + total_num + "笔流水！", PayConstant.RETURN_VALUE_SUCCESS, null);
            response.put("response_header", retHeader);
            _log.info("商户查询订单成功,payOrder={}", response);
            _log.info("###### 商户查询订单处理完成 ######");
            
            if("MGR".equals(request_channel)) {
            	return EPayUtil.makeRetData(response, payContext.getString("res_key"));
            }else {
            	return EPayUtil.makeRetData(response);
            }
            
        }catch (Exception e) {
            _log.error(e, "");
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null);
            response.put("response_header", retHeader);
            return EPayUtil.makeRetFail(response);
        }
    }

    /**
     * 验证创建订单请求参数,参数通过返回JSONObject对象,否则返回错误文本信息
     * @param params
     * @return
     */
    private String validateParams(JSONObject params, JSONObject payContext) {
        // 验证请求参数,参数有问题返回错误提示
        String errorMessage;
        // 请求参数
        JSONObject request_header = params.getJSONObject("request_header");
        String request_channel =  request_header.getString("request_channel");// 请求渠道
        
        JSONObject request_body = params.getJSONObject("request_body");
        String mch_id = request_body.getString("mch_id");					// 商户ID
        String start_time = request_body.getString("start_time");			// 开始时间
        String end_time = request_body.getString("end_time");				// 结束时间
        String mch_order_no = request_body.getString("mch_order_no");		// 账单号
        String pay_order_id = request_body.getString("pay_order_id");		// 支付订单号
        String user_id = request_body.getString("user_id");					// 客户唯一标识
        String user_name = request_body.getString("user_name");				// 用户名称
        String status = request_body.getString("status");					// 支付状态
        String limit = request_body.getString("limit");						// 页码
        String offset = request_body.getString("offset");					// 每页数量

        String sign = params.getString("sign")	;							// 签名

        
        // 验证请求参数有效性（必选项）
        if(StringUtils.isBlank(request_channel)) {
            errorMessage = "请求参数[request_channel为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(mch_id)) {
            errorMessage = "请求参数[mch_id为空.";
            return errorMessage;
        }
        if("MGR".equals(request_channel)) {
        	// 签名信息
            if (StringUtils.isBlank(sign)) {
                errorMessage = "请求参数[sign为空.";
                return errorMessage;
            }
        }
        // 查询商户信息
        JSONObject mchInfo;
        String retStr = mchInfoServiceClient.selectMchInfoByMchId(getJsonParam("mch_id", mch_id));

        JSONObject retObj = JSON.parseObject(retStr);
        if("0000".equals(retObj.getString("code"))) {
            mchInfo = retObj.getJSONObject("result");
            if (mchInfo == null) {
                errorMessage = "未查询到[mch_id="+mch_id+"] 的商户信息 in db.";
                return errorMessage;
            }
            if(mchInfo.getByte("mch_status") != 1) {
                errorMessage = "商户号为 [mch_id="+mch_id+"] 的商户未启用 in db.";
                return errorMessage;
            }
        }else {
            errorMessage = "未查询到[mch_id="+mch_id+"] 的商户信息 in db.";
            _log.info("查询商户没有正常返回数据,code={},msg={}", retObj.getString("code"), retObj.getString("msg"));
            return errorMessage;
        }
        
        if("MGR".equals(request_channel)) {
        	//商户请求私钥
            String req_key = mchInfo.getString("req_key");
            if (StringUtils.isBlank(req_key)) {
                errorMessage = "req_key is null[mch_id="+mch_id+"] record in db.";
                return errorMessage;
            }
            // 验证签名数据
            boolean verifyFlag = EPayUtil.verifyPaySign(params, req_key);
            if(!verifyFlag) {
                errorMessage = "验证签名信息失败.";
                return errorMessage;
            }
            
            //组织参数对象
            payContext.put("res_key", mchInfo.getString("res_key"));//商户响应私钥
        }
        payContext.put("mch_id", mch_id);
        payContext.put("start_time", start_time);
        payContext.put("end_time", end_time);
        payContext.put("mch_order_no", mch_order_no);
        payContext.put("pay_order_id", pay_order_id);
        payContext.put("user_id", user_id);
        payContext.put("user_name", user_name);
        payContext.put("status", status);
        payContext.put("limit", limit);
        payContext.put("offset", offset);
        
        return "success";
    }

    String getJsonParam(String[] names, Object[] values) {
        JSONObject jsonParam = new JSONObject();
        for (int i = 0; i < names.length; i++) {
            jsonParam.put(names[i], values[i]);
        }
        return jsonParam.toJSONString();
    }

    String getJsonParam(String name, Object value) {
        JSONObject jsonParam = new JSONObject();
        jsonParam.put(name, value);
        return jsonParam.toJSONString();
    }

}
