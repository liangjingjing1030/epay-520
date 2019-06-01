package org.epay.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.epay.common.util.MyBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 类名: 退款订单查询
 * 作者: HappyDan
 * 时间: 2019年4月21日
 * 版本: V1.0
 */
@Service
public class RefundOrderServiceClient {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 退款订单查询
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "refundOrderQueryFallback")
    public String refundOrderQuery(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/refund_order/user_query_refund_order?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }

    public String refundOrderQueryFallback(String jsonParam) {
        return "error";
    }

}