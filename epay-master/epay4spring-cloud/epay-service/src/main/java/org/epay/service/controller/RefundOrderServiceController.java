package org.epay.service.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.epay.common.util.MyBase64;
import org.epay.common.util.MyLog;
import org.epay.dal.dao.model.RefundOrder;
import org.epay.service.service.RefundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 类名: 退款订单查询
 * 作者: HappyDan
 * 时间: 2019年4月21日
 * 版本: V1.0
 */
@RestController
public class RefundOrderServiceController {

    private final MyLog _log = MyLog.getLog(RefundOrderServiceController.class);

    @Autowired
    private RefundOrderService refundOrderService;

    /**
     * 查询退款订单流水信息
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/refund_order/user_query_refund_order")
    public String queryRefundOrder(@RequestParam String jsonParam) {
        _log.info("MyBase64》》user_query_refund_order << {}", jsonParam);
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        JSONObject object = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        _log.info("user_query_refund_order << {}", object.toString());
        
        String mch_id  = "";		// 商户ID
        String start_time = "";		// 开始时间
        String end_time = "";		// 结束时间
        String refund_order_id = "";// 退款订单号
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
                return retObj.toJSONString();
        	}
		} catch (Exception e) {
			retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "mch_id参数缺失");
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
        	refund_order_id = object.getString("refund_order_id").trim();// 退款订单号
		} catch (Exception e) {
			refund_order_id = "";// 退款订单号
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
        _log.info("开始组装退款订单信息 << {}");
        RefundOrder refundOrder = new RefundOrder();
        refundOrder.setMch_id(mch_id);
        refundOrder.setStart_time(start_time);
        refundOrder.setEnd_time(end_time);
        refundOrder.setRefund_order_id(refund_order_id);
        refundOrder.setPay_orderid(pay_order_id);
        refundOrder.setUser_id(user_id);
        refundOrder.setUser_name(user_name);
        if(StringUtils.isNotBlank(status))refundOrder.setStatus(Byte.valueOf(status));
        if(StringUtils.isNotBlank(limit))refundOrder.setLimit(Integer.valueOf(limit));
        if(StringUtils.isNotBlank(offset))refundOrder.setOffset(Integer.valueOf(offset));
        
        List<RefundOrder> retRefundOrderList = refundOrderService.selectRefundOrderByConditions(refundOrder);
        if(retRefundOrderList == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "退款订单信息不存在");
            return retObj.toJSONString();
        }
        _log.info("查询退款订单信息结果 << {}", retRefundOrderList.toString());
        
        Integer ordersNum = refundOrderService.selectRefundOrderNumByConditions(refundOrder);
        
        //组装返回需要数据====================================Start
        List<Map<String, Object>> refundOrderList = new ArrayList<Map<String, Object>>();
    	for (RefundOrder refundOrder1 : retRefundOrderList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("create_time", refundOrder1.getCreate_time());
			map.put("refund_order_id", refundOrder1.getRefund_order_id());
			map.put("pay_orderid", refundOrder1.getPay_orderid());
			map.put("mch_id", refundOrder1.getMch_id());
			map.put("mch_refund_no", refundOrder1.getMch_refund_no());
			map.put("channel_id", refundOrder1.getChannel_id());
			map.put("pay_amount", refundOrder1.getPay_amount());
			map.put("refund_amount", refundOrder1.getRefund_amount());
			map.put("currency", refundOrder1.getCurrency());
			map.put("status", refundOrder1.getStatus());
			map.put("channel_order_no", refundOrder1.getChannel_order_no());
			map.put("channel_res_code", refundOrder1.getChannel_res_code());
			map.put("channel_res_msg", refundOrder1.getChannel_res_msg());
			refundOrderList.add(map);
		}
    	//组装返回需要数据====================================End
    	
    	retObj.put("total_num", ordersNum);
    	retObj.put("result", JSON.toJSON(refundOrderList));
        _log.info("queryRefundOrder >> {}", retObj);
        return CollectionUtils.isEmpty(retObj) ? null : retObj.toJSONString();
    }
    
    /**
     * 创建退款订单信息
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/refund_order/create_refund_order")
    public String createRefundOrder(@RequestParam String jsonParam) {
        _log.info("MyBase64》》接收创建退款订单请求,jsonParam={}", jsonParam);
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001");
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        try {
        	JSONObject object = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        	_log.info("接收创建退款订单请求,jsonParam={}", jsonParam);
        	object.remove("res_key");
        	RefundOrder refundOrder = JSON.parseObject(object.toJSONString(), RefundOrder.class);
            int result = refundOrderService.createRefundOrder(refundOrder);
            retObj.put("result", result);
        }catch (Exception e) {
            retObj.put("code", "9999"); // 系统错误
            retObj.put("msg", "系统错误");
        }
        return retObj.toJSONString();
    }

}
