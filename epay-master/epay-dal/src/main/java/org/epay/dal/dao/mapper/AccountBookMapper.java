package org.epay.dal.dao.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.epay.dal.dao.model.AccountBook;
import org.epay.dal.dao.model.AccountBookExample;

public interface AccountBookMapper {
    long countByExample(AccountBookExample example);

    int deleteByExample(AccountBookExample example);

    int deleteByPrimaryKey(String account_book_id);

    int insert(AccountBook record);

    int insertSelective(AccountBook record);

    List<AccountBook> selectByExample(AccountBookExample example);

    AccountBook selectByPrimaryKey(String account_book_id);

    int updateByExampleSelective(@Param("record") AccountBook record, @Param("example") AccountBookExample example);

    int updateByExample(@Param("record") AccountBook record, @Param("example") AccountBookExample example);

    int updateByPrimaryKeySelective(AccountBook record);

    int updateByPrimaryKey(AccountBook record);

    /**
     * 根据商户号 + 项目编号 查询
     * @param mch_id
     * @param items_id
     * @return
     */
    List<AccountBook> selectByMchIdAndItemsId(@Param("mch_id") String mch_id,
                                              @Param("items_id") String items_id);

    /**
     * 根据商户id与项目编号查询accountBook总数
     * @param mch_id
     * @param items_id
     * @return
     */
    int selectCountByMchIdAndItemsId(@Param("mch_id") String mch_id,
                                     @Param("items_id") String items_id);

    /**
     * 删除accountBook表(根据商户号+项目编号）
     * @param mch_id
     * @param items_id
     * @return
     */
    int deleteByMchIdAndItemsId(@Param("mch_id") String mch_id,
                                @Param("items_id") String items_id);

    /**
     * 查询符合要求的accountFile所对应的AccountBook总条数（项目编号 + 商户号 + 状态可有可无 + 用户标识可有可无 + 用户名称可有可无）
     * @param mch_id
     * @param items_id
     * @param status
     * @return
     */
    int selectCountByMchIdAndItemsIdAndStatus(@Param("mch_id") String mch_id,
                                              @Param("items_id") String items_id,
                                              @Param("status") String status,
                                              @Param("user_id") String user_id,
                                              @Param("user_name") String user_name);

    /**
     * 查询（项目编号s + 商户号 + 状态可有可无 + 用户标识可有可无 + 用户名称可有可无）所对应的accountBook
     * @param itemslist
     * @param mch_id
     * @param startIndex
     * @param pageSize
     * @param status
     * @return
     */
    List<AccountBook> selectAccountBookByItemsIdAndMchId(@Param("itemslist") List<String> itemslist,
                                                         @Param("mch_id") String mch_id,
                                                         @Param("startIndex") Integer startIndex,
                                                         @Param("pageSize") Integer pageSize,
                                                         @Param("status") String status,
                                                         @Param("user_id") String user_id,
                                                         @Param("user_name") String user_name);

    /**
     * 根据商户号查询AccountFile
     * @param mch_id
     * @return
     */
    List<AccountBook> getAllAccountBookByMchId(String mch_id);

    /**
     * 根据ids查询
     * @param list
     * @return
     */
    List<AccountBook> selectByPrimaryKeys(@Param("list") List<String> list);

    /**
     * 结算明细：分页查询之总数
     * @param mch_id
     * @param items_id
     * @param startIndex
     * @param pageSize
     * @return
     */
    int selectPageCountByMchIdAndItemsId(@Param("mch_id") String mch_id,
                                         @Param("items_id") String items_id,
                                         @Param("startIndex") int startIndex,
                                         @Param("pageSize") int pageSize);

    /**
     * 结算明细：分页查询
     * @param mch_id
     * @param items_id
     * @param startIndex
     * @param pageSize
     */
    List<AccountBook> selectPageByMchIdAndItemsId(@Param("mch_id") String mch_id,
                                                  @Param("items_id") String items_id,
                                                  @Param("startIndex") int startIndex,
                                                  @Param("pageSize") int pageSize);

    /**
     * 查询accountBook总金额
     * @param mch_id
     * @param items_id
     * @return
     */
    int selectAllMoney(@Param("mch_id") String mch_id,
                       @Param("items_id") String items_id);

    /**
     * 查询accountBook已缴费金额
     * @param mch_id
     * @param items_id
     * @return
     */
    int selectReadMoney(@Param("mch_id") String mch_id,
                        @Param("items_id") String items_id);

    /**
     * 根据商户号 + 项目编号 + 支付状态 查询
     * @param mch_id
     * @param items_id
     * @return
     */
    List<AccountBook> selectByMchIdAndItemsIdAndPayStatus(@Param("mch_id") String mch_id,
                                                          @Param("items_id") String items_id);

    /**
     *
     * @param mch_id
     * @param items_id
     * @param user_id
     * @return
     */
    AccountBook selectByMchIdAndItemsIdAndUserId(@Param("mch_id") String mch_id,
                                                 @Param("items_id") String items_id,
                                                 @Param("user_id") String user_id);

    int selectTotalCountAccountBookAndPayOrderByConditionWhenPayStatusIsZero(Map<String, Object> conditionMap);

    List<AccountBook> selectPageAccountBookAndPayOrderByConditionWhenPayStatusIsZero(Map<String, Object> conditionMap);

    /**
     *
     * @param mch_id
     * @param items_id
     * @return
     */
    List<AccountBook> selectAccountBookByMchIdAndItemsId(@Param("mch_id") String mch_id,
                                                         @Param("items_id") String items_id);

    /**
     * AccountBook + PayOrder分页总数
     * @param conditionMap
     * @return
     */
    List<AccountBook> selectPageAccountBookAndPayOrderByCondition(Map<String, Object> conditionMap);

    /**
     * AccountBook + PayOrder分页
     * @param conditionMap
     * @return
     */
    int selectTotalCountAccountBookAndPayOrderByCondition(Map<String, Object> conditionMap);

    /**
     * 根据accountBook的ids查询联合查询accountBook+payOrder
     * @param list
     * @return
     */
    List<AccountBook> selectAccountBookAndPayOrderByAccountBookIds(@Param("list") ArrayList<String> list);
}