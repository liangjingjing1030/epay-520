package org.epay.service.service;

import org.epay.dal.dao.mapper.ActivityMapper;
import org.epay.dal.dao.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
}
