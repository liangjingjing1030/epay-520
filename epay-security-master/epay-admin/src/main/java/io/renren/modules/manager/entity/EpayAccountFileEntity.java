package io.renren.modules.manager.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 账单文件表
 * 
 * @author liyongshuai
 * @email ${email}
 * @date 2019-04-22 19:56:15
 */
@Data
@TableName("epay_account_file")
public class EpayAccountFileEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 文件ID
     */
    @TableId
    private String fileId;
    /**
     * 商户号
     */
    private String mchId;
    /**
     * 项目编号
     */
    private String itemsId;
    /**
     * 项目名称
     */
    private String itemsName;
    /**
     * 项目类别
     */
    private String itemsType;
    /**
     * 项目文件名称
     */
    private String itemsFilename;
    /**
     * 文件上传日期
     */
    private Date uploadDate;
    /**
     * 账单生效时间
     */
    private Date affectDate;
    /**
     * 账单失效时间
     */
    private Date expiryDate;
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


    public String getFileId() {
        return fileId;
    }


    public void setFileId(String fileId) {
        this.fileId = fileId;
    }


    public String getMchId() {
        return mchId;
    }


    public void setMchId(String mchId) {
        this.mchId = mchId;
    }


    public String getItemsId() {
        return itemsId;
    }


    public void setItemsId(String itemsId) {
        this.itemsId = itemsId;
    }


    public String getItemsName() {
        return itemsName;
    }


    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }


    public String getItemsType() {
        return itemsType;
    }


    public void setItemsType(String itemsType) {
        this.itemsType = itemsType;
    }


    public String getItemsFilename() {
        return itemsFilename;
    }


    public void setItemsFilename(String itemsFilename) {
        this.itemsFilename = itemsFilename;
    }


    public Date getUploadDate() {
        return uploadDate;
    }


    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }


    public Date getAffectDate() {
        return affectDate;
    }


    public void setAffectDate(Date affectDate) {
        this.affectDate = affectDate;
    }


    public Date getExpiryDate() {
        return expiryDate;
    }


    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
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
