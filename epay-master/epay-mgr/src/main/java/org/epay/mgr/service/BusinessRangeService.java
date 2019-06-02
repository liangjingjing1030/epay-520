package org.epay.mgr.service;

import org.epay.dal.dao.model.BusinessRange;

public interface BusinessRangeService {
    BusinessRange queryBusinessRange(String mch_range_id);
}
