package org.epay.service.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang.StringUtils;
import org.epay.common.util.DateUtils;
import org.epay.common.util.MyBase64;
import org.epay.common.util.MyLog;
import org.epay.dal.dao.mapper.AccountBookMapper;
import org.epay.dal.dao.model.*;
import org.epay.service.service.AccountBookService;
import org.epay.service.service.ActivityService;
import org.epay.service.service.BillService;
import org.epay.service.service.MchInfoService;
import org.epay.service.utils.MakeFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 类名: 商户账单信息查询类
 * 作者: GaoLiang
 * 时间: 2019年4月29日
 * 版本: V1.0
 */
@RestController
public class BillServiceController {

    private final MyLog _log = MyLog.getLog(BillServiceController.class);

    @Autowired
    private BillService accountFileService;

    @Autowired
    private AccountBookService accountBookService;

    @Autowired
    private MchInfoService mchInfoService;
    
    @Autowired
    private MakeFile makeFile;

    @Autowired
    private ActivityService activityService;

    // 修改单条accountBook
    @RequestMapping(value = "/bill/updateSingleAccountBookById")
    public String updateSingleAccountBookById(@RequestParam String jsonParam) {
        _log.info("updateSingleAccountBookById << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);

        String account_book_id = paramObj.getString("account_book_id");
        String mch_id = paramObj.getString("mch_id");
        String items_id = paramObj.getString("items_id");
        String user_id = paramObj.getString("user_id");
        String user_name = paramObj.getString("user_name");
        String items_money = paramObj.getString("items_money");
        String pay_time = paramObj.getString("pay_time");
        String pay_status = paramObj.getString("pay_status");

        AccountBook accountBook = new AccountBook();
        if(StringUtils.isNotBlank(account_book_id)) {
            accountBook.setAccount_book_id(account_book_id);
        }
        if(StringUtils.isNotBlank(mch_id)) {
            accountBook.setMch_id(mch_id);
        }
        if(StringUtils.isNotBlank(items_id)) {
            accountBook.setItems_id(items_id);
        }
        if(StringUtils.isNotBlank(user_id)) {
            accountBook.setUser_id(user_id);
        }
        if(StringUtils.isNotBlank(user_name)) {
            accountBook.setUser_name(user_name);
        }
        double itemsMoneyD = Double.parseDouble(items_money) * 100;
        if(StringUtils.isNotBlank(items_money)) {
            accountBook.setItems_money((long) itemsMoneyD);
        }
        if(StringUtils.isNotBlank(pay_time)) {
            accountBook.setPay_time(pay_time);
        }
        if(StringUtils.isNotBlank(pay_status)) {
            accountBook.setPay_status(Byte.parseByte(pay_status));
        }
        // 修改账单
        boolean updateOK = accountBookService.modefyAccountBookById(accountBook);

        JSONObject retObj = new JSONObject();
        // 删除账单失败
        if(!updateOK) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "修改一条缴费明细失败！");
            return retObj.toJSONString();
        }

        retObj.put("code", "0000");
        retObj.put("result" , updateOK);

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

    // 添加单条accountBook
    @RequestMapping(value = "/bill/addSingleAccountBookByAllField")
    public String addSingleAccountBookByAllField(@RequestParam String jsonParam) {
        _log.info("addSingleAccountBookByAllField << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);

        String mch_id = paramObj.getString("mch_id");
        String items_id = paramObj.getString("items_id");
        String user_id = paramObj.getString("user_id");
        String user_name = paramObj.getString("user_name");
        String items_money = paramObj.getString("items_money");
        String pay_time = paramObj.getString("pay_time");
        String pay_status = paramObj.getString("pay_status");

        AccountBook accountBook = new AccountBook();
        // 生成主键
        String time = "ZD" + DateUtils.getCurrentTimeStrDefault() + (int)((Math.random()*9+1)*1000000);
        accountBook.setAccount_book_id(time);
        if(StringUtils.isNotBlank(mch_id)) {
            accountBook.setMch_id(mch_id);
        }
        if(StringUtils.isNotBlank(items_id)) {
            accountBook.setItems_id(items_id);
        }
        if(StringUtils.isNotBlank(user_id)) {
            accountBook.setUser_id(user_id);
        }
        if(StringUtils.isNotBlank(user_name)) {
            accountBook.setUser_name(user_name);
        }
        double itemsMoneyD = Double.parseDouble(items_money) * 100;
        if(StringUtils.isNotBlank(items_money)) {
            accountBook.setItems_money((long) itemsMoneyD);
        }
        if(StringUtils.isNotBlank(pay_time)) {
            accountBook.setPay_time(pay_time);
        }
        if(StringUtils.isNotBlank(pay_status)) {
            accountBook.setPay_status(Byte.parseByte(pay_status));
        }
        // 添加账单
        boolean addOK = accountBookService.addAccountBook(accountBook);

        JSONObject retObj = new JSONObject();
        // 删除账单失败
        if(!addOK) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "添加一条缴费明细失败！");
            return retObj.toJSONString();
        }

        retObj.put("code", "0000");
        retObj.put("result" , addOK);

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

    // 查询当前商户下所有的accountFile的items_id
    @RequestMapping(value = "/bill/queryAllItemsIdByMchId")
    public String queryAllItemsIdByMchId(@RequestParam String jsonParam) {
        _log.info("queryAllItemsIdByMchId << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);

        String mch_id = paramObj.getString("mch_id");

        // 查询项目编号集合
        List<String> itemsIdList = accountFileService.queryItemsIdListByMchId(mch_id);

        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        if(itemsIdList == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "数据对象不存在");
            return retObj.toJSONString();
        }

        retObj.put("result", JSON.toJSON(itemsIdList));

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }


    /**
     * 查询当前商户下所有的活动的items_id
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/bill/queryAllActivityItemsIdByMchId")
    public String queryAllActivityItemsIdByMchId(@RequestParam String jsonParam) {
        _log.info("queryAllActivityItemsIdByMchId << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);

        String mch_id = paramObj.getString("mch_id");

        // 查询项目编号集合
        List<String> itemsIdList = activityService.queryAllActivityItemsId(mch_id);

        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        if(itemsIdList == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "数据对象不存在");
            return retObj.toJSONString();
        }

        retObj.put("result", JSON.toJSON(itemsIdList));

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

    // 删除单条accountBook
    @RequestMapping(value = "/bill/deleteSingleAccountBookById")
    public String deleteSingleAccountBookById(@RequestParam String jsonParam) {
        _log.info("deleteSingleAccountBookById << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);

        String account_book_id = paramObj.getString("account_book_id");

        // 删除账单
        boolean deleteOK = accountBookService.removeAccountBookById(account_book_id);

        JSONObject retObj = new JSONObject();
        // 删除账单失败
        if(!deleteOK) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "删除一条缴费明细失败！");
            return retObj.toJSONString();
        }

        retObj.put("code", "0000");
        retObj.put("result" , deleteOK);

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

    // 删除账单
    @RequestMapping(value = "/bill/deleteAccountFile")
    public String deleteAccountFile(@RequestParam String jsonParam) {
        _log.info("deleteAccountFile << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);

        JSONArray ids = paramObj.getJSONArray("ids");
        // json数组转ArrayList
        ArrayList<String> list = new ArrayList<String>();
        if (ids != null) {
            int len = ids.size();
            for (int i=0;i<len;i++){
                list.add(ids.get(i).toString());
            }
        }
        // 删除账单
        boolean deleteOK = accountFileService.deleteAccountFile(list);

        JSONObject retObj = new JSONObject();
        // 删除账单失败
        if(!deleteOK) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "删除账单失败！");
            return retObj.toJSONString();
        }

        retObj.put("code", "0000");
        retObj.put("result" , deleteOK);

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

    // 删除活动
    @RequestMapping(value = "/bill/delete_activity")
    public String deleteActivity(@RequestParam String jsonParam) {
        _log.info("delete_activity << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);

        JSONArray ids = paramObj.getJSONArray("ids");
        // json数组转ArrayList
        ArrayList<String> list = new ArrayList<String>();
        if (ids != null) {
            int len = ids.size();
            for (int i=0;i<len;i++){
                list.add(ids.get(i).toString());
            }
        }
        // 删除账单
        boolean deleteOK = activityService.removeActivity(list);

        JSONObject retObj = new JSONObject();
        // 删除账单失败
        if(!deleteOK) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "删除活动失败！");
            return retObj.toJSONString();
        }

        retObj.put("code", "0000");
        retObj.put("result" , deleteOK);

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

    // 停止活动
    @RequestMapping(value = "/bill/stop_activity")
    public String stopActivity(@RequestParam String jsonParam) {
        _log.info("stop_activity << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);

        JSONArray ids = paramObj.getJSONArray("ids");
        // json数组转ArrayList
        ArrayList<String> list = new ArrayList<String>();
        if (ids != null) {
            int len = ids.size();
            for (int i=0;i<len;i++){
                list.add(ids.get(i).toString());
            }
        }
        //
        boolean deleteOK = activityService.stopActivity(list);

        JSONObject retObj = new JSONObject();
        // 删除账单失败
        if(!deleteOK) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "停止活动失败！");
            return retObj.toJSONString();
        }

        retObj.put("code", "0000");
        retObj.put("result" , deleteOK);

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

    // 恢复活动
    @RequestMapping(value = "/bill/restart_activity")
    public String restartActivity(@RequestParam String jsonParam) {
        _log.info("restart_activity << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);

        JSONArray ids = paramObj.getJSONArray("ids");
        // json数组转ArrayList
        ArrayList<String> list = new ArrayList<String>();
        if (ids != null) {
            int len = ids.size();
            for (int i=0;i<len;i++){
                list.add(ids.get(i).toString());
            }
        }
        //
        boolean deleteOK = activityService.restartActivity(list);

        JSONObject retObj = new JSONObject();
        // 删除账单失败
        if(!deleteOK) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "恢复活动失败！");
            return retObj.toJSONString();
        }

        retObj.put("code", "0000");
        retObj.put("result" , deleteOK);

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

    // 生成回单
    @RequestMapping(value = "/bill/userGenerateReceipt")
    public String userGenerateReceipt(@RequestParam String jsonParam) {
        _log.info("userGenerateReceipt << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);

        JSONArray ids = paramObj.getJSONArray("ids");
        // json数组转ArrayList
        ArrayList<String> list = new ArrayList<String>();
        if (ids != null) {
            int len = ids.size();
            for (int i=0;i<len;i++){
                list.add(ids.get(i).toString());
            }
        }
        // 生成回单
        List<AccountBook> accountBookList = accountBookService.queryAccountBookAndPayOrder(list);

        JSONObject retObj = new JSONObject();
        // 生成回单失败
        if(accountBookList == null) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "查询回单信息失败！");
            return retObj.toJSONString();
        }
        retObj.put("code", "0000");
        retObj.put("count", accountBookList.size());
        int i = 1;
        // 遍历list
        for(AccountBook accountBook : accountBookList) {
            retObj.put("result" + (i++), JSON.toJSON(accountBook));
        }

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

    // 批量删除accountBook
    @RequestMapping(value = "/bill/deleteAccountBooksByIds")
    public String deleteAccountBooksByIds(@RequestParam String jsonParam) {
        _log.info("deleteAccountBooksByIds << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);

        JSONArray ids = paramObj.getJSONArray("ids");
        // json数组转ArrayList
        ArrayList<String> list = new ArrayList<String>();
        if (ids != null) {
            int len = ids.size();
            for (int i=0;i<len;i++){
                list.add(ids.get(i).toString());
            }
        }
        // 批量删除缴费明细
        boolean deleteOK = accountBookService.removeAccountBooksByIds(list);

        JSONObject retObj = new JSONObject();
        // 批量删除缴费明细失败
        if(!deleteOK) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "批量删除缴费明细失败！");
            return retObj.toJSONString();
        }

        retObj.put("code", "0000");
        retObj.put("result" , deleteOK);

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

    // 判断账单是否重复上传
    @RequestMapping(value = "/bill/checkIsDuplicate")
    public String checkIsDuplicate(@RequestParam String jsonParam) {
        _log.info("checkIsDuplicate << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);

        String mch_Id = paramObj.getString("mch_Id");
        String items_id = paramObj.getString("items_id");

        // 判断账单是否重复上传
        int count = accountFileService.queryByMchIdAndItemsId(mch_Id, items_id);

        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");

        retObj.put("result" , count);

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

    /**
     * 创建活动
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/bill/user_create_activity")
    public String createActivity(@RequestParam String jsonParam) {
        _log.info("user_create_activity << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);

        String mch_Id = paramObj.getString("mch_Id");
        String activity_name = paramObj.getString("activity_name");
        String items_id = paramObj.getString("items_id");
        String activity_type = paramObj.getString("activity_type");
        String start_time = paramObj.getString("start_time");
        String end_time = paramObj.getString("end_time");

        String time = "AC" + DateUtils.getCurrentTimeStrDefault() + (int)((Math.random()*9+1)*1000000);
        Activity activity = new Activity();
        activity.setActivityId(time);
        activity.setMchId(mch_Id);
        activity.setItemsId(items_id);
        activity.setActivityName(activity_name);
        if(StringUtils.isNotBlank(activity_type)) {
            activity.setActivityType(activity_type);
        }
        activity.setStartTime(start_time);
        activity.setEndTime(end_time);
        activity.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        // 设置可用状态为可用  可用状态 0：不可用；1：可用
        activity.setActivityStatus((byte)1);// 默认可用

        // 创建活动
        int saveCount = activityService.save(activity);

        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        if(saveCount < 1) {
            retObj.put("code", "0002");
            retObj.put("msg", "创建活动失败！");
            return retObj.toJSONString();
        }

        retObj.put("result" , saveCount);

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }


    /**
     * 判断是否可以删除账单
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/bill/checkIfCanDeleteAccountFile")
    public String checkIfCanDeleteAccountFile(@RequestParam String jsonParam) {
        _log.info("checkIfCanDeleteAccountFile << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);

        JSONArray ids = paramObj.getJSONArray("ids");
        // json数组转list
        ArrayList<String> list = new ArrayList<String>();
        if (ids != null) {
            int len = ids.size();
            for (int i=0;i<len;i++){
                list.add(ids.get(i).toString());
            }
        }

        // 判断是否可以删除账单,count不为0表示accountBook中有付款
        int count = accountFileService.checkIfCanDeleteAccountFile(list);

        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(count < 1) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "查询账单是否可以删除失败！");
            return retObj.toJSONString();
        }

        retObj.put("result" , count);

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

    // 判断有无可以导出的账单
    @RequestMapping(value = "/bill/checkHaveAccountCanBeExport")
    public String checkHaveAccountCanBeExport(@RequestParam String jsonParam) {
        _log.info("checkHaveAccountCanBeExport << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);

        String mch_id = paramObj.getString("mch_id");

        // 判断有无可以导出的账单,count为账单数量(做加1处理,即只要查询成功，count最少为1)
        int count = accountFileService.checkHaveAccountCanBeExport(mch_id);

        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(count < 1) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "查询账单是否可以导出失败！");
            return retObj.toJSONString();
        }

        retObj.put("result" , count);

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }


    // 导入账单start————————————————————————————————————————————————————————————————————————
    @RequestMapping(value = "/bill/importAccountFile")
    public String importAccountFile(@RequestParam String jsonParam) {
        _log.info("importAccountFile << {}", jsonParam);
        String mchId = "";
        //String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(new String(MyBase64.decode(jsonParam)));
        // 1.json转bean对象
        JSONObject ac = paramObj.getJSONObject("accountFile");
        JSONObject mk = paramObj.getJSONObject("modelKey");
        AccountFile accountFile = JSON.parseObject(ac.toJSONString(), AccountFile.class);
        ModelKey modelKey = JSON.parseObject(mk.toJSONString(), ModelKey.class);
        // 解析map(json转map)
//        String str = paramObj.getString("titlePositionMap");
//        Map<String, Integer> map = JSON.parseObject(str,new TypeReference<Map<String,Integer>>(){});
        /*Set<Map.Entry<String, Integer>> m = map.entrySet();
        Iterator<Map.Entry<String, Integer>> it = m.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> en = it.next();
            String title = en.getKey();
            int position = en.getValue();
            System.out.println(title + "===" + position);
        }*/

        // 2.账单文件
        List<AccountBook> accountBookList = new ArrayList<>();
        Integer arrayCount = paramObj.getInteger("arrayCount");// 数组个数/accountBook个数/上传的表中行数
        for(int j = 1; j <= arrayCount; j++) {
            JSONArray array = paramObj.getJSONArray("array" + j);
            //array = {Account_book_id,Mch_id,Items_id,User_id,User_name,Currency,Items_money,Pay_time,Pay_status};
            // json数组转List
            List<String> list = new ArrayList<String>();
            if (array != null) {
                int len = array.size();
                for (int i=0;i<len;i++){
                    list.add(array.get(i).toString());
                }
            }
            // list转为accountBook对象
            AccountBook accountBook = new AccountBook();
            for(int i = 0 ; i < list.size() ; i++) {
                String s = list.get(i);
                if(i == 0) {
                    if(!"".equals(s)) {// 如果为空已在mgr中设置为""
                        accountBook.setAccount_book_id(s);
                    }
                } else if(i == 1) {
                    if(!"".equals(s)) {// 如果为空已在mgr中设置为""
                        accountBook.setMch_id(s);
                    }
                } else if(i == 2) {
                    if(!"".equals(s)) {// 如果为空已在mgr中设置为""
                        accountBook.setItems_id(s);
                    }
                } else if(i == 3) {
                    if(!"".equals(s)) {// 如果为空已在mgr中设置为""
                        accountBook.setUser_id(s);
                    }
                } else if(i == 4) {
                    if(!"".equals(s)) {// 如果为空已在mgr中设置为""
                        accountBook.setUser_name(s);
                    }
                } else if(i == 5) {
                    if(!"".equals(s)) {// 如果为空已在mgr中设置为""
                        accountBook.setCurrency(s);
                    }
                } else if(i == 6) {
                    if(!"".equals(s)) {// 如果为空已在mgr中设置为""
                        accountBook.setItems_money(Long.parseLong(s));
                    }
                } else if(i == 7) {// 支付时间
                    if(!"".equals(s)) {// 如果为空已在mgr中设置为""
                        accountBook.setPay_time(s);
                    }
                } else if(i == 8) {// 支付状态
                    if(!"".equals(s)) {// 如果为空已在mgr中设置为""
                        accountBook.setPay_status(Byte.parseByte(s));
                    }
                }
            }
            accountBookList.add(accountBook);
            mchId = accountBook.getMch_id();
        }

        // 3.模板内容
        List<ModelValue> modelValueList = new ArrayList<>();
        Integer arrayCount2 = paramObj.getInteger("arrayCount2");// 数组个数/modelValue个数/上传的表中行数
        for(int j = 1; j <= arrayCount2; j++) {
            JSONArray array = paramObj.getJSONArray("anotherArray" + j);
            //array = {Account_book_id,Mch_id,Items_id,User_id,User_name,Currency,Items_money,Pay_time,Pay_status};
            // json数组转List
            List<String> list = new ArrayList<String>();
            if (array != null) {
                int len = array.size();
                for (int i=0;i<len;i++){
                    list.add(array.get(i).toString());
                }
            }
            // list转为ModelValue对象
            ModelValue modelValue = new ModelValue();
            for(int i = 0 ; i < list.size() ; i++) {
                // 保存顺序modelValueId、modelKeyId、user_id、items_money、value1、value2……
                String s = list.get(i);
                if(i == 0) {
                    if(!"".equals(s)) {
                        modelValue.setModelValueId(s);
                    }
                } else if(i == 1) {
                    if(!"".equals(s)) {
                        modelValue.setModelKeyId(s);
                    }
                } else if(i == 2) {
                    if(!"".equals(s)) {
                        modelValue.setUserIdValue(s);
                    }
                } else if(i == 3) {
                    if(!"".equals(s)) {
                        modelValue.setItemsMoneyValue(Long.parseLong(s));
                    }
                } else if(i == 4) {
                    if(!"".equals(s)) {// 如果为空已在mgr中设置为""
                        modelValue.setValue1(s);
                    }
                }  else if(i == 5) {
                    if(!"".equals(s)) {// 如果为空已在mgr中设置为""
                        modelValue.setValue2(s);
                    }
                }  else if(i == 6) {
                    if(!"".equals(s)) {// 如果为空已在mgr中设置为""
                        modelValue.setValue3(s);
                    }
                }  else if(i == 7) {
                    if(!"".equals(s)) {// 如果为空已在mgr中设置为""
                        modelValue.setValue4(s);
                    }
                }  else if(i == 8) {
                    if(!"".equals(s)) {// 如果为空已在mgr中设置为""
                        modelValue.setValue5(s);
                    }
                }  else if(i == 9) {
                    if(!"".equals(s)) {// 如果为空已在mgr中设置为""
                        modelValue.setValue6(s);
                    }
                }  else if(i == 10) {
                    if(!"".equals(s)) {// 如果为空已在mgr中设置为""
                        modelValue.setValue7(s);
                    }
                }  else if(i == 11) {
                    if(!"".equals(s)) {// 如果为空已在mgr中设置为""
                        modelValue.setValue8(s);
                    }
                }  else if(i == 12) {
                    if(!"".equals(s)) {// 如果为空已在mgr中设置为""
                        modelValue.setValue9(s);
                    }
                }  else if(i == 13) {
                    if(!"".equals(s)) {// 如果为空已在mgr中设置为""
                        modelValue.setValue10(s);
                    }
                }
            }
            modelValueList.add(modelValue);
        }
        // 导入账单结果
        boolean importOK = accountFileService.importAccountFileAndAccountBook(accountFile,accountBookList,modelKey,modelValueList);

        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");

        retObj.put("result", importOK);

        _log.info("result:{}", retObj.toJSONString());
        
        MchInfo mchInfo = mchInfoService.selectMchInfoByMchId(mchId);
        String mch_range = mchInfo.getMch_range();
        try {
			String retFile = makeFile.makeFile(mchId, mch_range);
			System.out.println("生成文件结果==========" + retFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return retObj.toJSONString();
    }
    // 导入账单end——————————————————————————————————————————————————————————————————————————


    // accountFile分页显示
    @RequestMapping(value = "/bill/AccountFilePageByMchId")
    public String selectMchInfoByMchId(@RequestParam String jsonParam) {
        _log.info("AccountFilePageByMchId << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);
        String mch_id = paramObj.getString("mch_id");
        String startIndex = paramObj.getString("startIndex");
        String pageSize = paramObj.getString("pageSize");

        // 查询商户账单信息
        Map<String, Object> dataMap = accountFileService.selectAccountFilePageByMchId(mch_id, startIndex, pageSize);

        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        int total = (int) dataMap.get("total");
        if(total == 0) {
            retObj.put("code", "0002");
            retObj.put("msg", "当前商户未上传账单");
            return retObj.toJSONString();
        }

        retObj.put("total", total);
        List<AccountFile> accountFileList = (List<AccountFile>) dataMap.get("pageList");
        retObj.put("count", accountFileList.size());
        int i = 1;
        // 遍历list
        for(AccountFile accountFile : accountFileList) {
            retObj.put("result" + (i++), JSON.toJSON(accountFile));
        }

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }


    // 活动分页显示
    @RequestMapping(value = "/bill/ActivityPageByMchId")
    public String selectActivityByMchId(@RequestParam String jsonParam) {
        _log.info("ActivityPageByMchId << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);
        String mch_id = paramObj.getString("mch_id");
        String startIndex = paramObj.getString("startIndex");
        String pageSize = paramObj.getString("pageSize");

        // 查询商户活动信息
        Map<String, Object> dataMap = activityService.selectActivityPageByMchId(mch_id, startIndex, pageSize);

        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        int total = (int) dataMap.get("total");
        if(total == 0) {
            retObj.put("code", "0002");
            retObj.put("msg", "当前商户未创建活动");
            return retObj.toJSONString();
        }

        retObj.put("total", total);
        List<Activity> activityListi = (List<Activity>) dataMap.get("pageList");
        retObj.put("count", activityListi.size());
        int i = 1;
        // 遍历list
        for(Activity activity : activityListi) {
            retObj.put("result" + (i++), JSON.toJSON(activity));
        }

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }


    // 导出全部账单
    @RequestMapping(value = "/bill/exportAllAccountBook")
    public String exportAllAccountBook(@RequestParam String jsonParam) {
        _log.info("exportAllAccountBook << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);
        String mch_id = paramObj.getString("mch_id");

        // 查询账单信息list TODO
        List<AccountBook> accountBookList = accountFileService.exportAllAccountBook(mch_id);

        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        if(accountBookList == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "数据对象不存在");
            return retObj.toJSONString();
        }

        retObj.put("count", accountBookList.size());
        int i = 1;
        // 遍历list
        for(AccountBook accountBook : accountBookList) {
            retObj.put("result" + (i++), JSON.toJSON(accountBook));
        }

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

    // 导出选中账单
    @RequestMapping(value = "/bill/exportSelectedAccountBook")
    public String exportSelectedAccountBook(@RequestParam String jsonParam) {
        _log.info("exportSelectedAccountBook << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);
        JSONArray ids = paramObj.getJSONArray("ids");
        // json数组转ArrayList
        List<String> list = new ArrayList<String>();
        if (ids != null) {
            int len = ids.size();
            for (int i=0;i<len;i++){
                list.add(ids.get(i).toString());
            }
        }

        // 查询账单信息list TODO
        List<AccountBook> accountBookList = accountFileService.exportSelectedAccountBook(list);

        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        if(accountBookList == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "数据对象不存在");
            return retObj.toJSONString();
        }

        retObj.put("count", accountBookList.size());
        int i = 1;
        // 遍历list
        for(AccountBook accountBook : accountBookList) {
            retObj.put("result" + (i++), JSON.toJSON(accountBook));
        }

        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

    // accountBook分页显示
    @RequestMapping(value = "/bill/accountBookPageByConfition")
    public String accountBookPageByConfition(@RequestParam String jsonParam) {
        _log.info("accountBookPageByConfition << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);
        String mch_id = paramObj.getString("mch_id");
        String items_id = paramObj.getString("items_id");
        String items_name = paramObj.getString("items_name");
        String items_filename = paramObj.getString("items_filename");
        String upload_date = paramObj.getString("upload_date");
        String status = paramObj.getString("status");
        String user_id = paramObj.getString("user_id");
        String user_name = paramObj.getString("user_name");
        String startIndex = paramObj.getString("startIndex");
        String pageSize = paramObj.getString("pageSize");

        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("mch_id", mch_id);
        conditionMap.put("items_id", items_id);
        conditionMap.put("items_name", items_name);
        conditionMap.put("items_filename", items_filename);
        conditionMap.put("upload_date", upload_date);
        conditionMap.put("status", status);
        conditionMap.put("user_id", user_id);
        conditionMap.put("user_name", user_name);
        conditionMap.put("startIndex", startIndex);
        conditionMap.put("pageSize", pageSize);

        // 查询商户订单信息
        Map<String, Object> dataMap = accountFileService.selectAccountBookByConfition(conditionMap);

        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        int total = (int) dataMap.get("total");
        if(total == 0) {
            retObj.put("code", "0002");
            retObj.put("msg", "未查询到符合要求的订单,请确认查询条件是否正确!");
            return retObj.toJSONString();
        }

        retObj.put("total", total);
        List<AccountBook> accountBookList = (List<AccountBook>) dataMap.get("pageList");
        List<AccountFile> accountFileListForPageShow = (List<AccountFile>) dataMap.get("accountFileListForPageShow");
        retObj.put("count", accountBookList.size());
        int i = 1;
        // 遍历accountBookList
        for(AccountBook accountBook : accountBookList) {
            retObj.put("result" + (i++), JSON.toJSON(accountBook));
        }
        int j = 1;
        // 遍历accountFileListForPageShow
        for(AccountFile accountFile : accountFileListForPageShow) {
            retObj.put("anotherResult" + (j++), JSON.toJSON(accountFile));
        }
        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

    // accountBook + PayOrder分页显示
    @RequestMapping(value = "/bill/AccountBookAndPayOrderPageByConfition")
    public String AccountBookAndPayOrderPageByConfition(@RequestParam String jsonParam) {
        _log.info("AccountBookAndPayOrderPageByConfition << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);
        String mch_id = paramObj.getString("mch_id");
        String items_id = paramObj.getString("items_id");
        String user_id = paramObj.getString("user_id");
        String user_name = paramObj.getString("user_name");
        String pay_time = paramObj.getString("pay_time");
        String pay_status = paramObj.getString("pay_status");
        String settle_status = paramObj.getString("settle_status");
        String pay_channel = paramObj.getString("pay_channel");
        String mch_order_no = paramObj.getString("mch_order_no");
        String user_channel_account = paramObj.getString("user_channel_account");
        String channel_mch_id = paramObj.getString("channel_mch_id");
        String channel_order_no = paramObj.getString("channel_order_no");
        String res_code = paramObj.getString("res_code");
        String expire_time = paramObj.getString("expire_time");
        String startIndex = paramObj.getString("startIndex");
        String pageSize = paramObj.getString("pageSize");

        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("mch_id", mch_id);
        conditionMap.put("items_id", items_id);
        conditionMap.put("user_id", user_id);
        conditionMap.put("user_name", user_name);
        conditionMap.put("pay_time", pay_time);
        if(StringUtils.isNotBlank(pay_status)) {
            conditionMap.put("pay_status", Byte.parseByte(pay_status));
        }
        if(StringUtils.isNotBlank(settle_status)) {
            conditionMap.put("settle_status", Byte.parseByte(settle_status));
        }
        conditionMap.put("pay_channel", pay_channel);
        conditionMap.put("mch_order_no", mch_order_no);
        conditionMap.put("user_channel_account", user_channel_account);
        conditionMap.put("channel_mch_id", channel_mch_id);
        conditionMap.put("channel_order_no", channel_order_no);
        conditionMap.put("res_code", res_code);
        conditionMap.put("expire_time", expire_time);
        conditionMap.put("startIndex", Integer.parseInt(startIndex));
        conditionMap.put("pageSize", Integer.parseInt(pageSize));

        // 查询商户订单信息
        Map<String, Object> dataMap = accountBookService.selectAccountBookAndPayOrderByConfition(conditionMap);

        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        int total = (int) dataMap.get("total");
        if(total == 0) {
            retObj.put("code", "0002");
            retObj.put("msg", "未查询到符合要求的账单与订单,请确认查询条件是否正确!");
            return retObj.toJSONString();
        }

        retObj.put("total", total);
        List<AccountBook> accountBookList = (List<AccountBook>) dataMap.get("pageList");
        retObj.put("count", accountBookList.size());
        int i = 1;
        // 遍历accountBookList
        for(AccountBook accountBook : accountBookList) {
            retObj.put("result" + (i++), JSON.toJSON(accountBook));
        }
        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

    // accountFile分页显示 + 统计
    @RequestMapping(value = "/bill/accountFilePageAddCountByMchId")
    public String accountFilePageAddCountByMchId(@RequestParam String jsonParam) {
        _log.info("accountFilePageAddCountByMchId << {}", jsonParam);

        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);
        String mch_id = paramObj.getString("mch_id");
        String startIndex = paramObj.getString("startIndex");
        String pageSize = paramObj.getString("pageSize");

        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("mch_id", mch_id);
        conditionMap.put("startIndex", Integer.parseInt(startIndex));
        conditionMap.put("pageSize", Integer.parseInt(pageSize));

        // 查询商户订单信息
        Map<String, Object> dataMap = accountFileService.selectAccountFileAddCount(conditionMap);

        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if(StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001"); // 参数错误
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }
        int total = (int) dataMap.get("total");
        if(total == 0) {
            retObj.put("code", "0002");
            retObj.put("msg", "未查询到符合要求的订单,请确认查询条件是否正确!");
            return retObj.toJSONString();
        }
        // accountFile总数
        retObj.put("total", total);
        // 本次分页数据+结算数据
        List<AccountFileForCount> accountFileForCountList = (List<AccountFileForCount>) dataMap.get("pageList");
        // 本次分页数量
        retObj.put("count", accountFileForCountList.size());
        int i = 1;
        // accountFileForCountList
        for(AccountFileForCount accountFileForCount : accountFileForCountList) {
            retObj.put("result" + (i++), JSON.toJSON(accountFileForCount));
        }
        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }

}
