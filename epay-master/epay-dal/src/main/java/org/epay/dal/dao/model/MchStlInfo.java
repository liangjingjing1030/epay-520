package org.epay.dal.dao.model;

import java.io.Serializable;

public class MchStlInfo implements Serializable {
    /**
     * 商户号
     *
     * @mbg.generated
     */
    private String mch_id;

    /**
     * 商户结算银行账号
     *
     * @mbg.generated
     */
    private String mch_bank_account;

    /**
     * 商户结算账户户名
     *
     * @mbg.generated
     */
    private String mch_bank_name;

    /**
     * 开户行
     *
     * @mbg.generated
     */
    private String account_bank;

    /**
     * 账户类型，0-对私，1-对公
     *
     * @mbg.generated
     */
    private String account_type;

    /**
     * 商户交易结算日(交易结算频率)T+N
     *
     * @mbg.generated
     */
    private String mch_stl_day;

    /**
     * 费率代码
     *
     * @mbg.generated
     */
    private String cost_rate;

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

    public String getMch_bank_account() {
        return mch_bank_account;
    }

    public void setMch_bank_account(String mch_bank_account) {
        this.mch_bank_account = mch_bank_account == null ? null : mch_bank_account.trim();
    }

    public String getMch_bank_name() {
        return mch_bank_name;
    }

    public void setMch_bank_name(String mch_bank_name) {
        this.mch_bank_name = mch_bank_name == null ? null : mch_bank_name.trim();
    }

    public String getAccount_bank() {
        return account_bank;
    }

    public void setAccount_bank(String account_bank) {
        this.account_bank = account_bank == null ? null : account_bank.trim();
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type == null ? null : account_type.trim();
    }

    public String getMch_stl_day() {
        return mch_stl_day;
    }

    public void setMch_stl_day(String mch_stl_day) {
        this.mch_stl_day = mch_stl_day == null ? null : mch_stl_day.trim();
    }

    public String getCost_rate() {
        return cost_rate;
    }

    public void setCost_rate(String cost_rate) {
        this.cost_rate = cost_rate == null ? null : cost_rate.trim();
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
        sb.append(", mch_bank_account=").append(mch_bank_account);
        sb.append(", mch_bank_name=").append(mch_bank_name);
        sb.append(", account_bank=").append(account_bank);
        sb.append(", account_type=").append(account_type);
        sb.append(", mch_stl_day=").append(mch_stl_day);
        sb.append(", cost_rate=").append(cost_rate);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
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
        MchStlInfo other = (MchStlInfo) that;
        return (this.getMch_id() == null ? other.getMch_id() == null : this.getMch_id().equals(other.getMch_id()))
            && (this.getMch_bank_account() == null ? other.getMch_bank_account() == null : this.getMch_bank_account().equals(other.getMch_bank_account()))
            && (this.getMch_bank_name() == null ? other.getMch_bank_name() == null : this.getMch_bank_name().equals(other.getMch_bank_name()))
            && (this.getAccount_bank() == null ? other.getAccount_bank() == null : this.getAccount_bank().equals(other.getAccount_bank()))
            && (this.getAccount_type() == null ? other.getAccount_type() == null : this.getAccount_type().equals(other.getAccount_type()))
            && (this.getMch_stl_day() == null ? other.getMch_stl_day() == null : this.getMch_stl_day().equals(other.getMch_stl_day()))
            && (this.getCost_rate() == null ? other.getCost_rate() == null : this.getCost_rate().equals(other.getCost_rate()))
            && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
            && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
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
        result = prime * result + ((getMch_bank_account() == null) ? 0 : getMch_bank_account().hashCode());
        result = prime * result + ((getMch_bank_name() == null) ? 0 : getMch_bank_name().hashCode());
        result = prime * result + ((getAccount_bank() == null) ? 0 : getAccount_bank().hashCode());
        result = prime * result + ((getAccount_type() == null) ? 0 : getAccount_type().hashCode());
        result = prime * result + ((getMch_stl_day() == null) ? 0 : getMch_stl_day().hashCode());
        result = prime * result + ((getCost_rate() == null) ? 0 : getCost_rate().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getReserve1() == null) ? 0 : getReserve1().hashCode());
        result = prime * result + ((getReserve2() == null) ? 0 : getReserve2().hashCode());
        result = prime * result + ((getReserve3() == null) ? 0 : getReserve3().hashCode());
        result = prime * result + ((getReserve4() == null) ? 0 : getReserve4().hashCode());
        result = prime * result + ((getReserve5() == null) ? 0 : getReserve5().hashCode());
        return result;
    }
}