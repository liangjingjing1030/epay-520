package org.epay.service.controller;

import org.apache.commons.lang.StringUtils;
import org.epay.common.util.MyBase64;
import org.epay.common.util.MyLog;
import org.epay.dal.dao.model.BankBranch;
import org.epay.service.service.BankBranckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 类名: 查询机构编号
 * 作者: GaoLiang
 * 时间: 2019年5月23日
 * 版本: V1.0
 */
@RestController
public class BankBranchServiceController {

    private final MyLog _log = MyLog.getLog(BankBranchServiceController.class);

    @Autowired
    private BankBranckService bankBranckService;

    /**
     * 查询机构信息
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/bank_branch/queryBankBranch")
    public String selectBankBranch(@RequestParam String jsonParam) {
        _log.info("selectPtUserByLoginName << {}", jsonParam);
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");

        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }

        JSONObject paramObj = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        String branch_id = ""; // 拓展网点号

        try {
            branch_id  = paramObj.getString("branch_id").trim();	// 拓展网点号
        	if(StringUtils.isBlank(branch_id)) {
        		retObj.put("code", "0001"); // 参数错误
                retObj.put("msg", "branch_id参数为空");
                return retObj.toJSONString();
        	}
		} catch (Exception e) {
			retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "branch_id参数缺失");
            return retObj.toJSONString();
		}

        BankBranch bankBranch = bankBranckService.selectBankBranchByBranchId(branch_id);

        if(bankBranch == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "机构信息不存在");
            return retObj.toJSONString();
        }
    	retObj.put("result", JSON.toJSON(bankBranch));
        _log.info("selectPtUserByLoginName >> {}", retObj.toJSONString());

        return CollectionUtils.isEmpty(retObj) ? null : retObj.toJSONString();
    }

    /**
     * 根据机构名称查询机构号
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/bank_branch/queryBranchIdByBranchName")
    public String queryBranchIdByBranchName(@RequestParam String jsonParam) {
        _log.info("queryBranchIdByBranchName << {}", jsonParam);
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");

        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }

        JSONObject paramObj = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        String branchName = ""; // 拓展网点名

        try {
            branchName  = paramObj.getString("branchName").trim();	// 拓展网点名
        	if(StringUtils.isBlank(branchName)) {
        		retObj.put("code", "0001"); // 参数错误
                retObj.put("msg", "branchName参数为空");
                return retObj.toJSONString();
        	}
		} catch (Exception e) {
			retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "branchName参数缺失");
            return retObj.toJSONString();
		}

        BankBranch bankBranch = bankBranckService.selectBankBranchByBranchName(branchName);

        if(bankBranch == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "机构信息不存在");
            return retObj.toJSONString();
        }
    	retObj.put("result", JSON.toJSON(bankBranch));
        _log.info("queryBranchIdByBranchName >> {}", retObj.toJSONString());

        return CollectionUtils.isEmpty(retObj) ? null : retObj.toJSONString();
    }

}
