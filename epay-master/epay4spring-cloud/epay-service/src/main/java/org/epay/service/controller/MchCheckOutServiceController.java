package org.epay.service.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.epay.common.util.MyBase64;
import org.epay.common.util.MyLog;
import org.epay.dal.dao.model.*;
import org.epay.service.service.MchChannelService;
import org.epay.service.service.MchCheckOutService;
import org.epay.service.service.RefundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 类名: 结算查询
 * 作者: GaoLiang
 * 时间: 2019-5-15
 * 版本: V1.0
 */
@RestController
public class MchCheckOutServiceController {

    private final MyLog _log = MyLog.getLog(MchCheckOutServiceController.class);

    @Autowired
    private MchCheckOutService mchCheckOutService;

    /**
     * 查询结算流水信息
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/mch_checkOut/mch_checkOut_page")
    public String queryMchCheckOutPage(@RequestParam String jsonParam) {
        _log.info("mch_checkOut_page << {}", jsonParam);
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            _log.info("mch_checkOut_page << {}", retObj.toString());
            return retObj.toJSONString();
        }
        JSONObject object = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        String mch_id  = "";		// 商户ID
        String pay_channel  = "";	// 支付渠道
        String start_time = "";		// 开始时间
        String end_time = "";		// 结束时间
        String startIndex = "";		// 页码
        String pageSize = "";		// 每页数量
        
        try {
        	mch_id  = object.getString("mch_id").trim();	// 商户ID
        	if(StringUtils.isBlank(mch_id)) {
        		retObj.put("code", "0001"); // 参数错误
                retObj.put("msg", "mch_id参数为空");
                _log.info("mch_checkOut_page << {}", retObj.toString());
                return retObj.toJSONString();
        	}
		} catch (Exception e) {
			retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "mch_id参数缺失");
            _log.info("mch_checkOut_page << {}", retObj.toString());
            return retObj.toJSONString();
		}
        try {
            pay_channel = object.getString("pay_channel").trim();//
		} catch (Exception e) {
            pay_channel = "";//
		}
        try {
        	start_time = object.getString("start_time").trim();// 开始时间
		} catch (Exception e) {
			start_time = "";// 开始时间
		}
        try {
        	end_time = object.getString("end_time").trim();// 结束时间
		} catch (Exception e) {
			end_time = "";// 结束时间
		}
        try {
            startIndex = object.getString("startIndex").trim();// 页码
		} catch (Exception e) {
            startIndex = "";// 页码
		}
        try {
            pageSize = object.getString("pageSize").trim();// 每页数量
		} catch (Exception e) {
            pageSize = "";// 每页数量
		}

        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("mch_id", mch_id);
        conditionMap.put("pay_channel", pay_channel);
        conditionMap.put("start_time", start_time);
        conditionMap.put("end_time", end_time);
        conditionMap.put("startIndex", Integer.parseInt(startIndex));
        conditionMap.put("pageSize", Integer.parseInt(pageSize));

        Map<String, Object> dataMap = mchCheckOutService.selectMchCheckOutByConditions(conditionMap);

        int total = (int) dataMap.get("total");
        if(total == 0) {
            retObj.put("code", "0002");
            retObj.put("msg", "当前无结算账单");
            _log.info("mch_checkOut_page << {}", retObj.toString());
            return retObj.toJSONString();
        }

        retObj.put("total", total);
        List<MchCheckOutDetail> mchCheckOutList = (List<MchCheckOutDetail>) dataMap.get("pageList");
        retObj.put("count", mchCheckOutList.size());
        int i = 1;
        // 遍历list
        for(MchCheckOutDetail mchCheckOutDetail : mchCheckOutList) {
            retObj.put("result" + (i++), JSON.toJSON(mchCheckOutDetail));
        }

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

    /**
     * 对账分页查询
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/mch_checkOut/mch_duizhang_page")
    public String queryMchDuiZhangPage(@RequestParam String jsonParam) {
        _log.info("mch_duizhang_page << {}", jsonParam);
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            _log.info("mch_duizhang_page << {}", retObj.toString());
            return retObj.toJSONString();
        }
        JSONObject object = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        String mch_id  = "";		// 商户ID
        String pay_channel  = "";	// 支付渠道
        String compare_status  = "";	// 对账状态
        String start_time = "";		// 开始时间
        String end_time = "";		// 结束时间
        String startIndex = "";		// 页码
        String pageSize = "";		// 每页数量

        try {
        	mch_id  = object.getString("mch_id").trim();	// 商户ID
        	if(StringUtils.isBlank(mch_id)) {
        		retObj.put("code", "0001"); // 参数错误
                retObj.put("msg", "mch_id参数为空");
                _log.info("mch_duizhang_page << {}", retObj.toString());
                return retObj.toJSONString();
        	}
		} catch (Exception e) {
			retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "mch_id参数缺失");
            _log.info("mch_duizhang_page << {}", retObj.toString());
            return retObj.toJSONString();
		}
        try {
            pay_channel = object.getString("pay_channel").trim();//
		} catch (Exception e) {
            pay_channel = "";//
		}
        try {
            compare_status = object.getString("compare_status").trim();//
		} catch (Exception e) {
            compare_status = "";//
		}
        try {
        	start_time = object.getString("start_time").trim();// 开始时间
		} catch (Exception e) {
			start_time = "";// 开始时间
		}
        try {
        	end_time = object.getString("end_time").trim();// 结束时间
		} catch (Exception e) {
			end_time = "";// 结束时间
		}
        try {
            startIndex = object.getString("startIndex").trim();// 页码
		} catch (Exception e) {
            startIndex = "";// 页码
		}
        try {
            pageSize = object.getString("pageSize").trim();// 每页数量
		} catch (Exception e) {
            pageSize = "";// 每页数量
		}

        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("mch_id", mch_id);
        conditionMap.put("pay_channel", pay_channel);
        conditionMap.put("compare_status", compare_status);
        conditionMap.put("start_time", start_time);
        conditionMap.put("end_time", end_time);
        conditionMap.put("startIndex", Integer.parseInt(startIndex));
        conditionMap.put("pageSize", Integer.parseInt(pageSize));

        Map<String, Object> dataMap = mchCheckOutService.selectMchDuiZhangByConditions(conditionMap);

        int total = (int) dataMap.get("total");
        if(total == 0) {
            retObj.put("code", "0002");
            retObj.put("msg", "当前无对账信息");
            _log.info("mch_duizhang_page << {}", retObj.toString());
            return retObj.toJSONString();
        }

        retObj.put("total", total);
        List<CompareHistorySrc> srcList = (List<CompareHistorySrc>) dataMap.get("pageList");

        // 处理时间格式，后台在返回json数据的时候，用自身的序列化机制会把时间变成一段很长的数字
        List<CompareHistorySrc> srcList2 = new ArrayList<>();
        for(CompareHistorySrc src : srcList) {
            Date compareDate = src.getCompareDate();
            Date txDate = src.getTxDate();
            String compareDateStr = new SimpleDateFormat("yyyy-MM-dd").format(compareDate);
            String txDateStr = new SimpleDateFormat("yyyy-MM-dd").format(txDate);
            src.setCompareDateStr(compareDateStr);
            src.setTxDateStr(txDateStr);
            // 取出渠道id
            PayOrder order = src.getOrder();
            src.setChannel_id(order.getChannel_id());
            srcList2.add(src);
        }

        retObj.put("count", srcList2.size());
        int i = 1;
        // 遍历list
        for(CompareHistorySrc compareHistorySrc : srcList2) {
            retObj.put("result" + (i++), JSON.toJSON(compareHistorySrc));
        }

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

}
