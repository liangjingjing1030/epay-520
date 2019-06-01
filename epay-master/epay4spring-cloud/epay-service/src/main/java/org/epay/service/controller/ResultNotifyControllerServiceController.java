package org.epay.service.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.epay.common.util.MyBase64;
import org.epay.common.util.MyLog;
import org.epay.dal.dao.model.AccountBook;
import org.epay.dal.dao.model.PayOrder;
import org.epay.dal.dao.model.RefundOrder;
import org.epay.service.service.AccountBookService;
import org.epay.service.service.PayOrderService;
import org.epay.service.service.RefundOrderService;
import org.epay.service.service.ResultNotifyService;
import org.epay.service.utils.ConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 类名: 结果通知接口
 * 作者: HappyDan
 * 时间: 2019年4月29日
 * 版本: V1.0
 */
@RestController
public class ResultNotifyControllerServiceController {

    private final MyLog _log = MyLog.getLog(ResultNotifyControllerServiceController.class);

    @Autowired
    private ResultNotifyService resultNotifyService;
    
    @Autowired
    private PayOrderService payOrderService;
    
    @Autowired
    private RefundOrderService refundOrderService;
    
    @Autowired
    private AccountBookService accountBookService;
    
    @Autowired
    private ConfigEntity configEntity;

    /**
     * 付款结果通知
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/result_notify/payResultNotify")
    public String payResultNotify(@RequestParam String jsonParam) {
        _log.info("MyBase64》》payResultNotify << {}", jsonParam);
        JSONObject retObj = new JSONObject();
        JSONObject paramObj = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        _log.info(" payResultNotify << {}", paramObj);
        JSONObject response = new JSONObject();//响应报文response
        JSONObject retHeader = new JSONObject();//响应报文头map对象
        JSONObject retBody = new JSONObject();//响应报文体map对象
        try{
        	//获取通知参数信息====================================Start
            String merOrderNo = paramObj.getString("merOrderNo").trim();// 订单号
            String txAmt = paramObj.getString("txAmt").trim();// 交易金额
            txAmt = txAmt.substring(0, txAmt.length() - 3) + txAmt.substring(txAmt.length() - 2, txAmt.length());
            //获取通知参数信息====================================End
            
            //判断通知参数信息是否与本平台的订单信息匹配====================================Start
            long tx_amt = Long.parseLong(txAmt);
            _log.info(" 支付平台返回的订单金额 << {}", tx_amt);
            PayOrder payOrder = payOrderService.selectPayOrderByPrimaryKey(merOrderNo);
            if(payOrder == null) {
            	retObj.put("txStatus", "FAIL");
            	retObj.put("txMsg", "未查询到订单信息");
            	_log.error(retObj.toString());
            	return retObj.toJSONString();
            }
            long amount = payOrder.getAmount();
            if(tx_amt != amount) {
            	retObj.put("txStatus", "FAIL");
            	retObj.put("txMsg", "金额与提交订单金额不匹配");
            	_log.error(retObj.toString());
            	return retObj.toJSONString();
            }
            //判断通知参数信息是否与本平台的订单信息匹配====================================End
            _log.info(" 通知参数与订单表数据信息相符<< {}");
            //更新数据库数据====================================Start
            PayOrder pay = payOrder;
            pay.setPay_order_id(merOrderNo);
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        	String myDate = format.format(new Date());
            pay.setPay_succ_time(myDate);
            pay.setUpdate_time(myDate);
            pay.setStatus((byte)1);
            int i = payOrderService.updateStatus(pay);
            _log.info("修改支付信息表数据条数", i);
            //====================================
            AccountBook accountBook = accountBookService.selectAccountBookByPrimaryKey(payOrder.getMch_order_no());
            accountBook.setAccount_book_id(payOrder.getMch_order_no());
            accountBook.setPay_channel(payOrder.getChannel_id());
            if("WX".equals(payOrder.getChannel_id().substring(0, 2))) {
            	accountBook.setChannel_settle_cost(Integer.valueOf(configEntity.getWx_settle_cost()));
            	Long channel_settle_money = (payOrder.getAmount())*(Integer.valueOf(configEntity.getWx_settle_cost()))/10000;
            	accountBook.setChannel_settle_money(channel_settle_money);
            }else if("ALIPAY".equals(payOrder.getChannel_id().substring(0, 6))) {
            	accountBook.setChannel_settle_cost(Integer.valueOf(configEntity.getAlipay_settle_cost()));
            	Long channel_settle_money = (payOrder.getAmount())*(Integer.valueOf(configEntity.getAlipay_settle_cost()))/10000;
            	accountBook.setChannel_settle_money(channel_settle_money);
            }
            accountBook.setPay_time(myDate);
            accountBook.setPay_status((byte)1);
            int j = accountBookService.updateStatus(accountBook);
            _log.info("修改账单信息表数据条数", j);
            //更新数据库数据====================================End
            
            //组装通知数据====================================Start
            retHeader.put("retCode", "SUCCESS");
            retHeader.put("retMsg", "订单支付成功，金额为：" + tx_amt + "元");
            retBody.put("mch_id", payOrder.getMch_id());
            retBody.put("status", "1");
            retBody.put("pay_order_id", payOrder.getPay_order_id());
            retBody.put("amount", amount);
            retBody.put("update_time", myDate);
            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            
            _log.info(" 返回支付平台数据 << {}", response.toString());
            
            resultNotifyService.sendPayResultNotify(response.toJSONString());
        }catch (Exception e) {
            retObj.put("txStatus", "FAIL");
        	retObj.put("txMsg", "内部系统错误");
        	_log.error(retObj.toString());
        	return retObj.toJSONString();
        }
        retObj.put("txStatus", "SUCCESS");
        return retObj.toJSONString();
    }

    /**
     * 退款结果通知
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/result_notify/refundResultNotify")
    public String refundResultNotify(@RequestParam String jsonParam) {
        _log.info("MyBase64》》refundResultNotify << {}", jsonParam);
        JSONObject retObj = new JSONObject();
        JSONObject paramObj = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        _log.info(" refundResultNotify << {}", paramObj.toString());
        JSONObject response = new JSONObject();//响应报文response
        JSONObject retHeader = new JSONObject();//响应报文头map对象
        JSONObject retBody = new JSONObject();//响应报文体map对象
        try{
        	//获取通知参数信息====================================Start
            String merOrderNo = paramObj.getString("merOrderNo").trim();// 订单号
            String txAmt = paramObj.getString("txAmt").trim();// 交易金额
            txAmt = txAmt.substring(0, txAmt.length() - 3) + txAmt.substring(txAmt.length() - 2, txAmt.length());
            //获取通知参数信息====================================End
            
            //判断通知参数信息是否与本平台的订单信息匹配====================================Start
            long tx_amt = Long.parseLong(txAmt);
            _log.info(" 参数金额 << {}", tx_amt);
            RefundOrder refundOrder = refundOrderService.selectRefundOrderByPrimaryKey(merOrderNo);
            if(refundOrder == null) {
            	retObj.put("txStatus", "FAIL");
            	retObj.put("txMsg", "未查询到订单信息");
            	_log.error(retObj.toString());
            	return retObj.toJSONString();
            }
            long amount = refundOrder.getRefund_amount();
            if(tx_amt != amount) {
            	retObj.put("txStatus", "FAIL");
            	retObj.put("txMsg", "金额与退款订单金额不匹配");
            	_log.error(retObj.toString());
            	return retObj.toJSONString();
            }
            //判断通知参数信息是否与本平台的订单信息匹配====================================End
            _log.info(" 通知参数与订单信息相符 << {}");
            //更新数据库数据====================================Start
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        	String myDate = format.format(new Date());
            RefundOrder refund = refundOrder;
            refund.setRefund_order_id(merOrderNo);
            refund.setStatus((byte)2);
            refund.setResult((byte)2);
            refund.setRefund_succ_time(myDate);
            refund.setUpdate_time(myDate);
            int i = refundOrderService.updateRefundOrder(refund);
            _log.info("修改退款信息表数据条数", i);
            //====================================
            PayOrder pay = payOrderService.selectPayOrderByPrimaryKey(refundOrder.getPay_orderid());
            pay.setPay_order_id(refundOrder.getPay_orderid());
            pay.setUpdate_time(myDate);
            pay.setStatus((byte)3);
            int j = payOrderService.updateStatus(pay);
            _log.info("修改支付信息表数据条数", j);
            //更新数据库数据====================================End
            
          //====================================
            AccountBook accountBook = accountBookService.selectAccountBookByPrimaryKey(pay.getMch_order_no());
            accountBook.setAccount_book_id(pay.getMch_order_no());
            accountBook.setPay_status((byte)3);
            int k = accountBookService.updateStatus(accountBook);
            _log.info("修改账单信息表数据条数", k);
            //更新数据库数据====================================End
            
            //组装通知数据====================================Start
            retHeader.put("retCode", "SUCCESS");
            retHeader.put("retMsg", "退款成功，金额为：" + tx_amt + "元");
            retBody.put("mch_id", refundOrder.getMch_id());
            retBody.put("status", "1");
            retBody.put("order_id", refundOrder.getRefund_order_id());
            retBody.put("amount", amount);
            retBody.put("update_time", myDate);
            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            
            _log.info(" 返回支付平台数据 << {}", response.toString());
            
            resultNotifyService.sendRefundResultNotify(response.toJSONString());
        }catch (Exception e) {
            retObj.put("txStatus", "FAIL");
        	retObj.put("txMsg", "内部系统错误");
        	_log.error(retObj.toString());
        	return retObj.toJSONString();
        }
        retObj.put("txStatus", "SUCCESS");
        return retObj.toJSONString();
    }
    
    public static void main(String[] args) {
		String a = "100.00";
		a = a.substring(0, a.length() - 3) + a.substring(a.length() - 2, a.length());
		System.out.println(a);
	}
}
