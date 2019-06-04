package org.epay.dal.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.epay.dal.dao.model.ModelValue;

public interface ModelValueMapper {
    int deleteByPrimaryKey(String modelValueId);

    int insert(ModelValue record);

    int insertSelective(ModelValue record);

    ModelValue selectByPrimaryKey(String modelValueId);

    int updateByPrimaryKeySelective(ModelValue record);

    int updateByPrimaryKey(ModelValue record);

    /**
     * 根据模板主键与唯一标识查询
     * @param modelKeyId
     * @param userIdValue
     * @return
     */
    ModelValue selectByModelKeyIdAndUserId(@Param("modelKeyId") String modelKeyId,
                                           @Param("userIdValue") String userIdValue);
}