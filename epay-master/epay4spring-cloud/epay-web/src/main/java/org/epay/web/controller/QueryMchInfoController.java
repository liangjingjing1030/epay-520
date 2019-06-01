package org.epay.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.epay.common.constant.PayConstant;
import org.epay.common.util.EPayUtil;
import org.epay.common.util.MyLog;
import org.epay.web.service.MchInfoServiceClient;
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
 * 类名: 根据地区经营范围查询商户信息
 * 作者: HappyDan
 * 时间: 2019年5月21日
 * 版本: V1.0
 */
@RestController
public class QueryMchInfoController {

    private final MyLog _log = MyLog.getLog(QueryMchInfoController.class);

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private MchInfoServiceClient mchInfoServiceClient;

    /**
     * 根据地区经营范围查询商户信息
     * 1)先验证接口参数以及签名信息
     * 2)根据参数查询商户信息
     * 3)返回商户信息数据
     * @param params
     * @return
     */
    @RequestMapping(value = "/user_query_mch_info")
    public String queryAccountBook(@RequestParam String params) {
        _log.info("###### 开始接收根据地区经营范围查询商户信息请求 ######");
        String logPrefix = "【根据地区经营范围查询商户信息】";
        JSONObject object;
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/user_query_mch_info, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
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

            String retStr = mchInfoServiceClient.userSelectMchInfo(payContext.toJSONString());
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}查询商户信息,结果:{}", logPrefix, retObj);
            
            if(!"0000".equals(retObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
            	return EPayUtil.makeRetFail(response);
            }
            
            //获取查询到的对象内容
            JSONArray mchInfoMap = retObj.getJSONArray("result");
            String total_num = retObj.getString("total_num");
            if (mchInfoMap == null) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "商户信息不存在", null, null);
            	response.put("response_header", retHeader);
            	return EPayUtil.makeRetFail(response);
            }
            
            //组装返回报文response
            retBody.put("total_num", total_num);
            retBody.put("result", mchInfoMap);
            response.put("response_body", retBody);
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "商户信息查询成功，共------" + total_num + "条数据！", PayConstant.RETURN_VALUE_SUCCESS, null);
            response.put("response_header", retHeader);
            
            _log.info("商户信息查询成功,accountBook={}", response);
            _log.info("###### 根据地区经营范围查询商户信息处理完成 ######");
            
            return EPayUtil.makeRetData(response);
        }catch (Exception e) {
        	_log.error(e, "");
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null);
            response.put("response_header", retHeader);
            return EPayUtil.makeRetFail(response);
        }
    }

    /**
     * 方法名: 验证请求参数,参数通过返回JSONObject对象,否则返回错误文本信息
     * 作    者: HappyDan
     * 时    间: 2019年4月8日
     * @param params
     * @param payContext
     * @return
     */
    private String validateParams(JSONObject params, JSONObject payContext) {
        // 验证请求参数,参数有问题返回错误提示
        String errorMessage;
        
        // 请求参数
        JSONObject request_header = params.getJSONObject("request_header");
        String request_channel =  request_header.getString("request_channel");// 请求渠道
        
        JSONObject request_body = params.getJSONObject("request_body");
        String mch_type = request_body.getString("mch_type");		//	商户类型
        String mch_range = request_body.getString("mch_range");		//	经营范围
        String mch_city = request_body.getString("mch_city");		//	商户所在城市

        // 验证请求参数有效性（必选项）
        if(StringUtils.isBlank(request_channel)) {
            errorMessage = "请求参数[request_channel] 为空.";
            return errorMessage;
        }
        payContext.put("mch_type", mch_type);
        payContext.put("mch_range", mch_range);
        payContext.put("mch_city", mch_city);
        
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
