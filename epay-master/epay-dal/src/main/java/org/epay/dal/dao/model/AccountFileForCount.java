package org.epay.dal.dao.model;

import java.io.Serializable;

public class AccountFileForCount implements Serializable {
    private String totalMoney;
    private String readMoney;
    private String backMoney;
    private String daiMoney;
    private String perent;
    /**
     * 文件ID
     *
     * @mbg.generated
     */
    private String file_id;

    /**
     * 商户号
     *
     * @mbg.generated
     */
    private String mch_id;

    /**
     * 项目编号
     *
     * @mbg.generated
     */
    private String items_id;

    /**
     * 项目名称
     *
     * @mbg.generated
     */
    private String items_name;

    /**
     * 项目类别
     *
     * @mbg.generated
     */
    private String items_type;

    /**
     * 项目文件名称
     *
     * @mbg.generated
     */
    private String items_filename;

    /**
     * 文件上传日期
     *
     * @mbg.generated
     */
    private String upload_date;

    /**
     * 账单生效时间
     *
     * @mbg.generated
     */
    private String affect_date;

    /**
     * 账单失效时间
     *
     * @mbg.generated
     */
    private String expiry_date;

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

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getReadMoney() {
        return readMoney;
    }

    public void setReadMoney(String readMoney) {
        this.readMoney = readMoney;
    }

    public String getBackMoney() {
        return backMoney;
    }

    public void setBackMoney(String backMoney) {
        this.backMoney = backMoney;
    }

    public String getDaiMoney() {
        return daiMoney;
    }

    public void setDaiMoney(String daiMoney) {
        this.daiMoney = daiMoney;
    }

    public String getPerent() {
        return perent;
    }

    public void setPerent(String perent) {
        this.perent = perent;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id == null ? null : file_id.trim();
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id == null ? null : mch_id.trim();
    }

    public String getItems_id() {
        return items_id;
    }

    public void setItems_id(String items_id) {
        this.items_id = items_id == null ? null : items_id.trim();
    }

    public String getItems_name() {
        return items_name;
    }

    public void setItems_name(String items_name) {
        this.items_name = items_name == null ? null : items_name.trim();
    }

    public String getItems_type() {
        return items_type;
    }

    public void setItems_type(String items_type) {
        this.items_type = items_type == null ? null : items_type.trim();
    }

    public String getItems_filename() {
        return items_filename;
    }

    public void setItems_filename(String items_filename) {
        this.items_filename = items_filename == null ? null : items_filename.trim();
    }

    public String getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(String upload_date) {
        this.upload_date = upload_date == null ? null : upload_date.trim();
    }

    public String getAffect_date() {
        return affect_date;
    }

    public void setAffect_date(String affect_date) {
        this.affect_date = affect_date == null ? null : affect_date.trim();
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date == null ? null : expiry_date.trim();
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
        sb.append(", file_id=").append(file_id);
        sb.append(", mch_id=").append(mch_id);
        sb.append(", items_id=").append(items_id);
        sb.append(", items_name=").append(items_name);
        sb.append(", items_type=").append(items_type);
        sb.append(", items_filename=").append(items_filename);
        sb.append(", upload_date=").append(upload_date);
        sb.append(", affect_date=").append(affect_date);
        sb.append(", expiry_date=").append(expiry_date);
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
        AccountFileForCount other = (AccountFileForCount) that;
        return (this.getFile_id() == null ? other.getFile_id() == null : this.getFile_id().equals(other.getFile_id()))
            && (this.getMch_id() == null ? other.getMch_id() == null : this.getMch_id().equals(other.getMch_id()))
            && (this.getItems_id() == null ? other.getItems_id() == null : this.getItems_id().equals(other.getItems_id()))
            && (this.getItems_name() == null ? other.getItems_name() == null : this.getItems_name().equals(other.getItems_name()))
            && (this.getItems_type() == null ? other.getItems_type() == null : this.getItems_type().equals(other.getItems_type()))
            && (this.getItems_filename() == null ? other.getItems_filename() == null : this.getItems_filename().equals(other.getItems_filename()))
            && (this.getUpload_date() == null ? other.getUpload_date() == null : this.getUpload_date().equals(other.getUpload_date()))
            && (this.getAffect_date() == null ? other.getAffect_date() == null : this.getAffect_date().equals(other.getAffect_date()))
            && (this.getExpiry_date() == null ? other.getExpiry_date() == null : this.getExpiry_date().equals(other.getExpiry_date()))
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
        result = prime * result + ((getFile_id() == null) ? 0 : getFile_id().hashCode());
        result = prime * result + ((getMch_id() == null) ? 0 : getMch_id().hashCode());
        result = prime * result + ((getItems_id() == null) ? 0 : getItems_id().hashCode());
        result = prime * result + ((getItems_name() == null) ? 0 : getItems_name().hashCode());
        result = prime * result + ((getItems_type() == null) ? 0 : getItems_type().hashCode());
        result = prime * result + ((getItems_filename() == null) ? 0 : getItems_filename().hashCode());
        result = prime * result + ((getUpload_date() == null) ? 0 : getUpload_date().hashCode());
        result = prime * result + ((getAffect_date() == null) ? 0 : getAffect_date().hashCode());
        result = prime * result + ((getExpiry_date() == null) ? 0 : getExpiry_date().hashCode());
        result = prime * result + ((getReserve1() == null) ? 0 : getReserve1().hashCode());
        result = prime * result + ((getReserve2() == null) ? 0 : getReserve2().hashCode());
        result = prime * result + ((getReserve3() == null) ? 0 : getReserve3().hashCode());
        result = prime * result + ((getReserve4() == null) ? 0 : getReserve4().hashCode());
        result = prime * result + ((getReserve5() == null) ? 0 : getReserve5().hashCode());
        return result;
    }
}