package org.epay.dal.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.epay.dal.dao.model.Activity;

import java.util.List;

public interface ActivityMapper {
    int deleteByPrimaryKey(String activityId);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(String activityId);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    /**
     * 根据商户号查询所有活动的项目编号
     * @param mch_id
     * @return
     */
    List<String> selectByMchId(String mch_id);

    int getTotalByMchId(String mchId);

    List<Activity> getPageList(@Param("mch_id") String mchId,
                               @Param("start_index") int parseInt,
                               @Param("page_size") int parseInt1);
}