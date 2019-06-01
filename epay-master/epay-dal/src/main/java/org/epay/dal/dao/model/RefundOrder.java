package org.epay.dal.dao.model;

import java.io.Serializable;

public class RefundOrder implements Serializable {
    /**
     * 退款订单号
     *
     * @mbg.generated
     */
    private String refund_order_id;

    /**
     * 支付订单号
     *
     * @mbg.generated
     */
    private String pay_orderid;

    /**
     * 商户ID
     *
     * @mbg.generated
     */
    private String mch_id;

    /**
     * 渠道ID
     *
     * @mbg.generated
     */
    private String channel_id;

    /**
     * 账单号
     *
     * @mbg.generated
     */
    private String mch_refund_no;

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
     * 客户渠道账单
     *
     * @mbg.generated
     */
    private String user_channel_account;

    /**
     * 支付金额,单位分
     *
     * @mbg.generated
     */
    private Long pay_amount;

    /**
     * 退款金额,单位分
     *
     * @mbg.generated
     */
    private Long refund_amount;

    /**
     * 三位货币代码,人民币:cny
     *
     * @mbg.generated
     */
    private String currency;

    /**
     * 退款状态:0-订单生成,1-退款中,2-退款成功,3-退款失败
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     * 渠道支付单号
     *
     * @mbg.generated
     */
    private String channel_pay_order_no;

    /**
     * 退款结果:0-不确认结果,1-等待手动处理,2-确认成功,3-确认失败
     *
     * @mbg.generated
     */
    private Byte result;

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
     * 备注
     *
     * @mbg.generated
     */
    private String remark_info;

    /**
     * 渠道用户标识,如微信openId,支付宝账号
     *
     * @mbg.generated
     */
    private String channel_user;

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
     * 渠道响应码
     *
     * @mbg.generated
     */
    private String channel_res_code;

    /**
     * 渠道相应描述
     *
     * @mbg.generated
     */
    private String channel_res_msg;

    /**
     * 特定渠道发起时额外参数
     *
     * @mbg.generated
     */
    private String extra;

    /**
     * 通知地址
     *
     * @mbg.generated
     */
    private String notifyurl;

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
     * 订单失效时间
     *
     * @mbg.generated
     */
    private String expire_time;

    /**
     * 订单退款成功时间
     *
     * @mbg.generated
     */
    private String refund_succ_time;

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
    
    private String start_time;//开始时间
    private String end_time;//结束时间
    private Integer limit;//查询页数
    private Integer offset;//每页数量
    
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

    public String getRefund_order_id() {
        return refund_order_id;
    }

    public void setRefund_order_id(String refund_order_id) {
        this.refund_order_id = refund_order_id == null ? null : refund_order_id.trim();
    }

    public String getPay_orderid() {
        return pay_orderid;
    }

    public void setPay_orderid(String pay_orderid) {
        this.pay_orderid = pay_orderid == null ? null : pay_orderid.trim();
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id == null ? null : mch_id.trim();
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id == null ? null : channel_id.trim();
    }

    public String getMch_refund_no() {
        return mch_refund_no;
    }

    public void setMch_refund_no(String mch_refund_no) {
        this.mch_refund_no = mch_refund_no == null ? null : mch_refund_no.trim();
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

    public Long getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(Long pay_amount) {
        this.pay_amount = pay_amount;
    }

    public Long getRefund_amount() {
        return refund_amount;
    }

    public void setRefund_amount(Long refund_amount) {
        this.refund_amount = refund_amount;
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

    public String getChannel_pay_order_no() {
        return channel_pay_order_no;
    }

    public void setChannel_pay_order_no(String channel_pay_order_no) {
        this.channel_pay_order_no = channel_pay_order_no == null ? null : channel_pay_order_no.trim();
    }

    public Byte getResult() {
        return result;
    }

    public void setResult(Byte result) {
        this.result = result;
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

    public String getRemark_info() {
        return remark_info;
    }

    public void setRemark_info(String remark_info) {
        this.remark_info = remark_info == null ? null : remark_info.trim();
    }

    public String getChannel_user() {
        return channel_user;
    }

    public void setChannel_user(String channel_user) {
        this.channel_user = channel_user == null ? null : channel_user.trim();
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

    public String getChannel_res_code() {
        return channel_res_code;
    }

    public void setChannel_res_code(String channel_res_code) {
        this.channel_res_code = channel_res_code == null ? null : channel_res_code.trim();
    }

    public String getChannel_res_msg() {
        return channel_res_msg;
    }

    public void setChannel_res_msg(String channel_res_msg) {
        this.channel_res_msg = channel_res_msg == null ? null : channel_res_msg.trim();
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra == null ? null : extra.trim();
    }

    public String getNotifyurl() {
        return notifyurl;
    }

    public void setNotifyurl(String notifyurl) {
        this.notifyurl = notifyurl == null ? null : notifyurl.trim();
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

    public String getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(String expire_time) {
        this.expire_time = expire_time == null ? null : expire_time.trim();
    }

    public String getRefund_succ_time() {
        return refund_succ_time;
    }

    public void setRefund_succ_time(String refund_succ_time) {
        this.refund_succ_time = refund_succ_time == null ? null : refund_succ_time.trim();
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
        sb.append(", refund_order_id=").append(refund_order_id);
        sb.append(", pay_orderid=").append(pay_orderid);
        sb.append(", mch_id=").append(mch_id);
        sb.append(", channel_id=").append(channel_id);
        sb.append(", mch_refund_no=").append(mch_refund_no);
        sb.append(", user_id=").append(user_id);
        sb.append(", user_name=").append(user_name);
        sb.append(", user_channel_account=").append(user_channel_account);
        sb.append(", pay_amount=").append(pay_amount);
        sb.append(", refund_amount=").append(refund_amount);
        sb.append(", currency=").append(currency);
        sb.append(", status=").append(status);
        sb.append(", channel_pay_order_no=").append(channel_pay_order_no);
        sb.append(", result=").append(result);
        sb.append(", client_ip=").append(client_ip);
        sb.append(", device=").append(device);
        sb.append(", remark_info=").append(remark_info);
        sb.append(", channel_user=").append(channel_user);
        sb.append(", channel_mch_id=").append(channel_mch_id);
        sb.append(", channel_order_no=").append(channel_order_no);
        sb.append(", channel_res_code=").append(channel_res_code);
        sb.append(", channel_res_msg=").append(channel_res_msg);
        sb.append(", extra=").append(extra);
        sb.append(", notifyurl=").append(notifyurl);
        sb.append(", param1=").append(param1);
        sb.append(", param2=").append(param2);
        sb.append(", expire_time=").append(expire_time);
        sb.append(", refund_succ_time=").append(refund_succ_time);
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
        RefundOrder other = (RefundOrder) that;
        return (this.getRefund_order_id() == null ? other.getRefund_order_id() == null : this.getRefund_order_id().equals(other.getRefund_order_id()))
            && (this.getPay_orderid() == null ? other.getPay_orderid() == null : this.getPay_orderid().equals(other.getPay_orderid()))
            && (this.getMch_id() == null ? other.getMch_id() == null : this.getMch_id().equals(other.getMch_id()))
            && (this.getChannel_id() == null ? other.getChannel_id() == null : this.getChannel_id().equals(other.getChannel_id()))
            && (this.getMch_refund_no() == null ? other.getMch_refund_no() == null : this.getMch_refund_no().equals(other.getMch_refund_no()))
            && (this.getUser_id() == null ? other.getUser_id() == null : this.getUser_id().equals(other.getUser_id()))
            && (this.getUser_name() == null ? other.getUser_name() == null : this.getUser_name().equals(other.getUser_name()))
            && (this.getUser_channel_account() == null ? other.getUser_channel_account() == null : this.getUser_channel_account().equals(other.getUser_channel_account()))
            && (this.getPay_amount() == null ? other.getPay_amount() == null : this.getPay_amount().equals(other.getPay_amount()))
            && (this.getRefund_amount() == null ? other.getRefund_amount() == null : this.getRefund_amount().equals(other.getRefund_amount()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getChannel_pay_order_no() == null ? other.getChannel_pay_order_no() == null : this.getChannel_pay_order_no().equals(other.getChannel_pay_order_no()))
            && (this.getResult() == null ? other.getResult() == null : this.getResult().equals(other.getResult()))
            && (this.getClient_ip() == null ? other.getClient_ip() == null : this.getClient_ip().equals(other.getClient_ip()))
            && (this.getDevice() == null ? other.getDevice() == null : this.getDevice().equals(other.getDevice()))
            && (this.getRemark_info() == null ? other.getRemark_info() == null : this.getRemark_info().equals(other.getRemark_info()))
            && (this.getChannel_user() == null ? other.getChannel_user() == null : this.getChannel_user().equals(other.getChannel_user()))
            && (this.getChannel_mch_id() == null ? other.getChannel_mch_id() == null : this.getChannel_mch_id().equals(other.getChannel_mch_id()))
            && (this.getChannel_order_no() == null ? other.getChannel_order_no() == null : this.getChannel_order_no().equals(other.getChannel_order_no()))
            && (this.getChannel_res_code() == null ? other.getChannel_res_code() == null : this.getChannel_res_code().equals(other.getChannel_res_code()))
            && (this.getChannel_res_msg() == null ? other.getChannel_res_msg() == null : this.getChannel_res_msg().equals(other.getChannel_res_msg()))
            && (this.getExtra() == null ? other.getExtra() == null : this.getExtra().equals(other.getExtra()))
            && (this.getNotifyurl() == null ? other.getNotifyurl() == null : this.getNotifyurl().equals(other.getNotifyurl()))
            && (this.getParam1() == null ? other.getParam1() == null : this.getParam1().equals(other.getParam1()))
            && (this.getParam2() == null ? other.getParam2() == null : this.getParam2().equals(other.getParam2()))
            && (this.getExpire_time() == null ? other.getExpire_time() == null : this.getExpire_time().equals(other.getExpire_time()))
            && (this.getRefund_succ_time() == null ? other.getRefund_succ_time() == null : this.getRefund_succ_time().equals(other.getRefund_succ_time()))
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
        result = prime * result + ((getRefund_order_id() == null) ? 0 : getRefund_order_id().hashCode());
        result = prime * result + ((getPay_orderid() == null) ? 0 : getPay_orderid().hashCode());
        result = prime * result + ((getMch_id() == null) ? 0 : getMch_id().hashCode());
        result = prime * result + ((getChannel_id() == null) ? 0 : getChannel_id().hashCode());
        result = prime * result + ((getMch_refund_no() == null) ? 0 : getMch_refund_no().hashCode());
        result = prime * result + ((getUser_id() == null) ? 0 : getUser_id().hashCode());
        result = prime * result + ((getUser_name() == null) ? 0 : getUser_name().hashCode());
        result = prime * result + ((getUser_channel_account() == null) ? 0 : getUser_channel_account().hashCode());
        result = prime * result + ((getPay_amount() == null) ? 0 : getPay_amount().hashCode());
        result = prime * result + ((getRefund_amount() == null) ? 0 : getRefund_amount().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getChannel_pay_order_no() == null) ? 0 : getChannel_pay_order_no().hashCode());
        result = prime * result + ((getResult() == null) ? 0 : getResult().hashCode());
        result = prime * result + ((getClient_ip() == null) ? 0 : getClient_ip().hashCode());
        result = prime * result + ((getDevice() == null) ? 0 : getDevice().hashCode());
        result = prime * result + ((getRemark_info() == null) ? 0 : getRemark_info().hashCode());
        result = prime * result + ((getChannel_user() == null) ? 0 : getChannel_user().hashCode());
        result = prime * result + ((getChannel_mch_id() == null) ? 0 : getChannel_mch_id().hashCode());
        result = prime * result + ((getChannel_order_no() == null) ? 0 : getChannel_order_no().hashCode());
        result = prime * result + ((getChannel_res_code() == null) ? 0 : getChannel_res_code().hashCode());
        result = prime * result + ((getChannel_res_msg() == null) ? 0 : getChannel_res_msg().hashCode());
        result = prime * result + ((getExtra() == null) ? 0 : getExtra().hashCode());
        result = prime * result + ((getNotifyurl() == null) ? 0 : getNotifyurl().hashCode());
        result = prime * result + ((getParam1() == null) ? 0 : getParam1().hashCode());
        result = prime * result + ((getParam2() == null) ? 0 : getParam2().hashCode());
        result = prime * result + ((getExpire_time() == null) ? 0 : getExpire_time().hashCode());
        result = prime * result + ((getRefund_succ_time() == null) ? 0 : getRefund_succ_time().hashCode());
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