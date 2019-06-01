package org.epay.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.epay.common.util.MyBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 类名: 订单详情
 * 作者: HappyDan
 * 时间: 2019年4月18日
 * 版本: V1.0
 */
@Service
public class UserPayOrderServiceClient {

    @Autowired
    RestTemplate restTemplate;
    
    /**
     * 创建支付订单
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "createPayOrderFallback")
    public String createPayOrder(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/pay_order/create_pay_order?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }

    public String createPayOrderFallback(String jsonParam) {
        return "error";
    }

    /**
     * 发送支付订单请求到支付平台
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "sendPayRequestFallback")
    public String sendPayRequest(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/pay/channel/gather_pay?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }

    public String sendPayRequestFallback(String jsonParam) {
        return "error";
    }

}