package org.epay.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.epay.common.constant.PayConstant;
import org.epay.common.util.EPayUtil;
import org.epay.common.util.MyLog;
import org.epay.web.service.ResultNotifyServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

/**
 * 类名: 结果通知类
 * 作者: HappyDan
 * 时间: 2019年4月29日
 * 版本: V1.0
 */
@RestController
public class ResultNotifyController {

    private final MyLog _log = MyLog.getLog(ResultNotifyController.class);

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private ResultNotifyServiceClient resultNotifyServiceClient;

    /**
     * 缴费和退款结果通知
     * @param params
     * @return
     */
    @RequestMapping(value = "/result_notify")
    public String resultNotify(@RequestParam String params) {
        _log.info("###### 开始接收缴费和退款结果通知请求 ######");
        String logPrefix = "【缴费和退款通知】";
        JSONObject object;
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/result_notify, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
            object = JSONObject.parseObject(params);
            // 验证参数有效性
            String errorMessage = validateParams(object);
            if (!"success".equalsIgnoreCase(errorMessage)) {
                _log.warn(errorMessage);
                return EPayUtil.makeRetFail(EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, errorMessage, null, null));
            }
            _log.debug("请求参数校验通过");

            String orderStatus = object.getString("orderStatus");// 订单状态
            String merOrderNo = object.getString("merOrderNo");// 订单号
            String firstOrderNo = merOrderNo.trim().substring(0, 3);
            
            if("01".equals(orderStatus) && "P00".equals(firstOrderNo)) {
            	String result = resultNotifyServiceClient.payResultNotify(params);
            	return result;
            }else if("01".equals(orderStatus) && "R00".equals(firstOrderNo)) {
            	String result = resultNotifyServiceClient.refundResultNotify(params);
            	return result;
            }
            Map<String, Object> map = new HashMap<String, Object>();
        	map.put("txStatus", "FAIL");
        	map.put("txMsg", "未找到该通知的订单号");
        	return EPayUtil.makeRetFail(map);
        	
        }catch (Exception e) {
        	_log.error(e, "");
            return EPayUtil.makeRetFail(EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null));
        }
    }

    /**
     * 验证请求参数,参数通过返回JSONObject对象,否则返回错误文本信息
     * @param params
     * @return
     */
    private String validateParams(JSONObject params) {
        // 验证请求参数,参数有问题返回错误提示
        String errorMessage;
        
        // 请求参数
        String acceptBizNo = params.getString("acceptBizNo");// 渠道号
        String merCustNo = params.getString("merCustNo");// 商户号
        String orderStatus = params.getString("orderStatus");// 订单状态
        String merOrderNo = params.getString("merOrderNo");// 订单号
        String txAmt = params.getString("txAmt");// 交易金额
        String payTime = params.getString("payTime");// 支付时间

        // 验证请求参数有效性（必选项）
        if(StringUtils.isBlank(acceptBizNo)) {
            errorMessage = "notify params[acceptBizNo] is null.";
            return errorMessage;
        }
        if(StringUtils.isBlank(merCustNo)) {
            errorMessage = "notify params[merCustNo] is null.";
            return errorMessage;
        }
        if(StringUtils.isBlank(orderStatus)) {
            errorMessage = "notify params[orderStatus] is null.";
            return errorMessage;
        }
        if(StringUtils.isBlank(merOrderNo)) {
            errorMessage = "notify params[merOrderNo] is null.";
            return errorMessage;
        }
        if(StringUtils.isBlank(txAmt)) {
            errorMessage = "notify params[txAmt] is null.";
            return errorMessage;
        }
        if(StringUtils.isBlank(payTime)) {
            errorMessage = "notify params[payTime] is null.";
            return errorMessage;
        }

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
