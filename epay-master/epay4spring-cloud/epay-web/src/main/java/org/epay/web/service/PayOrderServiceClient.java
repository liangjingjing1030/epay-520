package org.epay.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.epay.common.util.MyBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 类名: 客户缴费明细(订单详情)
 * 作者: HappyDan
 * 时间: 2019年4月20日
 * 版本: V1.0
 */
@Service
public class PayOrderServiceClient {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 客户查询订单流水
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "queryPayOrderDetailFallback")
    public String queryPayOrderDetail(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/pay_order/query_order_detail?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String queryPayOrderDetailFallback(String jsonParam) {
        return "mch query orders error";
    }
    
    /**
     * 查询单个支付订单信息
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "selectPayOrderByPrimaryKeyFallback")
    public String selectPayOrderByPrimaryKey(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/pay_order/selectPayOrderByPrimaryKey?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String selectPayOrderByPrimaryKeyFallback(String jsonParam) {
        return "select pay_order by primary_key Fallback error";
    }

    /**
     * 分页查询订单信息
     * @param jsonParam
     * @return
     * By GaoLiang
     */
    @HystrixCommand(fallbackMethod = "payOrderPageByConfitionFallback")
    public String payOrderPageByConfition(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/refund/payOrderPageByConfition?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String payOrderPageByConfitionFallback(String jsonParam) {
        return "select payOrderPageByConfition Fallback error";
    }
    
    /**
     * 删除单个支付订单信息
     * @param jsonParam
     * @return
     */
    @HystrixCommand(fallbackMethod = "deletePayOrderByPrimaryKeyFallback")
    public String deletePayOrderByPrimaryKey(String jsonParam) {
        return restTemplate.getForEntity("http://EPAY-SERVICE/pay_order/deletePayOrderByPrimaryKey?jsonParam=" + MyBase64.encode(jsonParam.getBytes()), String.class).getBody();
    }
    public String deletePayOrderByPrimaryKeyFallback(String jsonParam) {
        return "delete pay_order by primary_key Fallback error";
    }

}