package org.epay.dal.dao.model;

import java.io.Serializable;

public class BankTariff implements Serializable {
    /**
     * 结算编号
     *
     * @mbg.generated
     */
    private String bank_tariff_id;

    /**
     * 商户号
     *
     * @mbg.generated
     */
    private String mch_id;

    /**
     * 商户名称
     *
     * @mbg.generated
     */
    private String mch_name;

    /**
     * 项目编号
     *
     * @mbg.generated
     */
    private String items_id;

    /**
     * 三位货币代码,人民币:cny
     *
     * @mbg.generated
     */
    private String currency;

    /**
     * 交易金额，单位分，默认为0
     *
     * @mbg.generated
     */
    private Long deal_money;

    /**
     * 第三方结算金额，单位分，默认为0
     *
     * @mbg.generated
     */
    private Long third_party_deal_money;

    /**
     * 第三方结算费率
     *
     * @mbg.generated
     */
    private Integer third_party_checkout_rate;

    /**
     * 结算费率
     *
     * @mbg.generated
     */
    private Integer checkout_rate;

    /**
     * 手续费，单位分，默认为0
     *
     * @mbg.generated
     */
    private Long tariff_money;

    /**
     * 结算日期
     *
     * @mbg.generated
     */
    private String checkout_date;

    /**
     * 结算状态,0-未结算,1-结算成功,2-结算失败
     *
     * @mbg.generated
     */
    private Byte settle_status;

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

    public String getBank_tariff_id() {
        return bank_tariff_id;
    }

    public void setBank_tariff_id(String bank_tariff_id) {
        this.bank_tariff_id = bank_tariff_id == null ? null : bank_tariff_id.trim();
    }

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

    public String getItems_id() {
        return items_id;
    }

    public void setItems_id(String items_id) {
        this.items_id = items_id == null ? null : items_id.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Long getDeal_money() {
        return deal_money;
    }

    public void setDeal_money(Long deal_money) {
        this.deal_money = deal_money;
    }

    public Long getThird_party_deal_money() {
        return third_party_deal_money;
    }

    public void setThird_party_deal_money(Long third_party_deal_money) {
        this.third_party_deal_money = third_party_deal_money;
    }

    public Integer getThird_party_checkout_rate() {
        return third_party_checkout_rate;
    }

    public void setThird_party_checkout_rate(Integer third_party_checkout_rate) {
        this.third_party_checkout_rate = third_party_checkout_rate;
    }

    public Integer getCheckout_rate() {
        return checkout_rate;
    }

    public void setCheckout_rate(Integer checkout_rate) {
        this.checkout_rate = checkout_rate;
    }

    public Long getTariff_money() {
        return tariff_money;
    }

    public void setTariff_money(Long tariff_money) {
        this.tariff_money = tariff_money;
    }

    public String getCheckout_date() {
        return checkout_date;
    }

    public void setCheckout_date(String checkout_date) {
        this.checkout_date = checkout_date == null ? null : checkout_date.trim();
    }

    public Byte getSettle_status() {
        return settle_status;
    }

    public void setSettle_status(Byte settle_status) {
        this.settle_status = settle_status;
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
        sb.append(", bank_tariff_id=").append(bank_tariff_id);
        sb.append(", mch_id=").append(mch_id);
        sb.append(", mch_name=").append(mch_name);
        sb.append(", items_id=").append(items_id);
        sb.append(", currency=").append(currency);
        sb.append(", deal_money=").append(deal_money);
        sb.append(", third_party_deal_money=").append(third_party_deal_money);
        sb.append(", third_party_checkout_rate=").append(third_party_checkout_rate);
        sb.append(", checkout_rate=").append(checkout_rate);
        sb.append(", tariff_money=").append(tariff_money);
        sb.append(", checkout_date=").append(checkout_date);
        sb.append(", settle_status=").append(settle_status);
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
        BankTariff other = (BankTariff) that;
        return (this.getBank_tariff_id() == null ? other.getBank_tariff_id() == null : this.getBank_tariff_id().equals(other.getBank_tariff_id()))
            && (this.getMch_id() == null ? other.getMch_id() == null : this.getMch_id().equals(other.getMch_id()))
            && (this.getMch_name() == null ? other.getMch_name() == null : this.getMch_name().equals(other.getMch_name()))
            && (this.getItems_id() == null ? other.getItems_id() == null : this.getItems_id().equals(other.getItems_id()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getDeal_money() == null ? other.getDeal_money() == null : this.getDeal_money().equals(other.getDeal_money()))
            && (this.getThird_party_deal_money() == null ? other.getThird_party_deal_money() == null : this.getThird_party_deal_money().equals(other.getThird_party_deal_money()))
            && (this.getThird_party_checkout_rate() == null ? other.getThird_party_checkout_rate() == null : this.getThird_party_checkout_rate().equals(other.getThird_party_checkout_rate()))
            && (this.getCheckout_rate() == null ? other.getCheckout_rate() == null : this.getCheckout_rate().equals(other.getCheckout_rate()))
            && (this.getTariff_money() == null ? other.getTariff_money() == null : this.getTariff_money().equals(other.getTariff_money()))
            && (this.getCheckout_date() == null ? other.getCheckout_date() == null : this.getCheckout_date().equals(other.getCheckout_date()))
            && (this.getSettle_status() == null ? other.getSettle_status() == null : this.getSettle_status().equals(other.getSettle_status()))
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
        result = prime * result + ((getBank_tariff_id() == null) ? 0 : getBank_tariff_id().hashCode());
        result = prime * result + ((getMch_id() == null) ? 0 : getMch_id().hashCode());
        result = prime * result + ((getMch_name() == null) ? 0 : getMch_name().hashCode());
        result = prime * result + ((getItems_id() == null) ? 0 : getItems_id().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getDeal_money() == null) ? 0 : getDeal_money().hashCode());
        result = prime * result + ((getThird_party_deal_money() == null) ? 0 : getThird_party_deal_money().hashCode());
        result = prime * result + ((getThird_party_checkout_rate() == null) ? 0 : getThird_party_checkout_rate().hashCode());
        result = prime * result + ((getCheckout_rate() == null) ? 0 : getCheckout_rate().hashCode());
        result = prime * result + ((getTariff_money() == null) ? 0 : getTariff_money().hashCode());
        result = prime * result + ((getCheckout_date() == null) ? 0 : getCheckout_date().hashCode());
        result = prime * result + ((getSettle_status() == null) ? 0 : getSettle_status().hashCode());
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