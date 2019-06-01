package org.epay.mgr.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.epay.common.constant.Constant;
import org.epay.common.constant.PayConstant;
import org.epay.common.util.EPayUtil;
import org.epay.dal.dao.model.AccountBook;
import org.epay.dal.dao.model.AccountFile;
import org.epay.dal.dao.model.PayOrder;
import org.epay.dal.dao.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class RefundController {

//    static final String baseUrl = "http://127.0.0.1:3020/epay";
    @Value("${web.path}")
    String baseUrl;

    // 全部退款
    @RequestMapping("/refund/refundAll")
    @ResponseBody
    public Object refundAll(@RequestParam(value = "items_id", required = true)String items_id,
                              HttpServletRequest request) {

        // 第一步：根据项目编号查询PayOrder中已支付的list---------------------------------------start
        // 从session域中获取当前登录用户
        User user = (User) request.getSession().getAttribute("user");
        String mch_id = user.getLoginName();
        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        // TODO 临时写为 WX_APP = "微信APP支付";
        requestHeaderMap.put("request_channel", "WX_APP");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("items_id", items_id);
        requestBodyMap.put("mch_id", mch_id);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("根据项目编号查询订单接口,请求数据:" + reqData);

        String url = baseUrl + "/user_query_pay_order_by_items_id?";
        // 通过getway调用web工程(账单list)
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("根据项目编号查询订单接口,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

//        Map<String, Object> returnMap = new HashMap<>();
        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            List<Map<String, Object>> payOrderList = (List<Map<String, Object>>) retBody.get("payOrderList");

            // 返回商户信息
            if(payOrderList == null) {
                returnMap.put(Constant.OK, Constant.FAIL);
                returnMap.put(Constant.ERROR_MESSAGE, "系统繁忙，请稍后再试");
            }

            for(Map<String, Object> bodyMap : payOrderList) {
                String pay_order_id = bodyMap.get("pay_order_id").toString();
                String channel_id = bodyMap.get("channel_id").toString();
                String amount = bodyMap.get("amount").toString();
                String user_id = bodyMap.get("user_id").toString();
                String user_name = bodyMap.get("user_name").toString();
                String user_channel_account = bodyMap.get("user_channel_account").toString();

                // 第二步：退款---------------------------------------start
                orderRefund(pay_order_id,
                            mch_id,
                            channel_id,
                            amount,
                            user_id,
                            user_name,
                            user_channel_account,
                            request);

            }
            returnMap.put(Constant.OK, Constant.SUCCESS);
        } else {
            returnMap.put(Constant.OK, Constant.FAIL);
            returnMap.put(Constant.ERROR_MESSAGE, retHeader.get("retMsg"));
        }
        return returnMap;

    }

    // 退款
    @RequestMapping("/refund/orderRefund")
    @ResponseBody
    public Object orderRefund(@RequestParam(value = "pay_order_id", required = true)String pay_order_id,
                              @RequestParam(value = "mch_id", required = true)String mch_id,
                              @RequestParam(value = "channel_id", required = true)String channel_id,
                              @RequestParam(value = "amount", required = true)String amount1,
                              @RequestParam(value = "user_id", required = true)String user_id,
                              @RequestParam(value = "user_name", required = true)String user_name,
                              @RequestParam(value = "user_channel_account", required = true)String user_channel_account,
                              HttpServletRequest request) {

        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        // TODO 临时写为 WX_APP = "微信APP支付";
        requestHeaderMap.put("request_channel", "WX_APP");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("pay_orderid", pay_order_id);
        requestBodyMap.put("mch_id", mch_id);
        requestBodyMap.put("channel_id", channel_id);
        // 将金额转为“分”
        long amount = (long) (Double.parseDouble(amount1) * 100);

        requestBodyMap.put("refund_amount", amount);
        requestBodyMap.put("user_id", user_id);
        requestBodyMap.put("user_name", user_name);
        requestBodyMap.put("user_channel_account", user_channel_account);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("请求退款接口,请求数据:" + reqData);

        String url = baseUrl + "/user_refund_order?";
        // 通过getway调用web工程(账单list)
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("请求退款接口,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

//        Map<String, Object> returnMap = new HashMap<>();
        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {// "retCode"
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            String refund_order_id = (String) retBody.get("refund_order_id");// 退款订单号
            String pay_orderid = (String) retBody.get("pay_order_id");// 支付单号
            int status = (int) retBody.get("status");// 退款状态:0-订单生成,1-退款中,2-退款成功,3-退款失败

            // 返回商户信息
            if(0 == status) {
                returnMap.put(Constant.ERROR_MESSAGE, "退款状态:订单生成");
            } else if(1 == status) {
                returnMap.put(Constant.ERROR_MESSAGE, "正在退款……");
            } else if(2 == status) {
                returnMap.put(Constant.ERROR_MESSAGE, "退款成功!");
            } else if(3 == status) {
                returnMap.put(Constant.ERROR_MESSAGE, "退款失败!");
            }
            returnMap.put(Constant.OK, Constant.SUCCESS);

            // 验签
            /*String checkSign = PayDigestUtil.getSign(retMap, resKey, "sign", "payParams");
            String retSign = (String) retMap.get("sign");
            if(checkSign.equals(retSign)) {
                System.out.println("=========支付中心下单验签成功=========");
            }else {
                System.err.println("=========支付中心下单验签失败=========");
                return null;
            }*/
        } else {
            returnMap.put(Constant.OK, Constant.FAIL);
            returnMap.put(Constant.ERROR_MESSAGE, retHeader.get("retMsg"));
        }
        return returnMap;
    }

    // 订单分页查询
    @RequestMapping(value = "/refund/orderPage", method = RequestMethod.GET)
    @ResponseBody
    public Object orderPage(@RequestParam(value = "pay_order_id", required = false)String pay_order_id,
                                  @RequestParam(value = "mch_order_no", required = false)String mch_order_no,
                                  @RequestParam(value = "channel_id", required = false)String channel_id,
                                  @RequestParam(value = "user_id", required = false)String user_id,
                                  @RequestParam(value = "user_name", required = false)String user_name,
                                  @RequestParam(value = "user_channel_account", required = false)String user_channel_account,
                                  @RequestParam(value = "status", required = false)String status,
                                  @RequestParam(value = "pageNo", required = false)String pageNo1,
                                  @RequestParam(value = "pageSize", required = false)String pageSize1,
                                  HttpServletRequest request) {

        Integer pageNo = Integer.valueOf(pageNo1);
        Integer pageSize = Integer.valueOf(pageSize1);
        Integer startIndex = (pageNo - 1) * pageSize;

        // 从session域中获取当前登录用户
        User user = (User) request.getSession().getAttribute("user");
        String mch_id = user.getLoginName();
        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        requestHeaderMap.put("request_channel", "MGR");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("mch_id", mch_id);
        requestBodyMap.put("pay_order_id", pay_order_id);
        requestBodyMap.put("mch_order_no", mch_order_no);
        requestBodyMap.put("channel_id", channel_id);
        requestBodyMap.put("user_id", user_id);
        requestBodyMap.put("user_name", user_name);
        requestBodyMap.put("user_channel_account", user_channel_account);
        if(!"-99".equals(status)) {
            requestBodyMap.put("status", status);
        }
        requestBodyMap.put("startIndex", startIndex);
        requestBodyMap.put("pageSize", pageSize);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("请求查询订单接口,请求数据:" + reqData);

        String url = baseUrl + "/refund_query_order?";
        // 通过getway调用web工程(账单list)
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("请求查询订单接口,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

//        Map<String, Object> returnMap = new HashMap<>();
        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            List<Map<String, Object>> payOrderList = (List<Map<String, Object>>) retBody.get("payOrderList");
            int total = (int) retBody.get("total");

            List<PayOrder> list = new ArrayList<>();
            for(Map<String, Object> bodyMap : payOrderList) {
                PayOrder payOrder = new PayOrder();
                payOrder.setPay_order_id(bodyMap.get("pay_order_id").toString());
                payOrder.setMch_id(bodyMap.get("mch_id").toString());
                payOrder.setMch_order_no(bodyMap.get("mch_order_no").toString());
                payOrder.setChannel_id(bodyMap.get("channel_id").toString());
                payOrder.setUser_id(bodyMap.get("user_id").toString());
                payOrder.setUser_name(bodyMap.get("user_name").toString());
                payOrder.setUser_channel_account(bodyMap.get("user_channel_account").toString());
                // 将金额处理为“元”
                long amount = Long.parseLong(bodyMap.get("amount").toString());
                DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
                String amountStr = df.format((float) amount / 100) + "";

                payOrder.setAmount(amountStr);
                payOrder.setCurrency(bodyMap.get("currency").toString());
                payOrder.setStatus(Byte.valueOf(bodyMap.get("status").toString()));
                payOrder.setClient_ip(bodyMap.get("client_ip").toString());
                payOrder.setDevice(bodyMap.get("device").toString());
                payOrder.setSubject(bodyMap.get("subject").toString());
                payOrder.setBody(bodyMap.get("body").toString());
                payOrder.setExtra(bodyMap.get("extra").toString());
                payOrder.setChannel_mch_id(bodyMap.get("channel_mch_id").toString());
                payOrder.setChannel_order_no(bodyMap.get("channel_order_no").toString());
                payOrder.setRes_code(bodyMap.get("res_code").toString());
                payOrder.setRes_msg(bodyMap.get("res_msg").toString());
                payOrder.setNotify_url(bodyMap.get("notify_url").toString());
                payOrder.setNotify_count(bodyMap.get("notify_count").toString());
                payOrder.setLast_notify_time(bodyMap.get("last_notify_time").toString());
                payOrder.setExpire_time(bodyMap.get("expire_time").toString());
                payOrder.setPay_succ_time(bodyMap.get("pay_succ_time").toString());
                payOrder.setCreate_time(bodyMap.get("create_time").toString());
                payOrder.setUpdate_time(bodyMap.get("update_time").toString());
                list.add(payOrder);
            }

            // 返回商户信息
            returnMap.put("payOrderList", list);
            returnMap.put("total", total);
            returnMap.put(Constant.ERROR_MESSAGE, Constant.SUCCESS);

            // 验签
            /*String checkSign = PayDigestUtil.getSign(retMap, resKey, "sign", "payParams");
            String retSign = (String) retMap.get("sign");
            if(checkSign.equals(retSign)) {
                System.out.println("=========支付中心下单验签成功=========");
            }else {
                System.err.println("=========支付中心下单验签失败=========");
                return null;
            }*/
        } else {
            returnMap.put(Constant.ERROR_MESSAGE, Constant.FAIL);
        }
        return returnMap;
    }

    // 退款分页查询
    @RequestMapping(value = "/refund/refundPage", method = RequestMethod.GET)
    @ResponseBody
    public Object refundPage(@RequestParam(value = "start_time", required = false)String start_time1,
                             @RequestParam(value = "end_time", required = false)String end_time1,
                             @RequestParam(value = "refund_order_id", required = false)String refund_order_id,
                             @RequestParam(value = "pay_orderid", required = false)String pay_orderid,
                             @RequestParam(value = "user_id", required = false)String user_id,
                             @RequestParam(value = "user_name", required = false)String user_name,
                             @RequestParam(value = "status", required = false)String status,
                             @RequestParam(value = "pageNo", required = false)String pageNo,
                             @RequestParam(value = "pageSize", required = false)String pageSize,
                             HttpServletRequest request) {

        // 处理时间格式：2008-08-08————>20080808000000
        String start_time = "";
        String end_time = "";
        if(StringUtils.isNotBlank(start_time1)) {
            start_time = start_time1.replace("-", "") + "000000";
        }
        if(StringUtils.isNotBlank(end_time1)) {
            end_time = end_time1.replace("-", "") + "000000";
        }

        // 从session域中获取当前登录用户
        User user = (User) request.getSession().getAttribute("user");
        String mch_id = user.getLoginName();

        // 第一次调用查询退款，查询退款总数
        String result = refundPageMethod(mch_id, start_time, end_time, refund_order_id, pay_orderid, user_id, user_name, status, null, null);

        //转换成object
        Map retMap = JSON.parseObject(result);

        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            int totalAll = Integer.parseInt((String) retBody.get("total_num"));

            // 第二次调用查询退款，查询本次分页数据
            String result2 = refundPageMethod(mch_id, start_time, end_time, refund_order_id, pay_orderid, user_id, user_name, status, pageNo, pageSize);
            //转换成object
            Map retMap2 = JSON.parseObject(result2);
            Map<String, Object> retHeader2 = (Map<String, Object>) retMap2.get("response_header");
            if("SUCCESS".equals(retHeader2.get(PayConstant.RETURN_PARAM_RETCODE))) {
                Map<String, Object> retBody2 = (Map<String, Object>) retMap2.get("response_body");
                JSONArray refundOrderJson = (JSONArray) retBody2.get("result");
                // json数组转ArrayList
                ArrayList<String> list = new ArrayList<String>();
                if (refundOrderJson != null) {
                    int len = refundOrderJson.size();
                    for (int i=0;i<len;i++){
                        list.add(refundOrderJson.get(i).toString());
                    }
                }
                List list2 = new ArrayList();
                for(String s : list) {
                    JSONObject jo = JSONObject.parseObject(s);
                    String pay_amountStr = jo.getString("pay_amount");
                    String refund_amountStr = jo.getString("refund_amount");
                    long pay_amount = Long.parseLong(pay_amountStr);
                    long refund_amount = Long.parseLong(refund_amountStr);

                    DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
                    String newPay_amountStr = df.format((float) pay_amount / 100);
                    String newRefund_amounttStr = df.format((float) refund_amount / 100);
                    jo.remove("pay_amount");
                    jo.remove("refund_amount");
                    jo.put("pay_amount", newPay_amountStr);
                    jo.put("refund_amount", newRefund_amounttStr);
                    list2.add(jo);
                }

                // 返回商户信息
                returnMap.put("refundOrderJson", JSONArray.parseArray(JSON.toJSONString(list2)));
                returnMap.put("total", totalAll);
                returnMap.put(Constant.ERROR_MESSAGE, Constant.SUCCESS);
            } else {
                returnMap.put(Constant.ERROR_MESSAGE, retHeader.get(PayConstant.RETURN_PARAM_RETMSG) + "2");
            }
        } else {
            returnMap.put(Constant.ERROR_MESSAGE, retHeader.get(PayConstant.RETURN_PARAM_RETMSG) + "1");
        }

        return returnMap;
    }
    public String refundPageMethod(String mch_id, String start_time, String end_time, String refund_order_id, String pay_orderid, String user_id, String user_name, String status, String pageNo, String pageSize) {

        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        // TODO 临时写为 WX_APP = "微信APP支付";
        requestHeaderMap.put("request_channel", "WX_APP");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("mch_id", mch_id);
        requestBodyMap.put("start_time", start_time);
        requestBodyMap.put("end_time", end_time);
        requestBodyMap.put("refund_order_id", refund_order_id);
        requestBodyMap.put("pay_orderid", pay_orderid);
        requestBodyMap.put("user_id", user_id);
        requestBodyMap.put("user_name", user_name);
        if(!"-99".equals(status)) {
            requestBodyMap.put("status", status);
        }
        requestBodyMap.put("limit", pageNo);
        requestBodyMap.put("offset", pageSize);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("请求查询退款订单接口,请求数据:" + reqData);

        String url = baseUrl + "/user_query_refund_order?";
        // 通过getway调用web工程(账单list)
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("请求查询退款订单接口,响应数据:" + result);
        return result;
    }

}
