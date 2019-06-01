package org.epay.dal.dao.model;

import java.io.Serializable;

public class AccountBookAndPayOrder implements Serializable {
    /**
     * PayOrder部分
     * 支付订单号
     */
    private String pay_order_id;
    /**
     * 商户ID
     */
    private String mch_id;
    /**
     * 账单号
     */
    private String mch_order_no;
    /**
     * 渠道ID
     */
    private String channel_id;
    /**
     * 客户唯一标识（例如学生学号，客户身份证等）
     */
    private String user_id;
    /**
     * 用户名称
     */
    private String user_name;
    /**
     * 客户渠道账号
     */
    private String user_channel_account;
    /**
     * 支付金额,单位分
     */
    private Long amount;
    /**
     * 三位货币代码,人民币:cny
     */
    private String currency;
    /**
     * 支付状态,0-订单生成,1-支付成功,2-支付失败,3-已退款,4-支付中
     */
    private Byte status;
    /**
     * 客户端IP
     */
    private String client_ip;
    /**
     * 设备
     */
    private String device;
    /**
     * 商品标题
     */
    private String subject;
    /**
     * 商品描述信息
     */
    private String body;
    /**
     * 特定渠道发起时额外参数
     */
    private String extra;
    /**
     * 渠道商户ID
     */
    private String channel_mch_id;
    /**
     * 渠道订单号
     */
    private String channel_order_no;
    /**
     * 渠道支付码
     */
    private String res_code;
    /**
     * 渠道支付描述
     */
    private String res_msg;
    /**
     * 通知地址
     */
    private String notify_url;
    /**
     * 通知次数
     */
    private String notify_count;
    /**
     * 最后一次通知时间
     */
    private String last_notify_time;
    /**
     * 订单失效时间
     */
    private String expire_time;
    /**
     * 订单支付成功时间
     */
    private String pay_succ_time;
    /**
     * 创建时间
     */
    private String create_time;
    /**
     * 更新时间
     */
    private String update_time;
    /**AccountBook部分-------------------------------------------------------------------------
     * 账单ID
     */
    private String account_book_id;
    /**
     * 项目编号
     */
    private String items_id;
    /**
     * 应缴金额，单位分，默认为0
     */
    private Long items_money;
    /**
     * 支付时间
     */
    private String pay_time;
    /**
     * 支付状态,0-未支付,1-支付成功,2-支付失败,3-账单退款
     */
    private Byte pay_status;
    /**
     * 对账状态,0-未对账,1-对账成功,2-对账失败
     */
    private Byte check_status;
    /**
     * 结算状态,0-未结算,1-结算成功,2-结算失败
     */
    private Byte settle_status;
    /**
     * 支付渠道
     */
    private String pay_channel;
    /**
     * 渠道结算费率
     */
    private Integer channel_settle_cost;
    /**
     * 渠道结算金额，单位分，默认为0
     */
    private Long channel_settle_money;

    public String getPay_order_id() {
        return pay_order_id;
    }

    public void setPay_order_id(String pay_order_id) {
        this.pay_order_id = pay_order_id;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getMch_order_no() {
        return mch_order_no;
    }

    public void setMch_order_no(String mch_order_no) {
        this.mch_order_no = mch_order_no;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_channel_account() {
        return user_channel_account;
    }

    public void setUser_channel_account(String user_channel_account) {
        this.user_channel_account = user_channel_account;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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
        this.client_ip = client_ip;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
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

    public String getRes_msg() {
        return res_msg;
    }

    public void setRes_msg(String res_msg) {
        this.res_msg = res_msg;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getNotify_count() {
        return notify_count;
    }

    public void setNotify_count(String notify_count) {
        this.notify_count = notify_count;
    }

    public String getLast_notify_time() {
        return last_notify_time;
    }

    public void setLast_notify_time(String last_notify_time) {
        this.last_notify_time = last_notify_time;
    }

    public String getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(String expire_time) {
        this.expire_time = expire_time;
    }

    public String getPay_succ_time() {
        return pay_succ_time;
    }

    public void setPay_succ_time(String pay_succ_time) {
        this.pay_succ_time = pay_succ_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getAccount_book_id() {
        return account_book_id;
    }

    public void setAccount_book_id(String account_book_id) {
        this.account_book_id = account_book_id;
    }

    public String getItems_id() {
        return items_id;
    }

    public void setItems_id(String items_id) {
        this.items_id = items_id;
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
        this.pay_time = pay_time;
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
        this.pay_channel = pay_channel;
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
}