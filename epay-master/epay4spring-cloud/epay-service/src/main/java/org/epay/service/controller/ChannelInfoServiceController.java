package org.epay.service.controller;

import org.apache.commons.lang.StringUtils;
import org.epay.common.util.MyBase64;
import org.epay.common.util.MyLog;
import org.epay.dal.dao.model.ChannelInfo;
import org.epay.service.service.ChannelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 名称: 支付渠道接口
 * 作者: HappyDan
 * 时间: 2019年4月9日
 * 版本: V1.0
 */
@RestController
public class ChannelInfoServiceController {

    private final MyLog _log = MyLog.getLog(ChannelInfoServiceController.class);

    @Autowired
    private ChannelInfoService channelInfoService;

    @RequestMapping(value = "/channel_info/selectByChannelIdAndMchId")
    public String selectChannelInfoByChannelIdAndMchId(@RequestParam String jsonParam) {
        // 参数校验
        _log.info("selectChannelInfoByChannelIdAndMchId << {}", jsonParam);
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            _log.info("selectChannelInfoByChannelIdAndMchId << {}", retObj.toString());
            return retObj.toJSONString();
        }
        JSONObject paramObj = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        _log.info("selectChannelInfoByChannelIdAndMchId << {}", paramObj.toString());
        String channel_id = paramObj.getString("channel_id");
        String mch_id = paramObj.getString("mch_id");
        
        ChannelInfo channelInfo = channelInfoService.selectChannelInfoByChannelIdAndMchId(channel_id, mch_id);
        if(channelInfo == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "数据对象不存在");
            _log.info("selectChannelInfoByChannelIdAndMchId << {}", retObj.toString());
            return retObj.toJSONString();
        }
        retObj.put("result", JSON.toJSON(channelInfo));
        _log.info("selectPayChannel >> {}", retObj);
        return CollectionUtils.isEmpty(retObj) ? null : retObj.toJSONString();
    }


}
