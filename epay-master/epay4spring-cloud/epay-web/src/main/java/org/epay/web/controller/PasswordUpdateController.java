package org.epay.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.epay.common.constant.PayConstant;
import org.epay.common.util.EPayUtil;
import org.epay.common.util.MD5AndCreateKeyUtil;
import org.epay.common.util.MyLog;
import org.epay.web.service.MchInfoServiceClient;
import org.epay.web.service.MchRegisterServiceClient;
import org.epay.web.service.PtUserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名: 找回密码
 * 作者: GaoLiang
 * 时间: 2019年5月14日
 * 版本: V1.0
 */
@RestController
public class PasswordUpdateController {

    private final MyLog _log = MyLog.getLog(PasswordUpdateController.class);

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private MchInfoServiceClient mchInfoServiceClient;
    
    @Autowired
    private PtUserServiceClient ptUserServiceClient;

    /**
     * 找回密码
     * 1)根据请求参数查询商户表
     * 2)如果能查询到商户，则更新密码
     * @param params
     * @return
     */
    @RequestMapping(value = "/mch_updatePassword")
    public String mchRegister(@RequestParam String params) {
        _log.info("###### 开始接收商户修改密码请求 ######");
        String logPrefix = "【修改密码】";
        JSONObject object;
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/mch_updatePassword, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
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
            _log.debug("请求参数校验通过");

            // 更新密码
            String retPtUser = ptUserServiceClient.updatePassword(payContext.toJSONString());
            JSONObject userObj = JSON.parseObject(retPtUser);
            _log.info("{}添加商户登陆信息,结果:{}", logPrefix, userObj);
            if(!"0000".equals(userObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, userObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
            	return EPayUtil.makeRetFail(response);
            }

            String updateCount = userObj.getString("result");

            //组装返回报文response
            retBody.put("updateCount", updateCount);
            response.put("response_body", retBody);
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "修改密码成功", PayConstant.RETURN_VALUE_SUCCESS, null);
            response.put("response_header", retHeader);
            
            _log.info("修改密码成功,accountBook={}", response);
            _log.info("###### 修改密码处理完成 ######");
            
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
		String mch_id = request_body.getString("mch_id");						// 商户号
		String mch_name = request_body.getString("mch_name");					// 商户名称
		String mch_range = request_body.getString("mch_range");					// 经营范围
		String mch_type = request_body.getString("mch_type");					// 商户类型
		String mch_city = request_body.getString("mch_city");					// 商户所在城市
		String mch_address = request_body.getString("mch_address");				// 商户地址
		String contact_person = request_body.getString("contact_person");		// 联系人姓名
		String contact_phone = request_body.getString("contact_phone");			// 联系电话
		String newLoginPwd = request_body.getString("newLoginPwd");				// 新密码

        // 验证请求参数有效性（必选项）
        if(StringUtils.isBlank(request_channel)) {
            errorMessage = "request params[request_channel] error.";
            return errorMessage;
        }
        if(StringUtils.isBlank(mch_id)) {
            errorMessage = "request params[mch_id] error.";
            return errorMessage;
        }
        if(StringUtils.isBlank(newLoginPwd)) {
            errorMessage = "request params[pswd] error.";
            return errorMessage;
        }
        if(StringUtils.isBlank(mch_name)) {
            errorMessage = "request params[mch_name] error.";
            return errorMessage;
        }
        if(StringUtils.isBlank(mch_type)) {
            errorMessage = "request params[mch_type] error.";
            return errorMessage;
        }
        if(StringUtils.isBlank(mch_range)) {
            errorMessage = "request params[mch_range] error.";
            return errorMessage;
        }
        if(StringUtils.isBlank(mch_city)) {
            errorMessage = "request params[mch_city] error.";
            return errorMessage;
        }
        if(StringUtils.isBlank(mch_address)) {
            errorMessage = "request params[mch_address] error.";
            return errorMessage;
        }
        if(StringUtils.isBlank(contact_person)) {
            errorMessage = "request params[contact_person] error.";
            return errorMessage;
        }
        if(StringUtils.isBlank(contact_phone)) {
            errorMessage = "request params[contact_phone] error.";
            return errorMessage;
        }

        // 查询商户信息
        JSONObject mchInfo = null;
        String retMch = mchInfoServiceClient.selectMchInfoByMchId(getJsonParam("mch_id", mch_id));
        JSONObject mchObj = JSON.parseObject(retMch);
        if("0000".equals(mchObj.getString("code"))) {
            mchInfo = mchObj.getJSONObject("result");
            if (mchInfo == null) {
                errorMessage = "商户号输入错误";
                return errorMessage;
            }
        }
        String mch_name1 = mchInfo.getString("mch_name");
        String mch_type1 = mchInfo.getString("mch_type");
        String mch_range1 = mchInfo.getString("mch_range");
        String mch_city1 = mchInfo.getString("mch_city");
        String mch_address1 = mchInfo.getString("mch_address");
        String contact_person1 = mchInfo.getString("contact_person");
        String contact_phone1 = mchInfo.getString("contact_phone");
        // 判断用户输入的参数是否正确
        if(!mch_name.equals(mch_name1)) {
            errorMessage = "商户名称输入错误";
            return errorMessage;
        }
        if(!mch_type.equals(mch_type1)) {
            errorMessage = "商户类型输入错误";
            return errorMessage;
        }
        if(!mch_range.equals(mch_range1)) {
            errorMessage = "经营范围输入错误";
            return errorMessage;
        }
        if(!mch_city.equals(mch_city1)) {
            errorMessage = "城市输入错误";
            return errorMessage;
        }
        if(!mch_address.equals(mch_address1)) {
            errorMessage = "商户地址输入错误";
            return errorMessage;
        }
        if(!contact_person.equals(contact_person1)) {
            errorMessage = "联系人输入错误";
            return errorMessage;
        }
        if(!contact_phone.equals(contact_phone1)) {
            errorMessage = "联系人电话输入错误";
            return errorMessage;
        }

        String md5Pswd = MD5AndCreateKeyUtil.md5(newLoginPwd.trim(), "UTF-8");
        System.out.println(md5Pswd);
        payContext.put("mch_id", mch_id);
        payContext.put("newLoginPwd", md5Pswd);

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
