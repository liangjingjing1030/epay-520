package org.epay.dal.dao.mapper;

import org.epay.dal.dao.model.CompareHistoryTgt;

public interface CompareHistoryTgtMapper {
    int deleteByPrimaryKey(Integer seqNo);

    int insert(CompareHistoryTgt record);

    int insertSelective(CompareHistoryTgt record);

    CompareHistoryTgt selectByPrimaryKey(Integer seqNo);

    int updateByPrimaryKeySelective(CompareHistoryTgt record);

    int updateByPrimaryKey(CompareHistoryTgt record);
}