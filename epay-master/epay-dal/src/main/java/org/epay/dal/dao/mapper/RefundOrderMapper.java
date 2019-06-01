package org.epay.dal.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.epay.dal.dao.model.RefundOrder;
import org.epay.dal.dao.model.RefundOrderExample;

public interface RefundOrderMapper {
    long countByExample(RefundOrderExample example);

    int deleteByExample(RefundOrderExample example);

    int deleteByPrimaryKey(String refund_order_id);

    int insert(RefundOrder record);

    int insertSelective(RefundOrder record);

    List<RefundOrder> selectByExample(RefundOrderExample example);

    RefundOrder selectByPrimaryKey(String refund_order_id);

    int updateByExampleSelective(@Param("record") RefundOrder record, @Param("example") RefundOrderExample example);

    int updateByExample(@Param("record") RefundOrder record, @Param("example") RefundOrderExample example);

    int updateByPrimaryKeySelective(RefundOrder record);

    int updateByPrimaryKey(RefundOrder record);
}