package org.epay.service.controller;

import org.apache.commons.lang.StringUtils;
import org.epay.common.util.MyBase64;
import org.epay.common.util.MyLog;
import org.epay.dal.dao.model.User;
import org.epay.service.service.PtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 类名: 商户登陆信息
 * 作者: HappyDan
 * 时间: 2019年4月30日
 * 版本: V1.0
 */
@RestController
public class PtUserServiceController {

    private final MyLog _log = MyLog.getLog(PtUserServiceController.class);

    @Autowired
    private PtUserService ptUserService;

    /**
     * 修改密码
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/pt_user/updatePassword")
    public String updatePassword(@RequestParam String jsonParam) {
        _log.info("selectPtUserByLoginName << {}", jsonParam);
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        
        JSONObject paramObj = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        String mch_id = ""; // 商户ID
        String newLoginPwd = ""; // 商户ID

        try {
            mch_id  = paramObj.getString("mch_id").trim();	// 商户ID
            newLoginPwd  = paramObj.getString("newLoginPwd").trim();
        	if(StringUtils.isBlank(mch_id)) {
        		retObj.put("code", "0001"); // 参数错误
                retObj.put("msg", "mch_id参数为空");
                return retObj.toJSONString();
        	}
        	if(StringUtils.isBlank(newLoginPwd)) {
        		retObj.put("code", "0001"); // 参数错误
                retObj.put("msg", "newLoginPwd参数为空");
                return retObj.toJSONString();
        	}
		} catch (Exception e) {
			retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "参数缺失");
            return retObj.toJSONString();
		}
        
        int updateCount = ptUserService.updatePassword(mch_id, newLoginPwd);
        
        if(updateCount <= 0) {
            retObj.put("code", "0002");
            retObj.put("msg", "修改密码失败");
            return retObj.toJSONString();
        }
    	retObj.put("result", JSON.toJSON(updateCount));
        _log.info("selectPtUserByLoginName >> {}", retObj.toJSONString());
        
        return CollectionUtils.isEmpty(retObj) ? null : retObj.toJSONString();
    }

    /**
     * 查询商户登陆信息
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/pt_user/selectPtUserByLoginName")
    public String selectPtUser(@RequestParam String jsonParam) {
        _log.info("selectPtUserByLoginName << {}", jsonParam);
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");

        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }

        JSONObject paramObj = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        String login_name = ""; // 商户ID

        try {
        	login_name  = paramObj.getString("login_name").trim();	// 商户ID
        	if(StringUtils.isBlank(login_name)) {
        		retObj.put("code", "0001"); // 参数错误
                retObj.put("msg", "login_name参数为空");
                return retObj.toJSONString();
        	}
		} catch (Exception e) {
			retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "login_name参数缺失");
            return retObj.toJSONString();
		}

        User ptUser = ptUserService.selectPtUserByLoginName(login_name);

        if(ptUser == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "商户登陆信息不存在");
            return retObj.toJSONString();
        }
    	retObj.put("result", JSON.toJSON(ptUser));
        _log.info("selectPtUserByLoginName >> {}", retObj.toJSONString());

        return CollectionUtils.isEmpty(retObj) ? null : retObj.toJSONString();
    }

    /**
     * 添加商户登陆信息
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/pt_user/addIntoPtUser")
    public String addPtUser(@RequestParam String jsonParam) {
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");

        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }

        JSONObject paramObj = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        _log.info("pt_user/addIntoPtUser << {}", paramObj);
        String mch_id = ""; // 商户ID
        String pswd	= "";	//登陆密码
        String account = "";// 登录账号
        
        try {
        	mch_id  = paramObj.getString("mch_id").trim();	// 商户ID
        	if(StringUtils.isBlank(mch_id)) {
        		retObj.put("code", "0001"); // 参数错误
                retObj.put("msg", "mch_id参数为空");
                return retObj.toJSONString();
        	}
		} catch (Exception e) {
			retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "mch_id参数缺失");
            return retObj.toJSONString();
		}
        try {
        	pswd = paramObj.getString("pswd"); 	// 登陆密码
        	if(StringUtils.isBlank(pswd)) {
        		retObj.put("code", "0001"); // 参数错误
                retObj.put("msg", "pswd参数为空");
                return retObj.toJSONString();
        	}
		} catch (Exception e) {
			retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "pswd参数缺失");
            return retObj.toJSONString();
		}

        try {
            account = paramObj.getString("account");
        	if(StringUtils.isBlank(account)) {
        		retObj.put("code", "0001"); // 参数错误
                retObj.put("msg", "account参数为空");
                return retObj.toJSONString();
        	}
		} catch (Exception e) {
			retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "account参数缺失");
            return retObj.toJSONString();
		}

        User user = new User();
        user.setLoginName(mch_id);
        user.setPassword(pswd);
        user.setLoginAct(account);

        int retUser = ptUserService.insertIntoPtUser(user);
        
        if(retUser <= 0) {
            retObj.put("code", "0007");
            retObj.put("msg", "系统异常，添加商户信息失败");
            return retObj.toJSONString();
        }
    	retObj.put("result", JSON.toJSON(retUser));
        _log.info("addIntoPtUser >> {}", retObj.toJSONString());
        
        return CollectionUtils.isEmpty(retObj) ? null : retObj.toJSONString();
    }
    
    
    
}
