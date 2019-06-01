package org.epay.service.service;

import org.epay.common.util.EPayUtil;
import org.epay.service.utils.ConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 类名: 订单明细信息
 * 作者: HappyDan
 * 时间: 2019年4月21日
 * 版本: V1.0
 */
@Component
public class ResultNotifyService {
	
	@Autowired
	private ConfigEntity configEntity;

	/**
	 * 发送支付通知
	 * @param jsonParam
	 */
    @Async
    public void sendPayResultNotify(String jsonParam) {// TODO 配置聚合支付地址
    	
    	String url = configEntity.getPay_notify_url();
    	try {
    		String result = EPayUtil.call4Post(url + jsonParam);
        	System.out.println(result);
		} catch (Exception e) {
			System.out.println("付款通知异常，查看异常信息：");
			System.out.println(e);
		}
    }
    
	/**
	 * 发送退款通知
	 * @param jsonParam
	 */
    @Async
    public void sendRefundResultNotify(String jsonParam) {// TODO 配置聚合支付地址
    	
    	String url = configEntity.getRefund_notify_url();
    	try {
    		String result = EPayUtil.call4Post(url + jsonParam);
        	System.out.println(result);
		} catch (Exception e) {
			System.out.println("退款通知异常，查看异常信息：");
			System.out.println(e);
		}
    }

}
