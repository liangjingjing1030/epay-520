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
import org.epay.service.service.PayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 名称: 支付订单接口
 * 作者: HappyDan
 * 时间: 2019年4月9日
 * 版本: V1.0
 */
@RestController
public class PayOrderServiceController {

    private final MyLog _log = MyLog.getLog(PayOrderServiceController.class);

    @Autowired
    private PayOrderService payOrderService;
    @Autowired
    private AccountBookService accountBookService;

    /**
     * 根据项目编号查询payOrder列表（状态为已缴费）
     * 2019年6月1日15:20:54
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/bill/queryPayOrderListByItemsId")
    public String queryPayOrderListByItemsId(@RequestParam String jsonParam) {
        _log.info("queryPayOrderListByItemsId << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);

        String mch_id = paramObj.getString("mch_id");
        String items_id = paramObj.getString("items_id");

        // 1、根据项目编号查询accountBook的list
        List<AccountBook> accountBookList = accountBookService.queryAccountBookListByMchIdAndItemsId(mch_id, items_id);

        JSONObject retObj = new JSONObject();
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        if(accountBookList == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "查询账单错误");
            return retObj.toJSONString();
        }
        if(accountBookList.size() < 1) {
            retObj.put("code", "0003");
            retObj.put("msg", "当前账单无用户缴费");
            return retObj.toJSONString();
        }

        List<String> mchOrderNoList = new ArrayList<>();
        for(AccountBook accountBook : accountBookList) {
            mchOrderNoList.add(accountBook.getAccount_book_id());
        }
        // 2、根据accountbook的主键查询payOrder的list（状态为已缴费）
        List<PayOrder> payOrderList = payOrderService.queryPayOrderListByOrderNo(mchOrderNoList);

        if(payOrderList == null) {
            retObj.put("code", "0004");
            retObj.put("msg", "根据项目编号查询状态为已缴费的订单失败");
            return retObj.toJSONString();
        }

        retObj.put("code", "0000");
        retObj.put("count", payOrderList.size());
        // 遍历payOrderList
        int i = 1;
        for(PayOrder payOrder : payOrderList) {
            retObj.put("result" + (i++), JSON.toJSON(payOrder));
        }

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

    /**
     * 创建支付订单信息
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/pay_order/create_pay_order")
    public String createPayOrder(@RequestParam String jsonParam) {
        _log.info("MyBase64》》接收创建支付订单请求,jsonParam={}", jsonParam);
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001");
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        try {
        	JSONObject object = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        	_log.info("接收创建支付订单请求,jsonParam={}", object.toString());
        	
        	object.remove("res_key");
            PayOrder payOrder = JSON.parseObject(object.toJSONString(), PayOrder.class);
            int result = payOrderService.createPayOrder(payOrder);
            retObj.put("result", result);
        }catch (Exception e) {
            retObj.put("code", "9999"); // 系统错误
            retObj.put("msg", "系统错误");
        }
        return retObj.toJSONString();
    }

    /**
     * 查询订单流水明细
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/pay_order/query_order_detail")
    public String queryPayOrderDetail(@RequestParam String jsonParam) {
        _log.info("MyBase64>>queryPayOrderDetail << {}", jsonParam);
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        
        JSONObject object = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        _log.info("queryPayOrderDetail << {}", object.toString());
        
        String user_id = "";		// 客户唯一标识
        String user_name = "";		// 用户名称
        String pay_order_id = "";	// 支付订单号
        String status = "";			// 支付状态
        String start_time = "";		// 开始时间
        String end_time = "";		// 结束时间
        String limit = "";			// 页码
        String offset = "";			// 每页数量
        
        try {
        	user_id = object.getString("user_id").trim();// 客户唯一标识
        	if(StringUtils.isBlank(user_id)) {
        		retObj.put("code", "0001"); // 参数错误
                retObj.put("msg", "user_id参数为空");
                _log.info("user_id参数为空 << {}", retObj.toString());
                return retObj.toJSONString();
        	}
		} catch (Exception e) {
			retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "user_id参数缺失");
            _log.info("user_id参数缺失 << {}", retObj.toString());
            return retObj.toJSONString();
		}
        try {
        	user_name = object.getString("user_name").trim();// 用户名称
		} catch (Exception e) {
			user_name = "";// 用户名称
		}
        try {
        	pay_order_id = object.getString("pay_order_id").trim();// 支付订单号
		} catch (Exception e) {
			pay_order_id = "";// 支付订单号
		}
        try {
        	status = object.getString("status").trim();// 支付状态
		} catch (Exception e) {
			status = "";// 支付状态
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
        	limit = object.getString("limit").trim();// 页码
		} catch (Exception e) {
			limit = "";// 页码
		}
        try {
        	offset = object.getString("offset").trim();// 每页数量
		} catch (Exception e) {
			offset = "";// 每页数量
		}
        
        //根据查询条件组装查询对象====================================Start
    	PayOrder payOrder = new PayOrder();
    	payOrder.setUser_id(user_id);
    	payOrder.setUser_name(user_name);
    	payOrder.setPay_order_id(pay_order_id);
    	payOrder.setStatus(Byte.valueOf(status));
    	payOrder.setStart_time(start_time);
    	payOrder.setEnd_time(end_time);
    	payOrder.setLimit(Integer.valueOf(limit));
    	payOrder.setOffset(Integer.valueOf(offset));
    	//根据查询条件组装查询对象====================================End
    	
        List<PayOrder> retPayOrderDetail = payOrderService.selectPayOrderDetail(payOrder);
        
        if(retPayOrderDetail == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "订单流水信息不存在");
            _log.info("订单流水信息不存在 << {}", retObj.toString());
            return retObj.toJSONString();
        }
    	Integer ordersNum = payOrderService.selectPayOrderDetailNum(payOrder);
    	
    	//组装返回需要数据====================================Start
    	List<Map<String, Object>> payOrderDetailList = new ArrayList<Map<String,Object>>();
    	for (PayOrder payOrder1 : retPayOrderDetail) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("create_time", payOrder1.getCreate_time());
			map.put("mch_id", payOrder1.getMch_id());
			map.put("mch_order_no", payOrder1.getMch_order_no());
			map.put("channel_id", payOrder1.getChannel_id());
			map.put("pay_order_id", payOrder1.getPay_order_id());
			map.put("amount", payOrder1.getAmount());
			map.put("currency", payOrder1.getCurrency());
			map.put("status", payOrder1.getStatus());
			map.put("channel_order_no", payOrder1.getChannel_order_no());
			payOrderDetailList.add(map);
		}
    	//组装返回需要数据====================================End
    	
    	retObj.put("total_num", ordersNum);
    	retObj.put("result", JSON.toJSON(payOrderDetailList));

    	_log.info("queryPayOrderDetail >> {}", retObj.toJSONString());
    	return CollectionUtils.isEmpty(retObj) ? null : retObj.toJSONString();
    }
    
    /**
     * 根据订单号查询订单信息
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/pay_order/selectPayOrderByPrimaryKey")
    public String selectPayOrderByPrimaryKey(@RequestParam String jsonParam) {
        _log.info("MyBase64>>selectPayOrderByPrimaryKey << {}", jsonParam);
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        JSONObject paramObj = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        _log.info("selectPayOrderByPrimaryKey << {}", paramObj.toString());
        String pay_order_id = paramObj.getString("pay_order_id"); // 订单号
        
        PayOrder payOrder = payOrderService.selectPayOrderByPrimaryKey(pay_order_id);

        if(payOrder == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "支付订单信息不存在");
            return retObj.toJSONString();
        }
        retObj.put("result", JSON.toJSON(payOrder));
        _log.info("selectPayOrder >> {}", retObj);
        return retObj.toJSONString();
    }

}
