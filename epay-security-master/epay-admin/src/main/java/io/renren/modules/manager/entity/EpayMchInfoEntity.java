package io.renren.modules.manager.entity;

import java.io.Serializable;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 商户信息表
 * 
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-22 19:56:15
 */
@Data
@TableName("epay_mch_info")
public class EpayMchInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商户号
     */
    @TableId
    private String mchId;
    /**
     * 商户名称
     */
    private String mchName;
    /**
     * 商户类型
     */
    private String mchType;
    /**
     * 商户地址
     */
    private String mchAddress;
    /**
     * 商户状态 0-未激活,1-使用中,2-停止使用
     */
    private Integer mchStatus;
    /**
     * 请求私钥
     */
    private String reqKey;
    /**
     * 响应私钥
     */
    private String resKey;
    /**
     * 联系人姓名
     */
    private String contactPerson;
    /**
     * 联系电话
     */
    private String contactPhone;
    /**
     * 商户邮箱
     */
    private String contactEmail;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 审核时间
     */
    private String auditTime;
    /**
     * 审核状态 0-未审核,1-已审核,2-拒绝
     */
    private Integer auditStatus;
    /**
     * 审核原因
     */
    private String auditReason;
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


    public String getMchId() {
        return mchId;
    }


    public void setMchId(String mchId) {
        this.mchId = mchId;
    }


    public String getMchName() {
        return mchName;
    }


    public void setMchName(String mchName) {
        this.mchName = mchName;
    }


    public String getMchType() {
        return mchType;
    }


    public void setMchType(String mchType) {
        this.mchType = mchType;
    }


    public String getMchAddress() {
        return mchAddress;
    }


    public void setMchAddress(String mchAddress) {
        this.mchAddress = mchAddress;
    }


    public Integer getMchStatus() {
        return mchStatus;
    }


    public void setMchStatus(Integer mchStatus) {
        this.mchStatus = mchStatus;
    }


    public String getReqKey() {
        return reqKey;
    }


    public void setReqKey(String reqKey) {
        this.reqKey = reqKey;
    }


    public String getResKey() {
        return resKey;
    }


    public void setResKey(String resKey) {
        this.resKey = resKey;
    }


    public String getContactPerson() {
        return contactPerson;
    }


    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }


    public String getContactPhone() {
        return contactPhone;
    }


    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }


    public String getContactEmail() {
        return contactEmail;
    }


    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
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


    public String getAuditTime() {
        return auditTime;
    }


    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }


    public Integer getAuditStatus() {
        return auditStatus;
    }


    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }


    public String getAuditReason() {
        return auditReason;
    }


    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
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
