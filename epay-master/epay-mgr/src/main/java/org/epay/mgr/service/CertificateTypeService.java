package org.epay.mgr.service;

import org.epay.dal.dao.model.CertificateType;

public interface CertificateTypeService {
    CertificateType queryCertificateType(String certificate_type_id);
}
