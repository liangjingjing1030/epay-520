package org.epay.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.epay.common.constant.PayConstant;
import org.epay.common.util.EPayUtil;
import org.epay.common.util.MD5AndCreateKeyUtil;
import org.epay.common.util.MyLog;
import org.epay.web.service.BankBranchServiceClient;
import org.epay.web.service.MchInfoServiceClient;
import org.epay.web.service.MchRegisterServiceClient;
import org.epay.web.service.PtUserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 类名: 商户注册
 * 作者: HappyDan
 * 时间: 2019年4月30日
 * 版本: V1.0
 */
@RestController
public class MchRegisterController {

    private final MyLog _log = MyLog.getLog(MchRegisterController.class);

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private MchInfoServiceClient mchInfoServiceClient;
    
    @Autowired
    private PtUserServiceClient ptUserServiceClient;

    @Autowired
    private BankBranchServiceClient bankBranchServiceClient;
    
    @Autowired
    private MchRegisterServiceClient mchRegisterServiceClient;

    /**
     * 商户注册
     * 1)先验证接口参数以及签名信息
     * 2)根据参数查询是否有该商户号
     * 3)返回订单数据
     * @param params
     * @return
     */
    @RequestMapping(value = "/mch_register")
    public String mchRegister(@RequestParam String params) {
        _log.info("###### 开始接收商户注册请求 ######");
        String logPrefix = "【商户注册】";
        JSONObject object;
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/mch_register, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
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

            // 1、查询拓展网点号是否存在
            String retBankBranch = bankBranchServiceClient.queryBankBranch(payContext.toJSONString());
            JSONObject bankBranchObj = JSON.parseObject(retBankBranch);
            _log.info("{}查询机构信息,结果:{}", logPrefix, bankBranchObj);
            if(!"0000".equals(bankBranchObj.getString("code"))) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, bankBranchObj.getString("msg"), null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }

            // 2、插入信息到User表中
            String retPtUser = ptUserServiceClient.addIntoPtUser(payContext.toJSONString());
            JSONObject userObj = JSON.parseObject(retPtUser);
            _log.info("{}添加商户登陆信息,结果:{}", logPrefix, userObj);
            if(!"0000".equals(userObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, userObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
            	return EPayUtil.makeRetFail(response);
            }
            // 3、插入信息到商户表中
            String retMchRegister = mchRegisterServiceClient.addMchRegister(payContext.toJSONString());
            JSONObject mchObj = JSON.parseObject(retMchRegister);
            _log.info("{}添加商户注册信息,结果:{}", logPrefix, mchObj);
            if(!"0000".equals(mchObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, mchObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
            	return EPayUtil.makeRetFail(response);
            }
            
            //获取查询到的对象内容
            JSONObject mchMap = mchObj.getJSONObject("result");
            if (mchMap == null) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "添加商户注册信息失败", null, null);
            	response.put("response_header", retHeader);
            	return EPayUtil.makeRetFail(response);
            }
            String req_key = mchMap.getString("req_key");
            String res_key = mchMap.getString("res_key");
            
            //组装返回报文response
            retBody.put("req_key", req_key);
            retBody.put("res_key", res_key);
            response.put("response_body", retBody);
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "账户正在审核中，请注意查收审核结果！", PayConstant.RETURN_VALUE_SUCCESS, null);
            response.put("response_header", retHeader);
            
            _log.info("添加商户注册信息成功,accountBook={}", response);
            _log.info("###### 添加商户注册信息处理完成 ######");
            
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
		String pswd = request_body.getString("pswd");							// 登陆密码
		String mch_name = request_body.getString("mch_name");					// 商户名称
		String mch_type = request_body.getString("mch_type");					// 商户类型
		String mch_range = request_body.getString("mch_range");					// 经营范围
		String mch_city = request_body.getString("mch_city");					// 商户所在城市
		String mch_address = request_body.getString("mch_address");				// 商户地址
		String contact_person = request_body.getString("contact_person");		// 联系人姓名
		String contact_phone = request_body.getString("contact_phone");			// 联系电话
		String mch_bank_account = request_body.getString("mch_bank_account");	// 商户结算银行账号
		String mch_bank_name = request_body.getString("mch_bank_name");			// 商户结算账户户名
		String account_bank = request_body.getString("account_bank");			// 开户行
		String account_type = request_body.getString("account_type");			// 账户类型，0-对私，1-对公
		String mch_stl_day = request_body.getString("mch_stl_day");				// 商户交易结算日(交易结算频率)T+N
        // 拓展网点号改为必输项 by GaoLiang @2019年5月23日15:18:07
		String branch_id = request_body.getString("branch_id");			// 拓展网点号
        // 添加登录账号2019年6月1日20:38:30
		String account = request_body.getString("account");			// 登录账号


        // 验证请求参数有效性（必选项）
        if(StringUtils.isBlank(request_channel)) {
            errorMessage = "请求参数[request_channel] 为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(mch_id)) {
            errorMessage = "请求参数[mch_id] 为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(pswd)) {
            errorMessage = "请求参数[pswd] 为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(mch_name)) {
            errorMessage = "请求参数[mch_name] 为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(mch_type)) {
            errorMessage = "请求参数[mch_type] 为空.";
            return errorMessage;
        }
        if("个体商户".equals(mch_type)) {
        	mch_type = "1";
        }else if("企业".equals(mch_type)) {
        	mch_type = "2";
        }else if("小微商户".equals(mch_type)) {
        	mch_type = "3";
        }
        if(StringUtils.isBlank(mch_range)) {
            errorMessage = "请求参数[mch_range] 为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(mch_city)) {
            errorMessage = "请求参数[mch_city] 为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(mch_address)) {
            errorMessage = "请求参数[mch_address] 为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(contact_person)) {
            errorMessage = "请求参数[contact_person] 为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(contact_phone)) {
            errorMessage = "请求参数[contact_phone] 为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(mch_bank_account)) {
            errorMessage = "请求参数[mch_bank_account] 为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(mch_bank_name)) {
            errorMessage = "请求参数[mch_bank_name] 为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(account_bank)) {
            errorMessage = "请求参数[account_bank] 为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(account_type)) {
            errorMessage = "请求参数[account_type] 为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(mch_stl_day)) {
            errorMessage = "请求参数[mch_stl_day] 为空.";
            return errorMessage;
        }
        if(StringUtils.isBlank(branch_id)) {
            errorMessage = "请求参数[branch_id] 为空.";
            return errorMessage;
        }

        if(StringUtils.isBlank(account)) {
            errorMessage = "请求参数[account] 为空.";
            return errorMessage;
        }

        // 查询商户信息
        JSONObject ptUser;
        String retUser = ptUserServiceClient.selectPtUserByLoginName(getJsonParam("login_name", mch_id));
        JSONObject userObj = JSON.parseObject(retUser);
        if("0000".equals(userObj.getString("code"))) {
        	ptUser = userObj.getJSONObject("result");
            if (ptUser != null) {
                errorMessage = "用户名已存在";
                return errorMessage;
            }
        }
        
        JSONObject mchInfo;
        String retMch = mchInfoServiceClient.selectMchInfoByMchId(getJsonParam("mch_id", mch_id));
        JSONObject mchObj = JSON.parseObject(retMch);
        if("0000".equals(mchObj.getString("code"))) {
            mchInfo = mchObj.getJSONObject("result");
            if (mchInfo != null) {
                errorMessage = "商户信息已存在";
                return errorMessage;
            }
        }
        
        String md5Pswd = MD5AndCreateKeyUtil.md5(pswd.trim(), "UTF-8");
        System.out.println(md5Pswd);
        payContext.put("mch_id", mch_id);
        payContext.put("account", account);//2019-6-1 21:16:07添加
        payContext.put("pswd", md5Pswd);
        payContext.put("mch_name", mch_name);
        payContext.put("mch_type", mch_type);
        payContext.put("mch_range", mch_range);
        payContext.put("mch_city", mch_city);
        payContext.put("mch_address", mch_address);
        payContext.put("contact_person", contact_person);
        payContext.put("contact_phone", contact_phone);
        payContext.put("contact_email", request_body.getString("contact_email"));
        payContext.put("certificate_type", request_body.getString("certificate_type"));
        payContext.put("certificate_number", request_body.getString("certificate_number"));
        payContext.put("business_license", request_body.getString("business_license"));
        payContext.put("branch_id", request_body.getString("branch_id"));
        payContext.put("branch_name", request_body.getString("branch_name"));
        payContext.put("staff_id", request_body.getString("staff_id"));
        payContext.put("staff_name", request_body.getString("staff_name"));
        payContext.put("mch_bank_account", mch_bank_account);
        payContext.put("mch_bank_name", mch_bank_name);
        payContext.put("account_bank", account_bank);
        payContext.put("account_type", account_type);
        payContext.put("mch_stl_day", mch_stl_day);
        
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
