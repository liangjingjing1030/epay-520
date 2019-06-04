package org.epay.service.service;

import org.epay.dal.dao.mapper.MchCheckOutDetailMapper;
import org.epay.dal.dao.mapper.MchCheckOutMapper;
import org.epay.dal.dao.model.MchCheckOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.SimpleFormatter;

@Component
public class MchCheckOutService {

    @Autowired
    private MchCheckOutMapper mchCheckOutMapper;
    @Autowired
    private MchCheckOutDetailMapper mchCheckOutDetailMapper;

    /**
     * 结算分页查询
     * @param conditionMap
     * @return
     */
    public Map<String, Object> selectMchCheckOutByConditions(Map<String, Object> conditionMap) {
        Map<String, Object> dataMap = new HashMap<>();
        // 查询当前商户的所有结算总数
        dataMap.put("total", mchCheckOutDetailMapper.getTotalByCondition(conditionMap));
        // 查询当前商户的所有结算数据
        dataMap.put("pageList", mchCheckOutDetailMapper.getPageListByCondition(conditionMap));
        return dataMap;
    }

    /**
     * 查询结算汇总数据
     * @param mch_id
     * 2019年6月5日01:09:22
     */
    public MchCheckOut queryMoneySummary(String mch_id) {
        // 商户号 + 结算时间 唯一确定一条数据
        String nowDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        MchCheckOut mchCheckOut = mchCheckOutMapper.selectByMchIdAndUpdateTime(mch_id, nowDate);
        return mchCheckOut;
    }
}
