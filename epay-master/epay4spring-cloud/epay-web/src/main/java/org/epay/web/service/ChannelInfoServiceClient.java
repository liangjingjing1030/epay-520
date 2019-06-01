package org.epay.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.epay.common.util.MyBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * 类名: 商户支付渠道信息
 * 作者: HappyDan
 * 时间: 2019年4月21日
 * 版本: V1.0
 */
@Service
public class ChannelInfoServiceClient {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "selectChannelInfoByChannelIdAndMchIdFallback")
    public String selectChannelInfoByChannelIdAndMchId(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/channel_info/selectByChannelIdAndMchId?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }

    public String selectChannelInfoByChannelIdAndMchIdFallback(String jsonParam) {
        return "error";
    }

}