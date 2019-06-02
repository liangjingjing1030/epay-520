package org.epay.mgr.service.impl;

import org.epay.dal.dao.mapper.CertificateTypeMapper;
import org.epay.dal.dao.model.CertificateType;
import org.epay.mgr.service.CertificateTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificateTypeServiceImpl implements CertificateTypeService {

    @Autowired
    private CertificateTypeMapper certificateTypeMapper;

    @Override
    public CertificateType queryCertificateType(String certificate_type_id) {
        return certificateTypeMapper.selectByPrimaryKey(certificate_type_id);
    }
}
