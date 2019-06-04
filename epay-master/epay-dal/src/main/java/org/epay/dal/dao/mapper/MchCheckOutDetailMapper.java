package org.epay.dal.dao.mapper;

import org.epay.dal.dao.model.MchCheckOut;
import org.epay.dal.dao.model.MchCheckOutDetail;

import java.util.List;
import java.util.Map;

public interface MchCheckOutDetailMapper {
    int deleteByPrimaryKey(String mchCheckoutId);

    int insert(MchCheckOutDetail record);

    int insertSelective(MchCheckOutDetail record);

    MchCheckOutDetail selectByPrimaryKey(String mchCheckoutId);

    int updateByPrimaryKeySelective(MchCheckOutDetail record);

    int updateByPrimaryKey(MchCheckOutDetail record);

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
    List<MchCheckOutDetail> getPageListByCondition(Map<String, Object> conditionMap);

}