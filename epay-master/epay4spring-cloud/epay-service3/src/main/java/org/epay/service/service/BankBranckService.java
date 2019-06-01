package org.epay.service.service;

import org.epay.dal.dao.mapper.BankBranchMapper;
import org.epay.dal.dao.model.BankBranch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 类名: 商户登陆信息处理
 * 作者: HappyDan
 * 时间: 2019年4月30日
 * 版本: V1.0
 */
@Component
public class BankBranckService {

    @Autowired
    private BankBranchMapper bankBranckMapper;

    /**
     * 根据主键（机构编号）查询机构
     * @param branch_id
     * @return
     */
    public BankBranch selectBankBranchByBranchId(String branch_id) {
        return  bankBranckMapper.selectByPrimaryKey(Long.parseLong(branch_id));
    }

}
