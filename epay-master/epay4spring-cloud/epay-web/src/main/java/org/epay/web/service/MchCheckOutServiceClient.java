package org.epay.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.epay.common.util.MyBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 类名: 结算查询
 * 作者: GaoLiang
 * 时间: 2019年5月15日
 * 版本: V1.0
 */
@Service
public class MchCheckOutServiceClient {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 结算查询
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "mchCheckOutQueryFallback")
    public String mchCheckOutQuery(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/mch_checkOut/mch_checkOut_page?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }

    public String mchCheckOutQueryFallback(String jsonParam) {
        return "error";
    }

}