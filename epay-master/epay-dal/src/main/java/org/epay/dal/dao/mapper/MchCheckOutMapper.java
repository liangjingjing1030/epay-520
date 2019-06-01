package org.epay.dal.dao.mapper;

import org.epay.dal.dao.model.MchCheckOut;

import java.util.List;
import java.util.Map;

public interface MchCheckOutMapper {
    int deleteByPrimaryKey(String mchCheckoutId);

    int insert(MchCheckOut record);

    int insertSelective(MchCheckOut record);

    MchCheckOut selectByPrimaryKey(String mchCheckoutId);

    int updateByPrimaryKeySelective(MchCheckOut record);

    int updateByPrimaryKey(MchCheckOut record);

    /**
     * 查询当前商户的所有结算总数
     * @param conditionMap
     * @return
     */
    int getTotalByCondition(Map<String, Object> conditionMap);

    /**
     * 查询当前商户的所有结算数据
     * @param conditionMap
     * @return
     */
    List<MchCheckOut> getPageListByCondition(Map<String, Object> conditionMap);
}