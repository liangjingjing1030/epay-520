package org.epay.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.epay.common.constant.PayConstant;
import org.epay.common.util.EPayUtil;
import org.epay.common.util.MyLog;
import org.epay.web.service.PayOrderServiceClient;
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
 * 类名: 客户缴费明细查询
 * 作者: HappyDan
 * 时间: 2019年4月20日
 * 版本: V1.0
 */
@RestController
public class QueryPayOrderDetailController {

    private final MyLog _log = MyLog.getLog(QueryPayOrderDetailController.class);

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private PayOrderServiceClient payOrderServiceClient;

    /**
     * 客户缴费明细查询
     * 1)先验证接口参数以及签名信息
     * 2)根据参数查询订单
     * 3)返回订单数据
     * @param params
     * @return
     */
    @RequestMapping(value = "/user_query_order_detail")
    public String queryPayOrderDetail(@RequestParam String params) {
        _log.info("###### 开始接收客户缴费明细查询请求 ######");
        String logPrefix = "【客户缴费明细查询】";
        JSONObject object;
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/user_query_order_detail, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
        	object = JSONObject.parseObject(params);
            JSONObject payContext = new JSONObject();
            // 验证参数有效性
            String errorMessage = validateParams(object, payContext);
            if (!"success".equalsIgnoreCase(errorMessage)) {
                _log.warn(errorMessage);
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, errorMessage, null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            _log.debug("请求参数校验通过");
            String retStr = payOrderServiceClient.queryPayOrderDetail(payContext.toJSONString());
            
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}客户缴费明细查询,结果:{}", logPrefix, retObj);
            if(!"0000".equals(retObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            
            //获取查询到的对象内容
            JSONArray payOrderDetailMap = retObj.getJSONArray("result");
            String total_num = retObj.getString("total_num");
            if (payOrderDetailMap == null) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付订单不存在", null, null);
            	response.put("response_header", retHeader);
            	return EPayUtil.makeRetFail(response);
            }
            
            //组装返回报文response
            retBody.put("total_num", total_num);
            retBody.put("result", payOrderDetailMap);
            response.put("response_body", retBody);
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "缴费明细查询成功，共------" + total_num + "条记录！", PayConstant.RETURN_VALUE_SUCCESS, null);
            response.put("response_header", retHeader);
            _log.info("缴费明细查询成功,payOrder={}", response);
            _log.info("###### 缴费明细查询处理完成 ######");
            
            return EPayUtil.makeRetData(response);
            
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
        // 支付参数
        JSONObject request_header = params.getJSONObject("request_header");
        String request_channel =  request_header.getString("request_channel");// 请求渠道
        
        JSONObject request_body = params.getJSONObject("request_body");
        String user_id = request_body.getString("user_id");					// 客户唯一标识
        String user_name = request_body.getString("user_name");				// 用户名称
        String pay_order_id = request_body.getString("pay_order_id");		// 支付订单号
        String status = request_body.getString("status");					// 支付状态
        String start_time = request_body.getString("start_time");			// 开始时间
        String end_time = request_body.getString("end_time");				// 结束时间
        String limit = request_body.getString("limit");						// 页码
        String offset = request_body.getString("offset");					// 每页数量

        // 验证请求参数有效性（必选项）
        if(StringUtils.isBlank(request_channel)) {
            errorMessage = "请求参数[request_channel] 为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(user_id)) {
            errorMessage = "请求参数[user_id] 为空.";
            return errorMessage;
        }
        
        //组装查询json对象
        payContext.put("user_id", user_id);
        payContext.put("user_name", user_name);
        payContext.put("pay_order_id", pay_order_id);
        payContext.put("status", status);
        payContext.put("start_time", start_time);
        payContext.put("end_time", end_time);
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
