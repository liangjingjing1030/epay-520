package org.epay.dal.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.epay.dal.dao.model.PtUser;
import org.epay.dal.dao.model.PtUserExample;

public interface PtUserMapper {
    long countByExample(PtUserExample example);

    int deleteByExample(PtUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PtUser record);

    int insertSelective(PtUser record);

    List<PtUser> selectByExample(PtUserExample example);

    PtUser selectByPrimaryKey(Long id);
    
    /**
     * 根据登录名查询商户登陆信息
     * @param id
     * @return
     */
    PtUser selectPtUserByLoginName(String login_name);

    int updateByExampleSelective(@Param("record") PtUser record, @Param("example") PtUserExample example);

    int updateByExample(@Param("record") PtUser record, @Param("example") PtUserExample example);

    int updateByPrimaryKeySelective(PtUser record);

    int updateByPrimaryKey(PtUser record);
}