package org.epay.service.service;

import java.util.List;

import org.epay.dal.dao.mapper.ChannelInfoMapper;
import org.epay.dal.dao.model.ChannelInfo;
import org.epay.dal.dao.model.ChannelInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * 名称: FsbpMerCustPayService支付渠道实现类
 * 作者: HappyDan
 * 时间: 2019年4月5日
 * 版本: V1.0
 */
@Component
public class ChannelInfoService {

    @Autowired
    private ChannelInfoMapper channelInfoMapper;

    public ChannelInfo selectChannelInfoByChannelIdAndMchId(String channel_id, String mch_id) {
    	
    	ChannelInfoExample example = new ChannelInfoExample();
    	ChannelInfoExample.Criteria criteria = example.createCriteria();
        criteria.andChannel_idEqualTo(channel_id);
        criteria.andMch_idEqualTo(mch_id);
        List<ChannelInfo> channelInfoList = channelInfoMapper.selectByExample(example);
        return CollectionUtils.isEmpty(channelInfoList) ? null : channelInfoList.get(0);
    }

}
