package org.epay.service.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.epay.dal.dao.mapper.MchInfoMapper;
import org.epay.dal.dao.mapper.MchStlInfoMapper;
import org.epay.dal.dao.model.MchInfo;
import org.epay.dal.dao.model.MchInfoExample;
import org.epay.dal.dao.model.MchStlInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * 类名: MchInfoService商户信息
 * 作者: HappyDan
 * 时间: 2019年4月18日
 * 版本: V1.0
 */
@Component
public class MchInfoService {

    @Autowired
    private MchInfoMapper mchInfoMapper;
    
    @Autowired
    private MchStlInfoMapper mchStlInfoMapper;

    public MchInfo selectMchInfoByMchId(String mch_id) {
        return mchInfoMapper.selectByPrimaryKey(mch_id);
    }
    
    public int insertMchInfo(MchInfo mchInfo) {
        return mchInfoMapper.insertSelective(mchInfo);
    }

    public int insertMchStlInfo(MchStlInfo mchStlInfo) {
        return mchStlInfoMapper.insertSelective(mchStlInfo);
    }
    
    public List<MchInfo> userSelectMchInfo(MchInfo mchInfo) {
    	//获取查询条件====================================Start
    	String mch_type = mchInfo.getMch_type();
    	String mch_range = mchInfo.getMch_range();
    	String mch_city = mchInfo.getMch_city();
    	//获取查询条件====================================End
    	
    	MchInfoExample example = new MchInfoExample();
    	MchInfoExample.Criteria criteria = example.createCriteria();
    	if(StringUtils.isNotBlank(mch_type)) criteria.andMch_typeEqualTo(mch_type);
    	if(StringUtils.isNotBlank(mch_range)) criteria.andMch_rangeEqualTo(mch_range);
    	if(StringUtils.isNotBlank(mch_city)) criteria.andMch_cityEqualTo(mch_city);
        
        List<MchInfo> mchInfoList = mchInfoMapper.selectByExample(example);

        return CollectionUtils.isEmpty(mchInfoList) ? null : mchInfoList;
    }
    
}
