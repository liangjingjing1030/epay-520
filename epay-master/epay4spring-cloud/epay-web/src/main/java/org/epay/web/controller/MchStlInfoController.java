package org.epay.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.epay.common.constant.PayConstant;
import org.epay.common.util.EPayUtil;
import org.epay.common.util.MyLog;
import org.epay.web.service.MchInfoServiceClient;
import org.epay.web.service.MchStlInfoServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名: 商户结算信息查询
 * 作者: GaoLiang
 * 时间: 2019年4月28日
 * 版本: V1.0
 */
@RestController
public class MchStlInfoController {

    private final MyLog _log = MyLog.getLog(MchStlInfoController.class);

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private MchStlInfoServiceClient mchStlInfoServiceClient;

    /**
     * 商户信息查询
     * 1)先验证接口参数以及签名信息
     * 2)根据参数查询商户结算信息
     * 3)返回商户数据
     */
    @RequestMapping(value = "/mch_query_mchStlInfo")
    public String mchQueryOrders(@RequestParam String params) {
        _log.info("###### 开始接收商户结算信息请求 ######");
        JSONObject object;
        String logPrefix = "【商户结算信息查询】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        // 获取本地服务实例(获取service工程)
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/mch_query_mchStlInfo, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
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
            String mch_id = requestBody.getString("mch_id");// 商户ID
            System.out.println("web中controller的mch_id：" + mch_id);

            // 查询商户结算信息
//            String retStr = mchInfoServiceClient.mchQueryOrders(payContext.toJSONString());// 多个参数用这个
            String retStr = mchStlInfoServiceClient.selectMchStlInfoByMchId(getJsonParam("mch_id", mch_id));
            // 将商户结算信息转换为JSONObject对象
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}查询商户结算信息,结果:{}", logPrefix, retObj);

            // 0000表示查询成功，且查询对象不为null
            if(!"0000".equals(retObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (retObj == null) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "商户结算信息不存在", null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            // TODO
            Map<String, Object> map = new HashMap<String, Object>();
            //获取查询到的对象内容
            JSONObject mchStlInfo = retObj.getJSONObject("result");

            map.put("mch_id", mchStlInfo.getString("mch_id") == null ? "" : mchStlInfo.getString("mch_id"));
            map.put("mch_bank_account",	mchStlInfo.getString("mch_bank_account") == null ? "" : mchStlInfo.getString("mch_bank_account"));
            map.put("mch_bank_name",	mchStlInfo.getString("mch_bank_name") == null ? "" : mchStlInfo.getString("mch_bank_name"));
            map.put("account_bank",	mchStlInfo.getString("account_bank") == null ? "" : mchStlInfo.getString("account_bank"));
            map.put("account_type",	mchStlInfo.getString("account_type") == null ? "" : mchStlInfo.getString("account_type"));
            map.put("mch_stl_day", mchStlInfo.getString("mch_stl_day") == null ? "" : mchStlInfo.getString("mch_stl_day"));
            map.put("create_time", mchStlInfo.getString("create_time") == null ? "" : mchStlInfo.getString("create_time"));
            map.put("update_time", mchStlInfo.getString("update_time") == null ? "" : mchStlInfo.getString("update_time"));

            //组装返回报文response
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "商户结算信息查询成功", PayConstant.RETURN_VALUE_SUCCESS, null);
            retBody.put("result", map);

            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            _log.info("商户结算信息查询成功,mchStlInfo={}", response);
            _log.info("###### 商户结算信息查询处理完成 ######");
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
     * 验证商户信息查询请求参数,参数通过返回JSONObject对象,否则返回错误文本信息
     * @param params
     * @param payContext 空白JSONObject对象
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
        // 如果是管理平台需要验证签名
        if("MGR".equals(request_channel)) {
        	// 签名信息
            if (StringUtils.isBlank(sign)) {
                errorMessage = "请求参数[sign] 为空.";
                return errorMessage;
            }
        }
        // 查询商户信息
        JSONObject mchInfo;
        String retStr = mchStlInfoServiceClient.selectMchStlInfoByMchId(getJsonParam("mch_id", mch_id));

        JSONObject retObj = JSON.parseObject(retStr);
        if("0000".equals(retObj.getString("code"))) {
            mchInfo = retObj.getJSONObject("result");
            if (mchInfo == null) {
                errorMessage = "未查询到[mch_id="+mch_id+"] 的商户信息 in db.";
                return errorMessage;
            }
            // 商户状态,0-未激活,1-使用中,2-停止使用
            if(mchInfo.getByte("mch_status") != 1) {
                errorMessage = "商户号为 [mch_id="+mch_id+"] 的商户信息未启用 in db.";
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
            // 判断 (MD5加密签名后的请求参数+签名) 与 (原请求中签名) 是否相等
            boolean verifyFlag = EPayUtil.verifyPaySign(params, req_key);
            if(!verifyFlag) {
                errorMessage = "验证签名信息失败.";
                return errorMessage;
            }
            
            //组织请求参数对象
            payContext.put("res_key", mchInfo.getString("res_key"));//商户响应私钥
            payContext.put("mch_id", mch_id);
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

    /**
     * 将String封装到JSONObject中
     * @param name
     * @param value
     * @return
     */
    String getJsonParam(String name, Object value) {
        JSONObject jsonParam = new JSONObject();
        jsonParam.put(name, value);
        return jsonParam.toJSONString();
    }

}
