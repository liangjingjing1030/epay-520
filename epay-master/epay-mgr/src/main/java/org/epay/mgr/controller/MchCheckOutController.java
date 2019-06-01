package org.epay.mgr.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.epay.common.constant.Constant;
import org.epay.common.constant.PayConstant;
import org.epay.common.util.EPayUtil;
import org.epay.dal.dao.model.AccountBook;
import org.epay.dal.dao.model.AccountBookStr;
import org.epay.dal.dao.model.MchCheckOut;
import org.epay.dal.dao.model.User;
import org.epay.mgr.utils.WriteExcel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MchCheckOutController {

    @Value("${web.path}")
    String baseUrl;

    // 结算明细查询（查询AccountBook）
    @RequestMapping(value = "/count/countDetail", method = RequestMethod.GET)
    @ResponseBody
    public Object countDetail(@RequestParam(value = "itemsId", required = false)String items_id,
                             @RequestParam(value = "pageNo", required = false)String pageNo1,
                             @RequestParam(value = "pageSize", required = false)String pageSize1,
                             HttpServletRequest request) {

        Integer pageNo = Integer.valueOf(pageNo1);
        Integer pageSize = Integer.valueOf(pageSize1);
        Integer startIndex = (pageNo - 1) * pageSize;
        String startIndexStr = "" + startIndex;
        String pageSizeStr = pageSize1;

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
        requestBodyMap.put("startIndex", startIndex);
        requestBodyMap.put("pageSize", pageSize);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("请求查询账单接口,请求数据:" + reqData);

        String url = baseUrl + "/user_query_mch_checkOut_detail?";
        // 通过getway调用web工程(账单list)
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("请求查询账单接口,响应数据:" + result);

        //转换成object
        Map retMap = JSON.parseObject(result);

        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            List<Map<String, Object>> accountBookList = (List<Map<String, Object>>) retBody.get("accountBookList");
            int allMoney = (int) retBody.get("allMoney");
            int readMoney = (int) retBody.get("readMoney");
            int total = (int) retBody.get("total");

            List<AccountBookStr> list = new ArrayList<>();
            int i = 1;
            String itemsId = "";
            for(Map<String, Object> bodyMap : accountBookList) {
                // 下载账单明细所需,只运行一次
                if(i == 1) {
                    itemsId = bodyMap.get("items_id").toString();
                    i++;
                }
                AccountBookStr accountBookStr = new AccountBookStr();
                accountBookStr.setAccount_book_id(bodyMap.get("account_book_id").toString());
                accountBookStr.setMch_id(bodyMap.get("mch_id").toString());
                accountBookStr.setItems_id(bodyMap.get("items_id").toString());
                accountBookStr.setUser_id(bodyMap.get("user_id").toString());
                accountBookStr.setUser_name(bodyMap.get("user_name").toString());
                accountBookStr.setCurrency(bodyMap.get("currency").toString());
                // 将金额处理为“元”
                long items_money = Long.parseLong(bodyMap.get("items_money").toString());
                DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
                String items_moneyStr = df.format((float) items_money / 100);
                accountBookStr.setItems_money(items_moneyStr);
                accountBookStr.setPay_time(bodyMap.get("pay_time").toString());
                accountBookStr.setPay_status(Byte.valueOf(bodyMap.get("pay_status").toString()));
                list.add(accountBookStr);
            }
            int daiMoney = allMoney - readMoney;
            // 将钱数“分”转换为“元”，且保留小数
            DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
            String allMoneyStr = df.format((float) allMoney / 100);
            String readMoneyStr = df.format((float) readMoney / 100);
            String daiMoneyStr = df.format((float) daiMoney / 100);

            // 返回商户信息
            returnMap.put("itemsId", itemsId);// 下载账单明细所需
            returnMap.put("accountBookList", list);
            returnMap.put("allMoney", allMoneyStr);// 总金额
            returnMap.put("readMoney", readMoneyStr);// 已缴金额
            returnMap.put("daiMoney", daiMoneyStr);// 待缴金额
            returnMap.put("total", total);// 账单数量
            returnMap.put(Constant.ERROR_MESSAGE, Constant.OK);
        } else {
            returnMap.put(Constant.ERROR_MESSAGE, retHeader.get(PayConstant.RETURN_PARAM_RETMSG));
        }

        return returnMap;
    }

    // 结算分页查询
    @RequestMapping(value = "/count/countPage", method = RequestMethod.GET)
    @ResponseBody
    public Object countPage(@RequestParam(value = "items_id", required = false)String items_id,
                             @RequestParam(value = "start_time", required = false)String start_time1,
                             @RequestParam(value = "end_time", required = false)String end_time1,
                             @RequestParam(value = "moneyStates", required = false)String settle_status,
                             @RequestParam(value = "pageNo", required = false)String pageNo1,
                             @RequestParam(value = "pageSize", required = false)String pageSize1,
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

        Integer pageNo = Integer.valueOf(pageNo1);
        Integer pageSize = Integer.valueOf(pageSize1);
        Integer startIndex = (pageNo - 1) * pageSize;
        String startIndexStr = "" + startIndex;
        String pageSizeStr = pageSize1;

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
        requestBodyMap.put("start_time", start_time);
        requestBodyMap.put("end_time", end_time);
        if(!"-99".equals(settle_status)) {
            requestBodyMap.put("settle_status", settle_status);
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
        System.out.println("请求查询结算订单接口,请求数据:" + reqData);

        String url = baseUrl + "/user_query_mch_checkOut1?";
        // 通过getway调用web工程(账单list)
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("请求查询结算订单接口,响应数据:" + result);

        //转换成object
        Map retMap = JSON.parseObject(result);

        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            List<Map<String, Object>> mchCheckOutList = (List<Map<String, Object>>) retBody.get("mchCheckOutList");
            int total = (int) retBody.get("total");
            // 此处使用的是dao中的MchCheckOut，金额格式为String
            List<MchCheckOut> list = new ArrayList<>();
            for(Map<String, Object> bodyMap : mchCheckOutList) {
                MchCheckOut mchCheckOut = new MchCheckOut();
                mchCheckOut.setMchCheckoutId(bodyMap.get("mchCheckoutId").toString());
                mchCheckOut.setMchId(bodyMap.get("mchId").toString());
                mchCheckOut.setMchName(bodyMap.get("mchName").toString());
                mchCheckOut.setItemsId(bodyMap.get("itemsId").toString());
                mchCheckOut.setCurrency(bodyMap.get("currency").toString());
                // 将金额处理为“元”
                long dealMoney = Long.parseLong(bodyMap.get("dealMoney").toString());
                long checkoutMoney = Long.parseLong(bodyMap.get("checkoutMoney").toString());
                DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
                String dealMoneyStr = df.format((float) dealMoney / 100) + "";
                String checkoutMoneyStr = df.format((float) checkoutMoney / 100);
                mchCheckOut.setDealMoney(dealMoneyStr);
                mchCheckOut.setCheckoutMoney(checkoutMoneyStr);
                mchCheckOut.setCheckoutRate(Integer.parseInt(bodyMap.get("checkoutRate").toString()));
                mchCheckOut.setCheckoutDate(bodyMap.get("checkoutDate").toString());
                mchCheckOut.setSettleStatus(Byte.parseByte(bodyMap.get("settleStatus").toString()));
                mchCheckOut.setCreateTime(bodyMap.get("createTime").toString());
                mchCheckOut.setUpdateTime(bodyMap.get("updateTime").toString());
                list.add(mchCheckOut);
            }

            // 返回商户信息
            returnMap.put("mchCheckOutList", list);
            returnMap.put("total", total);// 账单数量
            returnMap.put(Constant.ERROR_MESSAGE, Constant.OK);
        } else {
            returnMap.put(Constant.ERROR_MESSAGE, retHeader.get(PayConstant.RETURN_PARAM_RETMSG) + "1");
        }

        return returnMap;
    }

    // 下载结算明细
    @RequestMapping("/count/downLoadCheckOutDetail")
    @ResponseBody
    public void downLoadCheckOutDetail(HttpServletResponse response,
                               HttpServletRequest request) {

        // 从session域中获取当前登录用户
        User user = (User) request.getSession().getAttribute("user");
        String mch_id = user.getLoginName();

        // 设置响应的内容类型为下载excel文件
        response.setContentType("application/vnd.ms-excel");
        // 设置响应头信息
        response.setHeader("Content-disposition", "attachment;filename="+System.currentTimeMillis()+".xls");
        // 要导出的账单的项目编号
        String items_id = request.getParameter("itemsId");

        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        // TODO 临时写为 WX_APP = "微信APP支付";
        requestHeaderMap.put("request_channel", "WX_APP");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("mch_id", mch_id);
        requestBodyMap.put("items_id", items_id);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("导出结算明细,请求数据:" + reqData);

        String url = baseUrl + "/count_export_mchCheckOut_detail?";
        // 通过getway调用web工程
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("导出结算明细,响应数据:" + result);
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
            HSSFWorkbook workbook = we.getWorkbook(accountBookList1, "结算明细", AccountBook.class);

            // 响应到浏览器
            try {
                workbook.write(response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
