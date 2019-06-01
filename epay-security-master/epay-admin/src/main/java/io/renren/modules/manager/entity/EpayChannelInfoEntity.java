package io.renren.modules.manager.entity;

import java.io.Serializable;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 商户渠道表
 * 
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-22 19:56:15
 */
@Data
@TableName("epay_channel_info")
public class EpayChannelInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 渠道主键ID
     */
    @TableId
    private Integer id;
    /**
     * 商户ID
     */
    private String mchId;
    /**
     * 渠道ID
     */
    private String channelId;
    /**
     * 渠道名称 如:alipay,wechat
     */
    private String channelName;
    /**
     * 渠道商户ID
     */
    private String channelMchid;
    /**
     * 渠道状态 0-停止使用,1-使用中
     */
    private Integer channelStatus;
    /**
     * 配置参数,json字符串
     */
    private String param;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;
    /**
     * 备用字段3
     */
    private String reserve3;
    /**
     * 备用字段4
     */
    private String reserve4;
    /**
     * 备用字段5
     */
    private String reserve5;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getMchId() {
        return mchId;
    }


    public void setMchId(String mchId) {
        this.mchId = mchId;
    }


    public String getChannelId() {
        return channelId;
    }


    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }


    public String getChannelName() {
        return channelName;
    }


    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }


    public String getChannelMchid() {
        return channelMchid;
    }


    public void setChannelMchid(String channelMchid) {
        this.channelMchid = channelMchid;
    }


    public Integer getChannelStatus() {
        return channelStatus;
    }


    public void setChannelStatus(Integer channelStatus) {
        this.channelStatus = channelStatus;
    }


    public String getParam() {
        return param;
    }


    public void setParam(String param) {
        this.param = param;
    }


    public String getRemark() {
        return remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getCreateTime() {
        return createTime;
    }


    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public String getUpdateTime() {
        return updateTime;
    }


    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }


    public String getReserve1() {
        return reserve1;
    }


    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }


    public String getReserve2() {
        return reserve2;
    }


    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }


    public String getReserve3() {
        return reserve3;
    }


    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }


    public String getReserve4() {
        return reserve4;
    }


    public void setReserve4(String reserve4) {
        this.reserve4 = reserve4;
    }


    public String getReserve5() {
        return reserve5;
    }


    public void setReserve5(String reserve5) {
        this.reserve5 = reserve5;
    }

}
