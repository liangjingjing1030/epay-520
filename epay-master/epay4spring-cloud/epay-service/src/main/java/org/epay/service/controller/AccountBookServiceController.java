package org.epay.service.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.epay.common.util.MyBase64;
import org.epay.common.util.MyLog;
import org.epay.dal.dao.model.AccountBook;
import org.epay.dal.dao.model.PayOrder;
import org.epay.service.service.AccountBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 名称: 云账单接口
 * 作者: HappyDan
 * 时间: 2019年4月20日
 * 版本: V1.0
 */
@RestController
public class AccountBookServiceController {

    private final MyLog _log = MyLog.getLog(AccountBookServiceController.class);

    @Autowired
    private AccountBookService accountBookService;

    /**
     * 客户应交费用查询
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/account_book/user_query_account_book")
    public String queryAccountBook(@RequestParam String jsonParam) {
        _log.info("user_query_account_book << {}", jsonParam);
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            _log.info("user_query_account_book << {}", retObj.toString());
            return retObj.toJSONString();
        }
        
        JSONObject paramObj = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        _log.info("user_query_account_book << {}", paramObj.toString());
        
        String mch_id = ""; 	// 商户ID
        String items_id = "";	// 项目号
        String user_id = ""; 	// 客户唯一标识
        String limit = "";		// 页码
        String offset = "";		// 每页数量
        
        try {
        	mch_id  = paramObj.getString("mch_id").trim();	// 商户ID
        	if(StringUtils.isBlank(mch_id)) {
        		retObj.put("code", "0001"); // 参数错误
                retObj.put("msg", "mch_id参数为空");
                _log.info("user_query_account_book << {}", retObj.toString());
                return retObj.toJSONString();
        	}
		} catch (Exception e) {
			retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "mch_id参数缺失");
            _log.info("user_query_account_book << {}", retObj.toString());
            return retObj.toJSONString();
		}
        try {
        	user_id = paramObj.getString("user_id"); 	// 客户唯一标识
        	if(StringUtils.isBlank(user_id)) {
        		retObj.put("code", "0001"); // 参数错误
                retObj.put("msg", "user_id参数为空");
                _log.info("user_query_account_book << {}", retObj.toString());
                return retObj.toJSONString();
        	}
		} catch (Exception e) {
			retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "user_id参数缺失");
            _log.info("user_query_account_book << {}", retObj.toString());
            return retObj.toJSONString();
		}
        try {
        	items_id = paramObj.getString("items_id");// 项目号
		} catch (Exception e) {
			items_id = "";// 项目号
		}
        try {
        	limit = paramObj.getString("limit").trim();// 页码
		} catch (Exception e) {
			limit = "";// 页码
		}
        try {
        	offset = paramObj.getString("offset").trim();// 每页数量
		} catch (Exception e) {
			offset = "";// 每页数量
		}

        AccountBook accountBook = new AccountBook();
        accountBook.setMch_id(mch_id);
        accountBook.setItems_id(items_id);
        accountBook.setUser_id(user_id);
    	if(StringUtils.isNotBlank(limit)) {
    		accountBook.setLimit(Integer.valueOf(limit));
    	}
    	if(StringUtils.isNotBlank(offset)) {
    		accountBook.setOffset(Integer.valueOf(offset));
    	}
        List<AccountBook> retAccountBook = accountBookService.selectAccountBookByConditions(accountBook);
        if(retAccountBook == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "账单信息不存在");
            return retObj.toJSONString();
        }
        Integer accountBookNum = accountBookService.selectAccountBookNumByConditions(accountBook);
        
        //组装返回需要数据====================================Start
    	List<Map<String, Object>> accountBookList = new ArrayList<Map<String,Object>>();
    	for (AccountBook accountBook1 : retAccountBook) {
    		Map<String, Object> map = new HashMap<String, Object>();
			map.put("account_book_id",	accountBook1.getAccount_book_id());
			map.put("mch_id",	accountBook1.getMch_id());
			map.put("items_id",	accountBook1.getItems_id());
			map.put("user_id",	accountBook1.getUser_id());
			map.put("currency",	accountBook1.getCurrency());
			map.put("items_money",	accountBook1.getItems_money());
			accountBookList.add(map);
    	}
    	retObj.put("total_num", accountBookNum);
    	retObj.put("result", JSON.toJSON(accountBookList));
    	//组装返回需要数据====================================End
        _log.info("selectAccountBookList >> {}", retObj.toJSONString());
        
        return CollectionUtils.isEmpty(retObj) ? null : retObj.toJSONString();
    }

    /**
     * 支付订单的账单查询
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/account_book/selectAccountBookByPrimaryKey")
    public String selectAccountBookByPrimaryKey(@RequestParam String jsonParam) {
        _log.info("selectAccountBookByPrimaryKey << {}", jsonParam);
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        JSONObject paramObj = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        String account_book_id = paramObj.getString("account_book_id"); // 账单号
        
        AccountBook accountBook = accountBookService.selectAccountBookByPrimaryKey(account_book_id);

        if(accountBook == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "账单信息不存在");
            return retObj.toJSONString();
        }
        retObj.put("result", JSON.toJSON(accountBook));
        _log.info("selectAccountBook >> {}", retObj);
        return CollectionUtils.isEmpty(retObj) ? null : retObj.toJSONString();
    }
    
    /**
     * 创建支付订单对应的账单信息
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/account_book/createAccountBook")
    public String createAccountBook(@RequestParam String jsonParam) {
        _log.info("MyBase64》》接收创建支付订单对应的账单信息请求,jsonParam={}", jsonParam);
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001");
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        try {
        	JSONObject object = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        	_log.info("接收创建支付订单对应的账单信息请求,jsonParam={}", object.toString());
        	
        	AccountBook accountBook = JSON.parseObject(object.toJSONString(), AccountBook.class);
            int result = accountBookService.createAccountBook(accountBook);
            retObj.put("result", result);
        }catch (Exception e) {
            retObj.put("code", "9999"); // 系统错误
            retObj.put("msg", "系统错误");
        }
        return retObj.toJSONString();
    }
    
    /**
     * 根据账单号删除账单信息
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/account_book/deleteAccountBookByPrimaryKey")
    public String deleteAccountBookByPrimaryKey(@RequestParam String jsonParam) {
        _log.info("deleteAccountBookByPrimaryKey << {}", jsonParam);
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        JSONObject paramObj = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        String account_book_id = paramObj.getString("account_book_id"); // 账单号
        
        int accountBook = accountBookService.deleteAccountBookByPrimaryKey(account_book_id);

        if(accountBook != 1) {
            retObj.put("code", "0002");
            retObj.put("msg", "删除账单信息失败");
            return retObj.toJSONString();
        }
        retObj.put("result", accountBook);
        _log.info("deleteAccountBookByPrimaryKey >> {}", retObj);
        return CollectionUtils.isEmpty(retObj) ? null : retObj.toJSONString();
    }
    
}
