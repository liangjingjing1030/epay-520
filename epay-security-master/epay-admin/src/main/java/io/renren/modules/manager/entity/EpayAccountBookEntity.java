package io.renren.modules.manager.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 账单明细表
 * 
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-22 19:56:15
 */
@Data
@TableName("epay_account_book")
public class EpayAccountBookEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 账单ID
     */
    @TableId
    private String accountBookId;
    /**
     * 商户号
     */
    private String mchId;
    /**
     * 项目编号
     */
    private String itemsId;
    /**
     * 客户唯一标识（例如学生学号，客户身份证等）
     */
    private String userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 三位货币代码,人民币:cny
     */
    private String currency;
    /**
     * 应缴金额，单位分
     */
    private Long itemsMoney;
    /**
     * 优惠金额，单位分
     */
    private Long discountsMoney;
    /**
     * 实际金额，单位分
     */
    private Long actualMoney;
    /**
     * 已支付金额，单位分
     */
    private Long paidMoney;
    /**
     * 未支付金额，单位分
     */
    private Long surplusMoney;
    /**
     * 滞纳金，单位分
     */
    private Long violateMoney;
    /**
     * 支付次数
     */
    private Integer payNumber;
    /**
     * 支付时间
     */
    private Date payTime;
    /**
     * 支付状态,0-失败,1-成功
     */
    private Integer payStatus;
    /**
     * 冲正状态,0-未冲正,1-已冲正
     */
    private Integer reverseStatus;
    /**
     * 冲正金额,单位分
     */
    private Long reverseMoney;
    /**
     * 冲正时间
     */
    private Date reverseTime;
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


    public String getAccountBookId() {
        return accountBookId;
    }


    public void setAccountBookId(String accountBookId) {
        this.accountBookId = accountBookId;
    }


    public String getMchId() {
        return mchId;
    }


    public void setMchId(String mchId) {
        this.mchId = mchId;
    }


    public String getItemsId() {
        return itemsId;
    }


    public void setItemsId(String itemsId) {
        this.itemsId = itemsId;
    }


    public String getUserId() {
        return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getCurrency() {
        return currency;
    }


    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public Long getItemsMoney() {
        return itemsMoney;
    }


    public void setItemsMoney(Long itemsMoney) {
        this.itemsMoney = itemsMoney;
    }


    public Long getDiscountsMoney() {
        return discountsMoney;
    }


    public void setDiscountsMoney(Long discountsMoney) {
        this.discountsMoney = discountsMoney;
    }


    public Long getActualMoney() {
        return actualMoney;
    }


    public void setActualMoney(Long actualMoney) {
        this.actualMoney = actualMoney;
    }


    public Long getPaidMoney() {
        return paidMoney;
    }


    public void setPaidMoney(Long paidMoney) {
        this.paidMoney = paidMoney;
    }


    public Long getSurplusMoney() {
        return surplusMoney;
    }


    public void setSurplusMoney(Long surplusMoney) {
        this.surplusMoney = surplusMoney;
    }


    public Long getViolateMoney() {
        return violateMoney;
    }


    public void setViolateMoney(Long violateMoney) {
        this.violateMoney = violateMoney;
    }


    public Integer getPayNumber() {
        return payNumber;
    }


    public void setPayNumber(Integer payNumber) {
        this.payNumber = payNumber;
    }


    public Date getPayTime() {
        return payTime;
    }


    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }


    public Integer getPayStatus() {
        return payStatus;
    }


    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }


    public Integer getReverseStatus() {
        return reverseStatus;
    }


    public void setReverseStatus(Integer reverseStatus) {
        this.reverseStatus = reverseStatus;
    }


    public Long getReverseMoney() {
        return reverseMoney;
    }


    public void setReverseMoney(Long reverseMoney) {
        this.reverseMoney = reverseMoney;
    }


    public Date getReverseTime() {
        return reverseTime;
    }


    public void setReverseTime(Date reverseTime) {
        this.reverseTime = reverseTime;
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
