package io.renren.modules.manager.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 
 * 
 * @author liyongshuai
 * @email ${email}
 * @date 2019-05-05 20:06:26
 */
@Data
@TableName("compare_question_profile")
public class CompareQuestionProfileEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
	 * 
	 */
    @TableId
    private Integer id;
    /**
     * 存疑日期
     */
    private Date questionDate;
    /**
     * 过账状态 0：未过账 1：已过账
     */
    private String postStatus;
    /**
     * 支付公司编号
     */
    private String payCompanyNo;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Date getQuestionDate() {
        return questionDate;
    }


    public void setQuestionDate(Date questionDate) {
        this.questionDate = questionDate;
    }


    public String getPostStatus() {
        return postStatus;
    }


    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }


    public String getPayCompanyNo() {
        return payCompanyNo;
    }


    public void setPayCompanyNo(String payCompanyNo) {
        this.payCompanyNo = payCompanyNo;
    }

}
