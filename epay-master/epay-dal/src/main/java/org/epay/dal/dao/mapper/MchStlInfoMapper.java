package org.epay.dal.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.epay.dal.dao.model.MchStlInfo;
import org.epay.dal.dao.model.MchStlInfoExample;

public interface MchStlInfoMapper {
    long countByExample(MchStlInfoExample example);

    int deleteByExample(MchStlInfoExample example);

    int deleteByPrimaryKey(String mch_id);

    int insert(MchStlInfo record);

    int insertSelective(MchStlInfo record);

    List<MchStlInfo> selectByExample(MchStlInfoExample example);

    MchStlInfo selectByPrimaryKey(String mch_id);

    int updateByExampleSelective(@Param("record") MchStlInfo record, @Param("example") MchStlInfoExample example);

    int updateByExample(@Param("record") MchStlInfo record, @Param("example") MchStlInfoExample example);

    int updateByPrimaryKeySelective(MchStlInfo record);

    int updateByPrimaryKey(MchStlInfo record);
}