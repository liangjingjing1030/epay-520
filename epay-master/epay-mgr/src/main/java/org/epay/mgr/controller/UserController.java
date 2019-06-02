package org.epay.mgr.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.epay.common.constant.Constant;
import org.epay.common.constant.PayConstant;
import org.epay.common.util.EPayUtil;
import org.epay.common.util.MD5AndCreateKeyUtil;
import org.epay.dal.dao.model.*;
import org.epay.mgr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Value("${web.path}")
    String baseUrl;

    @Autowired
    private UserService userService;

    //根路径拦截
    @RequestMapping("/mgr")
    public String index(Model model, HttpServletResponse response) {
        model.addAttribute("name", "simonsfan");
        return "forward:/login.jsp";
    }

    // 登录
    @RequestMapping("/public/login")
    @ResponseBody
    public Object login(HttpServletRequest request,
                        @RequestParam(value = "loginAct", required = true) String loginAct,
                        @RequestParam(value = "loginPwd", required = true) String loginPwd,
                        @RequestParam(value = "checkFlag", required = true) String checkFlag) {

        Map<String, Object> retMap = new HashMap<>();
        // 密码进行加密
        String md5Pswd = MD5AndCreateKeyUtil.md5(loginPwd.trim(), "UTF-8");
        User user = userService.login(loginAct, md5Pswd);
        // 判断用户是否存在
        if (null == user) {
            retMap.put(Constant.ERROR_MESSAGE, "用户名或密码输入错误，请重新输入");
            return retMap;
        }
        // 将用户信息放入session域中
        request.getSession().setAttribute("user", user);

        retMap.put(Constant.ERROR_MESSAGE, Constant.OK);

        return retMap;
    }

    // 修改密码
    @RequestMapping("/public/updatePassword")
    @ResponseBody
    public Object updatePassword(HttpServletRequest request,
                        @RequestParam(value = "new_password", required = true) String new_password1,
                        @RequestParam(value = "old_password", required = true) String old_password1) {

        Map<String, Object> retMap = new HashMap<>();
        // 密码进行加密
        String old_password = MD5AndCreateKeyUtil.md5(old_password1.trim(), "UTF-8");
        String new_password = MD5AndCreateKeyUtil.md5(new_password1.trim(), "UTF-8");

        User user = (User) request.getSession().getAttribute("user");
        String loginName = user.getLoginName();
        String password = user.getPassword();
        // 判断旧密码输入是否正确
        if(!password.equals(old_password)) {
            retMap.put(Constant.ERROR_MESSAGE, "原密码输入错误!");
            return retMap;
        }
        // 更新密码
        int updateCount = userService.updatePasswordByLoginNameAndLoginPassword(loginName, password, new_password);
        // 判断用户是更新成功
        if (updateCount <= 0) {
            retMap.put(Constant.ERROR_MESSAGE, "系统繁忙，请稍后再试!");
            return retMap;
        } else {
            // 将更新后的用户信息放入session域中
            user.setPassword(new_password);
            request.getSession().setAttribute("user", user);

            retMap.put(Constant.ERROR_MESSAGE, Constant.OK);

            return retMap;
        }
    }

    // 验证图形验证码
    @PostMapping(value = "mch/checkCaptcha")
    @ResponseBody
    public Map<String, Object> checkCaptcha(HttpServletRequest request,
                                            @RequestParam (value = "captcha", required = true) String capthca) {

        Map<String, Object> retMap = new HashMap<>();

        // 从session中获取captcha
        String sessionCaptcha = (String) request.getSession().getAttribute("captcha");

        // 比较两个字符串是否相同,在pom文件中有个apache提供的commons-lang3工具包，将一些方法进行了优化
        if(!StringUtils.equalsIgnoreCase(capthca,sessionCaptcha)) {
            retMap.put("errorMessage", "请输入正确的图形验证码");
            return retMap;
        }

        retMap.put("errorMessage", "OK");

        return retMap;
    }

    // 验证用户账户状态
    // loginAct不再是商户号@2019年6月1日23:18:27
    @RequestMapping("/mch/checkStatus")
    @ResponseBody
    public Object checkStatus(@RequestParam(value = "loginAct", required = true) String loginAct,
                              @RequestParam(value = "loginPwd", required = true) String loginPwd) {

        Map<String, Object> retMap = new HashMap<>();
        // 密码进行加密
        String md5Pswd = MD5AndCreateKeyUtil.md5(loginPwd.trim(), "UTF-8");
        User user = userService.queryByLoginNameAndPassword(loginAct, md5Pswd);
        if(user == null) {
            retMap.put(Constant.ERROR_MESSAGE, "用户名或密码错误，请重新输入");
            return retMap;
        }
        // 改为判断商户@2019年6月1日22:53:19
        MchInfo mchInfo = userService.queryMchInfoByMchId(user.getLoginName());
        if(mchInfo.getMch_status() == 1) {//商户状态,0-未激活,1-使用中,2-停止使用
            retMap.put(Constant.ERROR_MESSAGE, Constant.OK);
        } else {
            retMap.put(Constant.ERROR_MESSAGE, "当前账号不可用，请联系管理员");
        }
        return retMap;
    }

    // 查询私钥
    @RequestMapping("/public/queryKey")
    public void queryKey(HttpServletRequest request,HttpServletResponse response) {

        User user = (User) request.getSession().getAttribute("user");
        String loginName = user.getLoginName();
        String password = user.getPassword();

        List<User> userList = userService.queryKey(loginName, password);

        try {
            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString(userList);
            response.setContentType("text/json;charset=UTF-8");
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 查询证件类型
    @RequestMapping("/mch/register/queryCertificateType")
    public void queryCertificateType(HttpServletResponse response) {

        List<CertificateType> cerList = userService.queryAllCertificateType();

        try {
            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString(cerList);
            response.setContentType("text/json;charset=UTF-8");
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 查询经营范围
    @RequestMapping("/mch/register/queryBusinessRange")
    public void queryBusinessRange(HttpServletResponse response) {

        List<BusinessRange> rangeList = userService.queryAllBusinessRange();

        try {
            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString(rangeList);
            response.setContentType("text/json;charset=UTF-8");
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 查询省份
    @RequestMapping("/mch/register/queryProvince")
    public void queryProvince(HttpServletResponse response) {

        List<ProvinceCity> provinceList = userService.queryProvince();

        try {
            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString(provinceList);
            response.setContentType("text/json;charset=UTF-8");
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 查询城市
    @RequestMapping("/mch/register/queryCityByParentId")
    public void queryCityByParentId(HttpServletResponse response,
                                    HttpServletRequest request) {

        String id = request.getParameter("id");
        List<ProvinceCity> cityList = userService.queryCityByParentId(Integer.parseInt(id));

        try {
            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString(cityList);
            response.setContentType("text/json;charset=UTF-8");
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 注册
    @RequestMapping("/mch/register")
    @ResponseBody
    public Object registerOne(HttpServletRequest request,
                              @RequestParam(value = "loginAct", required = true) String loginAct,
                              @RequestParam(value = "loginPwd", required = true) String loginPwd,
                              @RequestParam(value = "mchName", required =  true)	String mchName,
                              @RequestParam(value = "mchType", required =  true)	String mchType,
                              @RequestParam(value = "range", required =  true)	String range,
                              @RequestParam(value = "mchAddress", required =  true)	String mchAddress,
                              @RequestParam(value = "contactName", required =  true)	String contactName,
                              @RequestParam(value = "contactPhone", required =  true)	String contactPhone,
                              @RequestParam(value = "contactEmail", required =  false)	String contactEmail,
                              @RequestParam(value = "cerType", required =  false)	String cerType,
                              @RequestParam(value = "cerNo", required =  false)	String cerNo,
                              @RequestParam(value = "businessLic", required =  false)	String businessLic,
                              @RequestParam(value = "city", required =  true)	String city,
                              @RequestParam(value = "accountNo", required =  true)	String accountNo,
                              @RequestParam(value = "accountName", required =  true)	String accountName,
                              @RequestParam(value = "openBank", required =  true)	String openBank,
                              @RequestParam(value = "accountType", required =  true)	String accountType,
                              @RequestParam(value = "dellDay", required =  true)	String dellDay,
                              @RequestParam(value = "branchId", required =  true)	String branchId,
                              @RequestParam(value = "branchName", required =  false)	String branchName,
                              @RequestParam(value = "staffNo", required =  false)	String staffNo,
                              @RequestParam(value = "staffName", required =  false)	String staffName,
                              @RequestParam(value = "account", required =  false)	String account) {

        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        // TODO 临时写为 WX_APP = "微信APP支付";
        requestHeaderMap.put("request_channel", "WX_APP");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("mch_id", loginAct);
        requestBodyMap.put("pswd", loginPwd);
        requestBodyMap.put("mch_name", mchName);
        // 1:个人商户；2:企业商户；3:小微商户
        /*if("1".equals(mchType)) {
            requestBodyMap.put("mch_type", "个人商户");
        } else if("2".equals(mchType)) {
            requestBodyMap.put("mch_type", "企业商户");
        } else if("3".equals(mchType)) {
            requestBodyMap.put("mch_type", "小微商户");
        }*/
        requestBodyMap.put("mch_type", mchType);

        requestBodyMap.put("mch_range", range);
        requestBodyMap.put("mch_address", mchAddress);
        requestBodyMap.put("contact_person", contactName);
        requestBodyMap.put("contact_phone", contactPhone);
        requestBodyMap.put("contact_email", contactEmail);// 非
        if(!"-99".equals(cerType)) {
            requestBodyMap.put("certificate_type", cerType);// 非
        }
        requestBodyMap.put("certificate_number", cerNo);// 非
        requestBodyMap.put("business_license", businessLic);// 非
        requestBodyMap.put("mch_city", city);
        requestBodyMap.put("mch_bank_account", accountNo);
        requestBodyMap.put("mch_bank_name", accountName);
        requestBodyMap.put("account_bank", openBank);
        // 1:对私；2：对公
        if("1".equals(accountType)) {
            requestBodyMap.put("account_type", "对私");
        } else if("2".equals(accountType)) {
            requestBodyMap.put("account_type", "对公");
        }
        requestBodyMap.put("mch_stl_day", dellDay);
        requestBodyMap.put("branch_id", branchId);// 非
        requestBodyMap.put("branch_name", branchName);// 非
        requestBodyMap.put("staff_id", staffNo);// 非
        requestBodyMap.put("staff_name", staffName);// 非
        requestBodyMap.put("account", account);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("请求注册接口,请求数据:" + reqData);

        String url = baseUrl + "/mch_register?";
        // 通过getway调用web工程(账单list)
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("请求注册接口,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

//        Map<String, Object> returnMap = new HashMap<>();
        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {// "retCode"
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            String req_key = (String) retBody.get("req_key");// 请求私钥
            String res_key = (String) retBody.get("res_key");// 响应私钥

            // 返回商户信息
            returnMap.put(Constant.OK, Constant.SUCCESS);
            returnMap.put(Constant.ERROR_MESSAGE, retHeader.get(PayConstant.RETURN_PARAM_RETMSG));

            // 验签
            /*String checkSign = PayDigestUtil.getSign(retMap, resKey, "sign", "payParams");
            String retSign = (String) retMap.get("sign");
            if(checkSign.equals(retSign)) {
                System.out.println("=========支付中心下单验签成功=========");
            }else {
                System.err.println("=========支付中心下单验签失败=========");
                return null;
            }*/
            // 注册成功后将请求私钥跟响应私钥存放到User表中
            String md5Pswd = MD5AndCreateKeyUtil.md5(loginPwd.trim(), "UTF-8");
            int updateCount = userService.updateUserByReqKeyAndResKey(loginAct, md5Pswd, req_key, res_key);
            if(updateCount != 1) {
                returnMap.put(Constant.OK, Constant.SUCCESS);
                returnMap.put(Constant.ERROR_MESSAGE, retHeader.get(PayConstant.RETURN_PARAM_RETMSG) + "保存密钥失败！");
            }
        } else {
            returnMap.put(Constant.OK, Constant.FAIL);
            returnMap.put(Constant.ERROR_MESSAGE, retHeader.get(PayConstant.RETURN_PARAM_RETMSG));
        }
        return returnMap;
    }

    // 找回密码
    @RequestMapping("/mch/passwordUpdate")
    @ResponseBody
    public Object passwordUpdate(HttpServletRequest request,
                              @RequestParam(value = "loginAct", required = true) String loginAct,
                              @RequestParam(value = "mchName", required =  true)	String mchName,
                              @RequestParam(value = "range", required =  true)	String range,
                              @RequestParam(value = "mchType", required =  true)	String mchType,
                              @RequestParam(value = "city", required =  true)	String city,
                              @RequestParam(value = "mchAddress", required =  true)	String mchAddress,
                              @RequestParam(value = "contactName", required =  true)	String contactName,
                              @RequestParam(value = "contactPhone", required =  true)	String contactPhone,
                              @RequestParam(value = "loginPwd", required =  true)	String newLoginPwd) {

        // 响应头中放入渠道信息
        JSONObject requestHeaderMap = new JSONObject();
        // TODO 临时写为 WX_APP = "微信APP支付";
        requestHeaderMap.put("request_channel", "WX_APP");
        // 响应体
        JSONObject requestBodyMap = new JSONObject();
        requestBodyMap.put("mch_id", loginAct);
        requestBodyMap.put("mch_name", mchName);
        requestBodyMap.put("mch_range", range);
        requestBodyMap.put("mch_type", mchType);
        requestBodyMap.put("mch_city", city);
        requestBodyMap.put("mch_address", mchAddress);
        requestBodyMap.put("contact_person", contactName);
        requestBodyMap.put("contact_phone", contactPhone);
        requestBodyMap.put("newLoginPwd", newLoginPwd);

        JSONObject paramMap = new JSONObject();
        // 请求报文：响应头 + 响应体 + sign(签名)
        paramMap.put("request_header", requestHeaderMap);
        paramMap.put("request_body", requestBodyMap);
        // TODO 待做
        paramMap.put("sign", "xxxxx");

        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("请求找回密码接口,请求数据:" + reqData);

        String url = baseUrl + "/mch_updatePassword?";
        // 通过getway调用web工程(账单list)
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("请求找回密码接口,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

//        Map<String, Object> returnMap = new HashMap<>();
        JSONObject returnMap = new JSONObject();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {// "retCode"
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            int updateCount = Integer.parseInt((String) retBody.get("updateCount"));

            if(updateCount != 1) {
                returnMap.put(Constant.OK, Constant.FAIL);
                returnMap.put(Constant.ERROR_MESSAGE, "修改密码失败，请稍后重试！");
                return returnMap;
            }
            // 返回商户信息
            returnMap.put(Constant.OK, Constant.SUCCESS);
            returnMap.put(Constant.ERROR_MESSAGE, retHeader.get(PayConstant.RETURN_PARAM_RETMSG));
        } else {
            returnMap.put(Constant.OK, Constant.FAIL);
            returnMap.put(Constant.ERROR_MESSAGE, retHeader.get(PayConstant.RETURN_PARAM_RETMSG));
        }
        return returnMap;
    }

}
