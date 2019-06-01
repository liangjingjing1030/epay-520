package org.epay.dal.dao.model;

import java.io.Serializable;

public class AccountBook implements Serializable {
    /**
     * 账单ID
     *
     * @mbg.generated
     */
    private String account_book_id;

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
     * 客户唯一标识（例如学生学号，客户身份证等）
     *
     * @mbg.generated
     */
    private String user_id;

    /**
     * 客户姓名
     *
     * @mbg.generated
     */
    private String user_name;

    /**
     * 三位货币代码,人民币:cny
     *
     * @mbg.generated
     */
    private String currency;

    /**
     * 应缴金额，单位分，默认为0
     *
     * @mbg.generated
     */
    private Long items_money;

    /**
     * 支付时间
     *
     * @mbg.generated
     */
    private String pay_time;

    /**
     * 支付状态,0-未支付,1-支付成功,2-支付失败,3-账单退款
     *
     * @mbg.generated
     */
    private Byte pay_status;

    /**
     * 对账状态,0-未对账,1-对账成功,2-对账失败
     *
     * @mbg.generated
     */
    private Byte check_status;

    /**
     * 结算状态,0-未结算,1-结算成功,2-结算失败
     *
     * @mbg.generated
     */
    private Byte settle_status;

    /**
     * 支付渠道
     *
     * @mbg.generated
     */
    private String pay_channel;

    /**
     * 渠道结算费率
     *
     * @mbg.generated
     */
    private Integer channel_settle_cost;

    /**
     * 渠道结算金额，单位分，默认为0
     *
     * @mbg.generated
     */
    private Long channel_settle_money;

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
    
    //add by HappyDan ====================================Start
    
    private Integer limit;//查询页数
    private Integer offset;//每页数量

	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	
	//add by HappyDan ====================================End

    //add by GaoLiang--------------------------------------------2019年5月25日23:00:19
    private String mch_order_no;
    private String user_channel_account;
    private String channel_mch_id;
    private String channel_order_no;
    private String res_code;
    private String expire_time;

    public String getMch_order_no() {
        return mch_order_no;
    }

    public void setMch_order_no(String mch_order_no) {
        this.mch_order_no = mch_order_no;
    }

    public String getUser_channel_account() {
        return user_channel_account;
    }

    public void setUser_channel_account(String user_channel_account) {
        this.user_channel_account = user_channel_account;
    }

    public String getChannel_mch_id() {
        return channel_mch_id;
    }

    public void setChannel_mch_id(String channel_mch_id) {
        this.channel_mch_id = channel_mch_id;
    }

    public String getChannel_order_no() {
        return channel_order_no;
    }

    public void setChannel_order_no(String channel_order_no) {
        this.channel_order_no = channel_order_no;
    }

    public String getRes_code() {
        return res_code;
    }

    public void setRes_code(String res_code) {
        this.res_code = res_code;
    }

    public String getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(String expire_time) {
        this.expire_time = expire_time;
    }
    //add by GaoLiang--------------------------------------------2019年5月25日23:00:19

    private static final long serialVersionUID = 1L;

    public String getAccount_book_id() {
        return account_book_id;
    }

    public void setAccount_book_id(String account_book_id) {
        this.account_book_id = account_book_id == null ? null : account_book_id.trim();
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id == null ? null : user_id.trim();
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name == null ? null : user_name.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Long getItems_money() {
        return items_money;
    }

    public void setItems_money(Long items_money) {
        this.items_money = items_money;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time == null ? null : pay_time.trim();
    }

    public Byte getPay_status() {
        return pay_status;
    }

    public void setPay_status(Byte pay_status) {
        this.pay_status = pay_status;
    }

    public Byte getCheck_status() {
        return check_status;
    }

    public void setCheck_status(Byte check_status) {
        this.check_status = check_status;
    }

    public Byte getSettle_status() {
        return settle_status;
    }

    public void setSettle_status(Byte settle_status) {
        this.settle_status = settle_status;
    }

    public String getPay_channel() {
        return pay_channel;
    }

    public void setPay_channel(String pay_channel) {
        this.pay_channel = pay_channel == null ? null : pay_channel.trim();
    }

    public Integer getChannel_settle_cost() {
        return channel_settle_cost;
    }

    public void setChannel_settle_cost(Integer channel_settle_cost) {
        this.channel_settle_cost = channel_settle_cost;
    }

    public Long getChannel_settle_money() {
        return channel_settle_money;
    }

    public void setChannel_settle_money(Long channel_settle_money) {
        this.channel_settle_money = channel_settle_money;
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
        sb.append(", account_book_id=").append(account_book_id);
        sb.append(", mch_id=").append(mch_id);
        sb.append(", items_id=").append(items_id);
        sb.append(", user_id=").append(user_id);
        sb.append(", user_name=").append(user_name);
        sb.append(", currency=").append(currency);
        sb.append(", items_money=").append(items_money);
        sb.append(", pay_time=").append(pay_time);
        sb.append(", pay_status=").append(pay_status);
        sb.append(", check_status=").append(check_status);
        sb.append(", settle_status=").append(settle_status);
        sb.append(", pay_channel=").append(pay_channel);
        sb.append(", channel_settle_cost=").append(channel_settle_cost);
        sb.append(", channel_settle_money=").append(channel_settle_money);
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
        AccountBook other = (AccountBook) that;
        return (this.getAccount_book_id() == null ? other.getAccount_book_id() == null : this.getAccount_book_id().equals(other.getAccount_book_id()))
            && (this.getMch_id() == null ? other.getMch_id() == null : this.getMch_id().equals(other.getMch_id()))
            && (this.getItems_id() == null ? other.getItems_id() == null : this.getItems_id().equals(other.getItems_id()))
            && (this.getUser_id() == null ? other.getUser_id() == null : this.getUser_id().equals(other.getUser_id()))
            && (this.getUser_name() == null ? other.getUser_name() == null : this.getUser_name().equals(other.getUser_name()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getItems_money() == null ? other.getItems_money() == null : this.getItems_money().equals(other.getItems_money()))
            && (this.getPay_time() == null ? other.getPay_time() == null : this.getPay_time().equals(other.getPay_time()))
            && (this.getPay_status() == null ? other.getPay_status() == null : this.getPay_status().equals(other.getPay_status()))
            && (this.getCheck_status() == null ? other.getCheck_status() == null : this.getCheck_status().equals(other.getCheck_status()))
            && (this.getSettle_status() == null ? other.getSettle_status() == null : this.getSettle_status().equals(other.getSettle_status()))
            && (this.getPay_channel() == null ? other.getPay_channel() == null : this.getPay_channel().equals(other.getPay_channel()))
            && (this.getChannel_settle_cost() == null ? other.getChannel_settle_cost() == null : this.getChannel_settle_cost().equals(other.getChannel_settle_cost()))
            && (this.getChannel_settle_money() == null ? other.getChannel_settle_money() == null : this.getChannel_settle_money().equals(other.getChannel_settle_money()))
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
        result = prime * result + ((getAccount_book_id() == null) ? 0 : getAccount_book_id().hashCode());
        result = prime * result + ((getMch_id() == null) ? 0 : getMch_id().hashCode());
        result = prime * result + ((getItems_id() == null) ? 0 : getItems_id().hashCode());
        result = prime * result + ((getUser_id() == null) ? 0 : getUser_id().hashCode());
        result = prime * result + ((getUser_name() == null) ? 0 : getUser_name().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getItems_money() == null) ? 0 : getItems_money().hashCode());
        result = prime * result + ((getPay_time() == null) ? 0 : getPay_time().hashCode());
        result = prime * result + ((getPay_status() == null) ? 0 : getPay_status().hashCode());
        result = prime * result + ((getCheck_status() == null) ? 0 : getCheck_status().hashCode());
        result = prime * result + ((getSettle_status() == null) ? 0 : getSettle_status().hashCode());
        result = prime * result + ((getPay_channel() == null) ? 0 : getPay_channel().hashCode());
        result = prime * result + ((getChannel_settle_cost() == null) ? 0 : getChannel_settle_cost().hashCode());
        result = prime * result + ((getChannel_settle_money() == null) ? 0 : getChannel_settle_money().hashCode());
        result = prime * result + ((getReserve1() == null) ? 0 : getReserve1().hashCode());
        result = prime * result + ((getReserve2() == null) ? 0 : getReserve2().hashCode());
        result = prime * result + ((getReserve3() == null) ? 0 : getReserve3().hashCode());
        result = prime * result + ((getReserve4() == null) ? 0 : getReserve4().hashCode());
        result = prime * result + ((getReserve5() == null) ? 0 : getReserve5().hashCode());
        return result;
    }
}