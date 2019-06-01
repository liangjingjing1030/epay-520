package org.epay.dal.dao.mapper;

import org.epay.dal.dao.model.BankBranch;

public interface BankBranchMapper {
    int deleteByPrimaryKey(Long deptId);

    int insert(BankBranch record);

    int insertSelective(BankBranch record);

    BankBranch selectByPrimaryKey(Long deptId);

    int updateByPrimaryKeySelective(BankBranch record);

    int updateByPrimaryKey(BankBranch record);
}