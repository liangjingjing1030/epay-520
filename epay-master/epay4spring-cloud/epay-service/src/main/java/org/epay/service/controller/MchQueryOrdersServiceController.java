package org.epay.service.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.epay.common.util.MyBase64;
import org.epay.common.util.MyLog;
import org.epay.dal.dao.model.PayOrder;
import org.epay.service.service.MchQueryOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 类名: 商户查询订单流水信息接口类
 * 作者: HappyDan
 * 时间: 2019年4月18日
 * 版本: V1.0
 */
@RestController
public class MchQueryOrdersServiceController {

    private final MyLog _log = MyLog.getLog(MchQueryOrdersServiceController.class);

    @Autowired
    private MchQueryOrdersService mchQueryOrdersService;

    @RequestMapping(value = "/orders/mch_query_orders")
    public String mchQueryOrders(@RequestParam String jsonParam) {
        _log.info("mchQueryOrders << {}", jsonParam);
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            _log.info("mchQueryOrders << {}", retObj.toString());
            return retObj.toJSONString();
        }
    	JSONObject object = JSONObject.parseObject(new String(MyBase64.decode(jsonParam)));
        _log.info("mchQueryOrders >> MyBase64 << {}", object.toJSONString());
        String mch_id  = "";		// 商户ID
        String start_time = "";		// 开始时间
        String end_time = "";		// 结束时间
        String mch_order_no = "";	// 账单号
        String pay_order_id = "";	// 支付订单号
        String user_id = "";		// 客户唯一标识
        String user_name = "";		// 用户名称
        String status = "";			// 支付状态
        String limit = "";			// 页码
        String offset = "";			// 每页数量
        try {
        	mch_id  = object.getString("mch_id").trim();	// 商户ID
        	if(StringUtils.isBlank(mch_id)) {
        		retObj.put("code", "0001"); // 参数错误
                retObj.put("msg", "mch_id参数为空");
                _log.info("mchQueryOrders << {}", retObj.toString());
                return retObj.toJSONString();
        	}
		} catch (Exception e) {
			retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "mch_id参数缺失");
            _log.info("mchQueryOrders << {}", retObj.toString());
            return retObj.toJSONString();
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
        	mch_order_no = object.getString("mch_order_no").trim();// 账单号
		} catch (Exception e) {
			mch_order_no = "";// 账单号
		}
        try {
        	pay_order_id = object.getString("pay_order_id").trim();// 支付订单号
		} catch (Exception e) {
			pay_order_id = "";// 支付订单号
		}
        try {
        	user_id = object.getString("user_id").trim();// 客户唯一标识
		} catch (Exception e) {
			user_id = "";// 客户唯一标识
		}
        try {
        	user_name = object.getString("user_name").trim();// 用户名称
		} catch (Exception e) {
			user_name = "";// 用户名称
		}
        try {
        	status = object.getString("status").trim();// 支付状态
		} catch (Exception e) {
			status = "";// 支付状态
		}
        try {
        	limit = object.getString("limit").trim();// 页码
		} catch (Exception e) {
			limit = "";// 页码
		}
        try {
        	offset = object.getString("offset").trim();// 每页数量
		} catch (Exception e) {
			offset = "";// 每页数量
		}
        
    	PayOrder payOrder = new PayOrder();
    	payOrder.setMch_id(mch_id);
    	payOrder.setStart_time(start_time);
    	payOrder.setEnd_time(end_time);
    	payOrder.setMch_order_no(mch_order_no);
    	payOrder.setPay_order_id(pay_order_id);
    	payOrder.setUser_id(user_id);
    	payOrder.setUser_name(user_name);
    	payOrder.setStatus(Byte.valueOf(status));
    	payOrder.setLimit(Integer.valueOf(limit));
    	payOrder.setOffset(Integer.valueOf(offset));
    	
    	//根据条件查询数据
    	List<PayOrder> retOrders = mchQueryOrdersService.mchQueryOrdersByConditions(payOrder);
        if(retOrders == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "订单流水信息不存在");
            _log.info("mchQueryOrders << {}", retObj.toString());
            return retObj.toJSONString();
        }
    	Integer ordersNum = retOrders.size();
    	//组装返回需要数据====================================Start
    	List<Map<String, Object>> orderList = new ArrayList<Map<String,Object>>();
    	for (PayOrder payOrder1 : retOrders) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("create_time", payOrder1.getCreate_time());
			map.put("mch_id", payOrder1.getMch_id());
			map.put("mch_order_no", payOrder1.getMch_order_no());
			map.put("channel_id", payOrder1.getChannel_id());
			map.put("user_id", payOrder1.getUser_id());
			map.put("user_name", payOrder1.getUser_name());
			map.put("pay_order_id", payOrder1.getPay_order_id());
			map.put("amount", payOrder1.getAmount());
			map.put("currency", payOrder1.getCurrency());
			map.put("status", payOrder1.getStatus());
			orderList.add(map);
		}
    	//组装返回需要数据====================================End
    	
    	retObj.put("total_num", ordersNum);
    	retObj.put("result", JSON.toJSON(orderList));

    	_log.info("MchQueryOrdersServiceController >> {}", retObj.toJSONString());
    	return CollectionUtils.isEmpty(retObj) ? null : retObj.toJSONString();
    }

}
