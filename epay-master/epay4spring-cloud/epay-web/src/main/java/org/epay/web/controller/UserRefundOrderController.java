package org.epay.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.epay.common.constant.PayConstant;
import org.epay.common.util.EPayUtil;
import org.epay.common.util.MyLog;
import org.epay.web.service.PayOrderServiceClient;
import org.epay.web.service.UserRefundOrderServiceClient;
import org.epay.web.utils.RefundOrderValidateParamsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 类名: 客户退款申请
 * 作者: HappyDan
 * 时间: 2019年4月22日
 * 版本: V1.0
 */
@RestController
public class UserRefundOrderController {

    private final MyLog _log = MyLog.getLog(UserRefundOrderController.class);

    @Autowired
    private DiscoveryClient client;
    
    @Autowired
    private RefundOrderValidateParamsUtils validateParamsUtils;
    
    @Autowired
    private PayOrderServiceClient payOrderServiceClient;
    
    @Autowired
    private UserRefundOrderServiceClient refundOrderServiceClient;


    /**
     * 客户退款申请
     * 1)先验证接口参数以及签名信息
     * 3)验证通过校验支付订单信息是否正确
     * 4)校验通过创建退款订单数据
     * 5)根据商户选择渠道,调用支付服务进行下单
     * 6)返回下单数据
     * @param params
     * @return
     */
    @RequestMapping(value = "/user_refund_order")
    public String refundOrder(@RequestParam String params) {
        _log.info("###### 开始接收客户支付订单申请退款请求 ######");
        String logPrefix = "【客户支付订单申请退款】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/user_refund_order, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
        	JSONObject object = JSONObject.parseObject(params);
            JSONObject channelObj = object.getJSONObject("request_header");
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject refundOrder = null;
            
            // 验证参数有效性====================================Start
            Object obj = validateParamsUtils.validateParams(object);
            if (obj instanceof String) {
                _log.info("{}参数校验不通过:{}", logPrefix, object);
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, object.toString(), null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (obj instanceof JSONObject) refundOrder = (JSONObject) obj;
            
            if(refundOrder == null) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心申请退款失败", null, null);
            	response.put("response_header", retHeader);
            	return EPayUtil.makeRetFail(response);
            }
            String res_key = refundOrder.getString("res_key");
            // 验证参数有效性====================================End

            //查询满足条件的支付订单====================================Start
            String pay_order_id = refundOrder.getString("pay_orderid");
            String payOrderResult = payOrderServiceClient.selectPayOrderByPrimaryKey(getJsonParam("pay_order_id", pay_order_id));
            JSONObject retPayOrder = JSON.parseObject(payOrderResult);
            if(!"0000".equals(retPayOrder.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retPayOrder.getString("msg"), null, null);
            	response.put("response_header", retHeader);
            	return EPayUtil.makeRetFail(response);
            }
            _log.info("{}客户退款查询,结果:{}", logPrefix, retPayOrder);
            JSONObject payOrderObj = retPayOrder.getJSONObject("result");
            if (payOrderObj == null) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付订单信息不存在", null, null);
            	response.put("response_header", retHeader);
            	return EPayUtil.makeRetFail(response);
            }
            if(payOrderObj.getByte("status") != 1) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付成功的订单信息不存在", null, null);
            	response.put("response_header", retHeader);
            	return EPayUtil.makeRetFail(response);
            }
            //查询满足条件的支付订单====================================End
            
            //创建退款订单====================================Start
            refundOrder.put("mch_refund_no", payOrderObj.getString("mch_order_no"));
            refundOrder.put("pay_amount", payOrderObj.getString("amount"));
            refundOrder.put("currency", payOrderObj.getString("currency"));
            String refundOrderResult = refundOrderServiceClient.createRefundOrder(refundOrder.toJSONString());
            _log.info("{}创建退款订单,结果:{}", logPrefix, refundOrderResult);
            if(StringUtils.isEmpty(refundOrderResult)) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "创建退款订单失败", null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            JSONObject resObj = JSON.parseObject(refundOrderResult);
            if(resObj == null || !"1".equals(resObj.getString("result"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "创建退款订单失败", null, null);
            	response.put("response_header", retHeader);
            	return EPayUtil.makeRetFail(response);
            }
            //创建退款订单====================================End
            refundOrder.put("res_key", res_key);
            return refundOrderServiceClient.sendRefundRequest(getJsonParam("refundOrder", refundOrder));
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
