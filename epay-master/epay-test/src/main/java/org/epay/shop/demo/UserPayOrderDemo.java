package org.epay.shop.demo;

import org.epay.common.util.EPayUtil;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

/**
 * 类名: 客户账单缴费
 * 作者: HappyDan
 * 时间: 2019年4月21日
 * 版本: V1.0
 */
@RestController
public class UserPayOrderDemo {

	static final String baseUrl = "http://127.0.0.1:3020/epay";

    public static void main(String[] args) {
    	userPayOrder();
    }

    static void userPayOrder() {
        JSONObject reqParam = new JSONObject();
        JSONObject reqHeader = new JSONObject();
        JSONObject reqBody = new JSONObject();
        reqHeader.put("request_channel", "WX_JSAPI");				// 发起渠道
        reqBody.put("mch_id", "happydan02");						// 商户ID
		reqBody.put("mch_order_no", "ZD20190519120000000800000");	// 商户账单号
		reqBody.put("user_id", "happydan02");						// 客户唯一标识
		reqBody.put("user_name", "快乐的蛋02");							// 用户名称
		reqBody.put("user_channel_account", "13934521567");			//  客户渠道账户
		reqBody.put("amount", "10000");								// 支付金额
		reqBody.put("currency", "cny");								// 币种
		reqParam.put("request_header", reqHeader);
        reqParam.put("request_body", reqBody);
        
        String reqData = "params=" + reqParam.toJSONString();
        System.out.println("======请求数据======:" + reqData);
        String url = baseUrl + "/user_pay_order?";
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("======响应数据======:" + result);
    }

}
