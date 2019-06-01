package org.epay.service.service;

import org.epay.common.util.EPayUtil;
import org.epay.service.utils.ConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

/**
 * 名称: 去聚合支付平台
 * 作者: HappyDan
 * 时间: 2019年4月9日
 * 版本: V1.0
 */
@Component
public class GatherPayService {
	
	@Autowired
	private ConfigEntity configEntity;
	
	/**
	 * 发送订单支付请求
	 * @param jsonParam
	 */
    public String sendPayRequest(String jsonParam) {// TODO 配置聚合支付地址
    	
    	String url = configEntity.getPay_url();
    	try {
//    		String result = EPayUtil.call4Post(url + jsonParam);
//    		JSONObject object = JSONObject.parseObject(result);
//    		String retCode = object.getString("retCode");// 支付结果代码
//          System.out.println(result);
    		String retCode = "SUCCESS";
            if("SUCCESS".equals(retCode)) {
            	return "SUCCESS";
            }else {
            	return "FAIL";
            }
		} catch (Exception e) {
			System.out.println("订单支付异常，查看异常信息：");
			System.out.println(e);
			return "FAIL";
		}
    }
    
	/**
	 * 发送退款请求
	 * @param jsonParam
	 */
    public String sendRefundRequest(String jsonParam) {// TODO 配置聚合支付地址
		// TODO 挡板
		return "SUCCESS";
    	/*ConfigEntity configEntity = new ConfigEntity();

    	String url = configEntity.getRefund_url();
    	try {
    		String result = EPayUtil.call4Post(url + jsonParam);
        	System.out.println(result);
        	JSONObject object = JSONObject.parseObject(result);
    		String retCode = object.getString("retCode");// 支付结果代码
            System.out.println(result);
            if("SUCCESS".equals(retCode)) {
            	return "SUCCESS";
            }else {
            	return "FAIL";
            }
		} catch (Exception e) {
			System.out.println("退款异常，查看异常信息：");
			System.out.println(e);
			return "FAIL";
		}*/
    	/*String url = configEntity.getRefund_url();
    	try {
    		String result = EPayUtil.call4Post(url + jsonParam);
        	System.out.println(result);
        	JSONObject object = JSONObject.parseObject(result);
    		String retCode = object.getString("retCode");// 支付结果代码
            System.out.println(result);
            if("SUCCESS".equals(retCode)) {
            	return "SUCCESS";
            }else {
            	return "FAIL";
            }
		} catch (Exception e) {
			System.out.println("退款异常，查看异常信息：");
			System.out.println(e);
			return "FAIL";
		}*/
    }
    
}
