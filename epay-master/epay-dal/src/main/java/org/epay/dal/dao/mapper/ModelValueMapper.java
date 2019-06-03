package org.epay.dal.dao.mapper;

import org.epay.dal.dao.model.ModelValue;

public interface ModelValueMapper {
    int deleteByPrimaryKey(String modelValueId);

    int insert(ModelValue record);

    int insertSelective(ModelValue record);

    ModelValue selectByPrimaryKey(String modelValueId);

    int updateByPrimaryKeySelective(ModelValue record);

    int updateByPrimaryKey(ModelValue record);
}