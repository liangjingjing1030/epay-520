package org.epay.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.epay.common.constant.PayConstant;
import org.epay.common.util.EPayUtil;
import org.epay.common.util.MyLog;
import org.epay.web.service.AccountBookServiceClient;
import org.epay.web.service.MchCheckOutServiceClient;
import org.epay.web.service.MchInfoServiceClient;
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
 * 类名: 结算汇总数据查询
 * 作者: GaoLiang
 * 时间: 2019年6月5日00:35:43
 * 版本: V1.0
 */
@RestController
public class QueryMoneySummaryController {

    private final MyLog _log = MyLog.getLog(QueryMoneySummaryController.class);

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private MchCheckOutServiceClient mchCheckOutServiceClient;

    @Autowired
    private MchInfoServiceClient mchInfoServiceClient;

    /**
     * 结算汇总数据查询
     * epay_mch_checkout表为汇总表
     * 每个商户每天生成一条数据
     * 通过字段update_time判断是哪天的数据
     * @param params
     * @return
     */
    @RequestMapping(value = "/user_query_money_summary")
    public String queryMchCheckOut(@RequestParam String params) {
        _log.info("###### 开始接收结算汇总数据查询请求 ######");
        String logPrefix = "【结算汇总数据查询】";
        JSONObject object;
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/user_query_money_summary, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
        	object = JSONObject.parseObject(params);
            JSONObject channelObj = object.getJSONObject("request_header");
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject payContext = new JSONObject();
            // 验证参数有效性
//            String errorMessage = validateParams(object, payContext);
//            if (!"success".equalsIgnoreCase(errorMessage)) {
//                _log.warn(errorMessage);
//                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, errorMessage, null, null);
//                response.put("response_header", retHeader);
//                return EPayUtil.makeRetFail(response);
//            }
//            _log.debug("请求参数及签名校验通过");

            // 获取请求体
            JSONObject requestBody = object.getJSONObject("request_body");
            String mch_id = requestBody.getString("mch_id");

            payContext.put("mch_id", mch_id);

            String retStr = mchCheckOutServiceClient.queryMoneySummary(payContext.toJSONString());

            // 将商户订单信息转换为JSONObject对象
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}结算汇总数据查询,结果:{}", logPrefix, retObj);

            // 0002:未查询到符合要求的账单
            if(!"0000".equals(retObj.getString("code"))) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (retObj == null) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "结算汇总数据不存在", null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }

            // 结果对象
            JSONObject mchCheckOut = retObj.getJSONObject("result");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("checkoutId", mchCheckOut.getString("checkoutId") == null ? "" : mchCheckOut.getString("checkoutId"));
            map.put("mchId", mchCheckOut.getString("mchId") == null ? "" : mchCheckOut.getString("mchId"));
            map.put("mchName", mchCheckOut.getString("mchName") == null ? "" : mchCheckOut.getString("mchName"));
            map.put("currency", mchCheckOut.getString("currency") == null ? "" : mchCheckOut.getString("currency"));
            map.put("dealMoney", mchCheckOut.getLong("dealMoney") == null ? "" : mchCheckOut.getLong("dealMoney"));
            map.put("checkoutMoney", mchCheckOut.getLong("checkoutMoney") == null ? "" : mchCheckOut.getLong("checkoutMoney"));
            map.put("checkoutRate", mchCheckOut.getInteger("checkoutRate") == null ? "" : mchCheckOut.getInteger("checkoutRate"));
            map.put("checkoutDate", mchCheckOut.getString("checkoutDate") == null ? "" : mchCheckOut.getString("checkoutDate"));
            map.put("settleStatus", mchCheckOut.getByte("settleStatus") == null ? "" : mchCheckOut.getByte("settleStatus"));
            map.put("createTime", mchCheckOut.getString("createTime") == null ? "" : mchCheckOut.getString("createTime"));
            map.put("updateTime", mchCheckOut.getString("updateTime") == null ? "" : mchCheckOut.getString("updateTime"));

            //组装返回报文response
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "结算汇总数据查询成功", PayConstant.RETURN_VALUE_SUCCESS, null);
            retBody.put("result", map);

            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            _log.info("结算汇总数据查询成功,mchInfo={}", response);
            _log.info("###### 结算汇总数据查询处理完成 ######");
            // 管理平台渠道
            /*if("MGR".equals(request_channel)) {
            	return EPayUtil.makeRetData(response, payContext.getString("res_key"));
            }else {
            	return EPayUtil.makeRetData(response);
            }*/
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
     * 作    者: GaoLiang
     * 时    间: 2019年5月16日
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
        String items_id = request_body.getString("items_id");				// 项目编号
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
        if(StringUtils.isBlank(items_id)) {
            errorMessage = "请求参数[items_id] 为空.";
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
            errorMessage = "未查询到 [mch_id="+mch_id+"] 的商户信息 in db.";
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
        payContext.put("items_id", items_id);
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
