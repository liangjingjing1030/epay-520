package org.epay.shop.demo;

import org.epay.common.util.EPayUtil;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

/**
 * 类名: 商户当日流水查询
 * 作者: HappyDan
 * 时间: 2019年4月18日
 * 版本: V1.0
 */
@RestController
public class QueryAllOrderDemo {

	static final String baseUrl = "http://127.0.0.1:3020/epay";

    public static void main(String[] args) {
    	queryAllOrder();
    }

    static void queryAllOrder() {
        JSONObject reqParam = new JSONObject();
        JSONObject reqHeader = new JSONObject();
        JSONObject reqBody = new JSONObject();

        reqHeader.put("request_channel", "WX_JSAPI");	//发起渠道
        reqBody.put("mch_id", "happydan02");	//商户ID
        reqBody.put("start_time", "20190501000000");	//开始时间
        reqBody.put("end_time", "20190509000000");	//结束时间
        reqBody.put("mch_order_no", "");	//账单号
        reqBody.put("pay_order_id", "");	//支付订单号
        reqBody.put("user_id", "");	//客户唯一标识
        reqBody.put("user_name", "");	//用户名称
        reqBody.put("status", "");	//支付状态
        reqBody.put("limit", "1");	//页码
        reqBody.put("offset", "5");	//每页数量
        reqParam.put("request_header", reqHeader);
        reqParam.put("request_body", reqBody);
        
        String reqData = "params=" + reqParam.toJSONString();
        System.out.println("======请求数据======:" + reqData);
        String url = baseUrl + "/mch_query_order?";
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("======响应数据======:" + result);
    }

}
