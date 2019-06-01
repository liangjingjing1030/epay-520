package org.epay.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.epay.common.constant.PayConstant;
import org.epay.common.util.EPayUtil;
import org.epay.common.util.MyLog;
import org.epay.web.service.AccountFilePageServiceClient;
import org.epay.web.service.MchChannelServiceClient;
import org.epay.web.service.PayOrderServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类名: 退款模块
 * 作者: GaoLiang
 * 时间: 2019年5月8日11:04:12
 * 版本: V1.0
 */
@RestController
public class RefundController {

    private final MyLog _log = MyLog.getLog(RefundController.class);

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private PayOrderServiceClient payOrderServiceClient;

    /**
     * 订单分页查询
     */
    @RequestMapping(value = "/refund_query_order")
    public String queryPayOrder(@RequestParam String params) {
        _log.info("###### 开始接收查询商户订单信息请求 ######");
        JSONObject object;
        String logPrefix = "【商户订单信息查询】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        // 获取本地服务实例(获取service工程)
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/refund_query_order, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
            // 将请求参数转换为JSONObject对象
            object = JSONObject.parseObject(params);
            // 获取请求头
            JSONObject channelObj = object.getJSONObject("request_header");
            // 从请求头中获取渠道信息
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject payContext = new JSONObject();
            // 验证参数有效性
            /*String errorMessage = validateParams(object, payContext);//携带请求参数+响应私钥
            if (!"success".equalsIgnoreCase(errorMessage)) {
                _log.warn(errorMessage);
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, errorMessage, null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            _log.debug("请求参数及签名校验通过");*/

            // 获取请求体
            JSONObject requestBody = object.getJSONObject("request_body");
            String mch_id = requestBody.getString("mch_id");                // 商户ID
            String pay_order_id = requestBody.getString("pay_order_id");    // 订单号
            String mch_order_no = requestBody.getString("mch_order_no");    // 账单号
            String channel_id = requestBody.getString("channel_id");
            String user_id = requestBody.getString("user_id");
            String user_name = requestBody.getString("user_name");
            String user_channel_account = requestBody.getString("user_channel_account");
            String status = requestBody.getString("status");
            String startIndex = requestBody.getString("startIndex");
            String pageSize = requestBody.getString("pageSize");

            payContext.put("mch_id", mch_id);
            payContext.put("pay_order_id", pay_order_id);
            payContext.put("mch_order_no", mch_order_no);
            payContext.put("channel_id", channel_id);
            payContext.put("user_id", user_id);
            payContext.put("user_name", user_name);
            payContext.put("user_channel_account", user_channel_account);
            payContext.put("status", status);
            payContext.put("startIndex", startIndex);
            payContext.put("pageSize", pageSize);
            // 查询商户订单信息list
//            String retStr = mchInfoServiceClient.mchQueryOrders(payContext.toJSONString());// 多个参数用这个
            String retStr = payOrderServiceClient.payOrderPageByConfition(payContext.toJSONString());
            // 将商户订单信息转换为JSONObject对象
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}查询订单信息,结果:{}", logPrefix, retObj);

            // 0002:未查询到符合要求的订单
            if(!"0000".equals(retObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (retObj == null) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "订单不存在", null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }

            List<Map<String, Object>> list = new ArrayList<>();
            //获取查询到的订单总条数
            int total = retObj.getInteger("total");
            // 本次分页查询的总条数
            int count = retObj.getInteger("count");
            for(int i = 1; i <= count; i++) {
                // 结果对象
                JSONObject payOrder = retObj.getJSONObject("result" + i);
                Map<String, Object> map = new HashMap<String, Object>();

                map.put("pay_order_id", payOrder.getString("pay_order_id") == null ? "" : payOrder.getString("pay_order_id"));
                map.put("mch_id", payOrder.getString("mch_id") == null ? "" : payOrder.getString("mch_id"));
                map.put("mch_order_no",	payOrder.getString("mch_order_no") == null ? "" : payOrder.getString("mch_order_no"));
                map.put("channel_id",	payOrder.getString("channel_id") == null ? "" : payOrder.getString("channel_id"));
                map.put("user_id",	payOrder.getString("user_id") == null ? "" : payOrder.getString("user_id"));
                map.put("user_name",	payOrder.getString("user_name") == null ? "" : payOrder.getString("user_name"));
                map.put("user_channel_account", payOrder.getString("user_channel_account") == null ? "" : payOrder.getString("user_channel_account"));
                map.put("amount", payOrder.getLong("amount") == null ? "" : payOrder.getLong("amount"));
                map.put("currency", payOrder.getString("currency") == null ? "" : payOrder.getString("currency"));
                map.put("status", payOrder.getByte("status") == null ? "" : payOrder.getByte("status"));
                map.put("client_ip", payOrder.getString("client_ip") == null ? "" : payOrder.getString("client_ip"));
                map.put("device", payOrder.getString("device") == null ? "" : payOrder.getString("device"));
                map.put("subject", payOrder.getString("subject") == null ? "" : payOrder.getString("subject"));
                map.put("body", payOrder.getString("body") == null ? "" : payOrder.getString("body"));
                map.put("extra", payOrder.getString("extra") == null ? "" : payOrder.getString("extra"));
                map.put("channel_mch_id", payOrder.getString("channel_mch_id") == null ? "" : payOrder.getString("channel_mch_id"));
                map.put("channel_order_no", payOrder.getString("channel_order_no") == null ? "" : payOrder.getString("channel_order_no"));
                map.put("res_code", payOrder.getString("res_code") == null ? "" : payOrder.getString("res_code"));
                map.put("res_msg", payOrder.getString("res_msg") == null ? "" : payOrder.getString("res_msg"));
                map.put("notify_url", payOrder.getString("notify_url") == null ? "" : payOrder.getString("notify_url"));
                map.put("notify_count", payOrder.getString("notify_count") == null ? "" : payOrder.getString("notify_count"));
                map.put("last_notify_time", payOrder.getString("last_notify_time") == null ? "" : payOrder.getString("last_notify_time"));
                map.put("expire_time", payOrder.getString("expire_time") == null ? "" : payOrder.getString("expire_time"));
                map.put("pay_succ_time", payOrder.getString("pay_succ_time") == null ? "" : payOrder.getString("pay_succ_time"));
                map.put("create_time", payOrder.getString("create_time") == null ? "" : payOrder.getString("create_time"));
                map.put("update_time", payOrder.getString("update_time") == null ? "" : payOrder.getString("update_time"));
                list.add(map);
            }

            //组装返回报文response
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "订单查询成功", PayConstant.RETURN_VALUE_SUCCESS, null);
            retBody.put("payOrderList", list);
            retBody.put("total", total);

            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            _log.info("订单查询成功,channel={}", response);
            _log.info("###### 订单查询处理完成 ######");
            // 管理平台账单
            /*if("MGR".equals(request_channel)) {
            	return EPayUtil.makeRetData(response, payContext.getString("res_key"));
            }else {
            	return EPayUtil.makeRetData(response);
            }*/
            return EPayUtil.makeRetData(response);//JSON.toJSONString

        }catch (Exception e) {
            _log.error(e, "");
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null);
            response.put("response_header", retHeader);
            return EPayUtil.makeRetFail(response);
        }
    }

}
