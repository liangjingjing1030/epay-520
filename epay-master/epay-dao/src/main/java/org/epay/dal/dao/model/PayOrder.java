package org.epay.dal.dao.model;

import java.io.Serializable;

public class PayOrder implements Serializable {
    /**
     * 支付订单号
     *
     * @mbg.generated
     */
    private String pay_order_id;

    /**
     * 商户ID
     *
     * @mbg.generated
     */
    private String mch_id;

    /**
     * 账单号
     *
     * @mbg.generated
     */
    private String mch_order_no;

    /**
     * 渠道ID
     *
     * @mbg.generated
     */
    private String channel_id;

    /**
     * 客户唯一标识（例如学生学号，客户身份证等）
     *
     * @mbg.generated
     */
    private String user_id;

    /**
     * 用户名称
     *
     * @mbg.generated
     */
    private String user_name;

    /**
     * 客户渠道账号
     *
     * @mbg.generated
     */
    private String user_channel_account;

    /**
     * 支付金额,单位分
     * update to Stirng @2019年5月22日10:41:21
     * @mbg.generated
     */
    private String amount;

    /**
     * 三位货币代码,人民币:cny
     *
     * @mbg.generated
     */
    private String currency;

    /**
     * 支付状态,0-订单生成,1-支付成功,2-支付失败,3-已退款,4-支付中
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     * 客户端IP
     *
     * @mbg.generated
     */
    private String client_ip;

    /**
     * 设备
     *
     * @mbg.generated
     */
    private String device;

    /**
     * 商品标题
     *
     * @mbg.generated
     */
    private String subject;

    /**
     * 商品描述信息
     *
     * @mbg.generated
     */
    private String body;

    /**
     * 特定渠道发起时额外参数
     *
     * @mbg.generated
     */
    private String extra;

    /**
     * 渠道商户ID
     *
     * @mbg.generated
     */
    private String channel_mch_id;

    /**
     * 渠道订单号
     *
     * @mbg.generated
     */
    private String channel_order_no;

    /**
     * 渠道支付码
     *
     * @mbg.generated
     */
    private String res_code;

    /**
     * 渠道支付描述
     *
     * @mbg.generated
     */
    private String res_msg;

    /**
     * 扩展参数1
     *
     * @mbg.generated
     */
    private String param1;

    /**
     * 扩展参数2
     *
     * @mbg.generated
     */
    private String param2;

    /**
     * 通知地址
     *
     * @mbg.generated
     */
    private String notify_url;

    /**
     * 通知次数
     *
     * @mbg.generated
     */
    private String notify_count;

    /**
     * 最后一次通知时间
     *
     * @mbg.generated
     */
    private String last_notify_time;

    /**
     * 订单失效时间
     *
     * @mbg.generated
     */
    private String expire_time;

    /**
     * 订单支付成功时间
     *
     * @mbg.generated
     */
    private String pay_succ_time;

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
    
    //add by HappyDan ====================================Start
    
    private String tradeType;//交易类型
    private String start_time;//开始时间
    private String end_time;//结束时间
    private Integer limit;//查询页数
    private Integer offset;//每页数量
    
    public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	
    public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

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

	private static final long serialVersionUID = 1L;

    public String getPay_order_id() {
        return pay_order_id;
    }

    public void setPay_order_id(String pay_order_id) {
        this.pay_order_id = pay_order_id == null ? null : pay_order_id.trim();
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id == null ? null : mch_id.trim();
    }

    public String getMch_order_no() {
        return mch_order_no;
    }

    public void setMch_order_no(String mch_order_no) {
        this.mch_order_no = mch_order_no == null ? null : mch_order_no.trim();
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id == null ? null : channel_id.trim();
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

    public String getUser_channel_account() {
        return user_channel_account;
    }

    public void setUser_channel_account(String user_channel_account) {
        this.user_channel_account = user_channel_account == null ? null : user_channel_account.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip == null ? null : client_ip.trim();
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device == null ? null : device.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra == null ? null : extra.trim();
    }

    public String getChannel_mch_id() {
        return channel_mch_id;
    }

    public void setChannel_mch_id(String channel_mch_id) {
        this.channel_mch_id = channel_mch_id == null ? null : channel_mch_id.trim();
    }

    public String getChannel_order_no() {
        return channel_order_no;
    }

    public void setChannel_order_no(String channel_order_no) {
        this.channel_order_no = channel_order_no == null ? null : channel_order_no.trim();
    }

    public String getRes_code() {
        return res_code;
    }

    public void setRes_code(String res_code) {
        this.res_code = res_code == null ? null : res_code.trim();
    }

    public String getRes_msg() {
        return res_msg;
    }

    public void setRes_msg(String res_msg) {
        this.res_msg = res_msg == null ? null : res_msg.trim();
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1 == null ? null : param1.trim();
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2 == null ? null : param2.trim();
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url == null ? null : notify_url.trim();
    }

    public String getNotify_count() {
        return notify_count;
    }

    public void setNotify_count(String notify_count) {
        this.notify_count = notify_count == null ? null : notify_count.trim();
    }

    public String getLast_notify_time() {
        return last_notify_time;
    }

    public void setLast_notify_time(String last_notify_time) {
        this.last_notify_time = last_notify_time == null ? null : last_notify_time.trim();
    }

    public String getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(String expire_time) {
        this.expire_time = expire_time == null ? null : expire_time.trim();
    }

    public String getPay_succ_time() {
        return pay_succ_time;
    }

    public void setPay_succ_time(String pay_succ_time) {
        this.pay_succ_time = pay_succ_time == null ? null : pay_succ_time.trim();
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
        sb.append(", pay_order_id=").append(pay_order_id);
        sb.append(", mch_id=").append(mch_id);
        sb.append(", mch_order_no=").append(mch_order_no);
        sb.append(", channel_id=").append(channel_id);
        sb.append(", user_id=").append(user_id);
        sb.append(", user_name=").append(user_name);
        sb.append(", user_channel_account=").append(user_channel_account);
        sb.append(", amount=").append(amount);
        sb.append(", currency=").append(currency);
        sb.append(", status=").append(status);
        sb.append(", client_ip=").append(client_ip);
        sb.append(", device=").append(device);
        sb.append(", subject=").append(subject);
        sb.append(", body=").append(body);
        sb.append(", extra=").append(extra);
        sb.append(", channel_mch_id=").append(channel_mch_id);
        sb.append(", channel_order_no=").append(channel_order_no);
        sb.append(", res_code=").append(res_code);
        sb.append(", res_msg=").append(res_msg);
        sb.append(", param1=").append(param1);
        sb.append(", param2=").append(param2);
        sb.append(", notify_url=").append(notify_url);
        sb.append(", notify_count=").append(notify_count);
        sb.append(", last_notify_time=").append(last_notify_time);
        sb.append(", expire_time=").append(expire_time);
        sb.append(", pay_succ_time=").append(pay_succ_time);
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
        PayOrder other = (PayOrder) that;
        return (this.getPay_order_id() == null ? other.getPay_order_id() == null : this.getPay_order_id().equals(other.getPay_order_id()))
            && (this.getMch_id() == null ? other.getMch_id() == null : this.getMch_id().equals(other.getMch_id()))
            && (this.getMch_order_no() == null ? other.getMch_order_no() == null : this.getMch_order_no().equals(other.getMch_order_no()))
            && (this.getChannel_id() == null ? other.getChannel_id() == null : this.getChannel_id().equals(other.getChannel_id()))
            && (this.getUser_id() == null ? other.getUser_id() == null : this.getUser_id().equals(other.getUser_id()))
            && (this.getUser_name() == null ? other.getUser_name() == null : this.getUser_name().equals(other.getUser_name()))
            && (this.getUser_channel_account() == null ? other.getUser_channel_account() == null : this.getUser_channel_account().equals(other.getUser_channel_account()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getClient_ip() == null ? other.getClient_ip() == null : this.getClient_ip().equals(other.getClient_ip()))
            && (this.getDevice() == null ? other.getDevice() == null : this.getDevice().equals(other.getDevice()))
            && (this.getSubject() == null ? other.getSubject() == null : this.getSubject().equals(other.getSubject()))
            && (this.getBody() == null ? other.getBody() == null : this.getBody().equals(other.getBody()))
            && (this.getExtra() == null ? other.getExtra() == null : this.getExtra().equals(other.getExtra()))
            && (this.getChannel_mch_id() == null ? other.getChannel_mch_id() == null : this.getChannel_mch_id().equals(other.getChannel_mch_id()))
            && (this.getChannel_order_no() == null ? other.getChannel_order_no() == null : this.getChannel_order_no().equals(other.getChannel_order_no()))
            && (this.getRes_code() == null ? other.getRes_code() == null : this.getRes_code().equals(other.getRes_code()))
            && (this.getRes_msg() == null ? other.getRes_msg() == null : this.getRes_msg().equals(other.getRes_msg()))
            && (this.getParam1() == null ? other.getParam1() == null : this.getParam1().equals(other.getParam1()))
            && (this.getParam2() == null ? other.getParam2() == null : this.getParam2().equals(other.getParam2()))
            && (this.getNotify_url() == null ? other.getNotify_url() == null : this.getNotify_url().equals(other.getNotify_url()))
            && (this.getNotify_count() == null ? other.getNotify_count() == null : this.getNotify_count().equals(other.getNotify_count()))
            && (this.getLast_notify_time() == null ? other.getLast_notify_time() == null : this.getLast_notify_time().equals(other.getLast_notify_time()))
            && (this.getExpire_time() == null ? other.getExpire_time() == null : this.getExpire_time().equals(other.getExpire_time()))
            && (this.getPay_succ_time() == null ? other.getPay_succ_time() == null : this.getPay_succ_time().equals(other.getPay_succ_time()))
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
        result = prime * result + ((getPay_order_id() == null) ? 0 : getPay_order_id().hashCode());
        result = prime * result + ((getMch_id() == null) ? 0 : getMch_id().hashCode());
        result = prime * result + ((getMch_order_no() == null) ? 0 : getMch_order_no().hashCode());
        result = prime * result + ((getChannel_id() == null) ? 0 : getChannel_id().hashCode());
        result = prime * result + ((getUser_id() == null) ? 0 : getUser_id().hashCode());
        result = prime * result + ((getUser_name() == null) ? 0 : getUser_name().hashCode());
        result = prime * result + ((getUser_channel_account() == null) ? 0 : getUser_channel_account().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getClient_ip() == null) ? 0 : getClient_ip().hashCode());
        result = prime * result + ((getDevice() == null) ? 0 : getDevice().hashCode());
        result = prime * result + ((getSubject() == null) ? 0 : getSubject().hashCode());
        result = prime * result + ((getBody() == null) ? 0 : getBody().hashCode());
        result = prime * result + ((getExtra() == null) ? 0 : getExtra().hashCode());
        result = prime * result + ((getChannel_mch_id() == null) ? 0 : getChannel_mch_id().hashCode());
        result = prime * result + ((getChannel_order_no() == null) ? 0 : getChannel_order_no().hashCode());
        result = prime * result + ((getRes_code() == null) ? 0 : getRes_code().hashCode());
        result = prime * result + ((getRes_msg() == null) ? 0 : getRes_msg().hashCode());
        result = prime * result + ((getParam1() == null) ? 0 : getParam1().hashCode());
        result = prime * result + ((getParam2() == null) ? 0 : getParam2().hashCode());
        result = prime * result + ((getNotify_url() == null) ? 0 : getNotify_url().hashCode());
        result = prime * result + ((getNotify_count() == null) ? 0 : getNotify_count().hashCode());
        result = prime * result + ((getLast_notify_time() == null) ? 0 : getLast_notify_time().hashCode());
        result = prime * result + ((getExpire_time() == null) ? 0 : getExpire_time().hashCode());
        result = prime * result + ((getPay_succ_time() == null) ? 0 : getPay_succ_time().hashCode());
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