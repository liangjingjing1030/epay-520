package org.epay.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.epay.common.util.MyBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 类名: 结果通知client
 * 作者: HappyDan
 * 时间: 2019年4月29日
 * 版本: V1.0
 */
@Service
public class ResultNotifyServiceClient {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 支付订单结果通知
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "payResultNotifyFallback")
    public String payResultNotify(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/result_notify/payResultNotify?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }

    public String payResultNotifyFallback(String jsonParam) {
        return "error";
    }
    
    /**
     * 退款订单结果通知
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "refundResultNotifyFallback")
    public String refundResultNotify(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/result_notify/refundResultNotify?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }

    public String refundResultNotifyFallback(String jsonParam) {
        return "error";
    }

}