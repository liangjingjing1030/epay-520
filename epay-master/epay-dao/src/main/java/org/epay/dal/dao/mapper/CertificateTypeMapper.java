package org.epay.dal.dao.mapper;

import org.epay.dal.dao.model.CertificateType;

import java.util.List;

public interface CertificateTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(CertificateType record);

    int insertSelective(CertificateType record);

    CertificateType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CertificateType record);

    int updateByPrimaryKey(CertificateType record);

    List<CertificateType> selectAll();
}