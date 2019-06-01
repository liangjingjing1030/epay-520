package org.epay.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.epay.common.util.MyBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 类名: 账单文件接口类
 * 作者: HappyDan
 * 时间: 2019年4月26日
 * 版本: V1.0
 */
@Service
public class AccountFileServiceClient {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 查询账单文件信息
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "mchQueryAccountFilesFallback")
    public String mchQueryAccountFiles(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/account_file/mch_query_account_files?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String mchQueryAccountFilesFallback(String jsonParam) {
        return "mch query Account_files error";
    }
    
}