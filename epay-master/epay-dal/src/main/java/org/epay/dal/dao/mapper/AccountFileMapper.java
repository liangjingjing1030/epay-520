package org.epay.dal.dao.mapper;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.epay.dal.dao.model.AccountFile;
import org.epay.dal.dao.model.AccountFileExample;

public interface AccountFileMapper {
    long countByExample(AccountFileExample example);

    int deleteByExample(AccountFileExample example);

    int deleteByPrimaryKey(String file_id);

    int insert(AccountFile record);

    int insertSelective(AccountFile record);

    List<AccountFile> selectByExample(AccountFileExample example);

    AccountFile selectByPrimaryKey(String file_id);

    int updateByExampleSelective(@Param("record") AccountFile record, @Param("example") AccountFileExample example);

    int updateByExample(@Param("record") AccountFile record, @Param("example") AccountFileExample example);

    int updateByPrimaryKeySelective(AccountFile record);

    int updateByPrimaryKey(AccountFile record);

    /**
     * 根据商户号查询账单总数
     * @param mchId
     * @return
     */
    int getTotalByMchId(String mchId);

    /**
     * 账单分页查询
     * @param mchId
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<AccountFile> getPageList(@Param("mchId") String mchId,
                                  @Param("startIndex") int startIndex,
                                  @Param("pageSize") int pageSize);

    /**
     * 判断账单是否重复上传
     * @param mch_Id
     * @param items_id
     * @return
     */
    int selectByMchIdAndItemsId(@Param("mch_Id") String mch_Id,
                                @Param("items_id") String items_id);

    /**
     * 多条件查询
     * @param conditionMap
     * @return
     */
    List<AccountFile> selectByConditionMapOfBillQuery(Map<String, Object> conditionMap);

    /**
     * 如果有多个，只取最后那个
     * @param mch_id
     * @param items_id
     * @return
     */
    AccountFile selectAccountFileByMchIdAndItemsId(@Param("mch_id") String mch_id,
                                                   @Param("items_id") String items_id);

    /**
     * 根据商户号查询AccountFile
     * @param mch_id
     * @return
     */
    List<AccountFile> selectByMchId(String mch_id);


    AccountFile selectByAllCondition(@Param("mch_id") String mch_id,
                                     @Param("items_id") String items_id,
                                     @Param("items_name") String items_name,
                                     @Param("affect_date") String affect_date,
                                     @Param("expiry_date") String expiry_date);

    /**
     * mch_id,startIndex,pageSize分页查询
     * @param conditionMap
     * @return
     */
    List<AccountFile> selectPageByMchId(Map<String, Object> conditionMap);

    /**
     * 查询当前商户所有账单数量
     * @param mch_id
     * @return
     */
    int selectCountByMchId(String mch_id);
}