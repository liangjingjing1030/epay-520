package org.epay.dal.dao.model;

import java.io.Serializable;

public class MchInfo implements Serializable {
    /**
     * 商户号
     *
     * @mbg.generated
     */
    private String mch_id;

    /**
     * 名称
     *
     * @mbg.generated
     */
    private String mch_name;

    /**
     * 商户类型：1-个体商户、2-企业、3-小微商户
     *
     * @mbg.generated
     */
    private String mch_type;

    /**
     * 经营范围：查询mach_range数据字典
     *
     * @mbg.generated
     */
    private String mch_range;

    /**
     * 商户所在城市
     *
     * @mbg.generated
     */
    private String mch_city;

    /**
     * 商户地址
     *
     * @mbg.generated
     */
    private String mch_address;

    /**
     * 商户状态,0-未激活,1-使用中,2-停止使用
     *
     * @mbg.generated
     */
    private Byte mch_status;

    /**
     * 联系人姓名
     *
     * @mbg.generated
     */
    private String contact_person;

    /**
     * 联系人电话
     *
     * @mbg.generated
     */
    private String contact_phone;

    /**
     * 商户邮箱
     *
     * @mbg.generated
     */
    private String contact_email;

    /**
     * 联系人证件类型:
0-身份证
1-护照
2-军官证 
3-士兵证
4-港澳居民身份证
5-户口本
6-外国护照
7-文职证
8-警官证
9-其它
A-台胞证
     *
     * @mbg.generated
     */
    private String certificate_type;

    /**
     * 联系人证件号
     *
     * @mbg.generated
     */
    private String certificate_number;

    /**
     * 营业执照号
     *
     * @mbg.generated
     */
    private String business_license;

    /**
     * 请求私钥
     *
     * @mbg.generated
     */
    private String req_key;

    /**
     * 响应私钥
     *
     * @mbg.generated
     */
    private String res_key;

    /**
     * 拓展网点号
     *
     * @mbg.generated
     */
    private String branch_id;

    /**
     * 拓展网点名称
     *
     * @mbg.generated
     */
    private String branch_name;

    /**
     * 拓展员工号
     *
     * @mbg.generated
     */
    private String staff_id;

    /**
     * 拓展员工名称
     *
     * @mbg.generated
     */
    private String stall_name;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private String create_time;

    /**
     * 更新时间
     *
     * @mbg.generated
     */
    private String update_time;

    /**
     * 审核时间
     *
     * @mbg.generated
     */
    private String audit_time;

    /**
     * 审核状态,0-未审核,1-已审核,2-拒绝
     *
     * @mbg.generated
     */
    private Byte audit_status;

    /**
     * 审核原因
     *
     * @mbg.generated
     */
    private String audit_reason;

    /**
     * 备用字段1
     *
     * @mbg.generated
     */
    private String reserve1;

    /**
     * 备用字段2
     *
     * @mbg.generated
     */
    private String reserve2;

    /**
     * 备用字段3
     *
     * @mbg.generated
     */
    private String reserve3;

    /**
     * 备用字段4
     *
     * @mbg.generated
     */
    private String reserve4;

    /**
     * 备用字段5
     *
     * @mbg.generated
     */
    private String reserve5;

    private static final long serialVersionUID = 1L;

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id == null ? null : mch_id.trim();
    }

    public String getMch_name() {
        return mch_name;
    }

    public void setMch_name(String mch_name) {
        this.mch_name = mch_name == null ? null : mch_name.trim();
    }

    public String getMch_type() {
        return mch_type;
    }

    public void setMch_type(String mch_type) {
        this.mch_type = mch_type == null ? null : mch_type.trim();
    }

    public String getMch_range() {
        return mch_range;
    }

    public void setMch_range(String mch_range) {
        this.mch_range = mch_range == null ? null : mch_range.trim();
    }

    public String getMch_city() {
        return mch_city;
    }

    public void setMch_city(String mch_city) {
        this.mch_city = mch_city == null ? null : mch_city.trim();
    }

    public String getMch_address() {
        return mch_address;
    }

    public void setMch_address(String mch_address) {
        this.mch_address = mch_address == null ? null : mch_address.trim();
    }

    public Byte getMch_status() {
        return mch_status;
    }

    public void setMch_status(Byte mch_status) {
        this.mch_status = mch_status;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person == null ? null : contact_person.trim();
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone == null ? null : contact_phone.trim();
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email == null ? null : contact_email.trim();
    }

    public String getCertificate_type() {
        return certificate_type;
    }

    public void setCertificate_type(String certificate_type) {
        this.certificate_type = certificate_type == null ? null : certificate_type.trim();
    }

    public String getCertificate_number() {
        return certificate_number;
    }

    public void setCertificate_number(String certificate_number) {
        this.certificate_number = certificate_number == null ? null : certificate_number.trim();
    }

    public String getBusiness_license() {
        return business_license;
    }

    public void setBusiness_license(String business_license) {
        this.business_license = business_license == null ? null : business_license.trim();
    }

    public String getReq_key() {
        return req_key;
    }

    public void setReq_key(String req_key) {
        this.req_key = req_key == null ? null : req_key.trim();
    }

    public String getRes_key() {
        return res_key;
    }

    public void setRes_key(String res_key) {
        this.res_key = res_key == null ? null : res_key.trim();
    }

    public String getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(String branch_id) {
        this.branch_id = branch_id == null ? null : branch_id.trim();
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name == null ? null : branch_name.trim();
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id == null ? null : staff_id.trim();
    }

    public String getStall_name() {
        return stall_name;
    }

    public void setStall_name(String stall_name) {
        this.stall_name = stall_name == null ? null : stall_name.trim();
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time == null ? null : create_time.trim();
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time == null ? null : update_time.trim();
    }

    public String getAudit_time() {
        return audit_time;
    }

    public void setAudit_time(String audit_time) {
        this.audit_time = audit_time == null ? null : audit_time.trim();
    }

    public Byte getAudit_status() {
        return audit_status;
    }

    public void setAudit_status(Byte audit_status) {
        this.audit_status = audit_status;
    }

    public String getAudit_reason() {
        return audit_reason;
    }

    public void setAudit_reason(String audit_reason) {
        this.audit_reason = audit_reason == null ? null : audit_reason.trim();
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1 == null ? null : reserve1.trim();
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2 == null ? null : reserve2.trim();
    }

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3 == null ? null : reserve3.trim();
    }

    public String getReserve4() {
        return reserve4;
    }

    public void setReserve4(String reserve4) {
        this.reserve4 = reserve4 == null ? null : reserve4.trim();
    }

    public String getReserve5() {
        return reserve5;
    }

    public void setReserve5(String reserve5) {
        this.reserve5 = reserve5 == null ? null : reserve5.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mch_id=").append(mch_id);
        sb.append(", mch_name=").append(mch_name);
        sb.append(", mch_type=").append(mch_type);
        sb.append(", mch_range=").append(mch_range);
        sb.append(", mch_city=").append(mch_city);
        sb.append(", mch_address=").append(mch_address);
        sb.append(", mch_status=").append(mch_status);
        sb.append(", contact_person=").append(contact_person);
        sb.append(", contact_phone=").append(contact_phone);
        sb.append(", contact_email=").append(contact_email);
        sb.append(", certificate_type=").append(certificate_type);
        sb.append(", certificate_number=").append(certificate_number);
        sb.append(", business_license=").append(business_license);
        sb.append(", req_key=").append(req_key);
        sb.append(", res_key=").append(res_key);
        sb.append(", branch_id=").append(branch_id);
        sb.append(", branch_name=").append(branch_name);
        sb.append(", staff_id=").append(staff_id);
        sb.append(", stall_name=").append(stall_name);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", audit_time=").append(audit_time);
        sb.append(", audit_status=").append(audit_status);
        sb.append(", audit_reason=").append(audit_reason);
        sb.append(", reserve1=").append(reserve1);
        sb.append(", reserve2=").append(reserve2);
        sb.append(", reserve3=").append(reserve3);
        sb.append(", reserve4=").append(reserve4);
        sb.append(", reserve5=").append(reserve5);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MchInfo other = (MchInfo) that;
        return (this.getMch_id() == null ? other.getMch_id() == null : this.getMch_id().equals(other.getMch_id()))
            && (this.getMch_name() == null ? other.getMch_name() == null : this.getMch_name().equals(other.getMch_name()))
            && (this.getMch_type() == null ? other.getMch_type() == null : this.getMch_type().equals(other.getMch_type()))
            && (this.getMch_range() == null ? other.getMch_range() == null : this.getMch_range().equals(other.getMch_range()))
            && (this.getMch_city() == null ? other.getMch_city() == null : this.getMch_city().equals(other.getMch_city()))
            && (this.getMch_address() == null ? other.getMch_address() == null : this.getMch_address().equals(other.getMch_address()))
            && (this.getMch_status() == null ? other.getMch_status() == null : this.getMch_status().equals(other.getMch_status()))
            && (this.getContact_person() == null ? other.getContact_person() == null : this.getContact_person().equals(other.getContact_person()))
            && (this.getContact_phone() == null ? other.getContact_phone() == null : this.getContact_phone().equals(other.getContact_phone()))
            && (this.getContact_email() == null ? other.getContact_email() == null : this.getContact_email().equals(other.getContact_email()))
            && (this.getCertificate_type() == null ? other.getCertificate_type() == null : this.getCertificate_type().equals(other.getCertificate_type()))
            && (this.getCertificate_number() == null ? other.getCertificate_number() == null : this.getCertificate_number().equals(other.getCertificate_number()))
            && (this.getBusiness_license() == null ? other.getBusiness_license() == null : this.getBusiness_license().equals(other.getBusiness_license()))
            && (this.getReq_key() == null ? other.getReq_key() == null : this.getReq_key().equals(other.getReq_key()))
            && (this.getRes_key() == null ? other.getRes_key() == null : this.getRes_key().equals(other.getRes_key()))
            && (this.getBranch_id() == null ? other.getBranch_id() == null : this.getBranch_id().equals(other.getBranch_id()))
            && (this.getBranch_name() == null ? other.getBranch_name() == null : this.getBranch_name().equals(other.getBranch_name()))
            && (this.getStaff_id() == null ? other.getStaff_id() == null : this.getStaff_id().equals(other.getStaff_id()))
            && (this.getStall_name() == null ? other.getStall_name() == null : this.getStall_name().equals(other.getStall_name()))
            && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
            && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
            && (this.getAudit_time() == null ? other.getAudit_time() == null : this.getAudit_time().equals(other.getAudit_time()))
            && (this.getAudit_status() == null ? other.getAudit_status() == null : this.getAudit_status().equals(other.getAudit_status()))
            && (this.getAudit_reason() == null ? other.getAudit_reason() == null : this.getAudit_reason().equals(other.getAudit_reason()))
            && (this.getReserve1() == null ? other.getReserve1() == null : this.getReserve1().equals(other.getReserve1()))
            && (this.getReserve2() == null ? other.getReserve2() == null : this.getReserve2().equals(other.getReserve2()))
            && (this.getReserve3() == null ? other.getReserve3() == null : this.getReserve3().equals(other.getReserve3()))
            && (this.getReserve4() == null ? other.getReserve4() == null : this.getReserve4().equals(other.getReserve4()))
            && (this.getReserve5() == null ? other.getReserve5() == null : this.getReserve5().equals(other.getReserve5()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMch_id() == null) ? 0 : getMch_id().hashCode());
        result = prime * result + ((getMch_name() == null) ? 0 : getMch_name().hashCode());
        result = prime * result + ((getMch_type() == null) ? 0 : getMch_type().hashCode());
        result = prime * result + ((getMch_range() == null) ? 0 : getMch_range().hashCode());
        result = prime * result + ((getMch_city() == null) ? 0 : getMch_city().hashCode());
        result = prime * result + ((getMch_address() == null) ? 0 : getMch_address().hashCode());
        result = prime * result + ((getMch_status() == null) ? 0 : getMch_status().hashCode());
        result = prime * result + ((getContact_person() == null) ? 0 : getContact_person().hashCode());
        result = prime * result + ((getContact_phone() == null) ? 0 : getContact_phone().hashCode());
        result = prime * result + ((getContact_email() == null) ? 0 : getContact_email().hashCode());
        result = prime * result + ((getCertificate_type() == null) ? 0 : getCertificate_type().hashCode());
        result = prime * result + ((getCertificate_number() == null) ? 0 : getCertificate_number().hashCode());
        result = prime * result + ((getBusiness_license() == null) ? 0 : getBusiness_license().hashCode());
        result = prime * result + ((getReq_key() == null) ? 0 : getReq_key().hashCode());
        result = prime * result + ((getRes_key() == null) ? 0 : getRes_key().hashCode());
        result = prime * result + ((getBranch_id() == null) ? 0 : getBranch_id().hashCode());
        result = prime * result + ((getBranch_name() == null) ? 0 : getBranch_name().hashCode());
        result = prime * result + ((getStaff_id() == null) ? 0 : getStaff_id().hashCode());
        result = prime * result + ((getStall_name() == null) ? 0 : getStall_name().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getAudit_time() == null) ? 0 : getAudit_time().hashCode());
        result = prime * result + ((getAudit_status() == null) ? 0 : getAudit_status().hashCode());
        result = prime * result + ((getAudit_reason() == null) ? 0 : getAudit_reason().hashCode());
        result = prime * result + ((getReserve1() == null) ? 0 : getReserve1().hashCode());
        result = prime * result + ((getReserve2() == null) ? 0 : getReserve2().hashCode());
        result = prime * result + ((getReserve3() == null) ? 0 : getReserve3().hashCode());
        result = prime * result + ((getReserve4() == null) ? 0 : getReserve4().hashCode());
        result = prime * result + ((getReserve5() == null) ? 0 : getReserve5().hashCode());
        return result;
    }
}