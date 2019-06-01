package io.renren.modules.manager.entity;

import java.io.Serializable;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 订单支付表
 * 
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-22 19:56:15
 */
@Data
@TableName("epay_pay_order")
public class EpayPayOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 支付订单号
     */
    @TableId
    private String payOrderId;
    /**
     * 商户ID
     */
    private String mchId;
    /**
     * 账单号
     */
    private String mchOrderNo;
    /**
     * 渠道ID
     */
    private String channelId;
    /**
     * 支付金额
     */
    private Long amount;
    /**
     * 三位货币代码
     */
    private String currency;
    /**
     * 支付状态 0-订单生成,1-支付中(目前未使用),2-支付成功,3-业务处理完成
     */
    private Integer status;
    /**
     * 客户端IP
     */
    private String clientIp;
    /**
     * 设备
     */
    private String device;
    /**
     * 商品标题
     */
    private String subject;
    /**
     * 商品描述信息
     */
    private String body;
    /**
     * 特定渠道发起时额外参数
     */
    private String extra;
    /**
     * 渠道商户ID
     */
    private String channelMchId;
    /**
     * 渠道订单号
     */
    private String channelOrderNo;
    /**
     * 渠道支付错误码
     */
    private String resCode;
    /**
     * 渠道支付错误描述
     */
    private String resMsg;
    /**
     * 扩展参数1
     */
    private String param1;
    /**
     * 扩展参数2
     */
    private String param2;
    /**
     * 通知地址
     */
    private String notifyUrl;
    /**
     * 通知次数
     */
    private String notifyCount;
    /**
     * 最后一次通知时间
     */
    private String lastNotifyTime;
    /**
     * 订单失效时间
     */
    private String expireTime;
    /**
     * 订单支付成功时间
     */
    private String paySuccTime;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;
    /**
     * 备用字段3
     */
    private String reserve3;
    /**
     * 备用字段4
     */
    private String reserve4;
    /**
     * 备用字段5
     */
    private String reserve5;


    public String getPayOrderId() {
        return payOrderId;
    }


    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
    }


    public String getMchId() {
        return mchId;
    }


    public void setMchId(String mchId) {
        this.mchId = mchId;
    }


    public String getMchOrderNo() {
        return mchOrderNo;
    }


    public void setMchOrderNo(String mchOrderNo) {
        this.mchOrderNo = mchOrderNo;
    }


    public String getChannelId() {
        return channelId;
    }


    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }


    public Long getAmount() {
        return amount;
    }


    public void setAmount(Long amount) {
        this.amount = amount;
    }


    public String getCurrency() {
        return currency;
    }


    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public Integer getStatus() {
        return status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getClientIp() {
        return clientIp;
    }


    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }


    public String getDevice() {
        return device;
    }


    public void setDevice(String device) {
        this.device = device;
    }


    public String getSubject() {
        return subject;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getBody() {
        return body;
    }


    public void setBody(String body) {
        this.body = body;
    }


    public String getExtra() {
        return extra;
    }


    public void setExtra(String extra) {
        this.extra = extra;
    }


    public String getChannelMchId() {
        return channelMchId;
    }


    public void setChannelMchId(String channelMchId) {
        this.channelMchId = channelMchId;
    }


    public String getChannelOrderNo() {
        return channelOrderNo;
    }


    public void setChannelOrderNo(String channelOrderNo) {
        this.channelOrderNo = channelOrderNo;
    }


    public String getResCode() {
        return resCode;
    }


    public void setResCode(String resCode) {
        this.resCode = resCode;
    }


    public String getResMsg() {
        return resMsg;
    }


    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }


    public String getParam1() {
        return param1;
    }


    public void setParam1(String param1) {
        this.param1 = param1;
    }


    public String getParam2() {
        return param2;
    }


    public void setParam2(String param2) {
        this.param2 = param2;
    }


    public String getNotifyUrl() {
        return notifyUrl;
    }


    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }


    public String getNotifyCount() {
        return notifyCount;
    }


    public void setNotifyCount(String notifyCount) {
        this.notifyCount = notifyCount;
    }


    public String getLastNotifyTime() {
        return lastNotifyTime;
    }


    public void setLastNotifyTime(String lastNotifyTime) {
        this.lastNotifyTime = lastNotifyTime;
    }


    public String getExpireTime() {
        return expireTime;
    }


    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }


    public String getPaySuccTime() {
        return paySuccTime;
    }


    public void setPaySuccTime(String paySuccTime) {
        this.paySuccTime = paySuccTime;
    }


    public String getCreateTime() {
        return createTime;
    }


    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public String getUpdateTime() {
        return updateTime;
    }


    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }


    public String getReserve1() {
        return reserve1;
    }


    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }


    public String getReserve2() {
        return reserve2;
    }


    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }


    public String getReserve3() {
        return reserve3;
    }


    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }


    public String getReserve4() {
        return reserve4;
    }


    public void setReserve4(String reserve4) {
        this.reserve4 = reserve4;
    }


    public String getReserve5() {
        return reserve5;
    }


    public void setReserve5(String reserve5) {
        this.reserve5 = reserve5;
    }

}
