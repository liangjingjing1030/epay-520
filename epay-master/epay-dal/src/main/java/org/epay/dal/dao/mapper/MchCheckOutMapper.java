package org.epay.dal.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.epay.dal.dao.model.MchCheckOut;

public interface MchCheckOutMapper {
    int deleteByPrimaryKey(String checkoutId);

    int insert(MchCheckOut record);

    int insertSelective(MchCheckOut record);

    MchCheckOut selectByPrimaryKey(String checkoutId);

    int updateByPrimaryKeySelective(MchCheckOut record);

    int updateByPrimaryKey(MchCheckOut record);

    /**
     * 商户号 + 支付时间 查询唯一一条数据
     * @param mch_id
     * @param updateTime
     * @return
     */
    MchCheckOut selectByMchIdAndUpdateTime(@Param("mch_id") String mch_id,
                                           @Param("updateTime") String updateTime);
}