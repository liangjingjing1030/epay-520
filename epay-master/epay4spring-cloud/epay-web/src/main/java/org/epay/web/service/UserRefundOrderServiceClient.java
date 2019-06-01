package org.epay.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.epay.common.util.MyBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 类名: 退款申请client
 * 作者: HappyDan
 * 时间: 2019年4月22日
 * 版本: V1.0
 */
@Service
public class UserRefundOrderServiceClient {

    @Autowired
    RestTemplate restTemplate;
    
    /**
     * 创建退款订单
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "createRefundOrderFallback")
    public String createRefundOrder(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/refund_order/create_refund_order?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }

    public String createRefundOrderFallback(String jsonParam) {
        return "error";
    }

    /**
     * 处理渠道退款
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "sendRefundRequestFallback")
    public String sendRefundRequest(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/refund/channel/gather_pay?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }

    public String sendRefundRequestFallback(String jsonParam) {
        return "error";
    }

}