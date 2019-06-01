package org.epay.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.epay.common.util.MyBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 类名: 商户渠道信息
 * 作者: GaoLiang
 * 时间: 2019年4月28日
 * 版本: V1.0
 */
@Service
public class MchChannelServiceClient {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "selectMchInfoByMchIdFallback")
    public String selectMchChannelByMchId(String jsonParam) {
        System.out.println("web中service参数：" + jsonParam);
        return restTemplate.getForEntity("http://EPAY-SERVICE/mch_info/selectMchChannelByMchId?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }

    public String selectMchInfoByMchIdFallback(String jsonParam) {
        return " select mchInfo error";
    }

}