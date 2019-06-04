package org.epay.mgr.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.epay.common.constant.Constant;
import org.epay.common.constant.PayConstant;
import org.epay.common.util.EPayUtil;
import org.epay.dal.dao.model.*;
import org.epay.mgr.utils.AccountBookUtil;
import org.epay.mgr.utils.WriteExcel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
//@EnableConfigurationProperties(RemoteProperties.class )
public class BillController {

    @Value("${web.path}")
    String baseUrl;

//    @Value("${receipt.path}")
//    String dealReceiptPath;

    // 修改单条accountBook
    @RequestMapping(value = "/bill/updateSingleAccountBook")
    @ResponseBody
    public Object updateSingleAccountBook(HttpServletRequest request,
                                 @RequestParam(value = "account_book_id", required = true) String account_book_id,
                                 @RequestParam(value = "items_id", required = true) String items_id,
                                 @RequestParam(value = "user_id", required = true) String user_id,
                                 @RequestParam(value = "user_name", required = false) String user_name,
                                 @RequestParam(value = "items_money", required = false) String items_money,
                                 @RequestParam(value = "pay_time", required = false) String pay_time,
                                 @RequestParam(value = "pay_status", required = false) String pay_status) {

        // 从session域中取值
        User user = (User) request.getSession().getAttribute("user");
        String mch_id = user.getLoginName();
        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        // TODO 临时写为 WX_APP = "微信APP支付";
        requestHeaderMap.put("request_channel", "WX_APP");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("account_book_id", account_book_id);
        requestBodyMap.put("mch_id", mch_id);
        requestBodyMap.put("items_id", items_id);
        requestBodyMap.put("user_id", user_id);
        requestBodyMap.put("user_name", user_name);
        requestBodyMap.put("items_money", items_money);
        requestBodyMap.put("pay_time", pay_time);
        requestBodyMap.put("pay_status", pay_status);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("修改一条缴费明细,请求数据:" + reqData);

        String url = baseUrl + "/bill_update_accountSingleAccountBook?";
        // 通过getway调用web工程
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("修改一条缴费明细,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");

        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            boolean updateOK = (boolean) retBody.get("result");

            // 返回商户信息
            if(updateOK) {
                returnMap.put("deleteOK", true);
                returnMap.put(Constant.ERROR_MESSAGE, Constant.OK);
                return returnMap;
            } else {
                returnMap.put("deleteOK", false);
                returnMap.put(Constant.ERROR_MESSAGE, "修改失败，请稍后再试");
                return returnMap;
            }

        } else {
            returnMap.put("deleteOK", false);
            returnMap.put(Constant.ERROR_MESSAGE, "系统繁忙，请稍后再试");
            return returnMap;
        }
    }

    // 添加单条accountBook
    @RequestMapping(value = "/bill/addSingleAccountBook")
    @ResponseBody
    public Object addSingleAccountBook(HttpServletRequest request,
                                 @RequestParam(value = "items_id", required = true) String items_id,
                                 @RequestParam(value = "user_id", required = true) String user_id,
                                 @RequestParam(value = "user_name", required = false) String user_name,
                                 @RequestParam(value = "items_money", required = false) String items_money,
                                 @RequestParam(value = "pay_time", required = false) String pay_time,
                                 @RequestParam(value = "pay_status", required = false) String pay_status) {

        // 从session域中取值
        User user = (User) request.getSession().getAttribute("user");
        String mch_id = user.getLoginName();
        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        // TODO 临时写为 WX_APP = "微信APP支付";
        requestHeaderMap.put("request_channel", "WX_APP");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("mch_id", mch_id);
        requestBodyMap.put("items_id", items_id);
        requestBodyMap.put("user_id", user_id);
        requestBodyMap.put("user_name", user_name);
        requestBodyMap.put("items_money", items_money);
        requestBodyMap.put("pay_time", pay_time);
        requestBodyMap.put("pay_status", pay_status);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("添加一条缴费明细,请求数据:" + reqData);

        String url = baseUrl + "/bill_add_accountSingleAccountBook?";
        // 通过getway调用web工程
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("添加一条缴费明细,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");

        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            boolean addOK = (boolean) retBody.get("result");

            // 返回商户信息
            if(addOK) {
                returnMap.put("addOK", true);
                returnMap.put(Constant.ERROR_MESSAGE, Constant.OK);
                return returnMap;
            } else {
                returnMap.put("addOK", false);
                returnMap.put(Constant.ERROR_MESSAGE, "添加失败，请稍后再试");
                return returnMap;
            }

        } else {
            returnMap.put("addOK", false);
            returnMap.put(Constant.ERROR_MESSAGE, "系统繁忙，请稍后再试");
            return returnMap;
        }
    }

    // 查询当前商户下所有的accountFile的items_id
    @RequestMapping(value = "/bill/queryAllItemsId")
    @ResponseBody
    public Object queryAllItemsId(HttpServletRequest request) {

        // 从session域中取值
        User user = (User) request.getSession().getAttribute("user");
        String mch_id = user.getLoginName();
        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        // TODO 临时写为 WX_APP = "微信APP支付";
        requestHeaderMap.put("request_channel", "WX_APP");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("mch_id", mch_id);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("查询项目编号,请求数据:" + reqData);

        String url = baseUrl + "/bill_query_all_itemsId?";
        // 通过getway调用web工程
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("查询项目编号,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");

        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            List<String> itemsIdList = (List<String>) retBody.get("result");

            // 返回商户信息
            if(itemsIdList != null) {
                returnMap.put("OK", true);
                returnMap.put(Constant.ERROR_MESSAGE, Constant.OK);
                returnMap.put("itemsIdList", itemsIdList);
                return returnMap;
            } else {
                returnMap.put("OK", false);
                returnMap.put(Constant.ERROR_MESSAGE, "查询项目编号失败，请稍后再试");
                return returnMap;
            }

        } else {
            returnMap.put("OK", false);
            returnMap.put(Constant.ERROR_MESSAGE, "系统繁忙，请稍后再试");
            return returnMap;
        }
    }

    /**
     * 查询当前商户下所有的活动的items_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/bill/queryAllAccountItemsId")
    @ResponseBody
    public Object queryAllAccountItemsId(HttpServletRequest request) {

        // 从session域中取值
        User user = (User) request.getSession().getAttribute("user");
        String mch_id = user.getLoginName();
        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        // TODO 临时写为 WX_APP = "微信APP支付";
        requestHeaderMap.put("request_channel", "WX_APP");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("mch_id", mch_id);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("查询活动的项目编号,请求数据:" + reqData);

        String url = baseUrl + "/bill_query_all_activity_itemsId?";
        // 通过getway调用web工程
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("查询活动的项目编号,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");

        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            List<String> itemsIdList = (List<String>) retBody.get("result");

            // 返回商户信息
            if(itemsIdList != null) {
                returnMap.put("OK", true);
                returnMap.put(Constant.ERROR_MESSAGE, Constant.OK);
                returnMap.put("itemsIdList", itemsIdList);
                return returnMap;
            } else {
                returnMap.put("OK", false);
                returnMap.put(Constant.ERROR_MESSAGE, "查询活动的项目编号失败，请稍后再试");
                return returnMap;
            }

        } else {
            returnMap.put("OK", false);
            returnMap.put(Constant.ERROR_MESSAGE, "系统繁忙，请稍后再试");
            return returnMap;
        }
    }

    // 删除单条accountBook
    @RequestMapping(value = "/bill/deleteAccountSingleAccountBook")
    @ResponseBody
    public Object deleteAccountSingleAccountBook(HttpServletRequest request,
                                 @RequestParam(value = "account_book_id", required = true) String account_book_id) {

        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        // TODO 临时写为 WX_APP = "微信APP支付";
        requestHeaderMap.put("request_channel", "WX_APP");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("account_book_id", account_book_id);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("删除一条缴费明细,请求数据:" + reqData);

        String url = baseUrl + "/bill_delete_accountSingleAccountBook?";
        // 通过getway调用web工程
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("删除一条缴费明细,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");

        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            boolean deleteOK = (boolean) retBody.get("result");

            // 返回商户信息
            if(deleteOK) {
                returnMap.put("deleteOK", true);
                returnMap.put(Constant.ERROR_MESSAGE, Constant.OK);
                return returnMap;
            } else {
                returnMap.put("deleteOK", false);
                returnMap.put(Constant.ERROR_MESSAGE, "删除失败，请稍后再试");
                return returnMap;
            }

        } else {
            returnMap.put("deleteOK", false);
            returnMap.put(Constant.ERROR_MESSAGE, "系统繁忙，请稍后再试");
            return returnMap;
        }
    }

    // 删除账单
    @RequestMapping(value = "/bill/deleteAccountFile", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteAccountFile(HttpServletRequest request) {

        String[] ids = request.getParameterValues("id");

        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        requestHeaderMap.put("request_channel", "MGR");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("ids", ids);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("删除账单,请求数据:" + reqData);

        String url = baseUrl + "/bill_delete_accountFile?";
        // 通过getway调用web工程
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("删除账单,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

//        Map<String, Object> returnMap = new HashMap<>();
        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");

        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            boolean deleteOK = (boolean) retBody.get("result");

            // 返回商户信息
            if(deleteOK) {
                returnMap.put("deleteOK", true);
                returnMap.put(Constant.ERROR_MESSAGE, Constant.OK);
                return returnMap;
            } else {
                returnMap.put("deleteOK", false);
                returnMap.put(Constant.ERROR_MESSAGE, "删除账单失败，请稍后再试");
                return returnMap;
            }

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
            returnMap.put("deleteOK", false);
            returnMap.put(Constant.ERROR_MESSAGE, "系统繁忙，请稍后再试");
            return returnMap;
        }
    }



    // 删除活动
    @RequestMapping(value = "/bill/deleteActivity", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteActivity(HttpServletRequest request) {

        String[] ids = request.getParameterValues("id");

        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        requestHeaderMap.put("request_channel", "MGR");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("ids", ids);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("删除活动,请求数据:" + reqData);

        String url = baseUrl + "/bill_delete_activity?";
        // 通过getway调用web工程
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("删除活动,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

//        Map<String, Object> returnMap = new HashMap<>();
        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");

        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            boolean deleteOK = (boolean) retBody.get("result");

            // 返回商户信息
            if(deleteOK) {
                returnMap.put("deleteOK", true);
                returnMap.put(Constant.ERROR_MESSAGE, Constant.OK);
                return returnMap;
            } else {
                returnMap.put("deleteOK", false);
                returnMap.put(Constant.ERROR_MESSAGE, "删除活动失败，请稍后再试");
                return returnMap;
            }

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
            returnMap.put("deleteOK", false);
            returnMap.put(Constant.ERROR_MESSAGE, "系统繁忙，请稍后再试");
            return returnMap;
        }
    }


    // 停止活动
    @RequestMapping(value = "/bill/stopActivity", method = RequestMethod.POST)
    @ResponseBody
    public Object stopActivity(HttpServletRequest request) {

        String[] ids = request.getParameterValues("id");

        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        requestHeaderMap.put("request_channel", "MGR");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("ids", ids);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("停止活动,请求数据:" + reqData);

        String url = baseUrl + "/bill_stop_activity?";
        // 通过getway调用web工程
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("停止活动,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

//        Map<String, Object> returnMap = new HashMap<>();
        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");

        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            boolean deleteOK = (boolean) retBody.get("result");

            // 返回商户信息
            if(deleteOK) {
                returnMap.put("deleteOK", true);
                returnMap.put(Constant.ERROR_MESSAGE, Constant.OK);
                return returnMap;
            } else {
                returnMap.put("deleteOK", false);
                returnMap.put(Constant.ERROR_MESSAGE, "停止活动失败，请稍后再试");
                return returnMap;
            }

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
            returnMap.put("deleteOK", false);
            returnMap.put(Constant.ERROR_MESSAGE, "系统繁忙，请稍后再试");
            return returnMap;
        }
    }


    // 恢复活动
    @RequestMapping(value = "/bill/restartActivity", method = RequestMethod.POST)
    @ResponseBody
    public Object restartActivity(HttpServletRequest request) {

        String[] ids = request.getParameterValues("id");

        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        requestHeaderMap.put("request_channel", "MGR");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("ids", ids);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("恢复活动,请求数据:" + reqData);

        String url = baseUrl + "/bill_restart_activity?";
        // 通过getway调用web工程
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("恢复活动,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

//        Map<String, Object> returnMap = new HashMap<>();
        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");

        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            boolean deleteOK = (boolean) retBody.get("result");

            // 返回商户信息
            if(deleteOK) {
                returnMap.put("deleteOK", true);
                returnMap.put(Constant.ERROR_MESSAGE, Constant.OK);
                return returnMap;
            } else {
                returnMap.put("deleteOK", false);
                returnMap.put(Constant.ERROR_MESSAGE, "恢复活动失败，请稍后再试");
                return returnMap;
            }

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
            returnMap.put("deleteOK", false);
            returnMap.put(Constant.ERROR_MESSAGE, "系统繁忙，请稍后再试");
            return returnMap;
        }
    }



    // accountFile合并统计，不发送Web
    @RequestMapping(value = "/bill/accountFileCount", method = RequestMethod.GET)
    @ResponseBody
    public Object accountFileCount(HttpServletRequest request) {
        // {120.10, 40.01, 80.09, 20.03  ,  1500.00, 0.00, 1500.00, 0.00}
        Map<String, Object> retMap = new HashMap<>();
        try {
            String[] ids = request.getParameterValues("id");
            double totalMoney = 0L;
            double readMoney = 0L;
            double daiMoney = 0L;
            double backMoney = 0L;
            for(int i = 0; i < ids.length; i++) {
                String[] split = ids[i].split(",");//{120.10, 40.01, 80.09, 20.03}
                totalMoney += Double.parseDouble(split[0]);
                readMoney += Double.parseDouble(split[1]);
                daiMoney += Double.parseDouble(split[2]);
                backMoney += Double.parseDouble(split[3]);
            }
            DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
            String perent = df.format((float) readMoney / totalMoney * 100) + "";

            retMap = new HashMap<>();
            retMap.put("totalMoney", totalMoney);
            retMap.put("readMoney", readMoney);
            retMap.put("daiMoney", daiMoney);
            retMap.put("backMoney", backMoney);
            retMap.put("perent", perent);
            retMap.put("count", ids.length);
            retMap.put("errorMessage", "SUCCESS");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            retMap.put("errorMessage", "统计失败！");
        }

        return retMap;
    }

    // 删除多条accountBook
    @RequestMapping(value = "/bill/deleteAccountBooks", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteAccountBooks(HttpServletRequest request) {

        String[] ids = request.getParameterValues("id");

        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        // TODO 临时写为 WX_APP = "微信APP支付";
        requestHeaderMap.put("request_channel", "WX_APP");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("ids", ids);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("批量删除缴费明细,请求数据:" + reqData);

        String url = baseUrl + "/bill_delete_accountBooks?";
        // 通过getway调用web工程
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("批量删除缴费明细,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

//        Map<String, Object> returnMap = new HashMap<>();
        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");

        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            boolean deleteOK = (boolean) retBody.get("result");

            // 返回商户信息
            if(deleteOK) {
                returnMap.put("deleteOK", true);
                returnMap.put(Constant.ERROR_MESSAGE, Constant.OK);
                return returnMap;
            } else {
                returnMap.put("deleteOK", false);
                returnMap.put(Constant.ERROR_MESSAGE, "批量删除缴费明细失败，请稍后再试");
                return returnMap;
            }

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
            returnMap.put("deleteOK", false);
            returnMap.put(Constant.ERROR_MESSAGE, "系统繁忙，请稍后再试");
            return returnMap;
        }
    }

    // 判断账单是否重复上传
    @RequestMapping("/bill/isDuplicate")
    @ResponseBody
    public Object isDuplicate(HttpServletRequest request,
                              @RequestParam(value = "xmbh", required = true)String xmbh) {

        // 从session域中取值
        User user = (User) request.getSession().getAttribute("user");
        String mch_Id = user.getLoginName();

        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        requestHeaderMap.put("request_channel", "MGR");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("mch_Id", mch_Id);
        requestBodyMap.put("items_id", xmbh);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("判断账单是否重复上传,请求数据:" + reqData);

        String url = baseUrl + "/bill_check_isDuplicate?";
        // 通过getway调用web工程
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("判断账单是否重复上传,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            // 查询结果
            int count = (int) retBody.get("result");
            if(count >= 1) {
                returnMap.put("ok", false);
                returnMap.put(Constant.ERROR_MESSAGE, "项目编号已存在！");
                return returnMap;
            } else {
                returnMap.put("ok", true);
                returnMap.put(Constant.ERROR_MESSAGE, Constant.OK);
                return returnMap;
            }

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
            // 查询失败
            returnMap.put("ok", false);
            returnMap.put(Constant.ERROR_MESSAGE,"系统繁忙，请稍后再试！");
            return returnMap;
        }
    }

    /**
     * 创建活动
     * 2019年6月2日14:25:16
     * GaoLiang
     */
    @RequestMapping("/bill/createActivity")
    @ResponseBody
    public Object createActivity(HttpServletRequest request,
                              @RequestParam(value = "activity_name", required = true)String activity_name,
                              @RequestParam(value = "items_id", required = true)String items_id,
                              @RequestParam(value = "activity_type", required = false)String activity_type,
                              @RequestParam(value = "start_time", required = true)String start_time,
                              @RequestParam(value = "end_time", required = true)String end_time) {

        // 从session域中取值
        User user = (User) request.getSession().getAttribute("user");
        String mch_Id = user.getLoginName();

        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        // TODO 临时写为 WX_APP = "微信APP支付";
        requestHeaderMap.put("request_channel", "WX_APP");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("mch_Id", mch_Id);
        requestBodyMap.put("activity_name", activity_name);
        requestBodyMap.put("items_id", items_id);
        requestBodyMap.put("activity_type", activity_type);
        requestBodyMap.put("start_time", start_time);
        requestBodyMap.put("end_time", end_time);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("创建活动,请求数据:" + reqData);

        String url = baseUrl + "/create_activity?";
        // 通过getway调用web工程
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("创建活动,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            // 查询结果
            int count = (int) retBody.get("result");
            if(count == 1) {
                returnMap.put("ok", true);
                returnMap.put(Constant.ERROR_MESSAGE, Constant.OK);
                return returnMap;
            } else {
                returnMap.put("ok", false);
                returnMap.put(Constant.ERROR_MESSAGE, "创建活动失败！");
                return returnMap;
            }
        } else {
            // 查询失败
            returnMap.put("ok", false);
            returnMap.put(Constant.ERROR_MESSAGE,"系统繁忙，请稍后再试！");
            return returnMap;
        }
    }


    // 判断是否可以删除账单accountFile
    @RequestMapping("/bill/checkIfCanDeleteAccountFile")
    @ResponseBody
    public Object queryChannel(HttpServletRequest request) {

        // 当传递2个参数时，这样接收接不到
        String[] ids = request.getParameterValues("id");

        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        requestHeaderMap.put("request_channel", "MGR");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("ids", ids);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("检查是否可以删除账单,请求数据:" + reqData);

        String url = baseUrl + "/bill_check_ifCanDeleteAccountFile?";
        // 通过getway调用web工程
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("检查是否可以删除账单,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            // 查询结果
            int count = (int) retBody.get("result");
            if(count > 1) {
                returnMap.put("ok", false);
                returnMap.put(Constant.ERROR_MESSAGE,"当前账单已经有用户缴费，无法删除，您可以选择更新账单");
                return returnMap;
            } else {
                returnMap.put("ok", true);
                returnMap.put(Constant.ERROR_MESSAGE, Constant.OK);
                return returnMap;
            }

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
            // 查询失败
            returnMap.put("ok", false);
            returnMap.put(Constant.ERROR_MESSAGE,"信息加载失败，请稍后再试");
            return returnMap;
        }
    }

    // 判断有无可以导出的账单
    @RequestMapping("/bill/checkExportAll")
    @ResponseBody
    public Object checkExportAll(HttpServletRequest request) {

        // 从session域中取值
        User user = (User) request.getSession().getAttribute("user");
        String mch_id = user.getLoginName();

        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        requestHeaderMap.put("request_channel", "MGR");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("mch_id", mch_id);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("判断有无可以导出的账单,请求数据:" + reqData);

        String url = baseUrl + "/bill_check_haveAccountCanBeExport?";
        // 通过getway调用web工程
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("判断有无可以导出的账单,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            // 查询结果，count为账单数量(做加1处理,即只要查询成功，count最少为1)
            int count = (int) retBody.get("result");
            if(count > 1) {
                returnMap.put("ok", true);
                returnMap.put(Constant.ERROR_MESSAGE, Constant.OK);
                return returnMap;
            } else {
                returnMap.put("ok", false);
                returnMap.put(Constant.ERROR_MESSAGE, "没有可以导出的账单,请先上传账单!");
                return returnMap;
            }

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
            // 查询失败
            returnMap.put("ok", false);
            returnMap.put(Constant.ERROR_MESSAGE,"系统繁忙，请稍后再试");
            return returnMap;
        }
    }

    // 下载账单模板
    @RequestMapping("/bill/downLoadAccountFileModel")
    @ResponseBody
    public void downLoadAccountFileModel(HttpServletResponse response,
                            HttpServletRequest request) {

        // 设置响应的内容类型为下载excel文件
        response.setContentType("application/vnd.ms-excel");
        // 设置响应头信息
        response.setHeader("Content-disposition", "attachment;filename="+System.currentTimeMillis()+".xls");

        // 从session域中获取当前登录商户的信息
        User user = (User) request.getSession().getAttribute("user");
        String mchId = user.getLoginName();

        // 导出excel
        WriteExcel<AccountBook> we = new WriteExcel<>();
        HSSFWorkbook workbook = we.getWorkbookModel("账单模板");

        // 响应到浏览器
        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 编辑账单模板
    @RequestMapping("/bill/modelEdit")
    @ResponseBody
    public void modelEdit(HttpServletResponse response,HttpServletRequest request,
                          @RequestParam(value = "user_id", required = true)String user_id,
                          @RequestParam(value = "userIdPosition", required = true)String userIdPosition,
                          @RequestParam(value = "items_money", required = true)String items_money,
                          @RequestParam(value = "items_money_position", required = true)String items_money_position,
                          @RequestParam(value = "select1", required = false)String select1,
                          @RequestParam(value = "select2", required = false)String select2,
                          @RequestParam(value = "select3", required = false)String select3,
                          @RequestParam(value = "select4", required = false)String select4,
                          @RequestParam(value = "select5", required = false)String select5,
                          @RequestParam(value = "select6", required = false)String select6,
                          @RequestParam(value = "select7", required = false)String select7,
                          @RequestParam(value = "select8", required = false)String select8,
                          @RequestParam(value = "select9", required = false)String select9,
                          @RequestParam(value = "select10", required = false)String select10,
                          @RequestParam(value = "position1", required = false)String position1,
                          @RequestParam(value = "position2", required = false)String position2,
                          @RequestParam(value = "position3", required = false)String position3,
                          @RequestParam(value = "position4", required = false)String position4,
                          @RequestParam(value = "position5", required = false)String position5,
                          @RequestParam(value = "position6", required = false)String position6,
                          @RequestParam(value = "position7", required = false)String position7,
                          @RequestParam(value = "position8", required = false)String position8,
                          @RequestParam(value = "position9", required = false)String position9,
                          @RequestParam(value = "position10", required = false)String position10) {

        // 设置响应的内容类型为下载excel文件
        response.setContentType("application/vnd.ms-excel");
        // 设置响应头信息
        response.setHeader("Content-disposition", "attachment;filename="+System.currentTimeMillis()+".xls");

        // 从session域中获取当前登录商户的信息
        User user = (User) request.getSession().getAttribute("user");
        String mchId = user.getLoginName();

        // 列位置信息
        Map<String, Integer> map = new HashMap<>();
        map.put("userIdPosition", Integer.parseInt(userIdPosition) - 1);
        map.put("items_money_position", Integer.parseInt(items_money_position) - 1);
        // 组装模板ModelKey
        ModelKey modelKey = new ModelKey();
        modelKey.setUserId(user_id);
        modelKey.setItemsMoney(items_money);
        if(StringUtils.isNotBlank(select1)) {
            modelKey.setSelect1(select1);
            map.put("position1", Integer.parseInt(position1) - 1);
        }
        if(StringUtils.isNotBlank(select2)) {
            modelKey.setSelect2(select2);
            map.put("position2", Integer.parseInt(position2) - 1);
        }
        if(StringUtils.isNotBlank(select3)) {
            modelKey.setSelect3(select3);
            map.put("position3", Integer.parseInt(position3) - 1);
        }
        if(StringUtils.isNotBlank(select4)) {
            modelKey.setSelect4(select4);
            map.put("position4", Integer.parseInt(position4) - 1);
        }
        if(StringUtils.isNotBlank(select5)) {
            modelKey.setSelect5(select5);
            map.put("position5", Integer.parseInt(position5) - 1);
        }
        if(StringUtils.isNotBlank(select6)) {
            modelKey.setSelect6(select6);
            map.put("position6", Integer.parseInt(position6) - 1);
        }
        if(StringUtils.isNotBlank(select7)) {
            modelKey.setSelect7(select7);
            map.put("position7", Integer.parseInt(position7) - 1);
        }
        if(StringUtils.isNotBlank(select8)) {
            modelKey.setSelect8(select8);
            map.put("position8", Integer.parseInt(position8) - 1);
        }
        if(StringUtils.isNotBlank(select9)) {
            modelKey.setSelect9(select9);
            map.put("position9", Integer.parseInt(position9) - 1);
        }
        if(StringUtils.isNotBlank(select10)) {
            modelKey.setSelect10(select10);
            map.put("position10", Integer.parseInt(position10) - 1);
        }

        // 导出excel
        WriteExcel<ModelKey> we = new WriteExcel<>();
        HSSFWorkbook workbook = we.getEditWorkbookModel(modelKey, map, "账单模板", ModelKey.class);

        // 响应到浏览器
        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 生成回单
     * @param request
     * @return
     */
    @RequestMapping("/bill/generateReceipt")
    @ResponseBody
    public void generateReceipt(HttpServletRequest request, HttpServletResponse response) {

        // 设置响应的内容类型为下载excel文件
        response.setContentType("application/vnd.ms-excel");
        // 设置响应头信息
        response.setHeader("Content-disposition", "attachment;filename="+System.currentTimeMillis()+".xls");

        // 从session域中取值
        User user = (User) request.getSession().getAttribute("user");
        String mch_id = user.getLoginName();

        // accountBook主键
        String[] ids = request.getParameterValues("id");

        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        // TODO 临时写为 WX_APP = "微信APP支付";
        requestHeaderMap.put("request_channel", "WX_APP");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("ids", ids);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("生成回单,请求数据:" + reqData);

        String url = baseUrl + "/bill_generate_receipt?";
        // 通过getway调用web工程
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("生成回单,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

//        Map<String, Object> returnMap = new HashMap<>();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");

        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            List<Map<String, Object>> accountBookList = (List<Map<String, Object>>) retBody.get("accountBookAndPayOrderList");

            List<AccountBookAndPayOrder> accountBookAndPayOrderList = new ArrayList<>();
            for(Map<String, Object> bodyMap : accountBookList) {
                AccountBookAndPayOrder bookAndOrder = new AccountBookAndPayOrder();
                bookAndOrder.setMch_id(mch_id);
                bookAndOrder.setAccount_book_id(bodyMap.get("account_book_id").toString());
                bookAndOrder.setItems_id(bodyMap.get("items_id").toString());
                bookAndOrder.setUser_id(bodyMap.get("user_id").toString());
                bookAndOrder.setUser_name(bodyMap.get("user_name").toString());
                bookAndOrder.setPay_time(bodyMap.get("pay_time").toString());
                bookAndOrder.setPay_status(Byte.parseByte(bodyMap.get("pay_status").toString()));
                bookAndOrder.setSettle_status(Byte.parseByte(bodyMap.get("settle_status").toString()));
                bookAndOrder.setPay_channel(bodyMap.get("pay_channel").toString());
                bookAndOrder.setMch_order_no(bodyMap.get("mch_order_no").toString());
                bookAndOrder.setUser_channel_account(bodyMap.get("user_channel_account").toString());
                bookAndOrder.setChannel_mch_id(bodyMap.get("channel_mch_id").toString());
                bookAndOrder.setChannel_order_no(bodyMap.get("channel_order_no").toString());
                bookAndOrder.setRes_code(bodyMap.get("res_code").toString());
                bookAndOrder.setExpire_time(bodyMap.get("expire_time").toString());
                accountBookAndPayOrderList.add(bookAndOrder);
            }
            // 导出excel
            WriteExcel<AccountBookAndPayOrder> we = new WriteExcel<>();
            HSSFWorkbook workbook = we.getdealReceipt(accountBookAndPayOrderList, "交易回单", AccountBookAndPayOrder.class);

            // 响应到浏览器
            try {
                workbook.write(response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

        }
    }


    // 导出全部账单
    @RequestMapping("/bill/exportAll")
    @ResponseBody
    public void exportAll(HttpServletResponse response,
                            HttpServletRequest request) {

        // 设置响应的内容类型为下载excel文件
        response.setContentType("application/vnd.ms-excel");
        // 设置响应头信息
        response.setHeader("Content-disposition", "attachment;filename="+System.currentTimeMillis()+".xls");
        // 从session域中取值
        User user = (User) request.getSession().getAttribute("user");
        String mch_id = user.getLoginName();

        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        requestHeaderMap.put("request_channel", "MGR");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("mch_id", mch_id);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("导出全部的账单,请求数据:" + reqData);

        String url = baseUrl + "/bill_export_allAccountBook?";
        // 通过getway调用web工程
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("导出全部的账单,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            // 查询结果
            List<Map<String, Object>> accountBookList = (List<Map<String, Object>>) retBody.get("accountBookList");

            List<AccountBook> accountBookList1 = new ArrayList<>();
            for(Map<String, Object> bodyMap : accountBookList) {
                AccountBook accountBook = new AccountBook();
                accountBook.setAccount_book_id(bodyMap.get("account_book_id").toString());
                accountBook.setMch_id(bodyMap.get("mch_id").toString());
                accountBook.setItems_id(bodyMap.get("items_id").toString());
                accountBook.setUser_id(bodyMap.get("user_id").toString());
                accountBook.setUser_name(bodyMap.get("user_name").toString());
                accountBook.setCurrency(bodyMap.get("currency").toString());
                accountBook.setItems_money(Long.valueOf(bodyMap.get("items_money").toString()));
                accountBook.setPay_time(bodyMap.get("pay_time").toString());
                accountBook.setPay_status(Byte.valueOf(bodyMap.get("pay_status").toString()));
                accountBookList1.add(accountBook);
            }

            // 导出excel
            WriteExcel<AccountBook> we = new WriteExcel<>();
            HSSFWorkbook workbook = we.getWorkbook(accountBookList1, "账单", AccountBook.class);

            // 响应到浏览器
            try {
                workbook.write(response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 验签
            /*String checkSign = PayDigestUtil.getSign(retMap, resKey, "sign", "payParams");
            String retSign = (String) retMap.get("sign");
            if(checkSign.equals(retSign)) {
                System.out.println("=========支付中心下单验签成功=========");
            }else {
                System.err.println("=========支付中心下单验签失败=========");
                return null;
            }*/
        }
    }

    // 导出选中账单
    @RequestMapping("/bill/exportSelected")
    @ResponseBody
    public void exportSelected(HttpServletResponse response,
                            HttpServletRequest request) {

        // 设置响应的内容类型为下载excel文件
        response.setContentType("application/vnd.ms-excel");
        // 设置响应头信息
        response.setHeader("Content-disposition", "attachment;filename="+System.currentTimeMillis()+".xls");
        // 要导出的账单的id
        String[] ids = request.getParameterValues("id");

        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        requestHeaderMap.put("request_channel", "MGR");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("ids", ids);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("导出选中账单,请求数据:" + reqData);

        String url = baseUrl + "/bill_export_selectedAccountBook?";
        // 通过getway调用web工程
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("导出选中账单,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            // 查询结果
            List<Map<String, Object>> accountBookList = (List<Map<String, Object>>) retBody.get("accountBookList");

            List<AccountBook> accountBookList1 = new ArrayList<>();
            for(Map<String, Object> bodyMap : accountBookList) {
                AccountBook accountBook = new AccountBook();
                accountBook.setAccount_book_id(bodyMap.get("account_book_id").toString());
                accountBook.setMch_id(bodyMap.get("mch_id").toString());
                accountBook.setItems_id(bodyMap.get("items_id").toString());
                accountBook.setUser_id(bodyMap.get("user_id").toString());
                accountBook.setUser_name(bodyMap.get("user_name").toString());
                accountBook.setCurrency(bodyMap.get("currency").toString());
                accountBook.setItems_money(Long.valueOf(bodyMap.get("items_money").toString()));
                accountBook.setPay_time(bodyMap.get("pay_time").toString());
                accountBook.setPay_status(Byte.valueOf(bodyMap.get("pay_status").toString()));
                accountBookList1.add(accountBook);
            }

            // 导出excel
            WriteExcel<AccountBook> we = new WriteExcel<>();
            HSSFWorkbook workbook = we.getWorkbook(accountBookList1, "账单", AccountBook.class);

            // 响应到浏览器
            try {
                workbook.write(response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 验签
            /*String checkSign = PayDigestUtil.getSign(retMap, resKey, "sign", "payParams");
            String retSign = (String) retMap.get("sign");
            if(checkSign.equals(retSign)) {
                System.out.println("=========支付中心下单验签成功=========");
            }else {
                System.err.println("=========支付中心下单验签失败=========");
                return null;
            }*/
        }
    }


    /**
     * 导入账单accountFile
     * @return
     */
    @PostMapping("/bill/importAccountFile")
//    @ResponseBody
    public String importAccountFile(@RequestParam("file") MultipartFile file,
                                    @RequestParam(value = "xmbh", required = true) String xmbh,
                                    @RequestParam(value = "xmmc", required = true) String xmmc,
                                    @RequestParam(value = "xmlb", required = false) String xmlb,
                                    @RequestParam(value = "shengxsj", required = true) String shengxsj,
                                    @RequestParam(value = "shixsj", required = true) String shixsj,
                                    HttpServletRequest request) {

        // 从session域中取值
        User user = (User) request.getSession().getAttribute("user");
        String mch_id = user.getLoginName();
        // 解析账单文件start——————————————————————————————————————————————————————————————
        // 获取文件名称
        String fileName = file.getOriginalFilename();
        // 将MultipartFile转为list
        Map<String, Object> dataMap = AccountBookUtil.readAccountBook(fileName, file, xmbh, mch_id);
        // 解析账单文件end————————————————————————————————————————————————————————————————
        ModelKey modelKey = (ModelKey) dataMap.get("modelKey");
        List<AccountBook> accountBookList = (List<AccountBook>) dataMap.get("accountBooksList");
        List<ModelValue> modelValueList = (List<ModelValue>) dataMap.get("modelValueList");
//        Map<String, Integer> titlePositionMap = (Map<String, Integer>) dataMap.get("titlePositionMap");

        AccountFile accountFile = new AccountFile();
        // TODO 预防session域失效 待做
        String id = "F00" + new Date().getTime() + (int)((Math.random()*9+1)*100000);
        String uploadDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        accountFile.setFile_id(id);
        accountFile.setMch_id(mch_id);
        accountFile.setItems_id(xmbh);
        accountFile.setItems_name(xmmc);
        accountFile.setItems_type(xmlb);
        accountFile.setItems_filename(fileName);
        accountFile.setUpload_date(uploadDate);
        accountFile.setAffect_date(shengxsj);
        accountFile.setExpiry_date(shixsj);

        // 处理账单条数特别多的情况
        int allCount = accountBookList.size();
        int begin = 0, end = 20, count = 1;
        if(end > allCount) {
            // 发送一次
            String result = subAccountBookListMethod(mch_id, accountBookList, accountFile, modelKey, modelValueList);
            System.out.println("请求导入账单接口,响应数据:" + result);
            //转换成object
            Map retMap = JSON.parseObject(result);
            Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
            if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
                Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
                // 导入结果
                boolean importOK = (boolean) retBody.get("result");
                if (importOK) {
                    return "redirect:/billUploadAndDelete.jsp";
                } else {
                    return "redirect:/billUploadAndDelete.jsp";
                }
            }
        }
        while (end <= allCount) {
            System.out.println("begin:" + begin + ",end:" + end);
            List<AccountBook> subAccountBookList = accountBookList.subList(begin, end);
            List<ModelValue> subModelValueList = modelValueList.subList(begin, end);
            if(subAccountBookList.size() == 0) {
                break;
            }
            // 循环发送
            String result = subAccountBookListMethod(mch_id, subAccountBookList, accountFile, modelKey, subModelValueList);
            System.out.println("请求导入账单接口,响应数据第" +(count++)+ "次:" + result);
            //转换成object
            Map retMap = JSON.parseObject(result);
            Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
            if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
                Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
                // 导入结果
                boolean importOK = (boolean) retBody.get("result");
                if(importOK) {
                    begin = end;
                    end = (end + 20) >= allCount ? allCount : (end + 20);
                    System.out.println("导入成功，第" + (count - 1) + "次");
                } else {
                    System.out.println("第" + (count - 1) + "次导入失败");
                    // 重新导入3次
                    for(int i = 1; i <= 3; i++) {
                        subAccountBookList = accountBookList.subList(begin, end);
                        subModelValueList = modelValueList.subList(begin, end);
                        if(subAccountBookList.size() == 0) {
                            break;
                        }
                        // 尝试3次
                        result = subAccountBookListMethod(mch_id, subAccountBookList, accountFile, modelKey, subModelValueList);
                        System.out.println(i + "重新请求导入账单接口,响应数据第" +(count - 1)+ "次:" + result);
                        //转换成object
                        retMap = JSON.parseObject(result);
                        retHeader = (Map<String, Object>) retMap.get("response_header");
                        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
                            retBody = (Map<String, Object>) retMap.get("response_body");
                            // 导入结果
                            importOK = (boolean) retBody.get("result");
                            if(importOK) {
                                begin = end;
                                end = (end + 20) >= allCount ? allCount : (end + 20);
                                break;
                            } else {
                                System.out.println(i + "再次尝试第" + (count - 1) + "次导入失败");
                                if(i ==3) {
                                    begin = end;
                                    end = (end + 20) >= allCount ? allCount : (end + 20);
                                }
                                continue;
                            }
                        }
                    }
                }
            }
        }
        // TODO 由于是form表单提交，这里处理不太好
        // 如果保存账单后想去首页，必须重定向发送billUpload请求，因为首页需要获取一些数据
        return "redirect:/billUploadAndDelete.jsp";
    }
    public String subAccountBookListMethod(String mch_id,
                                           List<AccountBook> subAccountBookList,
                                           AccountFile accountFile,
                                           ModelKey modelKey,
                                           List<ModelValue> modelValueList) {


        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        requestHeaderMap.put("request_channel", "MGR");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("accountFile", accountFile);
        requestBodyMap.put("modelKey", modelKey);
//        requestBodyMap.put("titlePositionMap", map);

        // 将accountBookList转为数组传输
        int i = 1;
        for(AccountBook ab : subAccountBookList) {
            Object[] array = {ab.getAccount_book_id(),
                    mch_id,// 修改：不再由用户上传，改由session域中取值
                    ab.getItems_id() == null ? "" : ab.getItems_id(),
                    ab.getUser_id() == null ? "" : ab.getUser_id(),
                    ab.getUser_name() == null ? "" : ab.getUser_name(),
                    ab.getCurrency() == null ? "" : ab.getCurrency(),
                    ab.getItems_money() == null ? "" : ab.getItems_money(),
                    ab.getPay_time() == null ? "" : ab.getPay_time(),
                    ab.getPay_status() == null ? "" : ab.getPay_status()};
            requestBodyMap.put("array" + (i++), array);
        }
        requestBodyMap.put("arrayCount", subAccountBookList.size());

        // 将modelValueList转为数组传输(保存顺序就按照如下顺序保存)
        int j = 1;
        for(ModelValue mv : modelValueList) {
            Object[] array = {mv.getModelValueId(),
                    mv.getModelKeyId(),
                    mv.getUserIdValue(),
                    mv.getItemsMoneyValue(),
                    mv.getValue1() == null ? "" : mv.getValue1(),
                    mv.getValue2() == null ? "" : mv.getValue2(),
                    mv.getValue3() == null ? "" : mv.getValue3(),
                    mv.getValue4() == null ? "" : mv.getValue4(),
                    mv.getValue5() == null ? "" : mv.getValue5(),
                    mv.getValue6() == null ? "" : mv.getValue6(),
                    mv.getValue7() == null ? "" : mv.getValue7(),
                    mv.getValue8() == null ? "" : mv.getValue8(),
                    mv.getValue9() == null ? "" : mv.getValue9(),
                    mv.getValue10() == null ? "" : mv.getValue10()};
            requestBodyMap.put("anotherArray" + (j++), array);
        }
        requestBodyMap.put("arrayCount2", modelValueList.size());

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("请求导入账单接口,请求数据:" + reqData);

        String url = baseUrl + "/bill_import_accountFile?";
        // 通过getway调用web工程
        String result = EPayUtil.call4Post(url + reqData);
        return result;
    }


    /**
     * 账单分页展示accountFile
     * @param pageNo1
     * @param pageSize1
     * @param request
     * @return
     */
    @RequestMapping(value = "/bill/accountFilePage", method = RequestMethod.GET)
    @ResponseBody
    public Object accountFilePage(@RequestParam(value = "pageNo", required = true)String pageNo1,
                               @RequestParam(value = "pageSize", required = true)String pageSize1,
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
        requestBodyMap.put("startIndex", startIndex);
        requestBodyMap.put("pageSize", pageSize);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("请求查询商户账单信息接口,请求数据:" + reqData);

        String url = baseUrl + "/bill_query_accountFile?";
        // 通过getway调用web工程(账单list)
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("请求查询商户账单信息接口,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

//        Map<String, Object> returnMap = new HashMap<>();
        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            List<Map<String, Object>> accountFileList = (List<Map<String, Object>>) retBody.get("accountFileList");
            int total = (int) retBody.get("total");

            List<AccountFile> list = new ArrayList<>();
            for(Map<String, Object> bodyMap : accountFileList) {
                AccountFile accountFile = new AccountFile();
                accountFile.setFile_id((String) bodyMap.get("file_id").toString());
                accountFile.setMch_id(bodyMap.get("mch_id").toString());
                accountFile.setItems_id(bodyMap.get("items_id").toString());
                accountFile.setItems_name(bodyMap.get("items_name").toString());
                accountFile.setItems_type(bodyMap.get("items_type").toString());
                accountFile.setItems_filename(bodyMap.get("items_filename").toString());
                accountFile.setUpload_date(bodyMap.get("upload_date").toString());
                accountFile.setAffect_date(bodyMap.get("affect_date").toString());
                accountFile.setExpiry_date(bodyMap.get("expiry_date").toString());
                list.add(accountFile);
            }

            // 返回商户信息
            returnMap.put("accountFileList", list);
            returnMap.put("total", total);// 账单数量
            returnMap.put(Constant.ERROR_MESSAGE, Constant.OK);

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
            returnMap.put(Constant.ERROR_MESSAGE, "系统繁忙，请稍后再试……");
        }
        return returnMap;
    }



    /**
     * 活动分页显示
     * @param pageNo1
     * @param pageSize1
     * @param request
     * @return
     */
    @RequestMapping(value = "/bill/ActivityPage", method = RequestMethod.GET)
    @ResponseBody
    public Object ActivityPage(@RequestParam(value = "pageNo", required = true)String pageNo1,
                               @RequestParam(value = "pageSize", required = true)String pageSize1,
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
        requestBodyMap.put("startIndex", startIndex);
        requestBodyMap.put("pageSize", pageSize);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("请求查询商户账单信息接口,请求数据:" + reqData);

        String url = baseUrl + "/bill_query_activity?";
        // 通过getway调用web工程(账单list)
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("请求查询商户账单信息接口,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

//        Map<String, Object> returnMap = new HashMap<>();
        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            List<Map<String, Object>> activityList = (List<Map<String, Object>>) retBody.get("ActivityList");
            int total = (int) retBody.get("total");

            List<Activity> list = new ArrayList<>();
            for(Map<String, Object> bodyMap : activityList) {
                Activity activity = new Activity();
                activity.setActivityId((String) bodyMap.get("activityId").toString());
                activity.setItemsId(bodyMap.get("itemsId").toString());
                activity.setMchId(bodyMap.get("mchId").toString());
                activity.setActivityName(bodyMap.get("activityName").toString());
                activity.setActivityType(bodyMap.get("activityType").toString());
                activity.setCreateTime(bodyMap.get("createTime").toString());
                activity.setStartTime(bodyMap.get("startTime").toString());
                activity.setEndTime(bodyMap.get("endTime").toString());
                activity.setActivityStatus(Byte.parseByte(bodyMap.get("activityStatus").toString()));
                list.add(activity);
            }

            // 返回商户信息
            returnMap.put("activityList", list);
            returnMap.put("total", total);// 账单数量
            returnMap.put(Constant.ERROR_MESSAGE, Constant.OK);

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
            returnMap.put(Constant.ERROR_MESSAGE, "系统繁忙，请稍后再试……");
        }
        return returnMap;
    }


    // 账单分页展示accountBook
    @RequestMapping(value = "/bill/accountBookPage", method = RequestMethod.GET)
    @ResponseBody
    public Object accountBookPage(@RequestParam(value = "xmbh", required = false)String xmbh,
                                  @RequestParam(value = "xmmc", required = false)String xmmc,
                                  @RequestParam(value = "wjmc", required = false)String wjmc,
                                  @RequestParam(value = "scrq", required = false)String scrq,
                                  @RequestParam(value = "status", required = false)String status,
                                  @RequestParam(value = "user_id", required = false)String user_id,
                                  @RequestParam(value = "user_name", required = false)String user_name,
                                  @RequestParam(value = "pageNo", required = true)String pageNo1,
                                  @RequestParam(value = "pageSize", required = true)String pageSize1,
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
        requestBodyMap.put("items_id", xmbh);
        requestBodyMap.put("items_name", xmmc);
        requestBodyMap.put("items_filename", wjmc);
        requestBodyMap.put("upload_date", scrq);
        if(!"-99".equals(status)) {
            requestBodyMap.put("status", status);
        }
        requestBodyMap.put("user_id", user_id);
        requestBodyMap.put("user_name", user_name);
        requestBodyMap.put("startIndex", startIndex);
        requestBodyMap.put("pageSize", pageSize);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("请求查询商户订单信息接口,请求数据:" + reqData);

        String url = baseUrl + "/bill_query_accountBook?";
        // 通过getway调用web工程(账单list)
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("请求查询商户订单信息接口,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

//        Map<String, Object> returnMap = new HashMap<>();
        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            List<Map<String, Object>> accountBookList = (List<Map<String, Object>>) retBody.get("accountBookList");
            int total = (int) retBody.get("total");

            List<AccountBookForPageShow> list = new ArrayList<>();
            for(Map<String, Object> bodyMap : accountBookList) {
                AccountBookForPageShow accountBookForPageShow = new AccountBookForPageShow();
                accountBookForPageShow.setAccount_book_id(bodyMap.get("account_book_id").toString());
                accountBookForPageShow.setMch_id(bodyMap.get("mch_id").toString());
                accountBookForPageShow.setItems_id(bodyMap.get("items_id").toString());
                accountBookForPageShow.setUser_id(bodyMap.get("user_id").toString());
                accountBookForPageShow.setUser_name(bodyMap.get("user_name").toString());
                accountBookForPageShow.setCurrency(bodyMap.get("currency").toString());
                // 将金额处理为“元”
                long items_money = Long.parseLong(bodyMap.get("items_money").toString());
                DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
                String items_moneyStr = df.format((float) items_money / 100) + "";

                accountBookForPageShow.setItems_money(items_moneyStr);
                accountBookForPageShow.setPay_time(bodyMap.get("pay_time").toString());
                accountBookForPageShow.setPay_status(Byte.valueOf(bodyMap.get("pay_status").toString()));
                // accountFile
                accountBookForPageShow.setItems_name(bodyMap.get("items_name").toString());
                accountBookForPageShow.setItems_filename(bodyMap.get("items_filename").toString());
                accountBookForPageShow.setUpload_date(bodyMap.get("upload_date").toString());
                accountBookForPageShow.setAffect_date(bodyMap.get("affect_date").toString());
                accountBookForPageShow.setExpiry_date(bodyMap.get("expiry_date").toString());
                list.add(accountBookForPageShow);
            }

            // 返回商户信息
            returnMap.put("accountBookList", list);
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

    // 账单分页展示accountBook + 统计
    @RequestMapping(value = "/bill/accountFilePageAddCount", method = RequestMethod.GET)
    @ResponseBody
    public Object accountFilePageAddCount(@RequestParam(value = "pageNo", required = true)String pageNo1,
                                          @RequestParam(value = "pageSize", required = true)String pageSize1,
                                          HttpServletRequest request) {

        Integer pageNo = Integer.valueOf(pageNo1);
        Integer pageSize = Integer.valueOf(pageSize1);
        Integer startIndex = (pageNo - 1) * pageSize;

        // 从session域中获取当前登录用户
        User user = (User) request.getSession().getAttribute("user");
        String mch_id = user.getLoginName();
        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        // TODO 临时写为 WX_APP = "微信APP支付";
        requestHeaderMap.put("request_channel", "WX_APP");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("mch_id", mch_id);
        requestBodyMap.put("startIndex", startIndex);
        requestBodyMap.put("pageSize", pageSize);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("请求查询商户订单信息接口,请求数据:" + reqData);

        String url = baseUrl + "/bill_query_accountFile_addCount?";
        // 通过getway调用web工程(账单list)
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("请求查询商户订单信息接口,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

//        Map<String, Object> returnMap = new HashMap<>();
        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            List<Map<String, Object>> accountFileForCountList = (List<Map<String, Object>>) retBody.get("accountFileForCountList");
            int total = (int) retBody.get("total");

            List<AccountFileForCount> list = new ArrayList<>();
            for(Map<String, Object> bodyMap : accountFileForCountList) {
                AccountFileForCount accountFileForCount = new AccountFileForCount();
                accountFileForCount.setItems_id(bodyMap.get("items_id").toString());
                accountFileForCount.setItems_name(bodyMap.get("items_name").toString());
                accountFileForCount.setItems_filename(bodyMap.get("items_filename").toString());
                accountFileForCount.setUpload_date(bodyMap.get("upload_date").toString());
                accountFileForCount.setAffect_date(bodyMap.get("affect_date").toString());
                accountFileForCount.setExpiry_date(bodyMap.get("expiry_date").toString());
                // 将金额处理为“元”
                long totalMoney = Long.parseLong(bodyMap.get("totalMoney").toString());
                long readMoney = Long.parseLong(bodyMap.get("readMoney").toString());
                long backMoney = Long.parseLong(bodyMap.get("backMoney").toString());
                long daiMoney = totalMoney - readMoney;
                DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
                String totalMoneyStr = df.format((float) totalMoney / 100) + "";
                String readMoneyStr = df.format((float) readMoney / 100) + "";
                String backMoneyStr = df.format((float) backMoney / 100) + "";
                String daiMoneyStr = df.format((float) daiMoney / 100) + "";
                String perent = df.format((float) readMoney / totalMoney * 100) + "";

                accountFileForCount.setTotalMoney(totalMoneyStr);
                accountFileForCount.setReadMoney(readMoneyStr);
                accountFileForCount.setBackMoney(backMoneyStr);
                accountFileForCount.setDaiMoney(daiMoneyStr);
                accountFileForCount.setPerent(perent);

                list.add(accountFileForCount);
            }

            // 返回商户信息
            returnMap.put("accountFileForCountList", list);
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

    // 账单分页展示accountBook + PayOrder
    @RequestMapping(value = "/bill/accountBookAndPayOrderPage", method = RequestMethod.GET)
    @ResponseBody
    public Object accountBookAndPayOrderPage(@RequestParam(value = "items_id", required = false)String items_id,
                                  @RequestParam(value = "user_id", required = false)String user_id,
                                  @RequestParam(value = "user_name", required = false)String user_name,
                                  @RequestParam(value = "pay_time", required = false)String pay_time,
                                  @RequestParam(value = "pay_status", required = false)String pay_status,
                                  @RequestParam(value = "settle_status", required = false)String settle_status,
                                  @RequestParam(value = "pay_channel", required = false)String pay_channel,
                                  @RequestParam(value = "mch_order_no", required = false)String mch_order_no,
                                  @RequestParam(value = "user_channel_account", required = false)String user_channel_account,
                                  @RequestParam(value = "channel_mch_id", required = false)String channel_mch_id,
                                  @RequestParam(value = "channel_order_no", required = false)String channel_order_no,
                                  @RequestParam(value = "res_code", required = false)String res_code,
                                  @RequestParam(value = "expire_time", required = false)String expire_time,
                                  @RequestParam(value = "pageNo", required = true)String pageNo1,
                                  @RequestParam(value = "pageSize", required = true)String pageSize1,
                                  HttpServletRequest request) {

        Integer pageNo = Integer.valueOf(pageNo1);
        Integer pageSize = Integer.valueOf(pageSize1);
        Integer startIndex = (pageNo - 1) * pageSize;

        // 从session域中获取当前登录用户
        User user = (User) request.getSession().getAttribute("user");
        String mch_id = user.getLoginName();
        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        // TODO 临时写为 WX_APP = "微信APP支付";
        requestHeaderMap.put("request_channel", "WX_APP");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("mch_id", mch_id);
        requestBodyMap.put("items_id", items_id);
        requestBodyMap.put("user_id", user_id);
        requestBodyMap.put("user_name", user_name);
        requestBodyMap.put("pay_time", pay_time);
        if(!"-99".equals(pay_status)) {
            requestBodyMap.put("pay_status", pay_status);
        }
        if(!"-99".equals(settle_status)) {
            requestBodyMap.put("settle_status", settle_status);
        }
        requestBodyMap.put("pay_channel", pay_channel);
        requestBodyMap.put("mch_order_no", mch_order_no);
        requestBodyMap.put("user_channel_account", user_channel_account);
        requestBodyMap.put("channel_mch_id", channel_mch_id);
        requestBodyMap.put("channel_order_no", channel_order_no);
        requestBodyMap.put("res_code", res_code);
        requestBodyMap.put("expire_time", expire_time);
        requestBodyMap.put("startIndex", startIndex);
        requestBodyMap.put("pageSize", pageSize);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("请求查询商户订单信息接口,请求数据:" + reqData);

        String url = baseUrl + "/bill_query_accountBookAndPayOrder?";
        // 通过getway调用web工程(账单list)
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("请求查询商户订单信息接口,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

//        Map<String, Object> returnMap = new HashMap<>();
        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            List<Map<String, Object>> accountBookList = (List<Map<String, Object>>) retBody.get("accountBookAndPayOrderList");
            int total = (int) retBody.get("total");

            List<AccountBookAndPayOrder> list = new ArrayList<>();
            for(Map<String, Object> bodyMap : accountBookList) {
                AccountBookAndPayOrder bookAndOrder = new AccountBookAndPayOrder();
                bookAndOrder.setAccount_book_id(bodyMap.get("account_book_id").toString());
                bookAndOrder.setItems_id(bodyMap.get("items_id").toString());
                bookAndOrder.setUser_id(bodyMap.get("user_id").toString());
                bookAndOrder.setUser_name(bodyMap.get("user_name").toString());
                bookAndOrder.setPay_time(bodyMap.get("pay_time").toString());
                bookAndOrder.setPay_status(Byte.parseByte(bodyMap.get("pay_status").toString()));
                bookAndOrder.setSettle_status(Byte.parseByte(bodyMap.get("settle_status").toString()));
                bookAndOrder.setPay_channel(bodyMap.get("pay_channel").toString());
                bookAndOrder.setMch_order_no(bodyMap.get("mch_order_no").toString());
                bookAndOrder.setUser_channel_account(bodyMap.get("user_channel_account").toString());
                bookAndOrder.setChannel_mch_id(bodyMap.get("channel_mch_id").toString());
                bookAndOrder.setChannel_order_no(bodyMap.get("channel_order_no").toString());
                bookAndOrder.setRes_code(bodyMap.get("res_code").toString());
                bookAndOrder.setExpire_time(bodyMap.get("expire_time").toString());
                list.add(bookAndOrder);
            }
            // 返回商户信息
            returnMap.put("accountBookAndPayOrderList", list);
            returnMap.put("total", total);
            returnMap.put(Constant.ERROR_MESSAGE, Constant.SUCCESS);
        } else {
            returnMap.put(Constant.ERROR_MESSAGE, Constant.FAIL);
        }
        return returnMap;
    }

}