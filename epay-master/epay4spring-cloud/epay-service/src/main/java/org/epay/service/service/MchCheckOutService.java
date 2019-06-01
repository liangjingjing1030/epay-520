package org.epay.service.service;

import org.epay.dal.dao.mapper.MchCheckOutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MchCheckOutService {

    @Autowired
    private MchCheckOutMapper mchCheckOutMapper;

    /**
     * 结算分页查询
     * @param conditionMap
     * @return
     */
    public Map<String, Object> selectMchCheckOutByConditions(Map<String, Object> conditionMap) {
        Map<String, Object> dataMap = new HashMap<>();
        // 查询当前商户的所有结算总数
        dataMap.put("total", mchCheckOutMapper.getTotalByCondition(conditionMap));
        // 查询当前商户的所有结算数据
        dataMap.put("pageList", mchCheckOutMapper.getPageListByCondition(conditionMap));
        return dataMap;
    }
}
