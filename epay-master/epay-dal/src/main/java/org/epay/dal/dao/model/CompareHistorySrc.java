package org.epay.dal.dao.model;

import java.util.Date;

public class CompareHistorySrc {
    private PayOrder order;
    private String Channel_id;

    private Integer seqNo;

    private Integer payOrderSeqno;

    private Date txDate;
    private String txDateStr;

    private Date compareDate;
    private String compareDateStr;

    private String compareUsername;

    private String compareStatus;

    private String questionType;

    private String processStatus;

    private String processUsername;

    private Date processDatetime;

    private String processRemark;

    private String txType;

    private String tableName;

    public String getChannel_id() {
        return Channel_id;
    }

    public void setChannel_id(String channel_id) {
        Channel_id = channel_id;
    }

    public PayOrder getOrder() {
        return order;
    }

    public void setOrder(PayOrder order) {
        this.order = order;
    }

    public String getTxDateStr() {
        return txDateStr;
    }

    public void setTxDateStr(String txDateStr) {
        this.txDateStr = txDateStr;
    }

    public String getCompareDateStr() {
        return compareDateStr;
    }

    public void setCompareDateStr(String compareDateStr) {
        this.compareDateStr = compareDateStr;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public Integer getPayOrderSeqno() {
        return payOrderSeqno;
    }

    public void setPayOrderSeqno(Integer payOrderSeqno) {
        this.payOrderSeqno = payOrderSeqno;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public Date getCompareDate() {
        return compareDate;
    }

    public void setCompareDate(Date compareDate) {
        this.compareDate = compareDate;
    }

    public String getCompareUsername() {
        return compareUsername;
    }

    public void setCompareUsername(String compareUsername) {
        this.compareUsername = compareUsername == null ? null : compareUsername.trim();
    }

    public String getCompareStatus() {
        return compareStatus;
    }

    public void setCompareStatus(String compareStatus) {
        this.compareStatus = compareStatus == null ? null : compareStatus.trim();
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType == null ? null : questionType.trim();
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus == null ? null : processStatus.trim();
    }

    public String getProcessUsername() {
        return processUsername;
    }

    public void setProcessUsername(String processUsername) {
        this.processUsername = processUsername == null ? null : processUsername.trim();
    }

    public Date getProcessDatetime() {
        return processDatetime;
    }

    public void setProcessDatetime(Date processDatetime) {
        this.processDatetime = processDatetime;
    }

    public String getProcessRemark() {
        return processRemark;
    }

    public void setProcessRemark(String processRemark) {
        this.processRemark = processRemark == null ? null : processRemark.trim();
    }

    public String getTxType() {
        return txType;
    }

    public void setTxType(String txType) {
        this.txType = txType == null ? null : txType.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }
}