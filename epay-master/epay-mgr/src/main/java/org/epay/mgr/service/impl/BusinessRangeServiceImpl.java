package org.epay.mgr.service.impl;

import org.epay.dal.dao.mapper.BusinessRangeMapper;
import org.epay.dal.dao.model.BusinessRange;
import org.epay.mgr.service.BusinessRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessRangeServiceImpl implements BusinessRangeService {

    @Autowired
    private BusinessRangeMapper businessRangeMapper;

    @Override
    public BusinessRange queryBusinessRange(String mch_range_id) {
        return businessRangeMapper.selectByPrimaryKey(mch_range_id);
    }

}
