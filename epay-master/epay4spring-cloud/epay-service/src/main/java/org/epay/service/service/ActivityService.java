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

    /**
     * 根据主键s查询活动
     * @param list
     * @return
     */
    public String queryByActivityIds(ArrayList<String> list) {
        int start = 0, stop = 0;
        for(String activity_id : list) {
            Activity a = activityMapper.selectByPrimaryKey(activity_id);
            if(a.getActivityStatus() == 0) {// 可用状态 0：不可用；1：可用
                stop++;
            } else if(a.getActivityStatus() == 1) {
                start++;
            }
        }
        //都是启用状态返回“start”，都是停止状态返回“stop”，都有返回“all”
        if(start == 0 && stop != 0) {
            return "stop";
        } else if(start != 0 && stop == 0) {
            return "start";
        }
        return "all";
    }

    /**
     * 根据项目编号查询
     * @param items_id
     * @return
     */
    public Activity queryByMchIdAndItemsId(String mch_id, String items_id) {
        return activityMapper.selectByMchIdAndItemsId(mch_id, items_id);
    }
}
