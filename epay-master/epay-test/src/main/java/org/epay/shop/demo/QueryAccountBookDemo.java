package org.epay.shop.demo;

import org.epay.common.util.EPayUtil;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

/**
 * 类名: 客户账单查询
 * 作者: HappyDan
 * 时间: 2019年4月20日
 * 版本: V1.0
 */
@RestController
public class QueryAccountBookDemo {

	static final String baseUrl = "http://127.0.0.1:3020/epay";

    public static void main(String[] args) {
    	queryAccountBook();
    }

    static void queryAccountBook() {
        JSONObject reqParam = new JSONObject();
        JSONObject reqHeader = new JSONObject();
        JSONObject reqBody = new JSONObject();

        reqHeader.put("request_channel", "WX_JSAPI");	//发起渠道
        reqBody.put("mch_id", "happydan02");			//商户号
        reqBody.put("user_id", "happydan02");			//客户唯一标识
        reqBody.put("items_id", "aaa");				//项目编号
        reqBody.put("limit", "1");						//页码
        reqBody.put("offset", "5");						//每页数量
        reqParam.put("request_header", reqHeader);
        reqParam.put("request_body", reqBody);
        
        String reqData = "params=" + reqParam.toJSONString();
        System.out.println("======请求数据======:" + reqData);
        String url = baseUrl + "/user_query_account_book?";
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("======响应数据======:" + result);
    }

}
