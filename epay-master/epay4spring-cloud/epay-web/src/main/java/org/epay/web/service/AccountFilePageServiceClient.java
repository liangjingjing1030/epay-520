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
public class AccountFilePageServiceClient {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "AccountFilePageByMchIdFallback")
    public String AccountFilePageByMchId(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/bill/AccountFilePageByMchId?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String AccountFilePageByMchIdFallback(String jsonParam) {
        return " select mchInfo error";
    }

    @HystrixCommand(fallbackMethod = "checkIfCanDeleteAccountFileFallback")
    public String checkIfCanDeleteAccountFile(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/bill/checkIfCanDeleteAccountFile?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String checkIfCanDeleteAccountFileFallback(String jsonParam) {
        return " check ifCanDeleteAccountFile error";
    }

    @HystrixCommand(fallbackMethod = "deleteAccountFileFallback")
    public String deleteAccountFile(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/bill/deleteAccountFile?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String deleteAccountFileFallback(String jsonParam) {
        return " delete AccountFile error";
    }

    @HystrixCommand(fallbackMethod = "generateReceiptFallback")
    public String generateReceipt(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/bill/userGenerateReceipt?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String generateReceiptFallback(String jsonParam) {
        return " generate receipt error";
    }

    @HystrixCommand(fallbackMethod = "deleteAccountBooksFallback")
    public String deleteAccountBooks(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/bill/deleteAccountBooksByIds?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String deleteAccountBooksFallback(String jsonParam) {
        return " delete AccountBooks error";
    }

    @HystrixCommand(fallbackMethod = "deleteSingleAccountBookFallback")
    public String deleteSingleAccountBook(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/bill/deleteSingleAccountBookById?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String deleteSingleAccountBookFallback(String jsonParam) {
        return " delete SingleAccountBook error";
    }

    @HystrixCommand(fallbackMethod = "updateSingleAccountBookFallback")
    public String updateSingleAccountBook(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/bill/updateSingleAccountBookById?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String updateSingleAccountBookFallback(String jsonParam) {
        return " update SingleAccountBook error";
    }

    @HystrixCommand(fallbackMethod = "queryPayOrderListByItemsIdFallback")
    public String queryPayOrderListByItemsId(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/bill/queryPayOrderListByItemsId?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String queryPayOrderListByItemsIdFallback(String jsonParam) {
        return " update SingleAccountBook error";
    }

    @HystrixCommand(fallbackMethod = "addSingleAccountBookFallback")
    public String addSingleAccountBook(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/bill/addSingleAccountBookByAllField?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String addSingleAccountBookFallback(String jsonParam) {
        return " add SingleAccountBook error";
    }

    @HystrixCommand(fallbackMethod = "queryAllItemsIdFallback")
    public String queryAllItemsId(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/bill/queryAllItemsIdByMchId?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String queryAllItemsIdFallback(String jsonParam) {
        return " query queryAllItemsId error";
    }

    @HystrixCommand(fallbackMethod = "importAccountFileFallback")
    public String importAccountFile(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/bill/importAccountFile?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String importAccountFileFallback(String jsonParam) {
        return " import AccountFile error";
    }

    @HystrixCommand(fallbackMethod = "checkIsDuplicateFallback")
    public String checkIsDuplicate(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/bill/checkIsDuplicate?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String checkIsDuplicateFallback(String jsonParam) {
        return " check IsDuplicate error";
    }

    @HystrixCommand(fallbackMethod = "accountBookPageByConfitionFallback")
    public String AccountBookPageByConfition(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/bill/accountBookPageByConfition?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String accountBookPageByConfitionFallback(String jsonParam) {
        return " check IsDuplicate error";
    }

    @HystrixCommand(fallbackMethod = "AccountBookAndPayOrderPageByConfitionFallback")
    public String AccountBookAndPayOrderPageByConfition(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/bill/AccountBookAndPayOrderPageByConfition?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String AccountBookAndPayOrderPageByConfitionFallback(String jsonParam) {
        return " query AccountBookAndPayOrder error";
    }

    @HystrixCommand(fallbackMethod = "AccountFilePageAddCountFallback")
    public String AccountFilePageAddCount(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/bill/accountFilePageAddCountByMchId?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String AccountFilePageAddCountFallback(String jsonParam) {
        return " query accountFileaddCount error";
    }

    @HystrixCommand(fallbackMethod = "checkHaveAccountCanBeExportFallback")
    public String checkHaveAccountCanBeExport(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/bill/checkHaveAccountCanBeExport?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String checkHaveAccountCanBeExportFallback(String jsonParam) {
        return " check haveAccountCanBeExport error";
    }

    @HystrixCommand(fallbackMethod = "exportAllAccountBookFallback")
    public String exportAllAccountBook(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/bill/exportAllAccountBook?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String exportAllAccountBookFallback(String jsonParam) {
        return " export allAccountBook error";
    }

    @HystrixCommand(fallbackMethod = "exportSelectedAccountBookFallback")
    public String exportSelectedAccountBook(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/bill/exportSelectedAccountBook?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String exportSelectedAccountBookFallback(String jsonParam) {
        return " export selectedAccountBook error";
    }

}