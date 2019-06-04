package org.epay.service.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.epay.common.util.MD5AndCreateKeyUtil;
import org.epay.common.util.MyBase64;
import org.epay.common.util.MyLog;
import org.epay.dal.dao.model.MchInfo;
import org.epay.dal.dao.model.MchStlInfo;
import org.epay.service.service.MchInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 类名: 商户信息接口类
 * 作者: HappyDan
 * 时间: 2019年4月18日
 * 版本: V1.0
 */
@RestController
public class MchInfoServiceController {

    private final MyLog _log = MyLog.getLog(MchInfoServiceController.class);

    @Autowired
    private MchInfoService mchInfoService;

    /**
     * 根据商户ID查询商户信息
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/mch_info/selectMchInfoByMchId")
    public String selectMchInfoByMchId(@RequestParam String jsonParam) {
        // TODO 参数校验
        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);
        String mch_id = paramObj.getString("mch_id");
        MchInfo mchInfo = mchInfoService.selectMchInfoByMchId(mch_id);
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            _log.info("mchQueryOrders << {}", retObj.toString());
            return retObj.toJSONString();
        }
        if(mchInfo == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "数据对象不存在");
            _log.info("mchQueryOrders << {}", retObj.toString());
            return retObj.toJSONString();
        }
        retObj.put("result", JSON.toJSON(mchInfo));
        _log.info("result:{}", retObj.toJSONString());
        return CollectionUtils.isEmpty(retObj) ? null : retObj.toJSONString();
    }

    /**
     * 添加商户信息
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/mch_register/addMchRegister")
    public String addIntoMchInfo(@RequestParam String jsonParam) {
        _log.info("addMchRegister << {}", jsonParam);
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            _log.info("addMchRegister << {}", retObj.toString());
            return retObj.toJSONString();
        }
        
        JSONObject paramObj = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        
        String mch_id  = paramObj.getString("mch_id").trim();
        String mch_name  = paramObj.getString("mch_name").trim();
        String mch_type  = paramObj.getString("mch_type").trim();
        String mch_range  = paramObj.getString("mch_range").trim();
        String mch_city  = paramObj.getString("mch_city").trim();
        String mch_address  = paramObj.getString("mch_address").trim();
        String contact_person  = paramObj.getString("contact_person").trim();
        String contact_phone  = paramObj.getString("contact_phone").trim();
        String branch_id  = paramObj.getString("branch_id").trim();
        String contact_email  = "";
        String certificate_type  = "";
        String certificate_number  = "";
        String business_license  = "";
        String branch_name  = "";
        String staff_id  = "";
        String staff_name  = "";
        String mch_bank_account  = paramObj.getString("mch_bank_account").trim();
        String mch_bank_name  = paramObj.getString("mch_bank_name").trim();
        String account_bank  = paramObj.getString("account_bank").trim();
        String account_type  = paramObj.getString("account_type").trim();
        String mch_stl_day  = paramObj.getString("mch_stl_day").trim();
        
        try {
        	contact_email  = paramObj.getString("contact_email").trim();
        	if(StringUtils.isBlank(contact_email)) {
        		contact_email = "";
        	}
		} catch (Exception e) {
			contact_email = "";
		}
        try {
        	certificate_type  = paramObj.getString("certificate_type").trim();
        	if(StringUtils.isBlank(certificate_type)) {
        		certificate_type = "";
        	}
		} catch (Exception e) {
			certificate_type = "";
		}
        try {
        	certificate_number  = paramObj.getString("certificate_number").trim();
        	if(StringUtils.isBlank(certificate_number)) {
        		certificate_number = "";
        	}
		} catch (Exception e) {
			certificate_number = "";
		}
        try {
        	business_license  = paramObj.getString("business_license").trim();
        	if(StringUtils.isBlank(business_license)) {
        		business_license = "";
        	}
		} catch (Exception e) {
			business_license = "";
		}
        try {
        	branch_name  = paramObj.getString("branch_name").trim();
        	if(StringUtils.isBlank(branch_name)) {
        		branch_name = "";
        	}
		} catch (Exception e) {
			branch_name = "";
		}
        try {
        	staff_id  = paramObj.getString("staff_id").trim();
        	if(StringUtils.isBlank(staff_id)) {
        		staff_id = "";
        	}
		} catch (Exception e) {
			staff_id = "";
		}
        try {
        	staff_name  = paramObj.getString("staff_name").trim();
        	if(StringUtils.isBlank(staff_name)) {
        		staff_name = "";
        	}
		} catch (Exception e) {
			staff_name = "";
		}

        String req_key = MD5AndCreateKeyUtil.getReqKey();
        String res_key = MD5AndCreateKeyUtil.getResKey();
        SimpleDateFormat fm = new SimpleDateFormat("yyyyMMddhhmmss"); // "yyyyMMddhhmmssSSS G
		String myDate = fm.format(new Date());
		
        MchInfo mchInfo = new MchInfo();
        mchInfo.setMch_id(mch_id);//商户号
        mchInfo.setMch_name(mch_name);//商户名称
        mchInfo.setMch_type(mch_type);//商户类型
        mchInfo.setMch_range(mch_range);//经营范围
        mchInfo.setMch_city(mch_city);//商户所在城市
        mchInfo.setMch_address(mch_address);//商户地址
//        mchInfo.setMch_status((byte)0);//商户状态
        mchInfo.setContact_person(contact_person);//联系人姓名
        mchInfo.setContact_phone(contact_phone);//联系电话
        mchInfo.setContact_email(contact_email);//商户邮箱
        mchInfo.setCertificate_type(certificate_type);//联系人证件类型
        mchInfo.setCertificate_number(certificate_number);//联系人证件号
        mchInfo.setBusiness_license(business_license);//营业执照号
        mchInfo.setReq_key(req_key);//请求私钥
        mchInfo.setRes_key(res_key);//响应私钥
        mchInfo.setBranch_id(branch_id);//拓展网点号
        mchInfo.setBranch_name(branch_name);//拓展网点名称
        mchInfo.setStaff_id(staff_id);//拓展员工号
        mchInfo.setStall_name(staff_name);//拓展员工名称
        mchInfo.setCreate_time(myDate);//创建时间
        mchInfo.setAudit_status((byte)0);//审核状态
        // 如果能走到这一步，说明机构号正确，保存机构号
        mchInfo.setNext_audit_dept_id(Long.parseLong(branch_id));
        int retMchInfo = mchInfoService.insertMchInfo(mchInfo);
        if(retMchInfo <= 0) {
            retObj.put("code", "0007");
            retObj.put("msg", "系统异常，添加商户失败");
            _log.info("addMchRegister << {}", retObj.toString());
            return retObj.toJSONString();
        }
        
        MchStlInfo mchStlInfo = new MchStlInfo();
        mchStlInfo.setMch_id(mch_id);//商户号
        mchStlInfo.setMch_bank_account(mch_bank_account);//商户结算银行账号
        mchStlInfo.setMch_bank_name(mch_bank_name);//商户结算账户户名
        mchStlInfo.setAccount_bank(account_bank);//开户行
        mchStlInfo.setAccount_type(account_type);//账户类型，0-对私，1-对公
        mchStlInfo.setMch_stl_day(mch_stl_day);//商户交易结算日(交易结算频率)T+N
        mchStlInfo.setCreate_time(myDate);//创建时间
        int retMchStlInfo = mchInfoService.insertMchStlInfo(mchStlInfo);
        if(retMchStlInfo <= 0) {
            retObj.put("code", "0007");
            retObj.put("msg", "系统异常，添加商户失败");
            _log.info("addMchRegister << {}", retObj.toString());
            return retObj.toJSONString();
        }
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("req_key", req_key);
        map.put("res_key", res_key);
    	retObj.put("result", JSON.toJSON(map));
        _log.info("addMchRegister >> {}", retObj.toJSONString());
        
        return CollectionUtils.isEmpty(retObj) ? null : retObj.toJSONString();
    }
    
    /**
     *  根据地区经营范围查询商户信息
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/mch_info/userSelectMchInfo")
    public String userSelectMchInfo(@RequestParam String jsonParam) {
        _log.info("userSelectMchInfo << {}", jsonParam);
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        // TODO 参数校验
        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);
        
        String mch_type = paramObj.getString("mch_type");		//	商户类型
        String mch_range = paramObj.getString("mch_range");		//	经营范围
        String mch_city = paramObj.getString("mch_city");		//	商户所在城市
        MchInfo mchInfo = new MchInfo();
        mchInfo.setMch_type(mch_type);
        mchInfo.setMch_range(mch_range);
        mchInfo.setMch_city(mch_city);
        
        List<MchInfo> retMchInfo = mchInfoService.userSelectMchInfo(mchInfo);
        
        if(retMchInfo == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "商户信息不存在");
            return retObj.toJSONString();
        }
        Integer mchInfoNum = retMchInfo.size();
        
        //组装返回需要数据====================================Start
    	List<Map<String, Object>> mchInfoList = new ArrayList<Map<String,Object>>();
    	for (MchInfo mchInfo1 : retMchInfo) {
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put("mch_id", mchInfo1.getMch_id());	// 商户号
    		map.put("mch_name", mchInfo1.getMch_name());	//	商户名称
    		map.put("mch_type", mchInfo1.getMch_type());	//	商户类型
    		map.put("mch_range", mchInfo1.getMch_range());	//	经营范围
    		map.put("mch_address", mchInfo1.getMch_address());	//	商户地址
//    		map.put("mch_status", mchInfo1.getMch_status());	//	商户状态
    		mchInfoList.add(map);
    	}
    	retObj.put("total_num", mchInfoNum);
    	retObj.put("result", JSON.toJSON(mchInfoList));
    	//组装返回需要数据====================================End
        _log.info("selectmchInfoList >> {}", retObj.toJSONString());
        
        return CollectionUtils.isEmpty(retObj) ? null : retObj.toJSONString();
    }


}
