package org.epay.service.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.epay.common.util.MyBase64;
import org.epay.common.util.MyLog;
import org.epay.dal.dao.model.MchStlInfo;
import org.epay.service.service.MchStlInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名: 商户结算信息查询类
 * 作者: GaoLiang
 * 时间: 2019年4月28日
 * 版本: V1.0
 */
@RestController
public class MchStlInfoServiceController {

    private final MyLog _log = MyLog.getLog(MchStlInfoServiceController.class);

    @Autowired
    private MchStlInfoService mchStlInfoService;

    @RequestMapping(value = "/mch_info/selectMchStlInfoByMchId")
    public String selectMchInfoByMchId(@RequestParam String jsonParam) {
        _log.info("mchStlInfoQuery << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);
        String mch_id = paramObj.getString("mch_id");
        System.out.println("service中controller的mch_id：" + mch_id);

        System.out.println("mch_id=" + mch_id);
        // 查询商户结算信息
        MchStlInfo mchStlInfo = mchStlInfoService.selectMchStlInfoByMchId(mch_id);

        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        if(mchStlInfo == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "数据对象不存在");
            return retObj.toJSONString();
        }
        retObj.put("result", JSON.toJSON(mchStlInfo));
        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

}
