package org.epay.dal.dao.mapper;

import org.epay.dal.dao.model.ModelKey;

public interface ModelKeyMapper {
    int deleteByPrimaryKey(String modelKeyId);

    int insert(ModelKey record);

    int insertSelective(ModelKey record);

    ModelKey selectByPrimaryKey(String modelKeyId);

    int updateByPrimaryKeySelective(ModelKey record);

    int updateByPrimaryKey(ModelKey record);
}