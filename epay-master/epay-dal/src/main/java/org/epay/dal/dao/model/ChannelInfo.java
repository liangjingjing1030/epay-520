package org.epay.dal.dao.model;

import java.io.Serializable;

public class ChannelInfo implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Integer channel_info_id;

    /**
     * 商户ID
     *
     * @mbg.generated
     */
    private String mch_id;

    /**
     * 渠道ID：
WX_JSAPI = "微信公众号支付";
WX_NATIVE = "微信原生扫码支付";
WX_APP = "微信APP支付";
WX_MWEB = "微信H5支付";
IAP = "苹果应用内支付";
ALIPAY_MOBILE = "支付宝移动支付";
ALIPAY_PC = "支付宝PC支付";
ALIPAY_WAP = "支付宝WAP支付";
ALIPAY_QR = "支付宝当面付之扫码支付";
PC_MGR = "管理平台支付";
JD_PAY = "京东支付
     *
     * @mbg.generated
     */
    private String channel_id;

    /**
     * 渠道名称；
WX_JSAPI = "微信公众号支付";
WX_NATIVE = "微信原生扫码支付";
WX_APP = "微信APP支付";
WX_MWEB = "微信H5支付";
IAP = "苹果应用内支付";
ALIPAY_MOBILE = "支付宝移动支付";
ALIPAY_PC = "支付宝PC支付";
ALIPAY_WAP = "支付宝WAP支付";
ALIPAY_QR = "支付宝当面付之扫码支付";
PC_MGR = "管理平台支付";
JD_PAY = "京东支付
     *
     * @mbg.generated
     */
    private String channel_name;

    /**
     * 渠道商户ID
     *
     * @mbg.generated
     */
    private String channel_mchId;

    /**
     * 渠道状态,0-停止使用,1-使用中
     *
     * @mbg.generated
     */
    private Byte channel_status;

    /**
     * 配置参数,json字符串
     *
     * @mbg.generated
     */
    private String param;

    /**
     * 备注
     *
     * @mbg.generated
     */
    private String remark;

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

    public Integer getChannel_info_id() {
        return channel_info_id;
    }

    public void setChannel_info_id(Integer channel_info_id) {
        this.channel_info_id = channel_info_id;
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

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name == null ? null : channel_name.trim();
    }

    public String getChannel_mchId() {
        return channel_mchId;
    }

    public void setChannel_mchId(String channel_mchId) {
        this.channel_mchId = channel_mchId == null ? null : channel_mchId.trim();
    }

    public Byte getChannel_status() {
        return channel_status;
    }

    public void setChannel_status(Byte channel_status) {
        this.channel_status = channel_status;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param == null ? null : param.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        sb.append(", channel_info_id=").append(channel_info_id);
        sb.append(", mch_id=").append(mch_id);
        sb.append(", channel_id=").append(channel_id);
        sb.append(", channel_name=").append(channel_name);
        sb.append(", channel_mchId=").append(channel_mchId);
        sb.append(", channel_status=").append(channel_status);
        sb.append(", param=").append(param);
        sb.append(", remark=").append(remark);
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
        ChannelInfo other = (ChannelInfo) that;
        return (this.getChannel_info_id() == null ? other.getChannel_info_id() == null : this.getChannel_info_id().equals(other.getChannel_info_id()))
            && (this.getMch_id() == null ? other.getMch_id() == null : this.getMch_id().equals(other.getMch_id()))
            && (this.getChannel_id() == null ? other.getChannel_id() == null : this.getChannel_id().equals(other.getChannel_id()))
            && (this.getChannel_name() == null ? other.getChannel_name() == null : this.getChannel_name().equals(other.getChannel_name()))
            && (this.getChannel_mchId() == null ? other.getChannel_mchId() == null : this.getChannel_mchId().equals(other.getChannel_mchId()))
            && (this.getChannel_status() == null ? other.getChannel_status() == null : this.getChannel_status().equals(other.getChannel_status()))
            && (this.getParam() == null ? other.getParam() == null : this.getParam().equals(other.getParam()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
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
        result = prime * result + ((getChannel_info_id() == null) ? 0 : getChannel_info_id().hashCode());
        result = prime * result + ((getMch_id() == null) ? 0 : getMch_id().hashCode());
        result = prime * result + ((getChannel_id() == null) ? 0 : getChannel_id().hashCode());
        result = prime * result + ((getChannel_name() == null) ? 0 : getChannel_name().hashCode());
        result = prime * result + ((getChannel_mchId() == null) ? 0 : getChannel_mchId().hashCode());
        result = prime * result + ((getChannel_status() == null) ? 0 : getChannel_status().hashCode());
        result = prime * result + ((getParam() == null) ? 0 : getParam().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
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