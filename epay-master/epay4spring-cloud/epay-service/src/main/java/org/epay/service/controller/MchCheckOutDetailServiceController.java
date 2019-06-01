package org.epay.service.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.epay.common.util.MyBase64;
import org.epay.common.util.MyLog;
import org.epay.dal.dao.model.AccountBook;
import org.epay.dal.dao.model.MchCheckOut;
import org.epay.service.service.AccountBookService;
import org.epay.service.service.MchCheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类名: 结算明细查询
 * 作者: GaoLiang
 * 时间: 2019-5-16
 * 版本: V1.0
 */
@RestController
public class MchCheckOutDetailServiceController {

    private final MyLog _log = MyLog.getLog(MchCheckOutDetailServiceController.class);

    @Autowired
    private AccountBookService accountBookService;

    /**
     * 查询结算明细信息
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/account_book/user_query_mchCheckOutDetail")
    public String queryMchCheckOutPage(@RequestParam String jsonParam) {
        _log.info("user_query_mchCheckOutDetail << {}", jsonParam);
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            _log.info("user_query_mchCheckOutDetail << {}", retObj.toString());
            return retObj.toJSONString();
        }
        JSONObject object = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        _log.info("user_query_mchCheckOutDetail << {}", object.toString());
        String mch_id  = "";		// 商户ID
        String items_id  = "";		// 项目编号
        String startIndex = "";		// 页码
        String pageSize = "";		// 每页数量
        
        try {
        	mch_id  = object.getString("mch_id").trim();	// 商户ID
            items_id  = object.getString("items_id").trim();	// 商户ID
        	if(StringUtils.isBlank(mch_id)) {
        		retObj.put("code", "0001"); // 参数错误
                retObj.put("msg", "mch_id参数为空");
                _log.info("user_query_mchCheckOutDetail << {}", retObj.toString());
                return retObj.toJSONString();
        	}
        	if(StringUtils.isBlank(items_id)) {
        		retObj.put("code", "0001"); // 参数错误
                retObj.put("msg", "items_id参数为空");
                _log.info("user_query_mchCheckOutDetail << {}", retObj.toString());
                return retObj.toJSONString();
        	}
		} catch (Exception e) {
			retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "mch_id参数缺失");
            _log.info("user_query_mchCheckOutDetail << {}", retObj.toString());
            return retObj.toJSONString();
		}
        try {
            items_id = object.getString("items_id").trim();// 项目编号
		} catch (Exception e) {
            items_id = "";// 项目编号
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

        int startIndexNum = Integer.parseInt(startIndex);
        int pageSizeNum = Integer.parseInt(pageSize);

        Map<String, Object> dataMap = accountBookService.selectAccountBookByMchIdAndItemsId(mch_id, items_id, startIndexNum, pageSizeNum);

        int total = (int) dataMap.get("total");
        if(total == 0) {
            retObj.put("code", "0002");
            retObj.put("msg", "当前无结算账单详情");
            _log.info("user_query_mchCheckOutDetail << {}", retObj.toString());
            return retObj.toJSONString();
        }
        int allMoney = (int) dataMap.get("allMoney");
        int readMoney = (int) dataMap.get("readMoney");

        retObj.put("allMoney", allMoney);
        retObj.put("readMoney", readMoney);
        retObj.put("total", total);
        List<AccountBook> accountBookList = (List<AccountBook>) dataMap.get("pageList");
        retObj.put("count", accountBookList.size());
        int i = 1;
        // 遍历list
        for(AccountBook accountBook : accountBookList) {
            retObj.put("result" + (i++), JSON.toJSON(accountBook));
        }

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

}
