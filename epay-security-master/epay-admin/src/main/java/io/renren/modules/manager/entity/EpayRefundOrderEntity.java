package io.renren.modules.manager.entity;

import java.io.Serializable;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 订单退款表
 * 
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-22 19:56:15
 */
@Data
@TableName("epay_refund_order")
public class EpayRefundOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 退款订单号
     */
    @TableId
    private String refundOrderId;
    /**
     * 支付订单号
     */
    private String payOrderid;
    /**
     * 渠道支付单号
     */
    private String channelPayOrderNo;
    /**
     * 商户ID
     */
    private String mchId;
    /**
     * 商户退款单号
     */
    private String mchRefundNo;
    /**
     * 渠道ID
     */
    private String channelId;
    /**
     * 支付金额
     */
    private Long payAmount;
    /**
     * 退款金额
     */
    private Long refundAmount;
    /**
     * 三位货币代码
     */
    private String currency;
    /**
     * 退款状态:0-订单生成,1-退款中,2-退款成功,3-退款失败,4-业务处理完成
     */
    private Integer ctatus;
    /**
     * 退款结果:0-不确认结果,1-等待手动处理,2-确认成功,3-确认失败
     */
    private Integer result;
    /**
     * 客户端IP
     */
    private String clientIp;
    /**
     * 设备
     */
    private String device;
    /**
     * 备注
     */
    private String remarkInfo;
    /**
     * 渠道用户标识,如微信openId,支付宝账号
     */
    private String channelUser;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 渠道商户ID
     */
    private String channelMchId;
    /**
     * 渠道订单号
     */
    private String channelOrderNo;
    /**
     * 渠道响应码
     */
    private String channelResCode;
    /**
     * 渠道相应描述
     */
    private String channelResMsg;
    /**
     * 特定渠道发起时额外参数
     */
    private String extra;
    /**
     * 通知地址
     */
    private String notifyurl;
    /**
     * 扩展参数1
     */
    private String param1;
    /**
     * 扩展参数2
     */
    private String param2;
    /**
     * 订单失效时间
     */
    private String expireTime;
    /**
     * 订单退款成功时间
     */
    private String refundSuccTime;
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


    public String getRefundOrderId() {
        return refundOrderId;
    }


    public void setRefundOrderId(String refundOrderId) {
        this.refundOrderId = refundOrderId;
    }


    public String getPayOrderid() {
        return payOrderid;
    }


    public void setPayOrderid(String payOrderid) {
        this.payOrderid = payOrderid;
    }


    public String getChannelPayOrderNo() {
        return channelPayOrderNo;
    }


    public void setChannelPayOrderNo(String channelPayOrderNo) {
        this.channelPayOrderNo = channelPayOrderNo;
    }


    public String getMchId() {
        return mchId;
    }


    public void setMchId(String mchId) {
        this.mchId = mchId;
    }


    public String getMchRefundNo() {
        return mchRefundNo;
    }


    public void setMchRefundNo(String mchRefundNo) {
        this.mchRefundNo = mchRefundNo;
    }


    public String getChannelId() {
        return channelId;
    }


    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }


    public Long getPayAmount() {
        return payAmount;
    }


    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }


    public Long getRefundAmount() {
        return refundAmount;
    }


    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }


    public String getCurrency() {
        return currency;
    }


    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public Integer getCtatus() {
        return ctatus;
    }


    public void setCtatus(Integer ctatus) {
        this.ctatus = ctatus;
    }


    public Integer getResult() {
        return result;
    }


    public void setResult(Integer result) {
        this.result = result;
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


    public String getRemarkInfo() {
        return remarkInfo;
    }


    public void setRemarkInfo(String remarkInfo) {
        this.remarkInfo = remarkInfo;
    }


    public String getChannelUser() {
        return channelUser;
    }


    public void setChannelUser(String channelUser) {
        this.channelUser = channelUser;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
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


    public String getChannelResCode() {
        return channelResCode;
    }


    public void setChannelResCode(String channelResCode) {
        this.channelResCode = channelResCode;
    }


    public String getChannelResMsg() {
        return channelResMsg;
    }


    public void setChannelResMsg(String channelResMsg) {
        this.channelResMsg = channelResMsg;
    }


    public String getExtra() {
        return extra;
    }


    public void setExtra(String extra) {
        this.extra = extra;
    }


    public String getNotifyurl() {
        return notifyurl;
    }


    public void setNotifyurl(String notifyurl) {
        this.notifyurl = notifyurl;
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


    public String getExpireTime() {
        return expireTime;
    }


    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }


    public String getRefundSuccTime() {
        return refundSuccTime;
    }


    public void setRefundSuccTime(String refundSuccTime) {
        this.refundSuccTime = refundSuccTime;
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
