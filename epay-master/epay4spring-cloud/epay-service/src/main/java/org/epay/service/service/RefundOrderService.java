package org.epay.service.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.epay.dal.dao.mapper.RefundOrderMapper;
import org.epay.dal.dao.model.RefundOrder;
import org.epay.dal.dao.model.RefundOrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;

/**
 * 类名: 退款订单查询
 * 作者: HappyDan
 * 时间: 2019年4月21日
 * 版本: V1.0
 */
@Component
public class RefundOrderService {

    @Autowired
    private RefundOrderMapper refundOrderMapper;
    
    /**
     * 根据主键查询退款订单对象信息
     */
    public RefundOrder selectRefundOrderByPrimaryKey(String refund_order_id) {
    	return refundOrderMapper.selectByPrimaryKey(refund_order_id);
    }

    /**
     * 查询退款订单信息
     * @param accountBook
     * @return
     */
    public List<RefundOrder> selectRefundOrderByConditions(RefundOrder refundOrder) {
    	//获取查询条件====================================Start
    	String 	mch_id 		 = refundOrder.getMch_id();
    	String 	start_time 	 = refundOrder.getStart_time();
    	String 	end_time 	 = refundOrder.getEnd_time();
    	String 	refund_order_id = refundOrder.getRefund_order_id();
    	String 	pay_order_id = refundOrder.getPay_orderid();
    	String 	user_id 	 = refundOrder.getUser_id();
    	String 	user_name 	 = refundOrder.getUser_name();
    	Byte 	status 		 = refundOrder.getStatus();
    	Integer limit 		 = refundOrder.getLimit();
    	Integer offset 		 = refundOrder.getOffset();
    	//获取查询条件====================================End
    	
    	//组装查询实力对象example====================================Start
    	RefundOrderExample example = new RefundOrderExample();
    	if(limit != null && offset != null) {
    		PageHelper.startPage(limit, offset);
    	}
//    	example.setLimit(limit);
//      example.setOffset(offset);
    	RefundOrderExample.Criteria criteria = example.createCriteria();
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
        if(StringUtils.isNotBlank(refund_order_id)) criteria.andRefund_order_idEqualTo(refund_order_id);
        if(StringUtils.isNotBlank(pay_order_id)) criteria.andPay_orderidEqualTo(pay_order_id);
        if(StringUtils.isNotBlank(user_id)) criteria.andUser_idEqualTo(user_id);
        if(StringUtils.isNotBlank(user_name)) criteria.andUser_nameEqualTo(user_name);
        if(status != null) criteria.andStatusEqualTo(status);
        //组装查询实力对象example====================================End
    	List<RefundOrder> refundOrderList = refundOrderMapper.selectByExample(example);
    	
    	return CollectionUtils.isEmpty(refundOrderList) ? null : refundOrderList;
    }
    
    /**
     * 查询退款订单信息数量
     * @param accountBook
     * @return
     */
    public Integer selectRefundOrderNumByConditions(RefundOrder refundOrder) {
    	//获取查询条件====================================Start
    	String 	mch_id 		 = refundOrder.getMch_id();
    	String 	start_time 	 = refundOrder.getStart_time();
    	String 	end_time 	 = refundOrder.getEnd_time();
    	String 	refund_order_id = refundOrder.getRefund_order_id();
    	String 	pay_order_id = refundOrder.getPay_orderid();
    	String 	user_id 	 = refundOrder.getUser_id();
    	String 	user_name 	 = refundOrder.getUser_name();
    	Byte 	status 		 = refundOrder.getStatus();
    	//获取查询条件====================================End
    	
    	//组装查询实力对象example====================================Start
    	RefundOrderExample example = new RefundOrderExample();
    	RefundOrderExample.Criteria criteria = example.createCriteria();
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
        if(StringUtils.isNotBlank(refund_order_id)) criteria.andRefund_order_idEqualTo(refund_order_id);
        if(StringUtils.isNotBlank(pay_order_id)) criteria.andPay_orderidEqualTo(pay_order_id);
        if(StringUtils.isNotBlank(user_id)) criteria.andUser_idEqualTo(user_id);
        if(StringUtils.isNotBlank(user_name)) criteria.andUser_nameEqualTo(user_name);
        if(status != null) criteria.andStatusEqualTo(status);
        //组装查询实力对象example====================================End
    	List<RefundOrder> refundOrderList = refundOrderMapper.selectByExample(example);
    	Integer ordersNum = 0;
    	if(CollectionUtils.isEmpty(refundOrderList)) {
    		ordersNum = 0;
    	}else {
    		ordersNum = refundOrderList.size();
    	}
    	
    	return ordersNum;
    }
    
    /**
     * 创建退款订单信息
     * @param payOrder
     * @return
     */
    public int createRefundOrder(RefundOrder refundOrder) {
        return refundOrderMapper.insertSelective(refundOrder);
    }
    
    /**
     * 更新退款申请信息
     * @param payOrder
     * @return
     */
    public int updateRefundOrder(RefundOrder refundOrder) {
    	return refundOrderMapper.updateByPrimaryKeySelective(refundOrder);
    }
    
    /**
     * 根据主键删除退款订单
     */
    public int deleteRefundOrderByPrimaryKey(String refund_order_id) {
    	return refundOrderMapper.deleteByPrimaryKey(refund_order_id);
    }
}
