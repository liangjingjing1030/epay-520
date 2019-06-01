package io.renren.modules.manager.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 
 * 
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-23 21:24:36
 */
@Data
@TableName("paycompany_order")
public class PaycompanyOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
	 * 
	 */
    @TableId
    private Integer seqNo;
    /**
     * 合作支付机构编号
     */
    private String payCompanyNo;
    /**
     * 支付公司清算日期
     */
    private Date payCompanyClearDate;
    /**
     * 机构交易时间
     */
    private Date payCompanyTxDate;
    /**
     * 收款户名
     */
    private String payeeName;
    /**
     * 收款账号
     */
    private String payeeAcc;
    /**
     * 扣款户名
     */
    private String payName;
    /**
     * 扣款账号
     */
    private String payAcc;
    /**
     * 扣款银行
     */
    private String payBankcode;
    /**
     * 交易金额
     */
    private BigDecimal txAmt;
    /**
     * 交易手续费
     */
    private BigDecimal txFee;
    /**
     * 交易类型
     */
    private String txType;
    /**
     * 商户名称
     */
    private String mername;
    /**
     * 商户号
     */
    private String merno;
    /**
     * 交易流水号
     */
    private String txSerialNo;
    /**
     * 平台交易日期
     */
    private Date platTxDate;
    /**
     * 平台交易时间
     */
    private Date platTxTime;
    /**
	 * 
	 */
    private String importDate;
    /**
	 * 
	 */
    private String remoteTxJournalNo;
    /**
     * 交易返回码
     */
    private String trandeCode;
    /**
     * 对账日期
     */
    private Date targetDate;


    public Integer getSeqNo() {
        return seqNo;
    }


    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }


    public String getPayCompanyNo() {
        return payCompanyNo;
    }


    public void setPayCompanyNo(String payCompanyNo) {
        this.payCompanyNo = payCompanyNo;
    }


    public Date getPayCompanyClearDate() {
        return payCompanyClearDate;
    }


    public void setPayCompanyClearDate(Date payCompanyClearDate) {
        this.payCompanyClearDate = payCompanyClearDate;
    }


    public Date getPayCompanyTxDate() {
        return payCompanyTxDate;
    }


    public void setPayCompanyTxDate(Date payCompanyTxDate) {
        this.payCompanyTxDate = payCompanyTxDate;
    }


    public String getPayeeName() {
        return payeeName;
    }


    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }


    public String getPayeeAcc() {
        return payeeAcc;
    }


    public void setPayeeAcc(String payeeAcc) {
        this.payeeAcc = payeeAcc;
    }


    public String getPayName() {
        return payName;
    }


    public void setPayName(String payName) {
        this.payName = payName;
    }


    public String getPayAcc() {
        return payAcc;
    }


    public void setPayAcc(String payAcc) {
        this.payAcc = payAcc;
    }


    public String getPayBankcode() {
        return payBankcode;
    }


    public void setPayBankcode(String payBankcode) {
        this.payBankcode = payBankcode;
    }


    public BigDecimal getTxAmt() {
        return txAmt;
    }


    public void setTxAmt(BigDecimal txAmt) {
        this.txAmt = txAmt;
    }


    public BigDecimal getTxFee() {
        return txFee;
    }


    public void setTxFee(BigDecimal txFee) {
        this.txFee = txFee;
    }


    public String getTxType() {
        return txType;
    }


    public void setTxType(String txType) {
        this.txType = txType;
    }


    public String getMername() {
        return mername;
    }


    public void setMername(String mername) {
        this.mername = mername;
    }


    public String getMerno() {
        return merno;
    }


    public void setMerno(String merno) {
        this.merno = merno;
    }


    public String getTxSerialNo() {
        return txSerialNo;
    }


    public void setTxSerialNo(String txSerialNo) {
        this.txSerialNo = txSerialNo;
    }


    public Date getPlatTxDate() {
        return platTxDate;
    }


    public void setPlatTxDate(Date platTxDate) {
        this.platTxDate = platTxDate;
    }


    public Date getPlatTxTime() {
        return platTxTime;
    }


    public void setPlatTxTime(Date platTxTime) {
        this.platTxTime = platTxTime;
    }


    public String getImportDate() {
        return importDate;
    }


    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }


    public String getRemoteTxJournalNo() {
        return remoteTxJournalNo;
    }


    public void setRemoteTxJournalNo(String remoteTxJournalNo) {
        this.remoteTxJournalNo = remoteTxJournalNo;
    }


    public String getTrandeCode() {
        return trandeCode;
    }


    public void setTrandeCode(String trandeCode) {
        this.trandeCode = trandeCode;
    }


    public Date getTargetDate() {
        return targetDate;
    }


    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

}
