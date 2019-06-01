package org.epay.service.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.epay.dal.dao.mapper.PayOrderMapper;
import org.epay.dal.dao.model.PayOrder;
import org.epay.dal.dao.model.PayOrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;

/**
 * 类名: 商户查询订单流水
 * 作者: HappyDan
 * 时间: 2019年4月19日
 * 版本: V1.0
 */
@Component
public class MchQueryOrdersService {

    @Autowired
    private PayOrderMapper payOrderMapper;
    
    public List<PayOrder> mchQueryOrdersByConditions(PayOrder payOrder) {
    	//获取查询条件====================================Start
    	String 	mch_id 		 = payOrder.getMch_id();
    	String 	start_time 	 = payOrder.getStart_time();
    	String 	end_time 	 = payOrder.getEnd_time();
    	String 	mch_order_no = payOrder.getMch_order_no();
    	String 	pay_order_id = payOrder.getPay_order_id();
    	String 	user_id 	 = payOrder.getUser_id();
    	String 	user_name 	 = payOrder.getUser_name();
    	Byte 	status 		 = payOrder.getStatus();
    	Integer limit 		 = payOrder.getLimit();
    	Integer offset 		 = payOrder.getOffset();
    	//获取查询条件====================================End
    	
    	//组装查询实力对象example====================================Start
        PayOrderExample example = new PayOrderExample();
    	if(limit != null && offset != null) {
    		PageHelper.startPage(limit, offset);
    	}
//        example.setLimit(limit);
//        example.setOffset(offset);
        PayOrderExample.Criteria criteria = example.createCriteria();
        criteria.andMch_idEqualTo(mch_id);
        if(StringUtils.isNotBlank(start_time)) {
        	if (StringUtils.isNotBlank(end_time)) {
        		criteria.andCreate_timeBetween(start_time, end_time);
			}else {
				criteria.andCreate_timeGreaterThanOrEqualTo(start_time);
			}
        }else {
        	if (StringUtils.isNotBlank(end_time)) {
        		criteria.andCreate_timeLessThanOrEqualTo(end_time);
			}
		}
        if(StringUtils.isNotBlank(mch_order_no)) criteria.andMch_order_noEqualTo(mch_order_no);
        if(StringUtils.isNotBlank(pay_order_id)) criteria.andPay_order_idEqualTo(pay_order_id);
        if(StringUtils.isNotBlank(user_id)) criteria.andUser_idEqualTo(user_id);
        if(StringUtils.isNotBlank(user_name)) criteria.andUser_nameEqualTo(user_name);
        if(status != null) criteria.andStatusEqualTo(status);
        //组装查询实力对象example====================================End
        
        List<PayOrder> orderList = payOrderMapper.selectByExample(example);

        return CollectionUtils.isEmpty(orderList) ? null : orderList;
    }
    
    /**
     * 商户查询订单流水数量
     * @param payOrder
     * @return
     */
    public Integer mchQueryOrdersNumByConditions(PayOrder payOrder) {
    	//获取查询条件====================================Start
    	String 	mch_id 		 = payOrder.getMch_id();
    	String 	start_time 	 = payOrder.getStart_time();
    	String 	end_time 	 = payOrder.getEnd_time();
    	String 	mch_order_no = payOrder.getMch_order_no();
    	String 	pay_order_id = payOrder.getPay_order_id();
    	String 	user_id 	 = payOrder.getUser_id();
    	String 	user_name 	 = payOrder.getUser_name();
    	Byte 	status 		 = payOrder.getStatus();
    	//获取查询条件====================================End
    	
    	//组装查询实力对象example====================================Start
        PayOrderExample example = new PayOrderExample();
        PayOrderExample.Criteria criteria = example.createCriteria();
        criteria.andMch_idEqualTo(mch_id);
        if(StringUtils.isNotBlank(start_time)) {
        	if (StringUtils.isNotBlank(end_time)) {
        		criteria.andCreate_timeBetween(start_time, end_time);
			}else {
				criteria.andCreate_timeGreaterThanOrEqualTo(start_time);
			}
        }else {
        	if (StringUtils.isNotBlank(end_time)) {
        		criteria.andCreate_timeLessThanOrEqualTo(end_time);
			}
		}
        if(StringUtils.isNotBlank(mch_order_no)) criteria.andMch_order_noEqualTo(mch_order_no);
        if(StringUtils.isNotBlank(pay_order_id)) criteria.andPay_order_idEqualTo(pay_order_id);
        if(StringUtils.isNotBlank(user_id)) criteria.andUser_idEqualTo(user_id);
        if(StringUtils.isNotBlank(user_name)) criteria.andUser_nameEqualTo(user_name);
        if(status != null) criteria.andStatusEqualTo(status);
        //组装查询实力对象example====================================End
        
        List<PayOrder> orderList = payOrderMapper.selectByExample(example);
        Integer ordersNum = 0;
        if(CollectionUtils.isEmpty(orderList)) {
        	ordersNum = 0;
        }else {
        	ordersNum = orderList.size();
        }
        return ordersNum;
    }

}
