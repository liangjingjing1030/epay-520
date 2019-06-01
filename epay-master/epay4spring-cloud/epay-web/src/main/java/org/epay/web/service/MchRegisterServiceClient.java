package org.epay.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.epay.common.util.MyBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 类名: 商户注册
 * 作者: HappyDan
 * 时间: 2019年4月30日
 * 版本: V1.0
 */
@Service
public class MchRegisterServiceClient {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 商户注册
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "addMchRegisterFallback")
    public String addMchRegister(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/mch_register/addMchRegister?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }

    public String addMchRegisterFallback(String jsonParam) {
        return "error";
    }
    

}