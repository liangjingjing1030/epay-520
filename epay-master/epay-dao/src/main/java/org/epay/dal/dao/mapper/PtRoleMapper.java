package org.epay.dal.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.epay.dal.dao.model.PtRole;
import org.epay.dal.dao.model.PtRoleExample;

public interface PtRoleMapper {
    long countByExample(PtRoleExample example);

    int deleteByExample(PtRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(PtRole record);

    int insertSelective(PtRole record);

    List<PtRole> selectByExample(PtRoleExample example);

    PtRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PtRole record, @Param("example") PtRoleExample example);

    int updateByExample(@Param("record") PtRole record, @Param("example") PtRoleExample example);

    int updateByPrimaryKeySelective(PtRole record);

    int updateByPrimaryKey(PtRole record);
}