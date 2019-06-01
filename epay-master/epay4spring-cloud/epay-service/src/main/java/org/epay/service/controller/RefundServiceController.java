package org.epay.service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.epay.common.util.MyBase64;
import org.epay.common.util.MyLog;
import org.epay.dal.dao.model.PayOrder;
import org.epay.service.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 类名: 退款模块服务类
 * 作者: GaoLiang
 * 时间: 2019年5月8日11:16:28
 * 版本: V1.0
 */
@RestController
public class RefundServiceController {

    private final MyLog _log = MyLog.getLog(RefundServiceController.class);

    @Autowired
    private RefundService refundService;

    // PayOrder分页显示
    @RequestMapping(value = "/refund/payOrderPageByConfition")
    public String accountBookPageByConfition(@RequestParam String jsonParam) {
        _log.info("MyBase64》》payOrderPageByConfition << {}", jsonParam);
        String param = new String(MyBase64.decode(jsonParam));
        _log.info(" payOrderPageByConfition << {}", param.toString());
        
        JSONObject paramObj = JSON.parseObject(param);
        String mch_id = paramObj.getString("mch_id");
        String pay_order_id = paramObj.getString("pay_order_id");
        String mch_order_no = paramObj.getString("mch_order_no");
        String channel_id = paramObj.getString("channel_id");
        String user_id = paramObj.getString("user_id");
        String user_name = paramObj.getString("user_name");
        String user_channel_account = paramObj.getString("user_channel_account");
        String status = paramObj.getString("status");
        String startIndex = paramObj.getString("startIndex");
        String pageSize = paramObj.getString("pageSize");

        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("mch_id", mch_id);
        conditionMap.put("pay_order_id", pay_order_id);
        conditionMap.put("mch_order_no", mch_order_no);
        conditionMap.put("channel_id", channel_id);
        conditionMap.put("user_id", user_id);
        conditionMap.put("user_name", user_name);
        conditionMap.put("user_channel_account", user_channel_account);
        if(status != null) {
            conditionMap.put("status", Byte.parseByte(status));
        } else {
            conditionMap.put("status", Byte.parseByte("100"));
        }
        conditionMap.put("startIndex", Integer.parseInt(startIndex));
        conditionMap.put("pageSize", Integer.parseInt(pageSize));

        // 查询订单信息
        Map<String, Object> dataMap = refundService.selectPayOrderByConfition(conditionMap);
        _log.info(" 查询订单信息结果 << {}", dataMap.toString());
        
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        int total = (int) dataMap.get("total");
        if(total == 0) {
            retObj.put("code", "0002");
            retObj.put("msg", "未查询到符合要求的订单,请确认查询条件是否正确!");
            return retObj.toJSONString();
        }

        retObj.put("total", total);
        List<PayOrder> payOrderList = (List<PayOrder>) dataMap.get("pageList");
        retObj.put("count", payOrderList.size());
        int i = 1;
        // payOrderList
        for(PayOrder payOrder : payOrderList) {
            retObj.put("result" + (i++), JSON.toJSON(payOrder));
        }

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

}
