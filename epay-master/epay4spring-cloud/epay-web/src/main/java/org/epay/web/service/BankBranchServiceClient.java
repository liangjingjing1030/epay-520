package org.epay.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.epay.common.util.MyBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 类名: 查询机构号client
 * 作者: GaoLiang
 * 时间: 2019年5月23日
 * 版本: V1.0
 */
@Service
public class BankBranchServiceClient {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 根据登录名查询商户信息
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "queryBankBranchFallback")
    public String queryBankBranch(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE3/bank_branch/queryBankBranch?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String queryBankBranchFallback(String jsonParam) {
        return "queryBankBranch error";
    }

    @HystrixCommand(fallbackMethod = "queryBranchIdByBranchNameFallback")
    public String queryBranchIdByBranchName(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE3/bank_branch/queryBranchIdByBranchName?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String queryBranchIdByBranchNameFallback(String jsonParam) {
        return "queryBankBranch error";
    }

}