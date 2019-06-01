package org.epay.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.epay.common.util.MyBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @author dingzhiwei jmdhappy@126.com
 * @date 2017-07-05
 * @version V1.0
 * @Copyright: www.EPAY.org
 */
@Service
public class FsbpMerCustInfoServiceClient {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "selectFsbpMerCustInfoFallback")
    public String selectFsbpMerCustInfo(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/fsbp_mer_cust_info/select?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }

    public String selectFsbpMerCustInfoFallback(String jsonParam) {
        return "error";
    }

}