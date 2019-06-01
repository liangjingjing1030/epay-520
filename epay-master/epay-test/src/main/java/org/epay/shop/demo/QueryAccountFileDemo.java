package org.epay.shop.demo;

import org.epay.common.util.EPayUtil;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

/**
 * 类名: 商户查询账单文件
 * 作者: HappyDan
 * 时间: 2019年4月26日
 * 版本: V1.0
 */
@RestController
public class QueryAccountFileDemo {

	static final String baseUrl = "http://127.0.0.1:3020/epay";

    public static void main(String[] args) {
    	queryAccountFile();
    }

    static void queryAccountFile() {
        JSONObject reqParam = new JSONObject();
        JSONObject reqHeader = new JSONObject();
        JSONObject reqBody = new JSONObject();

        reqHeader.put("request_channel", "WX_JSAPI");	//发起渠道
        reqBody.put("mch_id", "happydan01");			//商户ID
        reqParam.put("request_header", reqHeader);
        reqParam.put("request_body", reqBody);
        
        String reqData = "params=" + reqParam.toJSONString();
        System.out.println("======请求数据======:" + reqData);
        String url = baseUrl + "/mch_query_account_files?";
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("======响应数据======:" + result);
    }

}
