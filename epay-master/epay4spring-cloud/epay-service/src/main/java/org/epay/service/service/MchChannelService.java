package org.epay.service.service;

import org.epay.dal.dao.mapper.ChannelInfoMapper;
import org.epay.dal.dao.model.ChannelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商户渠道信息查询接口
 * GaoLiang
 * 2019年4月28日
 */
@Component
public class MchChannelService {
    @Autowired
    private ChannelInfoMapper channelInfoMapper;

    public List<ChannelInfo> selectMchChannelByMchId(String mch_id) {
        return channelInfoMapper.selectChannelByMchInfo(mch_id);
    }
}
