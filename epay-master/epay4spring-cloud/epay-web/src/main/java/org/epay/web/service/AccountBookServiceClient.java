package org.epay.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.epay.common.util.MyBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 名称: 账单查询
 * 作者: HappyDan
 * 时间: 2019年4月20日
 * 版本: V1.0
 */
@Service
public class AccountBookServiceClient {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 支付订单查询单个账单信息
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "selectAccountBookByPrimaryKeyFallback")
    public String selectAccountBookByPrimaryKey(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/account_book/selectAccountBookByPrimaryKey?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }

    public String selectAccountBookByPrimaryKeyFallback(String jsonParam) {
        return "error";
    }
    
    /**
     * 客户账单查询
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "accountBookQueryFallback")
    public String accountBookQuery(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/account_book/user_query_account_book?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }

    public String accountBookQueryFallback(String jsonParam) {
        return "error";
    }

    /**
     * 客户账单查询
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "mchCheckOutDetailQueryFallback")
    public String mchCheckOutDetailQuery(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/account_book/user_query_mchCheckOutDetail?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }

    public String mchCheckOutDetailQueryFallback(String jsonParam) {
        return "error";
    }

    /**
     * 结算明细下载
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "exportMchCheckOutDetailFallback")
    public String exportMchCheckOutDetail(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/account_book/export_mch_check_out_detail?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }

    public String exportMchCheckOutDetailFallback(String jsonParam) {
        return "error";
    }
    
    /**
     * 创建支付订单对应的账单信息
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "createAccountBookFallback")
    public String createAccountBook(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/account_book/createAccountBook?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }

    public String createAccountBookFallback(String jsonParam) {
        return "error";
    }

    /**
     * 根据账单号删除账单信息
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "deleteAccountBookByPrimaryKeyFallback")
    public String deleteAccountBookByPrimaryKey(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/account_book/deleteAccountBookByPrimaryKey?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }

    public String deleteAccountBookByPrimaryKeyFallback(String jsonParam) {
        return "error";
    }
    
}