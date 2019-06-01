package org.epay.shop.demo;

import org.epay.common.util.EPayUtil;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

/**
 * 类名: 商户注册
 * 作者: HappyDan
 * 时间: 2019年4月30日
 * 版本: V1.0
 */
@RestController
public class MchRegisterDemo {

	static final String baseUrl = "http://127.0.0.1:3020/epay";

    public static void main(String[] args) {
    	mchRegister();
    }

    static void mchRegister() {
        JSONObject reqParam = new JSONObject();
        JSONObject reqHeader = new JSONObject();
        JSONObject reqBody = new JSONObject();

        reqHeader.put("request_channel", "WX_JSAPI");				//发起渠道
        reqBody.put("mch_id", "happydan03");						//商户号
        reqBody.put("pswd", "happydan03");							//登陆密码
        reqBody.put("mch_name", "快乐的蛋");							//商户名称
        reqBody.put("mch_type", "1");								//商户类型
        reqBody.put("mch_range", "水电暖");							//经营范围
        reqBody.put("mch_city", "日照市");								//商户所在城市
        reqBody.put("mch_address", "日照市中心103");					//商户地址
        reqBody.put("contact_person", "蛋哥");						//联系人姓名
        reqBody.put("contact_phone", "13934521567");				//联系电话
        reqBody.put("contact_email", "664022644@qq.com");			//商户邮箱
        reqBody.put("certificate_type", "0");						//联系人证件类型
        reqBody.put("certificate_number", "141***199*0*0*****");	//联系人证件号
        reqBody.put("business_license", "鲁L1234568");				//营业执照号
        reqBody.put("branch_id", "");								//拓展网点号
        reqBody.put("branch_name", "");								//拓展网点名称
        reqBody.put("staff_id", "");								//拓展员工号
        reqBody.put("staff_name", "");								//拓展员工名称
        reqBody.put("mch_bank_account", "6230771080004173973");		//商户结算银行账号
        reqBody.put("mch_bank_name", "蛋哥");							//商户结算账户户名
        reqBody.put("account_bank", "日照银行");						//开户行
        reqBody.put("account_type", "1");							//账户类型，0-对私，1-对公
        reqBody.put("mch_stl_day", "T" + "+" + "1");							//商户交易结算日(交易结算频率)T+N
        reqParam.put("request_header", reqHeader);
        reqParam.put("request_body", reqBody);
        
        String reqData = "params=" + reqParam.toJSONString();
        System.out.println("======请求数据======:" + reqData);
        String url = baseUrl + "/mch_register?";
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("======响应数据======:" + result);
    }

}
