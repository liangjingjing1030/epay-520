package org.epay.dal.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.epay.dal.dao.model.PayOrder;
import org.epay.dal.dao.model.PayOrderExample;

public interface PayOrderMapper {
    long countByExample(PayOrderExample example);

    int deleteByExample(PayOrderExample example);

    int deleteByPrimaryKey(String pay_order_id);

    int insert(PayOrder record);

    int insertSelective(PayOrder record);

    List<PayOrder> selectByExample(PayOrderExample example);

    PayOrder selectByPrimaryKey(String pay_order_id);

    int updateByExampleSelective(@Param("record") PayOrder record, @Param("example") PayOrderExample example);

    int updateByExample(@Param("record") PayOrder record, @Param("example") PayOrderExample example);

    int updateByPrimaryKeySelective(PayOrder record);

    int updateByPrimaryKey(PayOrder record);

    /**
     * 查询符合要求的订单总数
     * @param conditionMap
     * @return
     */
    int getTotalByCondition(Map<String, Object> conditionMap);

    /**
     * 查询符合要求的订单
     * @param conditionMap
     * @return
     */
    List<PayOrder> getPageList(Map<String, Object> conditionMap);

    int getTotalByConditionWhenStatusIsZero(Map<String, Object> conditionMap);

    List<PayOrder> getPageListWhenStatusIsZero(Map<String, Object> conditionMap);

    /**
     * 查询订单（状态为已缴费）
     * //支付状态,0-订单生成,1-支付成功,2-支付失败,3-已退款,4-支付中
     * @param mchOrderNoList
     * @return
     */
    List<PayOrder> selectByMchOrderNoAndStatus(List<String> mchOrderNoList);
}