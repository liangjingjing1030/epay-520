package org.epay.shop.demo;

import org.epay.common.util.EPayUtil;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

/**
 * 类名: 客户退款申请
 * 作者: HappyDan
 * 时间: 2019年4月22日
 * 版本: V1.0
 */
@RestController
public class UserRefundOrderDemo {

	static final String baseUrl = "http://127.0.0.1:3020/epay";

    public static void main(String[] args) {
    	userRefundOrder();
    }

    static void userRefundOrder() {
        JSONObject reqParam = new JSONObject();
        JSONObject reqHeader = new JSONObject();
        JSONObject reqBody = new JSONObject();

        reqHeader.put("request_channel", "WX_JSAPI");	// 发起渠道
        reqBody.put("pay_orderid", "");	// 支付订单号
        reqBody.put("mch_id", "");	// 商户ID
        reqBody.put("refund_amount", "");	// 退款金额,单位分
        reqBody.put("user_id", "");	//	客户唯一标识
        reqBody.put("user_name", "");	// 用户名称
        reqBody.put("user_channel_account", "");	// 客户渠道账户
		reqParam.put("request_header", reqHeader);
        reqParam.put("request_body", reqBody);
        
        String reqData = "params=" + reqParam.toJSONString();
        System.out.println("======请求数据======:" + reqData);
        String url = baseUrl + "/mch_refund_order?";
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("======响应数据======:" + result);
    }

}
