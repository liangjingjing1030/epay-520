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
 * 类名: 订单明细信息
 * 作者: HappyDan
 * 时间: 2019年4月21日
 * 版本: V1.0
 */
@Component
public class PayOrderService {

    @Autowired
    private PayOrderMapper payOrderMapper;

    public int createPayOrder(PayOrder payOrder) {
        return payOrderMapper.insertSelective(payOrder);
    }

    /**
     * 根据订单号查询订单信息
     * @param payOrderId
     * @return
     */
    public PayOrder selectPayOrderByPrimaryKey(String payOrderId) {
        return payOrderMapper.selectByPrimaryKey(payOrderId);
    }

    /**
     * 查询支付订单详情
     * @param payOrder
     * @return
     */
    public List<PayOrder> selectPayOrderDetail(PayOrder payOrder) {
    	//获取查询条件====================================Start
    	String 	user_id 	 = payOrder.getUser_id();
    	String 	user_name 	 = payOrder.getUser_name();
    	String 	pay_order_id = payOrder.getPay_order_id();
    	Byte 	status 		 = payOrder.getStatus();
    	String 	start_time 	 = payOrder.getStart_time();
    	String 	end_time 	 = payOrder.getEnd_time();
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
        if(StringUtils.isNotBlank(user_id)) criteria.andUser_idEqualTo(user_id);
        if(StringUtils.isNotBlank(user_name)) criteria.andUser_nameEqualTo(user_name);
        if(StringUtils.isNotBlank(pay_order_id)) criteria.andPay_order_idEqualTo(pay_order_id);
        if(status != null) criteria.andStatusEqualTo(status);
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
        //组装查询实力对象example====================================End
        List<PayOrder> payOrderDetailList = payOrderMapper.selectByExample(example);

        return CollectionUtils.isEmpty(payOrderDetailList) ? null : payOrderDetailList;
    }
    
    /**
     * 查询支付订单详情
     * @param payOrder
     * @return
     */
    public Integer selectPayOrderDetailNum(PayOrder payOrder) {
    	//获取查询条件====================================Start
    	String 	user_id 	 = payOrder.getUser_id();
    	String 	user_name 	 = payOrder.getUser_name();
    	String 	pay_order_id = payOrder.getPay_order_id();
    	Byte 	status 		 = payOrder.getStatus();
    	String 	start_time 	 = payOrder.getStart_time();
    	String 	end_time 	 = payOrder.getEnd_time();
    	//获取查询条件====================================End
    	
    	//组装查询实力对象example====================================Start
        PayOrderExample example = new PayOrderExample();
        PayOrderExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(user_id)) criteria.andUser_idEqualTo(user_id);
        if(StringUtils.isNotBlank(user_name)) criteria.andUser_nameEqualTo(user_name);
        if(StringUtils.isNotBlank(pay_order_id)) criteria.andPay_order_idEqualTo(pay_order_id);
        if(status != null) criteria.andStatusEqualTo(status);
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
        //组装查询实力对象example====================================End
        List<PayOrder> payOrderDetailList = payOrderMapper.selectByExample(example);
        Integer ordersNum = 0;
        if(CollectionUtils.isEmpty(payOrderDetailList)) {
        	ordersNum = 0;
        }else {
        	ordersNum = payOrderDetailList.size();
        }
        return ordersNum;
    }

    public int updateStatus(PayOrder payOrder) {
    	return payOrderMapper.updateByPrimaryKey(payOrder);
    }

    /**
     * 查询订单（状态为已缴费）
     * @param mchOrderNoList
     * @return
     */
    public List<PayOrder> queryPayOrderListByOrderNo(List<String> mchOrderNoList) {
        return payOrderMapper.selectByMchOrderNoAndStatus(mchOrderNoList);
    }
}
