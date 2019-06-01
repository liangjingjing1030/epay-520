package org.epay.dal.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.epay.dal.dao.model.PtRoleOperator;
import org.epay.dal.dao.model.PtRoleOperatorExample;

public interface PtRoleOperatorMapper {
    long countByExample(PtRoleOperatorExample example);

    int deleteByExample(PtRoleOperatorExample example);

    int deleteByPrimaryKey(String id);

    int insert(PtRoleOperator record);

    int insertSelective(PtRoleOperator record);

    List<PtRoleOperator> selectByExample(PtRoleOperatorExample example);

    PtRoleOperator selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PtRoleOperator record, @Param("example") PtRoleOperatorExample example);

    int updateByExample(@Param("record") PtRoleOperator record, @Param("example") PtRoleOperatorExample example);

    int updateByPrimaryKeySelective(PtRoleOperator record);

    int updateByPrimaryKey(PtRoleOperator record);
}