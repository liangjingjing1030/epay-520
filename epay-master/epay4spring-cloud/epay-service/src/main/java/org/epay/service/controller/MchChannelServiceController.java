package org.epay.service.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.epay.common.util.MyBase64;
import org.epay.common.util.MyLog;
import org.epay.dal.dao.model.ChannelInfo;
import org.epay.service.service.MchChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 类名: 商户渠道信息查询类
 * 作者: GaoLiang
 * 时间: 2019年4月28日
 * 版本: V1.0
 */
@RestController
public class MchChannelServiceController {

    private final MyLog _log = MyLog.getLog(MchChannelServiceController.class);

    @Autowired
    private MchChannelService mchChannelService;

    @RequestMapping(value = "/mch_info/selectMchChannelByMchId")
    public String selectMchInfoByMchId(@RequestParam String jsonParam) {
        _log.info("mchChannelQuery << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        _log.info("mchChannelQuery << {}", param.toString());
        
        JSONObject paramObj = JSON.parseObject(param);
        String mch_id = paramObj.getString("mch_id");
        System.out.println("service中controller的mch_id：" + mch_id);

        System.out.println("mch_id=" + mch_id);
        // 查询商户渠道信息list
        List<ChannelInfo> channelInfoList = mchChannelService.selectMchChannelByMchId(mch_id);

        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            _log.info("mchChannelQuery << {}", retObj.toString());
            return retObj.toJSONString();
        }
        if(channelInfoList == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "数据对象不存在");
            _log.info("mchChannelQuery << {}", retObj.toString());
            return retObj.toJSONString();
        }
        // { result1:{mch_id1,channel_id1,……},result2:{mch_id2,channel_id2,……},…… }
        int i = 1;
        // 遍历list
        for(ChannelInfo channelInfo : channelInfoList) {
            retObj.put("result" + (i++), JSON.toJSON(channelInfo));
        }
        // list总条数
        retObj.put("count", channelInfoList.size());
        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

}
