package org.epay.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.epay.common.constant.PayConstant;
import org.epay.common.util.EPayUtil;
import org.epay.common.util.MyLog;
import org.epay.web.service.MchCheckOutServiceClient;
import org.epay.web.service.MchInfoServiceClient;
import org.epay.web.service.RefundOrderServiceClient;
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
 * 类名: 结算查询
 * 作者: GaoLiang
 * 时间: 2019年5月15日
 * 版本: V1.0
 */
@RestController
public class QueryMchCheckOutController {

    private final MyLog _log = MyLog.getLog(QueryMchCheckOutController.class);

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private MchCheckOutServiceClient mchCheckOutServiceClient;

    @Autowired
    private MchInfoServiceClient mchInfoServiceClient;

    /**
     * 客户结算查询
     * 1)先验证接口参数以及签名信息
     * 2)根据参数查询结算表
     * 3)返回结算数据
     * @param params
     * @return
     */
    @RequestMapping(value = "/user_query_mch_checkOut1")
    public String queryMchCheckOut(@RequestParam String params) {
        _log.info("###### 开始接收客户结算查询请求 ######");
        String logPrefix = "【客户结算查询】";
        JSONObject object;
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/user_query_mch_checkOut1, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
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

            String retStr = mchCheckOutServiceClient.mchCheckOutQuery(payContext.toJSONString());

            // 将商户订单信息转换为JSONObject对象
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}查询结算信息,结果:{}", logPrefix, retObj);

            // 0002:未查询到符合要求的账单
            if(!"0000".equals(retObj.getString("code"))) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (retObj == null) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "账单不存在", null, null);
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
                JSONObject checkOutDetail = retObj.getJSONObject("result" + i);
                Map<String, Object> map = new HashMap<String, Object>();

                map.put("mchCheckoutId", checkOutDetail.getString("mchCheckoutId") == null ? "" : checkOutDetail.getString("mchCheckoutId"));
                map.put("mchId", checkOutDetail.getString("mchId") == null ? "" : checkOutDetail.getString("mchId"));
                map.put("mchName",	checkOutDetail.getString("mchName") == null ? "" : checkOutDetail.getString("mchName"));
                map.put("orderType",	checkOutDetail.getByte("orderType") == null ? "" : checkOutDetail.getByte("orderType"));
                map.put("currency", checkOutDetail.getString("currency") == null ? "" : checkOutDetail.getString("currency"));
                map.put("dealMoney", checkOutDetail.getLong("dealMoney") == null ? "" : checkOutDetail.getLong("dealMoney"));
                map.put("checkoutMoney", checkOutDetail.getLong("checkoutMoney") == null ? "" : checkOutDetail.getLong("checkoutMoney"));
                map.put("checkoutRate", checkOutDetail.getInteger("checkoutRate") == null ? "" : checkOutDetail.getInteger("checkoutRate"));
                map.put("checkoutDate", checkOutDetail.getString("checkoutDate") == null ? "" : checkOutDetail.getString("checkoutDate"));
                map.put("settleStatus", checkOutDetail.getByte("settleStatus") == null ? "" : checkOutDetail.getByte("settleStatus"));
                map.put("payChannel", checkOutDetail.getString("payChannel") == null ? "" : checkOutDetail.getString("payChannel"));
                map.put("createTime", checkOutDetail.getString("createTime") == null ? "" : checkOutDetail.getString("createTime"));
                map.put("updateTime", checkOutDetail.getString("updateTime") == null ? "" : checkOutDetail.getString("updateTime"));
                list.add(map);
            }

            //组装返回报文response
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "结算查询成功", PayConstant.RETURN_VALUE_SUCCESS, null);
            retBody.put("mchCheckOutList", list);
            retBody.put("total", total);

            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            _log.info("结算查询成功,channel={}", response);
            _log.info("###### 结算查询处理完成 ######");
            // 管理平台账单
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

        // 支付参数
        JSONObject request_header = params.getJSONObject("request_header");
        String request_channel =  request_header.getString("request_channel");// 请求渠道
        
        JSONObject request_body = params.getJSONObject("request_body");
        String mch_id = request_body.getString("mch_id");					// 商户ID
        String pay_channel = request_body.getString("pay_channel");		// 支付渠道
        String start_time = request_body.getString("start_time");			// 开始时间
        String end_time = request_body.getString("end_time");				// 结束时间
        String startIndex = request_body.getString("startIndex");			// 页码
        String pageSize = request_body.getString("pageSize");				// 每页数量

        String sign = params.getString("sign")	;							// 签名

        // 验证请求参数有效性（必选项）
        if(StringUtils.isBlank(request_channel)) {
            errorMessage = "请求参数[request_channel] 为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(mch_id)) {
            errorMessage = "请求参数[mch_id] 为空.";
            return errorMessage;
        }
        if("MGR".equals(request_channel)) {
        	// 签名信息
            if (StringUtils.isBlank(sign)) {
                errorMessage = "请求参数[sign] 为空.";
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
            if(mchInfo.getByte("audit_status") != 1) {
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
        payContext.put("pay_channel", pay_channel);
        payContext.put("start_time", start_time);
        payContext.put("end_time", end_time);
        payContext.put("startIndex", startIndex);
        payContext.put("pageSize", pageSize);

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
