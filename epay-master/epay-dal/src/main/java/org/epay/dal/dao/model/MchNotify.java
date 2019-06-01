package org.epay.dal.dao.model;

import java.io.Serializable;

public class MchNotify implements Serializable {
    /**
     * 订单ID
     *
     * @mbg.generated
     */
    private String order_id;

    /**
     * 商户ID
     *
     * @mbg.generated
     */
    private String mch_id;

    /**
     * 商户订单号
     *
     * @mbg.generated
     */
    private String mch_order_no;

    /**
     * 订单类型:1-支付,2-转账,3-退款
     *
     * @mbg.generated
     */
    private String order_type;

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
    private Byte notify_count;

    /**
     * 通知响应结果
     *
     * @mbg.generated
     */
    private String result;

    /**
     * 通知状态,1-通知中,2-通知成功,3-通知失败
     *
     * @mbg.generated
     */
    private Byte rtatus;

    /**
     * 最后一次通知时间
     *
     * @mbg.generated
     */
    private String last_notify_time;

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

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id == null ? null : order_id.trim();
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

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type == null ? null : order_type.trim();
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url == null ? null : notify_url.trim();
    }

    public Byte getNotify_count() {
        return notify_count;
    }

    public void setNotify_count(Byte notify_count) {
        this.notify_count = notify_count;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public Byte getRtatus() {
        return rtatus;
    }

    public void setRtatus(Byte rtatus) {
        this.rtatus = rtatus;
    }

    public String getLast_notify_time() {
        return last_notify_time;
    }

    public void setLast_notify_time(String last_notify_time) {
        this.last_notify_time = last_notify_time == null ? null : last_notify_time.trim();
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
        sb.append(", order_id=").append(order_id);
        sb.append(", mch_id=").append(mch_id);
        sb.append(", mch_order_no=").append(mch_order_no);
        sb.append(", order_type=").append(order_type);
        sb.append(", notify_url=").append(notify_url);
        sb.append(", notify_count=").append(notify_count);
        sb.append(", result=").append(result);
        sb.append(", rtatus=").append(rtatus);
        sb.append(", last_notify_time=").append(last_notify_time);
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
        MchNotify other = (MchNotify) that;
        return (this.getOrder_id() == null ? other.getOrder_id() == null : this.getOrder_id().equals(other.getOrder_id()))
            && (this.getMch_id() == null ? other.getMch_id() == null : this.getMch_id().equals(other.getMch_id()))
            && (this.getMch_order_no() == null ? other.getMch_order_no() == null : this.getMch_order_no().equals(other.getMch_order_no()))
            && (this.getOrder_type() == null ? other.getOrder_type() == null : this.getOrder_type().equals(other.getOrder_type()))
            && (this.getNotify_url() == null ? other.getNotify_url() == null : this.getNotify_url().equals(other.getNotify_url()))
            && (this.getNotify_count() == null ? other.getNotify_count() == null : this.getNotify_count().equals(other.getNotify_count()))
            && (this.getResult() == null ? other.getResult() == null : this.getResult().equals(other.getResult()))
            && (this.getRtatus() == null ? other.getRtatus() == null : this.getRtatus().equals(other.getRtatus()))
            && (this.getLast_notify_time() == null ? other.getLast_notify_time() == null : this.getLast_notify_time().equals(other.getLast_notify_time()))
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
        result = prime * result + ((getOrder_id() == null) ? 0 : getOrder_id().hashCode());
        result = prime * result + ((getMch_id() == null) ? 0 : getMch_id().hashCode());
        result = prime * result + ((getMch_order_no() == null) ? 0 : getMch_order_no().hashCode());
        result = prime * result + ((getOrder_type() == null) ? 0 : getOrder_type().hashCode());
        result = prime * result + ((getNotify_url() == null) ? 0 : getNotify_url().hashCode());
        result = prime * result + ((getNotify_count() == null) ? 0 : getNotify_count().hashCode());
        result = prime * result + ((getResult() == null) ? 0 : getResult().hashCode());
        result = prime * result + ((getRtatus() == null) ? 0 : getRtatus().hashCode());
        result = prime * result + ((getLast_notify_time() == null) ? 0 : getLast_notify_time().hashCode());
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