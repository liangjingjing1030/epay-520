package org.epay.dal.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.epay.dal.dao.model.PtOrg;
import org.epay.dal.dao.model.PtOrgExample;

public interface PtOrgMapper {
    long countByExample(PtOrgExample example);

    int deleteByExample(PtOrgExample example);

    int deleteByPrimaryKey(String id);

    int insert(PtOrg record);

    int insertSelective(PtOrg record);

    List<PtOrg> selectByExample(PtOrgExample example);

    PtOrg selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PtOrg record, @Param("example") PtOrgExample example);

    int updateByExample(@Param("record") PtOrg record, @Param("example") PtOrgExample example);

    int updateByPrimaryKeySelective(PtOrg record);

    int updateByPrimaryKey(PtOrg record);
}