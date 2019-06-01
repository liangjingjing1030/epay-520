package org.epay.service.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.epay.common.util.MyBase64;
import org.epay.common.util.MyLog;
import org.epay.dal.dao.model.AccountFile;
import org.epay.service.service.AccountFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 类名: 查询账单文件获取项目编号
 * 作者: HappyDan
 * 时间: 2019年4月26日
 * 版本: V1.0
 */
@RestController
public class MchQueryAccountFileServiceController {

    private final MyLog _log = MyLog.getLog(MchQueryAccountFileServiceController.class);

    @Autowired
    private AccountFileService accountFileService;

    @RequestMapping(value = "/account_file/mch_query_account_files")
    public String mchQueryAccountFile(@RequestParam String jsonParam) {
        _log.info("mch_query_account_files << {}", jsonParam);
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
    	JSONObject object = JSONObject.parseObject(new String(MyBase64.decode(jsonParam)));
        _log.info("mch_query_account_files >> MyBase64 << {}", object.toJSONString());
        String mch_id  = "";		// 商户ID
        try {
        	mch_id  = object.getString("mch_id").trim();	// 商户ID
        	if(StringUtils.isBlank(mch_id)) {
        		retObj.put("code", "0001"); // 参数错误
                retObj.put("msg", "mch_id参数为空");
                _log.info("mchQueryOrders << {}", retObj.toString());
                return retObj.toJSONString();
        	}
		} catch (Exception e) {
			retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "mch_id参数缺失");
            _log.info("mchQueryOrders << {}", retObj.toString());
            return retObj.toJSONString();
		}
    	
    	//根据条件查询数据
    	List<AccountFile> retAccountFiles = accountFileService.mchQueryAccountFileByMchId(mch_id);
        if(retAccountFiles == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "订单流水信息不存在");
            _log.info("mchQueryOrders << {}", retObj.toString());
            return retObj.toJSONString();
        }
    	//组装返回需要数据====================================Start
    	List<Map<String, Object>> accountFileList = new ArrayList<Map<String,Object>>();
    	for (AccountFile accountFile : retAccountFiles) {
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put("mch_id", accountFile.getMch_id());
    		map.put("items_id", accountFile.getItems_id());
    		map.put("items_name", accountFile.getItems_name());
    		map.put("items_type", accountFile.getItems_type());
    		map.put("items_filename", accountFile.getItems_filename());
    		map.put("upload_date", accountFile.getUpload_date());
    		map.put("affect_date", accountFile.getAffect_date());
    		map.put("expiry_date", accountFile.getExpiry_date());
    		accountFileList.add(map);
		}
    	//组装返回需要数据====================================End

    	retObj.put("result", JSON.toJSON(accountFileList));

    	_log.info("mch_query_account_files >> {}", retObj.toJSONString());
    	return CollectionUtils.isEmpty(retObj) ? null : retObj.toJSONString();
    }

}
