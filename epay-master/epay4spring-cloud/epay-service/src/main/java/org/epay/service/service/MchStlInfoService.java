package org.epay.service.service;

import org.epay.dal.dao.mapper.MchStlInfoMapper;
import org.epay.dal.dao.model.MchStlInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 商户结算信息查询接口
 * GaoLiang
 * 2019年4月28日20:24:01
 */
@Component
public class MchStlInfoService {
    @Autowired
    private MchStlInfoMapper mchStlInfoMapper;

    public MchStlInfo selectMchStlInfoByMchId(String mch_id) {
        return mchStlInfoMapper.selectByPrimaryKey(mch_id);
    }
}
