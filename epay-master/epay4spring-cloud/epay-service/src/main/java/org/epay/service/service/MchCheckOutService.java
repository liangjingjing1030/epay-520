package org.epay.service.service;

import org.epay.dal.dao.mapper.CompareHistorySrcMapper;
import org.epay.dal.dao.mapper.MchCheckOutDetailMapper;
import org.epay.dal.dao.mapper.MchCheckOutMapper;
import org.epay.dal.dao.model.CompareHistorySrc;
import org.epay.dal.dao.model.MchCheckOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

@Component
public class MchCheckOutService {

    @Autowired
    private MchCheckOutMapper mchCheckOutMapper;
    @Autowired
    private MchCheckOutDetailMapper mchCheckOutDetailMapper;
    @Autowired
    private CompareHistorySrcMapper compareHistorySrcMapper;

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
     * 对账分页查询
     * @param conditionMap
     * @return
     */
    public Map<String, Object> selectMchDuiZhangByConditions(Map<String, Object> conditionMap) {
        Map<String, Object> dataMap = new HashMap<>();
        // TODO 暂时不查tgt表
        // T=時差,M=匹配,Q=存疑
            // 1、对账状态为“时差”或“匹配“，只查询src表
                // 查询当前商户的所有对账总数
                dataMap.put("total", compareHistorySrcMapper.getSrcTotalByCondition(conditionMap));
                // 查询当前商户的所有对账数据
                dataMap.put("pageList", compareHistorySrcMapper.getSrcPageListByCondition(conditionMap));
                return dataMap;
    }
    /*public Map<String, Object> selectMchDuiZhangByConditions(Map<String, Object> conditionMap) {
        Map<String, Object> dataMap = new HashMap<>();
        // T=時差,M=匹配,Q=存疑
        if (conditionMap.get("compare_status") != null) {
            String compare_status = (String) conditionMap.get("compare_status");
            // 1、对账状态为“时差”或“匹配“，只查询src表
            if("T".equals(compare_status) || "M".equals(compare_status)) {
                // 查询当前商户的所有对账总数
                dataMap.put("total", compareHistorySrcMapper.getSrcTotalByCondition(conditionMap));
                // 查询当前商户的所有对账数据
                dataMap.put("pageList", compareHistorySrcMapper.getSrcPageListByCondition(conditionMap));
                return dataMap;
            }
        }

        //src的PAY_ORDER_SEQNO 是订单表的pay_order_id， tgt的PAYCOMPANY_ORDER_SEQNO是订单表的channel_order_no
        // 2、其余情况则查询src + tgt
        // 查询当前商户的所有对账总数
        int totalSrc = compareHistorySrcMapper.getSrcTotalByCondition(conditionMap);
        int totalTgt = compareHistorySrcMapper.getTgtTotalByCondition(conditionMap);
        // 查询当前商户的所有对账数据
        List<CompareHistorySrc> srcList = compareHistorySrcMapper.getSrcPageListByCondition(conditionMap);
        List<CompareHistorySrc> tgtList = compareHistorySrcMapper.getTgtPageListByCondition(conditionMap);
        boolean b = srcList.addAll(tgtList);
        dataMap.put("pageList",srcList);
        dataMap.put("total",totalSrc + totalTgt);
        return dataMap;
    }*/


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
