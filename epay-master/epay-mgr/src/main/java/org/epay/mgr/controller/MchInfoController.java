package org.epay.mgr.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.epay.common.constant.Constant;
import org.epay.common.constant.PayConstant;
import org.epay.common.util.EPayUtil;
import org.epay.dal.dao.model.BusinessRange;
import org.epay.dal.dao.model.CertificateType;
import org.epay.dal.dao.model.MchInfo;
import org.epay.dal.dao.model.User;
import org.epay.mgr.service.BusinessRangeService;
import org.epay.mgr.service.CertificateTypeService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MchInfoController {

//    static final String baseUrl = "http://127.0.0.1:3020/epay";

    @Value("${web.path}")
    String baseUrl;

    @Autowired
    private BusinessRangeService businessRangeService;

    @Autowired
    private CertificateTypeService certificateTypeService;

    @RequestMapping(value = "/mch/queryMchInfo", method = RequestMethod.GET)
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
        System.out.println("请求查询商户信息接口,请求数据:" + reqData);

        String url = baseUrl + "/mch_query_mchInfo?";
        // 通过getway调用web工程
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("请求查询商户信息接口,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

        Map<String, Object> returnMap = new HashMap<>();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            Map<String, Object> bodyMap = (Map<String, Object>) retBody.get("result");
            // 获取object中的返回对象
            MchInfo mchInfo = new MchInfo();
            mchInfo.setMch_id(mch_id);
            mchInfo.setMch_name(bodyMap.get("mch_name").toString());
            mchInfo.setMch_type(bodyMap.get("mch_type").toString());
            // 处理经营范围
            String mch_range_id = bodyMap.get("mch_range").toString();
            // 根据经营范围主键查询经营范围
            BusinessRange businessRange = businessRangeService.queryBusinessRange(mch_range_id);
            mchInfo.setMch_range(businessRange.getBusinessRange());
            mchInfo.setMch_city(bodyMap.get("mch_city").toString());
            mchInfo.setMch_address(bodyMap.get("mch_address").toString());
            mchInfo.setNext_audit_dept_id(Long.valueOf(bodyMap.get("next_audit_dept_id").toString()));
            mchInfo.setContact_person(bodyMap.get("contact_person").toString());
            mchInfo.setContact_phone(bodyMap.get("contact_phone").toString());
            mchInfo.setContact_email(bodyMap.get("contact_email").toString());
            // 处理证件类型
            String certificate_type_id = bodyMap.get("certificate_type").toString();
            if(StringUtils.isNotBlank(certificate_type_id)) {
                // 根据证件类型主键查询证件类型
                CertificateType c = certificateTypeService.queryCertificateType(certificate_type_id);
                mchInfo.setCertificate_type(c.getType());
            }
            mchInfo.setCertificate_number(bodyMap.get("certificate_number").toString());
            mchInfo.setBusiness_license(bodyMap.get("business_license").toString());
            mchInfo.setReq_key(bodyMap.get("req_key").toString());
            mchInfo.setRes_key(bodyMap.get("res_key").toString());
            mchInfo.setBranch_id(bodyMap.get("branch_id").toString());
            mchInfo.setBranch_name(bodyMap.get("branch_name").toString());
            mchInfo.setStaff_id(bodyMap.get("staff_id").toString());
            mchInfo.setStall_name(bodyMap.get("stall_name").toString());
            // 处理创建时间 20190601104321
            String create_time = bodyMap.get("create_time").toString();
            if(create_time.length() == 14) {
                create_time = dataMethod(create_time);
            }
            mchInfo.setCreate_time(create_time);
            String update_time = bodyMap.get("update_time").toString();
            if(update_time.length() == 14) {
                update_time = dataMethod(update_time);
            }
            mchInfo.setUpdate_time(update_time);
            String audit_time = bodyMap.get("audit_time").toString();
            if(audit_time.length() == 14) {
                audit_time = dataMethod(audit_time);
            }
            mchInfo.setAudit_time(audit_time);
            mchInfo.setAudit_status(Byte.parseByte(bodyMap.get("audit_status").toString()));
            mchInfo.setAudit_reason(bodyMap.get("audit_reason").toString());
            // 将商户信息放入session域中
            request.getSession().setAttribute("mchInfo", mchInfo);
            returnMap.put(Constant.ERROR_MESSAGE, Constant.OK);
            List<MchInfo> mchInfoList = new ArrayList<>();
            // 返回商户信息
            mchInfoList.add(mchInfo);
            returnMap.put("mchInfoList", mchInfoList);
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
            returnMap.put(Constant.ERROR_MESSAGE, "加载商户信息失败，请稍后再试……");
        }
        return returnMap;
    }

    public String dataMethod(String dataStr) {
        String year = dataStr.substring(0, 4);
        String month = dataStr.substring(4, 6);
        String day = dataStr.substring(6, 8);
        String hour = dataStr.substring(8, 10);
        String minute = dataStr.substring(10, 12);
        String second = dataStr.substring(12, 14);
        String new_create_time = year + "年" + month + "月" + day + "日 " + hour + ":" + minute + ":" + second;
        return new_create_time;
    }

}
