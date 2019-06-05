package org.epay.dal.dao.model;

import java.util.Date;

public class CompareHistoryTgt {
    private Integer seqNo;

    private Integer paycompanyOrderSeqno;

    private Date txDate;

    private Date compareDate;

    private String compareUsername;

    private String compareStatus;

    private String questionType;

    private String processStatus;

    private String processUsername;

    private Date processDatetime;

    private String processRemark;

    private String txType;

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public Integer getPaycompanyOrderSeqno() {
        return paycompanyOrderSeqno;
    }

    public void setPaycompanyOrderSeqno(Integer paycompanyOrderSeqno) {
        this.paycompanyOrderSeqno = paycompanyOrderSeqno;
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
}