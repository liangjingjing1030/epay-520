package org.epay.mgr.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.epay.common.constant.Constant;
import org.epay.common.constant.PayConstant;
import org.epay.common.util.EPayUtil;
import org.epay.dal.dao.model.ChannelInfo;
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
public class MchChannelController {

//    static final String baseUrl = "http://127.0.0.1:3020/epay";

    @Value("${web.path}")
    String baseUrl;

    @RequestMapping(value = "/mch/queryMchChannel", method = RequestMethod.GET)
    @ResponseBody
    public Object queryChannel(HttpServletRequest request) {
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
        System.out.println("请求查询商户渠道信息接口,请求数据:" + reqData);

        String url = baseUrl + "/mch_query_mchChannel?";
        // 通过getway调用web工程(渠道list)
        String result = EPayUtil.call4Post(url + reqData);
        System.out.println("请求查询商户渠道信息接口,响应数据:" + result);
        //转换成object
        Map retMap = JSON.parseObject(result);

        Map<String, Object> returnMap = new HashMap<>();
        Map<String, Object> retHeader = (Map<String, Object>) retMap.get("response_header");
        if("SUCCESS".equals(retHeader.get(PayConstant.RETURN_PARAM_RETCODE))) {
            Map<String, Object> retBody = (Map<String, Object>) retMap.get("response_body");
            List<Map<String, Object>> channelMapList = (List<Map<String, Object>>) retBody.get("channelMapList");
            List<ChannelInfo> list = new ArrayList<>();
            for(Map<String, Object> bodyMap : channelMapList) {
                ChannelInfo channelInfo = new ChannelInfo();
                channelInfo.setChannel_info_id((Integer) bodyMap.get("channel_info_id"));
                channelInfo.setMch_id(bodyMap.get("mch_id").toString());
                channelInfo.setChannel_id(bodyMap.get("channel_id").toString());
                channelInfo.setChannel_name(bodyMap.get("channel_name").toString());
                channelInfo.setChannel_mchId(bodyMap.get("channel_mchId").toString());
                channelInfo.setChannel_status((byte)(int) bodyMap.get("channel_status"));
                channelInfo.setParam(bodyMap.get("param").toString());
                channelInfo.setRemark(bodyMap.get("remark").toString());
                channelInfo.setCreate_time(bodyMap.get("create_time").toString());
                channelInfo.setUpdate_time(bodyMap.get("update_time").toString());
                list.add(channelInfo);
            }
            // 将商户信息放入session域中
            request.getSession().setAttribute("mchChannelList", list);
            returnMap.put(Constant.ERROR_MESSAGE, Constant.OK);
            /*List<MchStlInfo> mchStlInfoList = new ArrayList<>();
            // 返回商户信息
            mchStlInfoList.add(mchStlInfo);*/
            returnMap.put("mchChannelList", list);
            // 返回查询到的list的长度，便于控制前端标题的显示
            returnMap.put("size", list.size());
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
            returnMap.put(Constant.ERROR_MESSAGE, "加载商户渠道信息失败，请稍后再试……");
        }
        return returnMap;
    }

}