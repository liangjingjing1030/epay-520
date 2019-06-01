package io.renren.modules.manager.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * CS_CUST_FEE
 * 
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-23 20:47:10
 */
@Data
@TableName("cs_cust_fee")
public class CsCustFeeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 序列号
     */
    @TableId
    private String seqNo;
    /**
     * 受理商户/接入业务编号
     */
    private String acceptBizNo;
    /**
     * 商户客户号
     */
    private String merCustNo;
    /**
     * 客户类型
     */
    private String custType;
    /**
     * 业务类型
     */
    private String bizType;
    /**
     * 支付方式暂定
     */
    private String payType;
    /**
     * 交易费率代码
     */
    private String feeCode;
    /**
     * 退款佣金标志
     */
    private String rfCommFlg;
    /**
     * 退款收手续费标志
     */
    private String rfRecFeeFlg;
    /**
     * 紧急结算佣金费率代码
     */
    private String urgStlCommFeeRateCode;
    /**
     * 退款手续费代码
     */
    private String rfRecFeeCode;
    /**
     * 自动结算费率代码
     */
    private String autoSettlementFeeCode;
    /**
     * 自助结算费率代码
     */
    private String selfSettlementFeeCode;
    /**
     * 紧急结算费率代码
     */
    private String emergencySettlementFeeCode;
    /**
     * 订单有效期
     */
    private String orderEffectiveDuration;
    /**
     * 分润比例
     */
    private BigDecimal profitPercent;
    /**
     * 创建柜员
     */
    private String creTlr;
    /**
     * 创建日期
     */
    private Date creDte;
    /**
     * 创建机构
     */
    private String creBr;
    /**
     * 创建分行
     */
    private String creBk;
    /**
     * 最后更新柜员
     */
    private String updTlr;
    /**
     * 最后更新日期
     */
    private Date updDte;
    /**
     * 更新机构
     */
    private String updBr;
    /**
     * 更新分行
     */
    private String updBk;


    public String getSeqNo() {
        return seqNo;
    }


    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }


    public String getAcceptBizNo() {
        return acceptBizNo;
    }


    public void setAcceptBizNo(String acceptBizNo) {
        this.acceptBizNo = acceptBizNo;
    }


    public String getMerCustNo() {
        return merCustNo;
    }


    public void setMerCustNo(String merCustNo) {
        this.merCustNo = merCustNo;
    }


    public String getCustType() {
        return custType;
    }


    public void setCustType(String custType) {
        this.custType = custType;
    }


    public String getBizType() {
        return bizType;
    }


    public void setBizType(String bizType) {
        this.bizType = bizType;
    }


    public String getPayType() {
        return payType;
    }


    public void setPayType(String payType) {
        this.payType = payType;
    }


    public String getFeeCode() {
        return feeCode;
    }


    public void setFeeCode(String feeCode) {
        this.feeCode = feeCode;
    }


    public String getRfCommFlg() {
        return rfCommFlg;
    }


    public void setRfCommFlg(String rfCommFlg) {
        this.rfCommFlg = rfCommFlg;
    }


    public String getRfRecFeeFlg() {
        return rfRecFeeFlg;
    }


    public void setRfRecFeeFlg(String rfRecFeeFlg) {
        this.rfRecFeeFlg = rfRecFeeFlg;
    }


    public String getUrgStlCommFeeRateCode() {
        return urgStlCommFeeRateCode;
    }


    public void setUrgStlCommFeeRateCode(String urgStlCommFeeRateCode) {
        this.urgStlCommFeeRateCode = urgStlCommFeeRateCode;
    }


    public String getRfRecFeeCode() {
        return rfRecFeeCode;
    }


    public void setRfRecFeeCode(String rfRecFeeCode) {
        this.rfRecFeeCode = rfRecFeeCode;
    }


    public String getAutoSettlementFeeCode() {
        return autoSettlementFeeCode;
    }


    public void setAutoSettlementFeeCode(String autoSettlementFeeCode) {
        this.autoSettlementFeeCode = autoSettlementFeeCode;
    }


    public String getSelfSettlementFeeCode() {
        return selfSettlementFeeCode;
    }


    public void setSelfSettlementFeeCode(String selfSettlementFeeCode) {
        this.selfSettlementFeeCode = selfSettlementFeeCode;
    }


    public String getEmergencySettlementFeeCode() {
        return emergencySettlementFeeCode;
    }


    public void setEmergencySettlementFeeCode(String emergencySettlementFeeCode) {
        this.emergencySettlementFeeCode = emergencySettlementFeeCode;
    }


    public String getOrderEffectiveDuration() {
        return orderEffectiveDuration;
    }


    public void setOrderEffectiveDuration(String orderEffectiveDuration) {
        this.orderEffectiveDuration = orderEffectiveDuration;
    }


    public BigDecimal getProfitPercent() {
        return profitPercent;
    }


    public void setProfitPercent(BigDecimal profitPercent) {
        this.profitPercent = profitPercent;
    }


    public String getCreTlr() {
        return creTlr;
    }


    public void setCreTlr(String creTlr) {
        this.creTlr = creTlr;
    }


    public Date getCreDte() {
        return creDte;
    }


    public void setCreDte(Date creDte) {
        this.creDte = creDte;
    }


    public String getCreBr() {
        return creBr;
    }


    public void setCreBr(String creBr) {
        this.creBr = creBr;
    }


    public String getCreBk() {
        return creBk;
    }


    public void setCreBk(String creBk) {
        this.creBk = creBk;
    }


    public String getUpdTlr() {
        return updTlr;
    }


    public void setUpdTlr(String updTlr) {
        this.updTlr = updTlr;
    }


    public Date getUpdDte() {
        return updDte;
    }


    public void setUpdDte(Date updDte) {
        this.updDte = updDte;
    }


    public String getUpdBr() {
        return updBr;
    }


    public void setUpdBr(String updBr) {
        this.updBr = updBr;
    }


    public String getUpdBk() {
        return updBk;
    }


    public void setUpdBk(String updBk) {
        this.updBk = updBk;
    }

}
