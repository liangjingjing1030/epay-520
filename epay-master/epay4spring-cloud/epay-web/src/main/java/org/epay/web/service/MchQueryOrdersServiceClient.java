package org.epay.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.epay.common.util.MyBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 类名: 商户查询订单流水
 * 作者: HappyDan
 * 时间: 2019年4月18日
 * 版本: V1.0
 */
@Service
public class MchQueryOrdersServiceClient {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 商户查询订单流水
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "mchQueryOrdersFallback")
    public String mchQueryOrders(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/orders/mch_query_orders?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String mchQueryOrdersFallback(String jsonParam) {
        return "mch query orders error";
    }
    
}