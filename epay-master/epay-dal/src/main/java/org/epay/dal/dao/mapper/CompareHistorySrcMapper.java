package org.epay.dal.dao.mapper;

import org.epay.dal.dao.model.CompareHistorySrc;

import java.util.List;
import java.util.Map;

public interface CompareHistorySrcMapper {
    int deleteByPrimaryKey(Integer seqNo);

    int insert(CompareHistorySrc record);

    int insertSelective(CompareHistorySrc record);

    CompareHistorySrc selectByPrimaryKey(Integer seqNo);

    int updateByPrimaryKeySelective(CompareHistorySrc record);

    int updateByPrimaryKey(CompareHistorySrc record);

    /**
     * Src对账分页总数
     * @param conditionMap
     * @return
     */
    int getSrcTotalByCondition(Map<String, Object> conditionMap);

    /**
     * Src对象分页数据
     * @param conditionMap
     * @return
     */
    List<CompareHistorySrc> getSrcPageListByCondition(Map<String, Object> conditionMap);

    /**
     * Src + Tgt对账分页总数
     * @param conditionMap
     * @return
     */
    int getTgtTotalByCondition(Map<String, Object> conditionMap);

    /**
     * Src + Tgt对账分页数据
     * @param conditionMap
     * @return
     */
    List<CompareHistorySrc> getTgtPageListByCondition(Map<String, Object> conditionMap);
}