package org.epay.shop.demo;

import org.epay.common.util.EPayUtil;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

/**
 * 类名: 结果通知类
 * 作者: HappyDan
 * 时间: 2019年4月29日
 * 版本: V1.0
 */
@RestController
public class ResultNotifyDemo {

	static final String baseUrl = "http://127.0.0.1:3020/epay";

    public static void main(String[] args) {
    	resultNotify();
    }

    static void resultNotify() {
        JSONObject reqParam = new JSONObject();
       
        reqParam.put("acceptBizNo", "WX_JSAPI");	//渠道号
        reqParam.put("merCustNo", "happydan02");	//商户号
        reqParam.put("orderStatus", "");	//订单状态
        reqParam.put("merOrderNo", "");	//订单号
        reqParam.put("txAmt", "");	//交易金额
        reqParam.put("payTime", "");	//支付时间
        
        String reqData = "params=" + reqParam.toJSONString();
        System.out.println("======请求数据======:" + reqData);
        String url = baseUrl + "/result_notify?";
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("======响应数据======:" + result);
    }

}
