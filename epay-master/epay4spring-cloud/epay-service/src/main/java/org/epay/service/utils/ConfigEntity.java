package org.epay.service.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * 类名: 配置参数类
 * 作者: HappyDan
 * 时间: 2019年4月28日
 * 版本: V1.0
 */
@RefreshScope
@Service
public class ConfigEntity {

    // 支付地址
    @Value("${epay.pay_url}")
    private String pay_url;
    
    // 退款地址
    @Value("${epay.refund_url}")
    private String refund_url;
    
    // 支付通知地址
    @Value("${epay.pay_notify_url}")
    private String pay_notify_url;
    
    // 退款通知地址
    @Value("${epay.refund_notify_url}")
    private String refund_notify_url;
    
    //微信渠道结算费率
    @Value("${epay.wx_settle_cost}")
    private String wx_settle_cost;
    
    //支付宝结算费率
    @Value("${epay.alipay_settle_cost}")
    private String alipay_settle_cost;
    
    // 编码
    public static String CHARSET = "UTF-8";
    
    // 返回格式
    public static String FORMAT = "json";

	public String getPay_url() {
		return pay_url;
	}

	public void setPay_url(String pay_url) {
		this.pay_url = pay_url;
	}

	public String getRefund_url() {
		return refund_url;
	}

	public void setRefund_url(String refund_url) {
		this.refund_url = refund_url;
	}

	public String getPay_notify_url() {
		return pay_notify_url;
	}

	public void setPay_notify_url(String pay_notify_url) {
		this.pay_notify_url = pay_notify_url;
	}

	public String getRefund_notify_url() {
		return refund_notify_url;
	}

	public void setRefund_notify_url(String refund_notify_url) {
		this.refund_notify_url = refund_notify_url;
	}

	public static String getCHARSET() {
		return CHARSET;
	}

	public static void setCHARSET(String cHARSET) {
		CHARSET = cHARSET;
	}

	public static String getFORMAT() {
		return FORMAT;
	}

	public static void setFORMAT(String fORMAT) {
		FORMAT = fORMAT;
	}

	public String getWx_settle_cost() {
		return wx_settle_cost;
	}

	public void setWx_settle_cost(String wx_settle_cost) {
		this.wx_settle_cost = wx_settle_cost;
	}

	public String getAlipay_settle_cost() {
		return alipay_settle_cost;
	}

	public void setAlipay_settle_cost(String alipay_settle_cost) {
		this.alipay_settle_cost = alipay_settle_cost;
	}
    

    
}

