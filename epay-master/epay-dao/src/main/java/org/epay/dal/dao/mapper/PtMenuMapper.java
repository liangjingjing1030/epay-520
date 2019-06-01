package org.epay.dal.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.epay.dal.dao.model.PtMenu;
import org.epay.dal.dao.model.PtMenuExample;

public interface PtMenuMapper {
    long countByExample(PtMenuExample example);

    int deleteByExample(PtMenuExample example);

    int deleteByPrimaryKey(String id);

    int insert(PtMenu record);

    int insertSelective(PtMenu record);

    List<PtMenu> selectByExample(PtMenuExample example);

    PtMenu selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PtMenu record, @Param("example") PtMenuExample example);

    int updateByExample(@Param("record") PtMenu record, @Param("example") PtMenuExample example);

    int updateByPrimaryKeySelective(PtMenu record);

    int updateByPrimaryKey(PtMenu record);
}