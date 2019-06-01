package org.epay.service.service;

import org.epay.dal.dao.mapper.PayOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RefundService {

    @Autowired
    private PayOrderMapper payOrderMapper;

    // PayOrder分页查询
    public Map<String, Object> selectPayOrderByConfition(Map<String, Object> conditionMap) {
        Map<String, Object> dataMap = new HashMap<>();
        // 支付状态,0-订单生成，做单独处理
        byte status = (byte) conditionMap.get("status");
        if(status == 0) {
            // 查询符合要求的订单总数
            dataMap.put("total", payOrderMapper.getTotalByConditionWhenStatusIsZero(conditionMap));
            // 查询符合要求的订单
            dataMap.put("pageList", payOrderMapper.getPageListWhenStatusIsZero(conditionMap));
            return dataMap;
        }
        if(status == 100) {
            conditionMap.remove("status");
        }
        // 查询符合要求的订单总数
        dataMap.put("total", payOrderMapper.getTotalByCondition(conditionMap));
        // 查询符合要求的订单
        dataMap.put("pageList", payOrderMapper.getPageList(conditionMap));
        return dataMap;
    }

}
