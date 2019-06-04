package org.epay.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.epay.common.util.MyBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 类名: 商户信息
 * 作者: HappyDan
 * 时间: 2019年4月18日
 * 版本: V1.0
 */
@Service
public class MchInfoServiceClient {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "selectMchInfoByMchIdFallback")
    public String selectMchInfoByMchId(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/mch_info/selectMchInfoByMchId?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }

    public String selectMchInfoByMchIdFallback(String jsonParam) {
        return " select mchInfo error";
    }
    
    /**
     * 根据地区经营范围查询商户信息
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "userSelectMchInfoFallback")
    public String userSelectMchInfo(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/mch_info/userSelectMchInfo?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String userSelectMchInfoFallback(String jsonParam) {
        return " select mchInfo error";
    }


}