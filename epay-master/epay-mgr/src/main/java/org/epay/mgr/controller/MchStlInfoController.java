package org.epay.mgr.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.epay.common.constant.Constant;
import org.epay.common.constant.PayConstant;
import org.epay.common.util.EPayUtil;
import org.epay.dal.dao.model.MchStlInfo;
import org.epay.dal.dao.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MchStlInfoController {

//    static final String baseUrl = "http://127.0.0.1:3020/epay";
    @Value("${web.path}")
    String baseUrl;

    @RequestMapping(value = "/mch/queryMchStlInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object queryMchInfo(HttpServletRequest request) {
        // 从session域中获取当前登录用户
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
        System.out.println("请求查询商户结算信息接口,请求数据:" + reqData);

        String url = baseUrl + "/mch_query_mchStlInfo?";
        // 通过getway调用web工程
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("请求查询商户结算信息接口,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

        Map<String, Object> returnMap = new HashMap<>();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            Map<String, Object> bodyMap = (Map<String, Object>) retBody.get("result");
            // 获取object中的返回对象
            MchStlInfo mchStlInfo = new MchStlInfo();
            mchStlInfo.setMch_id(mch_id);
            mchStlInfo.setMch_bank_account(bodyMap.get("mch_bank_account").toString());
            mchStlInfo.setMch_bank_name(bodyMap.get("mch_bank_name").toString());
            mchStlInfo.setAccount_bank(bodyMap.get("account_bank").toString());
            mchStlInfo.setAccount_type(bodyMap.get("account_type").toString());
            mchStlInfo.setMch_stl_day(bodyMap.get("mch_stl_day").toString());
            mchStlInfo.setCreate_time(bodyMap.get("create_time").toString());
            mchStlInfo.setUpdate_time(bodyMap.get("update_time").toString());
            // 将商户信息放入session域中
            request.getSession().setAttribute("mchStlInfo", mchStlInfo);
            returnMap.put(Constant.ERROR_MESSAGE, Constant.OK);
            List<MchStlInfo> mchStlInfoList = new ArrayList<>();
            // 返回商户信息
            mchStlInfoList.add(mchStlInfo);
            returnMap.put("mchStlInfoList", mchStlInfoList);
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
            returnMap.put(Constant.ERROR_MESSAGE, "加载商户结算信息失败，请稍后再试……");
        }
        return returnMap;
    }

}
