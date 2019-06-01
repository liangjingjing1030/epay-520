package org.epay.service.service;

import java.util.List;

import org.epay.dal.dao.mapper.AccountFileMapper;
import org.epay.dal.dao.model.AccountFile;
import org.epay.dal.dao.model.AccountFileExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * 类名: 查询账单文件获取项目编号
 * 作者: HappyDan
 * 时间: 2019年4月26日
 * 版本: V1.0
 */
@Component
public class AccountFileService {

    @Autowired
    private AccountFileMapper accountFileMapper;
    
    public List<AccountFile> mchQueryAccountFileByMchId(String mch_id) {
    	//组装查询实力对象example====================================Start
    	AccountFileExample example = new AccountFileExample();
    	AccountFileExample.Criteria criteria = example.createCriteria();
        criteria.andMch_idEqualTo(mch_id);
        //组装查询实力对象example====================================End
        
        List<AccountFile> AccountFileList = accountFileMapper.selectByExample(example);

        return CollectionUtils.isEmpty(AccountFileList) ? null : AccountFileList;
    }

}
