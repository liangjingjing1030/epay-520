package org.epay.dal.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.epay.dal.dao.model.PtUserRole;
import org.epay.dal.dao.model.PtUserRoleExample;

public interface PtUserRoleMapper {
    long countByExample(PtUserRoleExample example);

    int deleteByExample(PtUserRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(PtUserRole record);

    int insertSelective(PtUserRole record);

    List<PtUserRole> selectByExample(PtUserRoleExample example);

    PtUserRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PtUserRole record, @Param("example") PtUserRoleExample example);

    int updateByExample(@Param("record") PtUserRole record, @Param("example") PtUserRoleExample example);

    int updateByPrimaryKeySelective(PtUserRole record);

    int updateByPrimaryKey(PtUserRole record);
}