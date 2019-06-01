package org.epay.dal.dao.model;

public class MchCheckOut {
    private String mchCheckoutId;

    private String mchId;

    private String mchName;

    private String itemsId;

    private String currency;

    private Long dealMoney;

    private Long checkoutMoney;

    private Integer checkoutRate;

    private String checkoutDate;

    private Byte settleStatus;

    private String createTime;

    private String updateTime;

    private String reserve1;

    private String reserve2;

    private String reserve3;

    private String reserve4;

    private String reserve5;

    public String getMchCheckoutId() {
        return mchCheckoutId;
    }

    public void setMchCheckoutId(String mchCheckoutId) {
        this.mchCheckoutId = mchCheckoutId == null ? null : mchCheckoutId.trim();
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId == null ? null : mchId.trim();
    }

    public String getMchName() {
        return mchName;
    }

    public void setMchName(String mchName) {
        this.mchName = mchName == null ? null : mchName.trim();
    }

    public String getItemsId() {
        return itemsId;
    }

    public void setItemsId(String itemsId) {
        this.itemsId = itemsId == null ? null : itemsId.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Long getDealMoney() {
        return dealMoney;
    }

    public void setDealMoney(Long dealMoney) {
        this.dealMoney = dealMoney;
    }

    public Long getCheckoutMoney() {
        return checkoutMoney;
    }

    public void setCheckoutMoney(Long checkoutMoney) {
        this.checkoutMoney = checkoutMoney;
    }

    public Integer getCheckoutRate() {
        return checkoutRate;
    }

    public void setCheckoutRate(Integer checkoutRate) {
        this.checkoutRate = checkoutRate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate == null ? null : checkoutDate.trim();
    }

    public Byte getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(Byte settleStatus) {
        this.settleStatus = settleStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
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
}