package org.epay.service.service;

import org.bouncycastle.cms.PasswordRecipientId;
import org.epay.dal.dao.mapper.ActivityMapper;
import org.epay.dal.dao.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    /**
     * 保存活动
     * @param activity
     * @return
     */
    public int save(Activity activity) {
        int i = activityMapper.insertSelective(activity);
        return i;
    }

    /**
     * 查询所有活动的项目编号
     * @param mch_id
     * @return
     */
    public List<String> queryAllActivityItemsId(String mch_id) {
        List<String> itemsIdList = activityMapper.selectByMchId(mch_id);
        return itemsIdList;
    }

    // 活动分页显示
    public Map<String, Object> selectActivityPageByMchId(String mchId, String startIndex, String pageSize) {
        Map<String, Object> dataMap = new HashMap<>();
        // 查询当前商户的所有上传账单总数
        dataMap.put("total", activityMapper.getTotalByMchId(mchId));
        // 查询当前商户的所有账单
        dataMap.put("pageList", activityMapper.getPageList(mchId, Integer.parseInt(startIndex), Integer.parseInt(pageSize)));
        return dataMap;
    }

    /**
     * 删除活动
     * @param list
     * @return
     */
    public boolean removeActivity(ArrayList<String> list) {
        int count = 0;
        for(String id : list) {
            count += activityMapper.deleteByPrimaryKey(id);
        }
        if(count == list.size()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 停止活动
     * @param list
     * @return
     */
    public boolean stopActivity(ArrayList<String> list) {
        int count = 0;
        for(String id : list) {
            Activity activity = new Activity();
            activity.setActivityId(id);
            activity.setActivityStatus((byte) 0);//可用状态 0：不可用；1：可用
            count += activityMapper.updateByPrimaryKeySelective(activity);
        }
        if(count == list.size()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 恢复活动
     * @param list
     * @return
     */
    public boolean restartActivity(ArrayList<String> list) {
        int count = 0;
        for(String id : list) {
            Activity activity = new Activity();
            activity.setActivityId(id);
            activity.setActivityStatus((byte) 1);//可用状态 0：不可用；1：可用
            count += activityMapper.updateByPrimaryKeySelective(activity);
        }
        if(count == list.size()) {
            return true;
        } else {
            return false;
        }
    }
}
