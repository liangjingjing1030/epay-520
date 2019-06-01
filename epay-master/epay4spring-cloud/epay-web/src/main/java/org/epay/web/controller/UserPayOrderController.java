package org.epay.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.epay.common.constant.PayConstant;
import org.epay.common.util.EPayUtil;
import org.epay.common.util.MyLog;
import org.epay.web.service.AccountBookServiceClient;
import org.epay.web.service.UserPayOrderServiceClient;
import org.epay.web.utils.PayOrderValidateParamsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 类名: 客户账单缴费
 * 作者: HappyDan
 * 时间: 2019年4月21日
 * 版本: V1.0
 */
@RestController
public class UserPayOrderController {

    private final MyLog _log = MyLog.getLog(UserPayOrderController.class);

    @Autowired
    private DiscoveryClient client;
    
    @Autowired
    private PayOrderValidateParamsUtils validateParamsUtils;
    
    @Autowired
    private UserPayOrderServiceClient payOrderServiceClient;
    
    @Autowired
    private AccountBookServiceClient accountBookServiceClient;

    /**
     * 客户账单缴费下单统一入口
     * 1)先验证接口参数以及签名信息
     * 2)验证通过创建支付订单
     * 3)根据商户选择渠道,调用支付服务进行下单
     * 4)返回下单数据
     * @param params
     * @return
     */
    @RequestMapping(value = "/user_pay_order")
    public String payOrder(@RequestParam String params) {
        _log.info("###### 开始接收客户账单缴费请求 ######");
        String logPrefix = "【客户账单缴费】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/user_pay_order, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
        	JSONObject object = JSONObject.parseObject(params);
            JSONObject channelObj = object.getJSONObject("request_header");
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject payOrder = null;
            
            // 验证参数有效性====================================Start
            Object obj = validateParamsUtils.validateParams(object);
            if (obj instanceof String) {
                _log.info("{}参数校验不通过:{}", logPrefix, object);
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, object.toString(), null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (obj instanceof JSONObject) payOrder = (JSONObject) obj;
            
            if(payOrder == null) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心下单失败", null, null);
            	response.put("response_header", retHeader);
            	return EPayUtil.makeRetFail(response);
            }
            String res_key = payOrder.getString("res_key");
            // 验证参数有效性====================================End

            //查询满足条件的账单====================================Start
            String account_book_id = payOrder.getString("mch_order_no");
            String accountResult = accountBookServiceClient.selectAccountBookByPrimaryKey(getJsonParam("account_book_id", account_book_id));
            JSONObject retAccount = JSON.parseObject(accountResult);
            if(!"0000".equals(retAccount.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retAccount.getString("msg"), null, null);
            	response.put("response_header", retHeader);
            	return EPayUtil.makeRetFail(response);
            }
            _log.info("{}客户退款查询,结果:{}", logPrefix, retAccount);
            JSONObject accountObj = retAccount.getJSONObject("result");
            if (accountObj == null) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "账单信息不存在", null, null);
            	response.put("response_header", retHeader);
            	return EPayUtil.makeRetFail(response);
            }
            if(accountObj.getByte("pay_status") != 0) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "代缴费账单信息不存在", null, null);
            	response.put("response_header", retHeader);
            	return EPayUtil.makeRetFail(response);
            }
            //查询满足条件的账单====================================End
            
            //创建支付订单====================================Start
            String payOrderResult = payOrderServiceClient.createPayOrder(payOrder.toJSONString());
            _log.info("{}创建支付订单,结果:{}", logPrefix, payOrderResult);
            if(StringUtils.isEmpty(payOrderResult)) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "创建支付订单失败", null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            JSONObject resObj = JSON.parseObject(payOrderResult);
            if(resObj == null || !"1".equals(resObj.getString("result"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "创建支付订单失败", null, null);
            	response.put("response_header", retHeader);
            	return EPayUtil.makeRetFail(response);
            }
            //创建支付订单====================================End
            payOrder.put("res_key", res_key);
            return payOrderServiceClient.sendPayRequest(getJsonParam("payOrder", payOrder));
        }catch (Exception e) {
            _log.error(e, "");
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null);
            response.put("response_header", retHeader);
            return EPayUtil.makeRetFail(response);
        }
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
