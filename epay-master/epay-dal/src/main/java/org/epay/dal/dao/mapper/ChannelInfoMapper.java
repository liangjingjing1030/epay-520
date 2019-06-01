package org.epay.dal.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.epay.dal.dao.model.ChannelInfo;
import org.epay.dal.dao.model.ChannelInfoExample;

public interface ChannelInfoMapper {
    long countByExample(ChannelInfoExample example);

    int deleteByExample(ChannelInfoExample example);

    int deleteByPrimaryKey(Integer channel_info_id);

    int insert(ChannelInfo record);

    int insertSelective(ChannelInfo record);

    List<ChannelInfo> selectByExample(ChannelInfoExample example);

    ChannelInfo selectByPrimaryKey(Integer channel_info_id);

    int updateByExampleSelective(@Param("record") ChannelInfo record, @Param("example") ChannelInfoExample example);

    int updateByExample(@Param("record") ChannelInfo record, @Param("example") ChannelInfoExample example);

    int updateByPrimaryKeySelective(ChannelInfo record);

    int updateByPrimaryKey(ChannelInfo record);

    /**
     * 根据商户号查询商户渠道信息
     * @param mch_id
     * @return
     */
    List<ChannelInfo> selectChannelByMchInfo(String mch_id);
}