package org.epay.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;
import org.apache.commons.lang.StringUtils;
import org.epay.common.constant.PayConstant;
import org.epay.common.util.EPayUtil;
import org.epay.common.util.MyLog;
import org.epay.web.service.AccountFilePageServiceClient;
import org.epay.web.service.MchChannelServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类名: 商户账单信息查询
 * 作者: GaoLiang
 * 时间: 2019年4月29日
 * 版本: V1.0
 */
@RestController
public class BillController {

    private final MyLog _log = MyLog.getLog(BillController.class);

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private MchChannelServiceClient mchChannelServiceClient;

    @Autowired
    private AccountFilePageServiceClient accountFileServiceClient;

    /**
     * 导入账单
     * 1.保存文件信息到accountFile表中
     * 2.解析表中的数据保存到accountBook表中
     */
    @RequestMapping(value = "/bill_import_accountFile")
    public String importAccountFile(@RequestParam String params) {
        _log.info("###### 开始接收导入账单请求 ######");
        JSONObject object;
        String logPrefix = "【导入账单】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        // 获取本地服务实例(获取service工程)
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/bill_import_accountFile, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
            // 将请求参数转换为JSONObject对象
            object = JSONObject.parseObject(params);
            // 获取请求头
            JSONObject channelObj = object.getJSONObject("request_header");
            // 从请求头中获取渠道信息
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject payContext = new JSONObject();
            // 验证参数有效性
            /*String errorMessage = validateParams(object, payContext);//携带请求参数+响应私钥
            if (!"success".equalsIgnoreCase(errorMessage)) {
                _log.warn(errorMessage);
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, errorMessage, null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            _log.debug("请求参数及签名校验通过");*/

            // 获取请求体
            JSONObject requestBody = object.getJSONObject("request_body");
            JSONObject accountFile2 = requestBody.getJSONObject("accountFile");// 账单信息
            Integer arrayCount = requestBody.getInteger("arrayCount");// 数组个数/accountBook个数/上传的表中行数
            for(int i = 1; i <= arrayCount; i++) {
                JSONArray array = requestBody.getJSONArray("array" + i);
                payContext.put("array" + i, array);
            }

            payContext.put("arrayCount", arrayCount);
            payContext.put("accountFile", accountFile2);
            // 查询商户账单信息list
//            String retStr = mchInfoServiceClient.mchQueryOrders(payContext.toJSONString());// 多个参数用这个
            String retStr = accountFileServiceClient.importAccountFile(payContext.toJSONString());
            // 将商户账单信息转换为JSONObject对象
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}导入账单,结果:{}", logPrefix, retObj);

            // 0000表示查询成功，且查询对象不为null
            if(!"0000".equals(retObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }

            //获取导入结果
            boolean importOK = retObj.getBoolean("result");

            //组装返回报文response
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "导入账单处理完成", PayConstant.RETURN_VALUE_SUCCESS, null);
            retBody.put("result", importOK);

            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            _log.info("导入账单完成,channel={}", response);
            _log.info("###### 导入账单完成处理完成 ######");
            // 管理平台账单
            /*if("MGR".equals(request_channel)) {
            	return EPayUtil.makeRetData(response, payContext.getString("res_key"));
            }else {
            	return EPayUtil.makeRetData(response);
            }*/
            return EPayUtil.makeRetData(response);//JSON.toJSONString

        }catch (Exception e) {
            _log.error(e, "");
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null);
            response.put("response_header", retHeader);
            return EPayUtil.makeRetFail(response);
        }
    }

    /**
     * 商户信息查询
     * 1)先验证接口参数以及签名信息
     * 2)根据参数查询商户账单信息
     * 3)返回商户数据
     */
    @RequestMapping(value = "/bill_query_accountFile")
    public String mchQueryOrders(@RequestParam String params) {
        _log.info("###### 开始接收商户账单信息请求 ######");
        JSONObject object;
        String logPrefix = "【商户账单信息查询】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        // 获取本地服务实例(获取service工程)
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/bill_query_accountFile, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
            // 将请求参数转换为JSONObject对象
            object = JSONObject.parseObject(params);
            // 获取请求头
            JSONObject channelObj = object.getJSONObject("request_header");
            // 从请求头中获取渠道信息
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject payContext = new JSONObject();
            // 验证参数有效性
            /*String errorMessage = validateParams(object, payContext);//携带请求参数+响应私钥
            if (!"success".equalsIgnoreCase(errorMessage)) {
                _log.warn(errorMessage);
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, errorMessage, null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            _log.debug("请求参数及签名校验通过");*/

            // 获取请求体
            JSONObject requestBody = object.getJSONObject("request_body");
            String mch_id = requestBody.getString("mch_id");// 商户ID
            String startIndex = requestBody.getString("startIndex");
            String pageSize = requestBody.getString("pageSize");//

            payContext.put("mch_id", mch_id);
            payContext.put("startIndex", startIndex);
            payContext.put("pageSize", pageSize);
            // 查询商户账单信息list
//            String retStr = mchInfoServiceClient.mchQueryOrders(payContext.toJSONString());// 多个参数用这个
            String retStr = accountFileServiceClient.AccountFilePageByMchId(payContext.toJSONString());
            // 将商户账单信息转换为JSONObject对象
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}查询商户账单信息,结果:{}", logPrefix, retObj);

            // 0000表示查询成功，且查询对象不为null
            if(!"0000".equals(retObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (retObj == null) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "商户账单信息不存在", null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }

            List<Map<String, Object>> list = new ArrayList<>();
            //获取查询到的渠道总条数
            int total = retObj.getInteger("total");
            // 本次分页查询的总条数
            int count = retObj.getInteger("count");
            for(int i = 1; i <= count; i++) {
                // 结果对象
                JSONObject accountFile = retObj.getJSONObject("result" + i);
                Map<String, Object> map = new HashMap<String, Object>();

                map.put("file_id", accountFile.getString("file_id") == null ? "" : accountFile.getString("file_id"));
                map.put("mch_id", accountFile.getString("mch_id") == null ? "" : accountFile.getString("mch_id"));
                map.put("items_id",	accountFile.getString("items_id") == null ? "" : accountFile.getString("items_id"));
                map.put("items_name",	accountFile.getString("items_name") == null ? "" : accountFile.getString("items_name"));
                map.put("items_type",	accountFile.getString("items_type") == null ? "" : accountFile.getString("items_type"));
                map.put("items_filename",	accountFile.getString("items_filename") == null ? "" : accountFile.getString("items_filename"));
                map.put("upload_date", accountFile.getString("upload_date") == null ? "" : accountFile.getString("upload_date"));
                map.put("affect_date", accountFile.getString("affect_date") == null ? "" : accountFile.getString("affect_date"));
                map.put("expiry_date", accountFile.getString("expiry_date") == null ? "" : accountFile.getString("expiry_date"));
                list.add(map);
            }

            //组装返回报文response
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "商户账单信息查询成功", PayConstant.RETURN_VALUE_SUCCESS, null);
            retBody.put("accountFileList", list);
            retBody.put("total", total);

            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            _log.info("商户账单信息查询成功,channel={}", response);
            _log.info("###### 商户账单信息查询处理完成 ######");
            // 管理平台账单
            /*if("MGR".equals(request_channel)) {
            	return EPayUtil.makeRetData(response, payContext.getString("res_key"));
            }else {
            	return EPayUtil.makeRetData(response);
            }*/
            return EPayUtil.makeRetData(response);//JSON.toJSONString

        }catch (Exception e) {
            _log.error(e, "");
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null);
            response.put("response_header", retHeader);
            return EPayUtil.makeRetFail(response);
        }
    }

    /*
     *   accountBook分页查询
     */
    @RequestMapping(value = "/bill_query_accountBook")
    public String queryAccountBook(@RequestParam String params) {
        _log.info("###### 开始接收查询商户订单信息请求 ######");
        JSONObject object;
        String logPrefix = "【商户订单信息查询】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        // 获取本地服务实例(获取service工程)
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/bill_query_accountBook, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
            // 将请求参数转换为JSONObject对象
            object = JSONObject.parseObject(params);
            // 获取请求头
            JSONObject channelObj = object.getJSONObject("request_header");
            // 从请求头中获取渠道信息
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject payContext = new JSONObject();
            // 验证参数有效性
            /*String errorMessage = validateParams(object, payContext);//携带请求参数+响应私钥
            if (!"success".equalsIgnoreCase(errorMessage)) {
                _log.warn(errorMessage);
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, errorMessage, null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            _log.debug("请求参数及签名校验通过");*/

            // 获取请求体
            JSONObject requestBody = object.getJSONObject("request_body");
            String mch_id = requestBody.getString("mch_id");                // 商户ID
            String items_id = requestBody.getString("items_id");            // 项目编号
            String items_name = requestBody.getString("items_name");        // 项目名称
            String items_filename = requestBody.getString("items_filename");// 文件名称
            String upload_date = requestBody.getString("upload_date");      // 上传日期
            String status = requestBody.getString("status");                // 状态
            String user_id = requestBody.getString("user_id");              // 用户标识
            String user_name = requestBody.getString("user_name");          // 用户名称
            String startIndex = requestBody.getString("startIndex");
            String pageSize = requestBody.getString("pageSize");

            payContext.put("mch_id", mch_id);
            payContext.put("items_id", items_id);
            payContext.put("items_name", items_name);
            payContext.put("items_filename", items_filename);
            payContext.put("upload_date", upload_date);
            payContext.put("status", status);
            payContext.put("user_id", user_id);
            payContext.put("user_name", user_name);
            payContext.put("startIndex", startIndex);
            payContext.put("pageSize", pageSize);
            // 查询商户订单信息list
//            String retStr = mchInfoServiceClient.mchQueryOrders(payContext.toJSONString());// 多个参数用这个
            String retStr = accountFileServiceClient.AccountBookPageByConfition(payContext.toJSONString());
            // 将商户订单信息转换为JSONObject对象
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}查询商户订单信息,结果:{}", logPrefix, retObj);

            // 0002:未查询到符合要求的订单
            if(!"0000".equals(retObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (retObj == null) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "商户订单信息不存在", null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }

            List<Map<String, Object>> list = new ArrayList<>();
            //获取查询到的订单总条数
            int total = retObj.getInteger("total");
            // 本次分页查询的总条数
            int count = retObj.getInteger("count");
            for(int i = 1; i <= count; i++) {
                // 结果对象
                JSONObject accountBook = retObj.getJSONObject("result" + i);
                JSONObject accountFile = retObj.getJSONObject("anotherResult" + i);
                Map<String, Object> map = new HashMap<String, Object>();

                map.put("account_book_id", accountBook.getString("account_book_id") == null ? "" : accountBook.getString("account_book_id"));
                map.put("mch_id", accountBook.getString("mch_id") == null ? "" : accountBook.getString("mch_id"));
                map.put("items_id",	accountBook.getString("items_id") == null ? "" : accountBook.getString("items_id"));
                map.put("user_id",	accountBook.getString("user_id") == null ? "" : accountBook.getString("user_id"));
                map.put("user_name",	accountBook.getString("user_name") == null ? "" : accountBook.getString("user_name"));
                map.put("currency",	accountBook.getString("currency") == null ? "" : accountBook.getString("currency"));
                map.put("items_money", accountBook.getLong("items_money") == null ? "" : accountBook.getLong("items_money"));
                map.put("pay_time", accountBook.getString("pay_time") == null ? "" : accountBook.getString("pay_time"));
                map.put("pay_status", accountBook.getByte("pay_status") == null ? "" : accountBook.getByte("pay_status"));

                map.put("items_name", accountFile.getString("items_name") == null ? "" : accountFile.getString("items_name"));
                map.put("items_filename", accountFile.getString("items_filename") == null ? "" : accountFile.getString("items_filename"));
                map.put("upload_date", accountFile.getString("upload_date") == null ? "" : accountFile.getString("upload_date"));
                map.put("affect_date", accountFile.getString("affect_date") == null ? "" : accountFile.getString("affect_date"));
                map.put("expiry_date", accountFile.getString("expiry_date") == null ? "" : accountFile.getString("expiry_date"));
                list.add(map);
            }

            //组装返回报文response
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "商户订单信息查询成功", PayConstant.RETURN_VALUE_SUCCESS, null);
            retBody.put("accountBookList", list);
            retBody.put("total", total);

            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            _log.info("商户订单信息查询成功,channel={}", response);
            _log.info("###### 商户订单信息查询处理完成 ######");
            // 管理平台账单
            /*if("MGR".equals(request_channel)) {
            	return EPayUtil.makeRetData(response, payContext.getString("res_key"));
            }else {
            	return EPayUtil.makeRetData(response);
            }*/
            return EPayUtil.makeRetData(response);//JSON.toJSONString

        }catch (Exception e) {
            _log.error(e, "");
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null);
            response.put("response_header", retHeader);
            return EPayUtil.makeRetFail(response);
        }
    }

    /*
     *   accountBook + payOrder分页查询
     */
    @RequestMapping(value = "/bill_query_accountBookAndPayOrder")
    public String queryAccountBookAndPayOrder(@RequestParam String params) {
        _log.info("###### 开始接收查询商户交易信息请求 ######");
        JSONObject object;
        String logPrefix = "【商户交易信息查询】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        // 获取本地服务实例(获取service工程)
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/bill_query_accountBookAndPayOrder, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
            // 将请求参数转换为JSONObject对象
            object = JSONObject.parseObject(params);
            // 获取请求头
            JSONObject channelObj = object.getJSONObject("request_header");
            // 从请求头中获取渠道信息
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject payContext = new JSONObject();
            // 验证参数有效性
            /*String errorMessage = validateParams(object, payContext);//携带请求参数+响应私钥
            if (!"success".equalsIgnoreCase(errorMessage)) {
                _log.warn(errorMessage);
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, errorMessage, null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            _log.debug("请求参数及签名校验通过");*/

            // 获取请求体
            JSONObject requestBody = object.getJSONObject("request_body");
            String mch_id = requestBody.getString("mch_id");                            // 商户ID
            String items_id = requestBody.getString("items_id");                        // 项目编号
            String user_id = requestBody.getString("user_id");                          // 用户标识
            String user_name = requestBody.getString("user_name");                      // 用户名称
            String pay_time = requestBody.getString("pay_time");                        // 支付时间
            String pay_status = requestBody.getString("pay_status");                    // 支付状态
            String settle_status = requestBody.getString("settle_status");              // 结算状态
            String pay_channel = requestBody.getString("pay_channel");                  // 支付渠道
            String mch_order_no = requestBody.getString("mch_order_no");                // 账单号
            String user_channel_account = requestBody.getString("user_channel_account");// 客户渠道账号
            String channel_mch_id = requestBody.getString("channel_mch_id");            // 渠道商户ID
            String channel_order_no = requestBody.getString("channel_order_no");        // 渠道订单号
            String res_code = requestBody.getString("res_code");                        // 渠道支付码
            String expire_time = requestBody.getString("expire_time");                  // 订单失效时间
            String startIndex = requestBody.getString("startIndex");
            String pageSize = requestBody.getString("pageSize");

            payContext.put("mch_id", mch_id);
            payContext.put("items_id", items_id);
            payContext.put("user_id", user_id);
            payContext.put("user_name", user_name);
            payContext.put("pay_time", pay_time);
            payContext.put("pay_status", pay_status);
            payContext.put("settle_status", settle_status);
            payContext.put("pay_channel", pay_channel);
            payContext.put("mch_order_no", mch_order_no);
            payContext.put("user_channel_account", user_channel_account);
            payContext.put("channel_mch_id", channel_mch_id);
            payContext.put("channel_order_no", channel_order_no);
            payContext.put("res_code", res_code);
            payContext.put("expire_time", expire_time);
            payContext.put("startIndex", startIndex);
            payContext.put("pageSize", pageSize);
            // 查询accountBook+payOrder信息list
            String retStr = accountFileServiceClient.AccountBookAndPayOrderPageByConfition(payContext.toJSONString());
            // 将商户订单信息转换为JSONObject对象
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}查询商户订单+账单信息,结果:{}", logPrefix, retObj);

            // 0002:未查询到符合要求的订单
            if(!"0000".equals(retObj.getString("code"))) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (retObj == null) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "商户交易信息不存在", null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }

            List<Map<String, Object>> list = new ArrayList<>();
            //获取查询到的订单+账单总条数
            int total = retObj.getInteger("total");
            // 本次分页查询的订单+账单总条数
            int count = retObj.getInteger("count");
            for(int i = 1; i <= count; i++) {
                // 结果对象
                JSONObject accountBook = retObj.getJSONObject("result" + i);
                Map<String, Object> map = new HashMap<String, Object>();

                map.put("account_book_id", accountBook.getString("account_book_id") == null ? "" : accountBook.getString("account_book_id"));
                map.put("items_id", accountBook.getString("items_id") == null ? "" : accountBook.getString("items_id"));
                map.put("user_id", accountBook.getString("user_id") == null ? "" : accountBook.getString("user_id"));
                map.put("user_name",	accountBook.getString("user_name") == null ? "" : accountBook.getString("user_name"));
                map.put("pay_time",	accountBook.getString("pay_time") == null ? "" : accountBook.getString("pay_time"));
                map.put("pay_status",	accountBook.getByte("pay_status") == null ? "" : accountBook.getByte("pay_status"));
                map.put("settle_status",	accountBook.getByte("settle_status") == null ? "" : accountBook.getByte("settle_status"));
                map.put("pay_channel", accountBook.getString("pay_channel") == null ? "" : accountBook.getString("pay_channel"));
                map.put("mch_order_no", accountBook.getString("mch_order_no") == null ? "" : accountBook.getString("mch_order_no"));
                map.put("user_channel_account", accountBook.getString("user_channel_account") == null ? "" : accountBook.getString("user_channel_account"));
                map.put("channel_mch_id", accountBook.getString("channel_mch_id") == null ? "" : accountBook.getString("channel_mch_id"));
                map.put("channel_order_no", accountBook.getString("channel_order_no") == null ? "" : accountBook.getString("channel_order_no"));
                map.put("res_code", accountBook.getString("res_code") == null ? "" : accountBook.getString("res_code"));
                map.put("expire_time", accountBook.getString("expire_time") == null ? "" : accountBook.getString("expire_time"));
                list.add(map);
            }

            //组装返回报文response
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "商户交易信息查询成功", PayConstant.RETURN_VALUE_SUCCESS, null);
            retBody.put("accountBookAndPayOrderList", list);
            retBody.put("total", total);

            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            _log.info("商户交易信息查询成功,channel={}", response);
            _log.info("###### 商户交易信息查询处理完成 ######");
            // 管理平台账单
            if("MGR".equals(request_channel)) {
                return EPayUtil.makeRetData(response, payContext.getString("res_key"));
            }else {
                return EPayUtil.makeRetData(response);
            }

        }catch (Exception e) {
            _log.error(e, "");
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null);
            response.put("response_header", retHeader);
            return EPayUtil.makeRetFail(response);
        }
    }

    /*
     *   accountFile分页查询+统计
     */
    @RequestMapping(value = "/bill_query_accountFile_addCount")
    public String queryAccountFileAddCount(@RequestParam String params) {
        _log.info("###### 开始接收查询商户订单信息请求 ######");
        JSONObject object;
        String logPrefix = "【商户账单统计查询】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        // 获取本地服务实例(获取service工程)
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/bill_query_accountFile_addCount, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
            // 将请求参数转换为JSONObject对象
            object = JSONObject.parseObject(params);
            // 获取请求头
            JSONObject channelObj = object.getJSONObject("request_header");
            // 从请求头中获取渠道信息
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject payContext = new JSONObject();
            // 验证参数有效性
            /*String errorMessage = validateParams(object, payContext);//携带请求参数+响应私钥
            if (!"success".equalsIgnoreCase(errorMessage)) {
                _log.warn(errorMessage);
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, errorMessage, null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            _log.debug("请求参数及签名校验通过");*/

            // 获取请求体
            JSONObject requestBody = object.getJSONObject("request_body");
            String mch_id = requestBody.getString("mch_id");                // 商户ID
            String startIndex = requestBody.getString("startIndex");
            String pageSize = requestBody.getString("pageSize");

            payContext.put("mch_id", mch_id);
            payContext.put("startIndex", startIndex);
            payContext.put("pageSize", pageSize);
            // 查询商户订单信息list
            String retStr = accountFileServiceClient.AccountFilePageAddCount(payContext.toJSONString());
            // 将商户订单信息转换为JSONObject对象
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}查询账单统计信息,结果:{}", logPrefix, retObj);

            // 0002:未查询到符合要求的订单
            if(!"0000".equals(retObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (retObj == null) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "商户账单统计不存在", null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }

            List<Map<String, Object>> list = new ArrayList<>();
            //获取查询到的accountFile总条数
            int total = retObj.getInteger("total");
            // 本次分页查询的总条数
            int count = retObj.getInteger("count");
            for(int i = 1; i <= count; i++) {
                // 结果对象
                JSONObject accountFileForCount = retObj.getJSONObject("result" + i);
                Map<String, Object> map = new HashMap<String, Object>();

                map.put("file_id", accountFileForCount.getString("file_id") == null ? "" : accountFileForCount.getString("file_id"));
                map.put("mch_id", accountFileForCount.getString("mch_id") == null ? "" : accountFileForCount.getString("mch_id"));
                map.put("items_id",	accountFileForCount.getString("items_id") == null ? "" : accountFileForCount.getString("items_id"));
                map.put("items_name",	accountFileForCount.getString("items_name") == null ? "" : accountFileForCount.getString("items_name"));
                map.put("items_type",	accountFileForCount.getString("items_type") == null ? "" : accountFileForCount.getString("items_type"));
                map.put("items_filename",	accountFileForCount.getString("items_filename") == null ? "" : accountFileForCount.getString("items_filename"));
                map.put("upload_date", accountFileForCount.getString("upload_date") == null ? "" : accountFileForCount.getString("upload_date"));
                map.put("affect_date", accountFileForCount.getString("affect_date") == null ? "" : accountFileForCount.getString("affect_date"));
                map.put("expiry_date", accountFileForCount.getString("expiry_date") == null ? "" : accountFileForCount.getString("expiry_date"));

                map.put("totalMoney", accountFileForCount.getLong("totalMoney") == null ? "" : accountFileForCount.getLong("totalMoney"));
                map.put("readMoney", accountFileForCount.getLong("readMoney") == null ? "" : accountFileForCount.getLong("readMoney"));
                map.put("backMoney", accountFileForCount.getLong("backMoney") == null ? "" : accountFileForCount.getLong("backMoney"));
                list.add(map);
            }

            //组装返回报文response
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "商户账单统计信息查询成功", PayConstant.RETURN_VALUE_SUCCESS, null);
            retBody.put("accountFileForCountList", list);
            retBody.put("total", total);

            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            _log.info("商户账单统计信息查询成功,channel={}", response);
            _log.info("###### 商户账单统计信息查询处理完成 ######");
            // 管理平台账单
            if("MGR".equals(request_channel)) {
            	return EPayUtil.makeRetData(response, payContext.getString("res_key"));
            }else {
            	return EPayUtil.makeRetData(response);
            }

        }catch (Exception e) {
            _log.error(e, "");
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null);
            response.put("response_header", retHeader);
            return EPayUtil.makeRetFail(response);
        }
    }


    /**
     * 导出全部账单
     */
    @RequestMapping(value = "/bill_export_allAccountBook")
    public String exportAllAccountBook(@RequestParam String params) {
        _log.info("###### 开始接收导出全部账单请求 ######");
        JSONObject object;
        String logPrefix = "【导出全部账单】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        // 获取本地服务实例(获取service工程)
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/bill_export_allAccountBook, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
            // 将请求参数转换为JSONObject对象
            object = JSONObject.parseObject(params);
            // 获取请求头
            JSONObject channelObj = object.getJSONObject("request_header");
            // 从请求头中获取渠道信息
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject payContext = new JSONObject();
            // 验证参数有效性
            /*String errorMessage = validateParams(object, payContext);//携带请求参数+响应私钥
            if (!"success".equalsIgnoreCase(errorMessage)) {
                _log.warn(errorMessage);
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, errorMessage, null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            _log.debug("请求参数及签名校验通过");*/

            // 获取请求体
            JSONObject requestBody = object.getJSONObject("request_body");
            String mch_id = requestBody.getString("mch_id");                // 商户ID

            payContext.put("mch_id", mch_id);

            // 查询账单list
//            String retStr = mchInfoServiceClient.mchQueryOrders(payContext.toJSONString());// 多个参数用这个
            String retStr = accountFileServiceClient.exportAllAccountBook(payContext.toJSONString());
            // 将商户订单信息转换为JSONObject对象
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}导出全部账单,结果:{}", logPrefix, retObj);

            // 0002:未查询到符合要求的订单
            if(!"0000".equals(retObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (retObj == null) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "没有可以导出的账单", null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }

            List<Map<String, Object>> list = new ArrayList<>();
            // 获取账单总数
            int count = retObj.getInteger("count");
            for(int i = 1; i <= count; i++) {
                // 结果对象
                JSONObject accountBook = retObj.getJSONObject("result" + i);
                Map<String, Object> map = new HashMap<String, Object>();

                map.put("account_book_id", accountBook.getString("account_book_id") == null ? "" : accountBook.getString("account_book_id"));
                map.put("mch_id", accountBook.getString("mch_id") == null ? "" : accountBook.getString("mch_id"));
                map.put("items_id",	accountBook.getString("items_id") == null ? "" : accountBook.getString("items_id"));
                map.put("user_id",	accountBook.getString("user_id") == null ? "" : accountBook.getString("user_id"));
                map.put("user_name",	accountBook.getString("user_name") == null ? "" : accountBook.getString("user_name"));
                map.put("currency",	accountBook.getString("currency") == null ? "" : accountBook.getString("currency"));
                map.put("items_money", accountBook.getLong("items_money") == null ? "" : accountBook.getLong("items_money"));
                map.put("pay_time", accountBook.getString("pay_time") == null ? "" : accountBook.getString("pay_time"));
                map.put("pay_status", accountBook.getByte("pay_status") == null ? "" : accountBook.getByte("pay_status"));
                list.add(map);
            }

            //组装返回报文response
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "导出全部账单查询成功", PayConstant.RETURN_VALUE_SUCCESS, null);
            retBody.put("accountBookList", list);

            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            _log.info("导出全部账单查询成功,channel={}", response);
            _log.info("###### 导出全部账单查询处理完成 ######");
            // 管理平台账单
            /*if("MGR".equals(request_channel)) {
            	return EPayUtil.makeRetData(response, payContext.getString("res_key"));
            }else {
            	return EPayUtil.makeRetData(response);
            }*/
            return EPayUtil.makeRetData(response);//JSON.toJSONString

        }catch (Exception e) {
            _log.error(e, "");
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null);
            response.put("response_header", retHeader);
            return EPayUtil.makeRetFail(response);
        }
    }

    /**
     * 导出选中账单
     */
    @RequestMapping(value = "/bill_export_selectedAccountBook")
    public String exportSelectedAccountBook(@RequestParam String params) {
        _log.info("###### 开始接收导出选中账单请求 ######");
        JSONObject object;
        String logPrefix = "【导出选中账单】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        // 获取本地服务实例(获取service工程)
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/bill_export_selectedAccountBook, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
            // 将请求参数转换为JSONObject对象
            object = JSONObject.parseObject(params);
            // 获取请求头
            JSONObject channelObj = object.getJSONObject("request_header");
            // 从请求头中获取渠道信息
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject payContext = new JSONObject();
            // 验证参数有效性
            /*String errorMessage = validateParams(object, payContext);//携带请求参数+响应私钥
            if (!"success".equalsIgnoreCase(errorMessage)) {
                _log.warn(errorMessage);
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, errorMessage, null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            _log.debug("请求参数及签名校验通过");*/

            // 获取请求体
            JSONObject requestBody = object.getJSONObject("request_body");
            JSONArray ids = requestBody.getJSONArray("ids");

            payContext.put("ids", ids);

            // 查询账单list
//            String retStr = mchInfoServiceClient.mchQueryOrders(payContext.toJSONString());// 多个参数用这个
            String retStr = accountFileServiceClient.exportSelectedAccountBook(payContext.toJSONString());
            // 将商户订单信息转换为JSONObject对象
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}导出选中账单,结果:{}", logPrefix, retObj);

            // 0002:未查询到符合要求的订单
            if(!"0000".equals(retObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (retObj == null) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "没有可以导出的账单", null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }

            List<Map<String, Object>> list = new ArrayList<>();
            // 获取账单总数
            int count = retObj.getInteger("count");
            for(int i = 1; i <= count; i++) {
                // 结果对象
                JSONObject accountBook = retObj.getJSONObject("result" + i);
                Map<String, Object> map = new HashMap<String, Object>();

                map.put("account_book_id", accountBook.getString("account_book_id") == null ? "" : accountBook.getString("account_book_id"));
                map.put("mch_id", accountBook.getString("mch_id") == null ? "" : accountBook.getString("mch_id"));
                map.put("items_id",	accountBook.getString("items_id") == null ? "" : accountBook.getString("items_id"));
                map.put("user_id",	accountBook.getString("user_id") == null ? "" : accountBook.getString("user_id"));
                map.put("user_name",	accountBook.getString("user_name") == null ? "" : accountBook.getString("user_name"));
                map.put("currency",	accountBook.getString("currency") == null ? "" : accountBook.getString("currency"));
                map.put("items_money", accountBook.getLong("items_money") == null ? "" : accountBook.getLong("items_money"));
                map.put("pay_time", accountBook.getString("pay_time") == null ? "" : accountBook.getString("pay_time"));
                map.put("pay_status", accountBook.getByte("pay_status") == null ? "" : accountBook.getByte("pay_status"));
                list.add(map);
            }

            //组装返回报文response
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "导出选中账单查询成功", PayConstant.RETURN_VALUE_SUCCESS, null);
            retBody.put("accountBookList", list);

            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            _log.info("导出选中账单查询成功,channel={}", response);
            _log.info("###### 导出选中账单查询处理完成 ######");
            // 管理平台账单
            /*if("MGR".equals(request_channel)) {
            	return EPayUtil.makeRetData(response, payContext.getString("res_key"));
            }else {
            	return EPayUtil.makeRetData(response);
            }*/
            return EPayUtil.makeRetData(response);//JSON.toJSONString

        }catch (Exception e) {
            _log.error(e, "");
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null);
            response.put("response_header", retHeader);
            return EPayUtil.makeRetFail(response);
        }
    }

    /**
     * 删除账单
     */
    @RequestMapping(value = "/bill_delete_accountFile")
    public String deleteAccountFile(@RequestParam String params) {
        _log.info("###### 删除账单信息请求 ######");
        JSONObject object;
        String logPrefix = "【删除账单】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        // 获取本地服务实例(获取service工程)
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/bill_delete_accountFile, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
            // 将请求参数转换为JSONObject对象
            object = JSONObject.parseObject(params);
            // 获取请求头
            JSONObject channelObj = object.getJSONObject("request_header");
            // 从请求头中获取渠道信息
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject payContext = new JSONObject();
            // 验证参数有效性
            /*String errorMessage = validateParams(object, payContext);//携带请求参数+响应私钥
            if (!"success".equalsIgnoreCase(errorMessage)) {
                _log.warn(errorMessage);
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, errorMessage, null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            _log.debug("请求参数及签名校验通过");*/

            // 获取请求体
            JSONObject requestBody = object.getJSONObject("request_body");
            // 要删除的accountFile的所有主键
            JSONArray ids = requestBody.getJSONArray("ids");

            payContext.put("ids", ids);
            // 查询商户账单信息list
//            String retStr = mchInfoServiceClient.mchQueryOrders(payContext.toJSONString());// 多个参数用这个
            String retStr = accountFileServiceClient.deleteAccountFile(payContext.toJSONString());
            // 将商户账单信息转换为JSONObject对象
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}删除账单,结果:{}", logPrefix, retObj);

            // 0000表示查询成功
            if(!"0000".equals(retObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (retObj == null) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "删除账单失败", null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            // 结果对象
            boolean deleteOK = retObj.getBoolean("result");

            //组装返回报文response
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "删除账单成功", PayConstant.RETURN_VALUE_SUCCESS, null);
            retBody.put("result", deleteOK);

            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            _log.info("删除账单查询成功,channel={}", response);
            _log.info("###### 删除账单处理完成 ######");
            // 管理平台账单
            /*if("MGR".equals(request_channel)) {
            	return EPayUtil.makeRetData(response, payContext.getString("res_key"));
            }else {
            	return EPayUtil.makeRetData(response);
            }*/
            return EPayUtil.makeRetData(response);//JSON.toJSONString

        }catch (Exception e) {
            _log.error(e, "");
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null);
            response.put("response_header", retHeader);
            return EPayUtil.makeRetFail(response);
        }
    }

    /**
     * 生成回单
     */
    @RequestMapping(value = "/bill_generate_receipt")
    public String generateReceipt(@RequestParam String params) {
        _log.info("###### 生成回单请求 ######");
        JSONObject object;
        String logPrefix = "【生成回单】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        // 获取本地服务实例(获取service工程)
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/bill_generate_receipt, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
            // 将请求参数转换为JSONObject对象
            object = JSONObject.parseObject(params);
            // 获取请求头
            JSONObject channelObj = object.getJSONObject("request_header");
            // 从请求头中获取渠道信息
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject payContext = new JSONObject();
            // 验证参数有效性
            /*String errorMessage = validateParams(object, payContext);//携带请求参数+响应私钥
            if (!"success".equalsIgnoreCase(errorMessage)) {
                _log.warn(errorMessage);
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, errorMessage, null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            _log.debug("请求参数及签名校验通过");*/

            // 获取请求体
            JSONObject requestBody = object.getJSONObject("request_body");
            // 要删除的accountFile的所有主键
            JSONArray ids = requestBody.getJSONArray("ids");

            payContext.put("ids", ids);
            // 查询商户accountBook+payOrder
            String retStr = accountFileServiceClient.generateReceipt(payContext.toJSONString());
            // 将商户账单信息转换为JSONObject对象
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}生成回单,结果:{}", logPrefix, retObj);

            // 0000表示查询成功
            if(!"0000".equals(retObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (retObj == null) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "查询回单信息失败", null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }

            List<Map<String, Object>> list = new ArrayList<>();
            // 查询的订单+账单总条数
            int count = retObj.getInteger("count");
            for(int i = 1; i <= count; i++) {
                // 结果对象
                JSONObject accountBook = retObj.getJSONObject("result" + i);
                Map<String, Object> map = new HashMap<String, Object>();

                map.put("account_book_id", accountBook.getString("account_book_id") == null ? "" : accountBook.getString("account_book_id"));
                map.put("items_id", accountBook.getString("items_id") == null ? "" : accountBook.getString("items_id"));
                map.put("user_id", accountBook.getString("user_id") == null ? "" : accountBook.getString("user_id"));
                map.put("user_name",	accountBook.getString("user_name") == null ? "" : accountBook.getString("user_name"));
                map.put("pay_time",	accountBook.getString("pay_time") == null ? "" : accountBook.getString("pay_time"));
                map.put("pay_status",	accountBook.getByte("pay_status") == null ? "" : accountBook.getByte("pay_status"));
                map.put("settle_status",	accountBook.getByte("settle_status") == null ? "" : accountBook.getByte("settle_status"));
                map.put("pay_channel", accountBook.getString("pay_channel") == null ? "" : accountBook.getString("pay_channel"));
                map.put("mch_order_no", accountBook.getString("mch_order_no") == null ? "" : accountBook.getString("mch_order_no"));
                map.put("user_channel_account", accountBook.getString("user_channel_account") == null ? "" : accountBook.getString("user_channel_account"));
                map.put("channel_mch_id", accountBook.getString("channel_mch_id") == null ? "" : accountBook.getString("channel_mch_id"));
                map.put("channel_order_no", accountBook.getString("channel_order_no") == null ? "" : accountBook.getString("channel_order_no"));
                map.put("res_code", accountBook.getString("res_code") == null ? "" : accountBook.getString("res_code"));
                map.put("expire_time", accountBook.getString("expire_time") == null ? "" : accountBook.getString("expire_time"));
                list.add(map);
            }

            //组装返回报文response
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "商户回单信息查询成功", PayConstant.RETURN_VALUE_SUCCESS, null);
            retBody.put("accountBookAndPayOrderList", list);

            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            _log.info("商户回单信息查询成功,channel={}", response);
            _log.info("###### 商户回单信息查询处理完成 ######");
            // 管理平台账单
            if("MGR".equals(request_channel)) {
                return EPayUtil.makeRetData(response, payContext.getString("res_key"));
            }else {
                return EPayUtil.makeRetData(response);
            }

        }catch (Exception e) {
            _log.error(e, "");
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null);
            response.put("response_header", retHeader);
            return EPayUtil.makeRetFail(response);
        }
    }

    /**
     * 批量删除缴费明细
     */
    @RequestMapping(value = "/bill_delete_accountBooks")
    public String deleteAccountBooks(@RequestParam String params) {
        _log.info("###### 批量删除缴费明细请求 ######");
        JSONObject object;
        String logPrefix = "【批量删除缴费明细】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        // 获取本地服务实例(获取service工程)
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/bill_delete_accountBooks, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
            // 将请求参数转换为JSONObject对象
            object = JSONObject.parseObject(params);
            // 获取请求头
            JSONObject channelObj = object.getJSONObject("request_header");
            // 从请求头中获取渠道信息
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject payContext = new JSONObject();
            // 验证参数有效性
            /*String errorMessage = validateParams(object, payContext);//携带请求参数+响应私钥
            if (!"success".equalsIgnoreCase(errorMessage)) {
                _log.warn(errorMessage);
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, errorMessage, null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            _log.debug("请求参数及签名校验通过");*/

            // 获取请求体
            JSONObject requestBody = object.getJSONObject("request_body");
            // 要删除的accountFile的所有主键
            JSONArray ids = requestBody.getJSONArray("ids");

            payContext.put("ids", ids);
            // 批量删除缴费明细
            String retStr = accountFileServiceClient.deleteAccountBooks(payContext.toJSONString());
            // 将商户账单信息转换为JSONObject对象
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}批量删除缴费明细,结果:{}", logPrefix, retObj);

            // 0000表示查询成功
            if(!"0000".equals(retObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (retObj == null) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "批量删除缴费明细失败", null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            // 结果对象
            boolean deleteOK = retObj.getBoolean("result");

            //组装返回报文response
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "删除账单成功", PayConstant.RETURN_VALUE_SUCCESS, null);
            retBody.put("result", deleteOK);

            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            _log.info("批量删除缴费明细成功,channel={}", response);
            _log.info("###### 批量删除缴费明细处理完成 ######");
            // 管理平台账单
            /*if("MGR".equals(request_channel)) {
            	return EPayUtil.makeRetData(response, payContext.getString("res_key"));
            }else {
            	return EPayUtil.makeRetData(response);
            }*/
            return EPayUtil.makeRetData(response);//JSON.toJSONString

        }catch (Exception e) {
            _log.error(e, "");
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null);
            response.put("response_header", retHeader);
            return EPayUtil.makeRetFail(response);
        }
    }

    /**
     * 删除单条accountBook
     */
    @RequestMapping(value = "/bill_delete_accountSingleAccountBook")
    public String deleteSingleAccountBook(@RequestParam String params) {
        _log.info("###### 删除一条缴费明细请求 ######");
        JSONObject object;
        String logPrefix = "【删除一条缴费明细】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        // 获取本地服务实例(获取service工程)
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/bill_delete_accountSingleAccountBook, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
            // 将请求参数转换为JSONObject对象
            object = JSONObject.parseObject(params);
            // 获取请求头
            JSONObject channelObj = object.getJSONObject("request_header");
            // 从请求头中获取渠道信息
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject payContext = new JSONObject();

            // 获取请求体
            JSONObject requestBody = object.getJSONObject("request_body");
            // 要删除的accountFile的所有主键
            String account_book_id = requestBody.getString("account_book_id");

            payContext.put("account_book_id", account_book_id);
            // 查询商户账单信息
            String retStr = accountFileServiceClient.deleteSingleAccountBook(payContext.toJSONString());
            // 将商户账单信息转换为JSONObject对象
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}删除一条缴费明细,结果:{}", logPrefix, retObj);

            // 0000表示查询成功
            if(!"0000".equals(retObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (retObj == null) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "删除一条缴费明细失败", null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            // 结果对象
            boolean deleteOK = retObj.getBoolean("result");

            //组装返回报文response
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "删除一条缴费明细成功", PayConstant.RETURN_VALUE_SUCCESS, null);
            retBody.put("result", deleteOK);

            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            _log.info("删除一条缴费明细成功,channel={}", response);
            _log.info("###### 删除一条缴费明细处理完成 ######");
            // 管理平台账单
            if("MGR".equals(request_channel)) {
            	return EPayUtil.makeRetData(response, payContext.getString("res_key"));
            }else {
            	return EPayUtil.makeRetData(response);
            }

        }catch (Exception e) {
            _log.error(e, "");
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null);
            response.put("response_header", retHeader);
            return EPayUtil.makeRetFail(response);
        }
    }

    /**
     * 修改单条accountBook
     */
    @RequestMapping(value = "/bill_update_accountSingleAccountBook")
    public String updateSingleAccountBook(@RequestParam String params) {
        _log.info("###### 修改一条缴费明细请求 ######");
        JSONObject object;
        String logPrefix = "【修改一条缴费明细】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        // 获取本地服务实例(获取service工程)
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/bill_update_accountSingleAccountBook, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
            // 将请求参数转换为JSONObject对象
            object = JSONObject.parseObject(params);
            // 获取请求头
            JSONObject channelObj = object.getJSONObject("request_header");
            // 从请求头中获取渠道信息
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject payContext = new JSONObject();

            // 获取请求体
            JSONObject requestBody = object.getJSONObject("request_body");
            // 要删除的accountFile的所有主键
            String account_book_id = requestBody.getString("account_book_id");
            String mch_id = requestBody.getString("mch_id");
            String items_id = requestBody.getString("items_id");
            String user_id = requestBody.getString("user_id");
            String user_name = requestBody.getString("user_name");
            String items_money = requestBody.getString("items_money");
            String pay_time = requestBody.getString("pay_time");
            String pay_status = requestBody.getString("pay_status");

            payContext.put("account_book_id", account_book_id);
            payContext.put("mch_id", mch_id);
            payContext.put("items_id", items_id);
            payContext.put("user_id", user_id);
            payContext.put("user_name", user_name);
            payContext.put("items_money", items_money);
            payContext.put("pay_time", pay_time);
            payContext.put("pay_status", pay_status);
            // 查询商户账单信息
            String retStr = accountFileServiceClient.updateSingleAccountBook(payContext.toJSONString());
            // 将商户账单信息转换为JSONObject对象
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}修改一条缴费明细,结果:{}", logPrefix, retObj);

            // 0000表示查询成功
            if(!"0000".equals(retObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (retObj == null) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "修改一条缴费明细失败", null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            // 结果对象
            boolean updateOK = retObj.getBoolean("result");

            //组装返回报文response
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "修改一条缴费明细成功", PayConstant.RETURN_VALUE_SUCCESS, null);
            retBody.put("result", updateOK);

            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            _log.info("修改一条缴费明细成功,channel={}", response);
            _log.info("###### 修改一条缴费明细处理完成 ######");
            // 管理平台账单
            if("MGR".equals(request_channel)) {
            	return EPayUtil.makeRetData(response, payContext.getString("res_key"));
            }else {
            	return EPayUtil.makeRetData(response);
            }

        }catch (Exception e) {
            _log.error(e, "");
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null);
            response.put("response_header", retHeader);
            return EPayUtil.makeRetFail(response);
        }
    }

    /**
     * 根据项目编号查询订单接口（状态为已缴费）
     * 2019年6月1日
     */
    @RequestMapping(value = "/user_query_pay_order_by_items_id")
    public String queryPayOrderListByItemsId(@RequestParam String params) {
        _log.info("###### 根据项目编号查询订单请求 ######");
        JSONObject object;
        String logPrefix = "【根据项目编号查询订单】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        // 获取本地服务实例(获取service工程)
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/user_query_pay_order_by_items_id, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
            // 将请求参数转换为JSONObject对象
            object = JSONObject.parseObject(params);
            // 获取请求头
            JSONObject channelObj = object.getJSONObject("request_header");
            // 从请求头中获取渠道信息
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject payContext = new JSONObject();

            // 获取请求体
            JSONObject requestBody = object.getJSONObject("request_body");
            //
            String mch_id = requestBody.getString("mch_id");
            String items_id = requestBody.getString("items_id");

            payContext.put("mch_id", mch_id);
            payContext.put("items_id", items_id);
            // 查询订单信息
            String retStr = accountFileServiceClient.queryPayOrderListByItemsId(payContext.toJSONString());
            // 将订单信息转换为JSONObject对象
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}根据项目编号查询订单,结果:{}", logPrefix, retObj);

            // 0000表示查询成功
            if(!"0000".equals(retObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (retObj == null) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "根据项目编号查询订单失败", null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }

            List<Map<String, Object>> list = new ArrayList<>();
            // 查询的总条数
            int count = retObj.getInteger("count");
            for(int i = 1; i <= count; i++) {
                // 结果对象
                JSONObject payOrder = retObj.getJSONObject("result" + i);
                Map<String, Object> map = new HashMap<String, Object>();

                map.put("pay_order_id", payOrder.getString("pay_order_id") == null ? "" : payOrder.getString("pay_order_id"));
                map.put("mch_id", payOrder.getString("mch_id") == null ? "" : payOrder.getString("mch_id"));
                map.put("mch_order_no",	payOrder.getString("mch_order_no") == null ? "" : payOrder.getString("mch_order_no"));
                map.put("channel_id",	payOrder.getString("channel_id") == null ? "" : payOrder.getString("channel_id"));
                map.put("user_id",	payOrder.getString("user_id") == null ? "" : payOrder.getString("user_id"));
                map.put("user_name",	payOrder.getString("user_name") == null ? "" : payOrder.getString("user_name"));
                map.put("user_channel_account", payOrder.getString("user_channel_account") == null ? "" : payOrder.getString("user_channel_account"));
                map.put("amount", payOrder.getLong("amount") == null ? "" : payOrder.getLong("amount"));
                map.put("currency", payOrder.getString("currency") == null ? "" : payOrder.getString("currency"));
                map.put("status", payOrder.getByte("status") == null ? "" : payOrder.getByte("status"));
                map.put("client_ip", payOrder.getString("client_ip") == null ? "" : payOrder.getString("client_ip"));
                map.put("device", payOrder.getString("device") == null ? "" : payOrder.getString("device"));
                map.put("subject", payOrder.getString("subject") == null ? "" : payOrder.getString("subject"));
                map.put("body", payOrder.getString("body") == null ? "" : payOrder.getString("body"));
                map.put("extra", payOrder.getString("extra") == null ? "" : payOrder.getString("extra"));
                map.put("channel_mch_id", payOrder.getString("channel_mch_id") == null ? "" : payOrder.getString("channel_mch_id"));
                map.put("channel_order_no", payOrder.getString("channel_order_no") == null ? "" : payOrder.getString("channel_order_no"));
                map.put("res_code", payOrder.getString("res_code") == null ? "" : payOrder.getString("res_code"));
                map.put("res_msg", payOrder.getString("res_msg") == null ? "" : payOrder.getString("res_msg"));
                map.put("notify_url", payOrder.getString("notify_url") == null ? "" : payOrder.getString("notify_url"));
                map.put("notify_count", payOrder.getString("notify_count") == null ? "" : payOrder.getString("notify_count"));
                map.put("last_notify_time", payOrder.getString("last_notify_time") == null ? "" : payOrder.getString("last_notify_time"));
                map.put("expire_time", payOrder.getString("expire_time") == null ? "" : payOrder.getString("expire_time"));
                map.put("pay_succ_time", payOrder.getString("pay_succ_time") == null ? "" : payOrder.getString("pay_succ_time"));
                map.put("create_time", payOrder.getString("create_time") == null ? "" : payOrder.getString("create_time"));
                map.put("update_time", payOrder.getString("update_time") == null ? "" : payOrder.getString("update_time"));
                list.add(map);
            }

            //组装返回报文response
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "商户已缴费订单查询成功", PayConstant.RETURN_VALUE_SUCCESS, null);
            retBody.put("payOrderList", list);

            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            _log.info("商户已缴费订单查询成功,channel={}", response);
            _log.info("###### 商户已缴费订单查询处理完成 ######");
            // 管理平台账单
            if("MGR".equals(request_channel)) {
            	return EPayUtil.makeRetData(response, payContext.getString("res_key"));
            }else {
            	return EPayUtil.makeRetData(response);
            }

        }catch (Exception e) {
            _log.error(e, "");
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null);
            response.put("response_header", retHeader);
            return EPayUtil.makeRetFail(response);
        }
    }

    /**
     * 添加单条accountBook
     */
    @RequestMapping(value = "/bill_add_accountSingleAccountBook")
    public String addSingleAccountBook(@RequestParam String params) {
        _log.info("###### 修改一条缴费明细请求 ######");
        JSONObject object;
        String logPrefix = "【添加一条缴费明细】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        // 获取本地服务实例(获取service工程)
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/bill_add_accountSingleAccountBook, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
            // 将请求参数转换为JSONObject对象
            object = JSONObject.parseObject(params);
            // 获取请求头
            JSONObject channelObj = object.getJSONObject("request_header");
            // 从请求头中获取渠道信息
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject payContext = new JSONObject();

            // 获取请求体
            JSONObject requestBody = object.getJSONObject("request_body");
            // 要删除的accountFile的所有主键
            String mch_id = requestBody.getString("mch_id");
            String items_id = requestBody.getString("items_id");
            String user_id = requestBody.getString("user_id");
            String user_name = requestBody.getString("user_name");
            String items_money = requestBody.getString("items_money");
            String pay_time = requestBody.getString("pay_time");
            String pay_status = requestBody.getString("pay_status");

            payContext.put("mch_id", mch_id);
            payContext.put("items_id", items_id);
            payContext.put("user_id", user_id);
            payContext.put("user_name", user_name);
            payContext.put("items_money", items_money);
            payContext.put("pay_time", pay_time);
            payContext.put("pay_status", pay_status);
            // 查询商户账单信息
            String retStr = accountFileServiceClient.addSingleAccountBook(payContext.toJSONString());
            // 将商户账单信息转换为JSONObject对象
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}添加一条缴费明细,结果:{}", logPrefix, retObj);

            // 0000表示查询成功
            if(!"0000".equals(retObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (retObj == null) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "添加一条缴费明细失败", null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            // 结果对象
            boolean addOK = retObj.getBoolean("result");

            //组装返回报文response
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "添加一条缴费明细成功", PayConstant.RETURN_VALUE_SUCCESS, null);
            retBody.put("result", addOK);

            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            _log.info("添加一条缴费明细成功,channel={}", response);
            _log.info("###### 添加一条缴费明细处理完成 ######");
            // 管理平台账单
            if("MGR".equals(request_channel)) {
            	return EPayUtil.makeRetData(response, payContext.getString("res_key"));
            }else {
            	return EPayUtil.makeRetData(response);
            }

        }catch (Exception e) {
            _log.error(e, "");
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null);
            response.put("response_header", retHeader);
            return EPayUtil.makeRetFail(response);
        }
    }

    /**
     * 查询当前商户下所有的accountFile的items_id
     */
    @RequestMapping(value = "/bill_query_all_itemsId")
    public String queryAllItemsId(@RequestParam String params) {
        _log.info("###### 查询项目编号请求 ######");
        JSONObject object;
        String logPrefix = "【查询项目编号】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        // 获取本地服务实例(获取service工程)
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/bill_add_accountSingleAccountBook, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
            // 将请求参数转换为JSONObject对象
            object = JSONObject.parseObject(params);
            // 获取请求头
            JSONObject channelObj = object.getJSONObject("request_header");
            // 从请求头中获取渠道信息
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject payContext = new JSONObject();

            // 获取请求体
            JSONObject requestBody = object.getJSONObject("request_body");
            // 要删除的accountFile的所有主键
            String mch_id = requestBody.getString("mch_id");

            payContext.put("mch_id", mch_id);
            // 查询商户账单信息
            String retStr = accountFileServiceClient.queryAllItemsId(payContext.toJSONString());
            // 将商户账单信息转换为JSONObject对象
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}查询项目编号,结果:{}", logPrefix, retObj);

            // 0000表示查询成功
            if(!"0000".equals(retObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (retObj == null) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "查询项目编号失败", null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            // 结果对象
            JSONArray itemsList = retObj.getJSONArray("result");

            //组装返回报文response
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "查询项目编号成功", PayConstant.RETURN_VALUE_SUCCESS, null);
            retBody.put("result", itemsList);

            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            _log.info("查询项目编号成功,channel={}", response);
            _log.info("###### 查询项目编号处理完成 ######");
            // 管理平台账单
            if("MGR".equals(request_channel)) {
            	return EPayUtil.makeRetData(response, payContext.getString("res_key"));
            }else {
            	return EPayUtil.makeRetData(response);
            }

        }catch (Exception e) {
            _log.error(e, "");
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null);
            response.put("response_header", retHeader);
            return EPayUtil.makeRetFail(response);
        }
    }

    /**
     * 检查是否可以删除账单
     */
    @RequestMapping(value = "/bill_check_ifCanDeleteAccountFile")
    public String checkIfCanDeleteAccountFile(@RequestParam String params) {
        _log.info("###### 检查是否可以删除账单信息请求 ######");
        JSONObject object;
        String logPrefix = "【检查是否可以删除账单】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        // 获取本地服务实例(获取service工程)
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/bill_check_ifCanDeleteAccountFile, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
            // 将请求参数转换为JSONObject对象
            object = JSONObject.parseObject(params);
            // 获取请求头
            JSONObject channelObj = object.getJSONObject("request_header");
            // 从请求头中获取渠道信息
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject payContext = new JSONObject();
            // 验证参数有效性
            /*String errorMessage = validateParams(object, payContext);//携带请求参数+响应私钥
            if (!"success".equalsIgnoreCase(errorMessage)) {
                _log.warn(errorMessage);
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, errorMessage, null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            _log.debug("请求参数及签名校验通过");*/

            // 获取请求体
            JSONObject requestBody = object.getJSONObject("request_body");
            // 要删除的accountFile的所有主键
            JSONArray ids = requestBody.getJSONArray("ids");

            payContext.put("ids", ids);
            // 查询商户账单信息list
//            String retStr = mchInfoServiceClient.mchQueryOrders(payContext.toJSONString());// 多个参数用这个
            String retStr = accountFileServiceClient.checkIfCanDeleteAccountFile(payContext.toJSONString());
            // 将商户账单信息转换为JSONObject对象
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}查询账单是否可以删除,结果:{}", logPrefix, retObj);

            // 0000表示查询成功
            if(!"0000".equals(retObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (retObj == null) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "查询账单是否可以删除失败", null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            // 结果对象
            int count = retObj.getInteger("result");

            //组装返回报文response
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "判断账单是否可以删除查询成功", PayConstant.RETURN_VALUE_SUCCESS, null);
            retBody.put("result", count);

            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            _log.info("判断账单是否可以删除查询成功,channel={}", response);
            _log.info("###### 判断账单是否可以删除查询处理完成 ######");
            // 管理平台账单
            /*if("MGR".equals(request_channel)) {
            	return EPayUtil.makeRetData(response, payContext.getString("res_key"));
            }else {
            	return EPayUtil.makeRetData(response);
            }*/
            return EPayUtil.makeRetData(response);//JSON.toJSONString

        }catch (Exception e) {
            _log.error(e, "");
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null);
            response.put("response_header", retHeader);
            return EPayUtil.makeRetFail(response);
        }
    }

    /**
     * 判断有无可以导出的账单
     */
    @RequestMapping(value = "/bill_check_haveAccountCanBeExport")
    public String checkHaveAccountCanBeExport(@RequestParam String params) {
        _log.info("###### 判断有无可以导出的账单信息请求 ######");
        JSONObject object;
        String logPrefix = "【判断有无可以导出的账单】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        // 获取本地服务实例(获取service工程)
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/bill_check_haveAccountCanBeExport, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
            // 将请求参数转换为JSONObject对象
            object = JSONObject.parseObject(params);
            // 获取请求头
            JSONObject channelObj = object.getJSONObject("request_header");
            // 从请求头中获取渠道信息
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject payContext = new JSONObject();
            // 验证参数有效性
            /*String errorMessage = validateParams(object, payContext);//携带请求参数+响应私钥
            if (!"success".equalsIgnoreCase(errorMessage)) {
                _log.warn(errorMessage);
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, errorMessage, null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            _log.debug("请求参数及签名校验通过");*/

            // 获取请求体
            JSONObject requestBody = object.getJSONObject("request_body");

            String mch_id = requestBody.getString("mch_id");

            payContext.put("mch_id", mch_id);
            // 查询商户账单信息list
//            String retStr = mchInfoServiceClient.mchQueryOrders(payContext.toJSONString());// 多个参数用这个
            String retStr = accountFileServiceClient.checkHaveAccountCanBeExport(payContext.toJSONString());
            // 将商户账单信息转换为JSONObject对象
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}查询账单是否可以导出,结果:{}", logPrefix, retObj);

            // 0000表示查询成功
            if(!"0000".equals(retObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (retObj == null) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "查询账单是否可以导出失败", null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            // 结果对象
            int count = retObj.getInteger("result");

            //组装返回报文response
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "判断账单是否可以导出查询成功", PayConstant.RETURN_VALUE_SUCCESS, null);
            retBody.put("result", count);

            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            _log.info("判断账单是否可以导出查询成功,channel={}", response);
            _log.info("###### 判断账单是否可以导出查询处理完成 ######");
            // 管理平台账单
            /*if("MGR".equals(request_channel)) {
            	return EPayUtil.makeRetData(response, payContext.getString("res_key"));
            }else {
            	return EPayUtil.makeRetData(response);
            }*/
            return EPayUtil.makeRetData(response);//JSON.toJSONString

        }catch (Exception e) {
            _log.error(e, "");
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null);
            response.put("response_header", retHeader);
            return EPayUtil.makeRetFail(response);
        }
    }

    /**
     * 判断账单是否重复上传
     */
    @RequestMapping(value = "/bill_check_isDuplicate")
    public String checkIsDuplicate(@RequestParam String params) {
        _log.info("###### 判断账单是否重复上传信息请求 ######");
        JSONObject object;
        String logPrefix = "【判断账单是否重复上传】";
        Map<String, Object> response = new HashMap<String, Object>();//响应报文response
        Map<String, Object> retHeader = new HashMap<String, Object>();//响应报文头map对象
        Map<String, Object> retBody = new HashMap<String, Object>();//响应报文体map对象
        // 获取本地服务实例(获取service工程)
        ServiceInstance instance = client.getLocalServiceInstance();
        _log.info("{}/bill_check_isDuplicate, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
            // 将请求参数转换为JSONObject对象
            object = JSONObject.parseObject(params);
            // 获取请求头
            JSONObject channelObj = object.getJSONObject("request_header");
            // 从请求头中获取渠道信息
            String request_channel = channelObj.getString("request_channel");
            retHeader.put("request_channel", request_channel);
            JSONObject payContext = new JSONObject();
            // 验证参数有效性
            /*String errorMessage = validateParams(object, payContext);//携带请求参数+响应私钥
            if (!"success".equalsIgnoreCase(errorMessage)) {
                _log.warn(errorMessage);
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, errorMessage, null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            _log.debug("请求参数及签名校验通过");*/

            // 获取请求体
            JSONObject requestBody = object.getJSONObject("request_body");

            String mch_Id = requestBody.getString("mch_Id");
            String items_id = requestBody.getString("items_id");

            payContext.put("mch_Id", mch_Id);
            payContext.put("items_id", items_id);
            // 查询商户账单信息list
//            String retStr = mchInfoServiceClient.mchQueryOrders(payContext.toJSONString());// 多个参数用这个
            String retStr = accountFileServiceClient.checkIsDuplicate(payContext.toJSONString());
            // 将商户账单信息转换为JSONObject对象
            JSONObject retObj = JSON.parseObject(retStr);
            _log.info("{}判断账单是否重复上传,结果:{}", logPrefix, retObj);

            // 0000表示查询成功
            if(!"0000".equals(retObj.getString("code"))) {
            	retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null);
            	response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            if (retObj == null) {
                retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "判断账单是否重复上传失败", null, null);
                response.put("response_header", retHeader);
                return EPayUtil.makeRetFail(response);
            }
            // 结果对象
            int count = retObj.getInteger("result");

            //组装返回报文response
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "判断账单是否重复上传成功", PayConstant.RETURN_VALUE_SUCCESS, null);
            retBody.put("result", count);

            response.put("response_header", retHeader);
            response.put("response_body", retBody);
            _log.info("判断账单是否重复上传成功,channel={}", response);
            _log.info("###### 判断账单是否重复上传处理完成 ######");
            // 管理平台账单
            /*if("MGR".equals(request_channel)) {
            	return EPayUtil.makeRetData(response, payContext.getString("res_key"));
            }else {
            	return EPayUtil.makeRetData(response);
            }*/
            return EPayUtil.makeRetData(response);//JSON.toJSONString

        }catch (Exception e) {
            _log.error(e, "");
            retHeader = EPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心系统异常", null, null);
            response.put("response_header", retHeader);
            return EPayUtil.makeRetFail(response);
        }
    }

    /**
     * 验证商户信息查询请求参数,参数通过返回JSONObject对象,否则返回错误文本信息
     * @param params
     * @param payContext 空白JSONObject对象
     * @return
     */
    private String validateParams(JSONObject params, JSONObject payContext) {
        // 验证请求参数,参数有问题返回错误提示
        String errorMessage;
        // 请求参数
        JSONObject request_header = params.getJSONObject("request_header");
        String request_channel =  request_header.getString("request_channel");// 请求账单
        
        JSONObject request_body = params.getJSONObject("request_body");
        String mch_id = request_body.getString("mch_id");					// 商户ID

        String sign = params.getString("sign")	;							// 签名

        
        // 验证请求参数有效性（必选项）
        if(StringUtils.isBlank(request_channel)) {
            errorMessage = "请求参数渠道为空";
            return errorMessage;
        }
        if(StringUtils.isBlank(mch_id)) {
            errorMessage = "请求参数[mch_id] 为空.";
            return errorMessage;
        }
        // 如果是管理平台需要验证签名
        if("MGR".equals(request_channel)) {
        	// 签名信息
            if (StringUtils.isBlank(sign)) {
                errorMessage = "请求参数[sign]  为空.";
                return errorMessage;
            }
        }
        // 查询商户信息
        JSONObject mchInfo;
        String retStr = mchChannelServiceClient.selectMchChannelByMchId(getJsonParam("mch_id", mch_id));

        JSONObject retObj = JSON.parseObject(retStr);
        if("0000".equals(retObj.getString("code"))) {
            mchInfo = retObj.getJSONObject("result");
            if (mchInfo == null) {
                errorMessage = "未查询到[mch_id="+mch_id+"] 的商户信息 in db.";
                return errorMessage;
            }
            // 商户状态,0-未激活,1-使用中,2-停止使用
            if(mchInfo.getByte("mch_status") != 1) {
                errorMessage = "商户未启用 [mch_id="+mch_id+"].";
                return errorMessage;
            }
        }else {
            errorMessage = "未查询到[mch_id=\"+mch_id+\"] 的商户信息 in db..";
            _log.info("查询商户没有正常返回数据,code={},msg={}", retObj.getString("code"), retObj.getString("msg"));
            return errorMessage;
        }
        
        if("MGR".equals(request_channel)) {
        	//商户请求私钥
            String req_key = mchInfo.getString("req_key");
            if (StringUtils.isBlank(req_key)) {
                errorMessage = "req_key is null[mch_id="+mch_id+"] record in db.";
                return errorMessage;
            }
            // 判断 (MD5加密签名后的请求参数+签名) 与 (原请求中签名) 是否相等
            boolean verifyFlag = EPayUtil.verifyPaySign(params, req_key);
            if(!verifyFlag) {
                errorMessage = "签名信息验证失败.";
                return errorMessage;
            }
            
            //组织请求参数对象
            payContext.put("res_key", mchInfo.getString("res_key"));//商户响应私钥
            payContext.put("mch_id", mch_id);
        }
        
        return "success";
    }

    String getJsonParam(String[] names, Object[] values) {
        JSONObject jsonParam = new JSONObject();
        for (int i = 0; i < names.length; i++) {
            jsonParam.put(names[i], values[i]);
        }
        return jsonParam.toJSONString();
    }

    /**
     * 将String封装到JSONObject中
     * @param name
     * @param value
     * @return
     */
    String getJsonParam(String name, Object value) {
        JSONObject jsonParam = new JSONObject();
        jsonParam.put(name, value);
        return jsonParam.toJSONString();
    }

}
