package org.epay.dal.dao.mapper;

import org.epay.dal.dao.model.BusinessRange;

import java.util.List;

public interface BusinessRangeMapper {
    int deleteByPrimaryKey(String id);

    int insert(BusinessRange record);

    int insertSelective(BusinessRange record);

    BusinessRange selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BusinessRange record);

    int updateByPrimaryKey(BusinessRange record);

    List<BusinessRange> selectAllBusinessRange();
}