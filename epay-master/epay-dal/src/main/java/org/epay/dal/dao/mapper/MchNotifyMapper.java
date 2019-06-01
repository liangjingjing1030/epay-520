package org.epay.dal.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.epay.dal.dao.model.MchNotify;
import org.epay.dal.dao.model.MchNotifyExample;

public interface MchNotifyMapper {
    long countByExample(MchNotifyExample example);

    int deleteByExample(MchNotifyExample example);

    int deleteByPrimaryKey(String order_id);

    int insert(MchNotify record);

    int insertSelective(MchNotify record);

    List<MchNotify> selectByExample(MchNotifyExample example);

    MchNotify selectByPrimaryKey(String order_id);

    int updateByExampleSelective(@Param("record") MchNotify record, @Param("example") MchNotifyExample example);

    int updateByExample(@Param("record") MchNotify record, @Param("example") MchNotifyExample example);

    int updateByPrimaryKeySelective(MchNotify record);

    int updateByPrimaryKey(MchNotify record);
}