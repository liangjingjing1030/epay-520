package org.epay.service.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.epay.dal.dao.mapper.AccountBookMapper;
import org.epay.dal.dao.model.AccountBook;
import org.epay.dal.dao.model.AccountBookExample;
import org.epay.dal.dao.model.PayOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;

/**
 * 名称: 云账单业务处理
 * 作者: HappyDan
 * 时间: 2019年4月2日
 * 版本: V1.0
 */
@Component
public class AccountBookService {

    @Autowired
    private AccountBookMapper accountBookMapper;

    public AccountBook selectAccountBook(String accountBookId) {
        return accountBookMapper.selectByPrimaryKey(accountBookId);
    }
    
    public int updateStatus(AccountBook accountBook) {
    	return accountBookMapper.updateByPrimaryKey(accountBook);

    }
    /**
     * 查询代缴费用
     * @param accountBook
     * @return
     */
    public List<AccountBook> selectAccountBookByConditions(AccountBook accountBook) {
    	
    	//获取查询条件====================================Start
    	String 	mch_id 		 = accountBook.getMch_id();
    	String 	user_id 	 = accountBook.getUser_id();
    	String 	items_id 	 = accountBook.getItems_id();
    	Integer limit 		 = accountBook.getLimit();
    	Integer offset 		 = accountBook.getOffset();
    	//获取查询条件====================================End
    	
    	AccountBookExample example = new AccountBookExample();
    	if(limit != null && offset != null) {
    		PageHelper.startPage(limit, offset);
    	}
//    	  example.setLimit(limit);
//        example.setOffset(offset);
    	AccountBookExample.Criteria criteria = example.createCriteria();
        criteria.andMch_idEqualTo(mch_id);
        criteria.andUser_idEqualTo(user_id);
        criteria.andPay_statusEqualTo((byte)0);
        if(StringUtils.isNotBlank(items_id)) criteria.andItems_idEqualTo(accountBook.getItems_id());
        
        List<AccountBook> accountBookList = accountBookMapper.selectByExample(example);

        return CollectionUtils.isEmpty(accountBookList) ? null : accountBookList;
    }
    
    /**
     * 查询代缴费用总数量
     * @param accountBook
     * @return
     */
    public Integer selectAccountBookNumByConditions(AccountBook accountBook) {
    	
    	//获取查询条件====================================Start
    	String 	mch_id 		 = accountBook.getMch_id();
    	String 	user_id 	 = accountBook.getUser_id();
    	String 	items_id 	 = accountBook.getItems_id();
    	//获取查询条件====================================End
    	
    	AccountBookExample example = new AccountBookExample();
    	AccountBookExample.Criteria criteria = example.createCriteria();
        criteria.andMch_idEqualTo(mch_id);
        criteria.andUser_idEqualTo(user_id);
        criteria.andPay_statusEqualTo((byte)0);
        if(StringUtils.isNotBlank(items_id)) criteria.andItems_idEqualTo(accountBook.getItems_id());
        
        List<AccountBook> accountBookList = accountBookMapper.selectByExample(example);
        Integer accountBookNum = 0;
        if(CollectionUtils.isEmpty(accountBookList)) {
        	accountBookNum = 0;
        }else {
        	accountBookNum = accountBookList.size();
        }
        return accountBookNum;
    }
    
    /**
     * 支付订单账单查询
     * @param account_book_id
     * @return
     */
    public AccountBook selectAccountBookByPrimaryKey(String account_book_id) {
    	AccountBook accountBook = accountBookMapper.selectByPrimaryKey(account_book_id);
    	if(accountBook != null) {
    		return accountBook;
    	}else {
    		return null;
    	}
    }

    /**
     * 根据商户号+项目编号分页查询
     * @param mch_id
     * @param items_id
     * @param startIndex
     * @param pageSize
     * @return
     */
    public Map<String, Object> selectAccountBookByMchIdAndItemsId(String mch_id, String items_id, int startIndex, int pageSize) {
        Map<String, Object> dataMap = new HashMap<>();
        // 查询accountBook总金额
        int allMoney = accountBookMapper.selectAllMoney(mch_id, items_id);
        // 查询accountBook已缴费金额,如果没有缴费的，查询结果为null
        int readMoney = accountBookMapper.selectReadMoney(mch_id, items_id);

        // 分页查询总数
        int total = accountBookMapper.selectPageCountByMchIdAndItemsId(mch_id, items_id, startIndex, pageSize);
        // 分页查询
        List<AccountBook> accountBookList = accountBookMapper.selectPageByMchIdAndItemsId(mch_id, items_id, startIndex, pageSize);
        dataMap.put("allMoney", allMoney);
        dataMap.put("readMoney", readMoney);
        dataMap.put("total", total);
        dataMap.put("pageList", accountBookList);
        return dataMap;
    }

    /**
     * 结算明细下载查询
     * @param mch_id
     * @param items_id
     * @return
     */
    public List<AccountBook> selectByMchIdAndItemsId(String mch_id, String items_id) {
        return accountBookMapper.selectByMchIdAndItemsIdAndPayStatus(mch_id, items_id);
    }

    /**
     * 创建支付订单对应的账单信息
     * @param payOrder
     * @return
     */
    public int createAccountBook(AccountBook accountBook) {
        return accountBookMapper.insertSelective(accountBook);
    }
    
    /**
     * 根据账单号删除账单信息
     * @param account_book_id
     * @return
     */
    public int deleteAccountBookByPrimaryKey(String account_book_id) {
    	int accountBook = accountBookMapper.deleteByPrimaryKey(account_book_id);
    	return accountBook;
    }

    /**
     * 根据主键删除accountBook
     * @param account_book_id
     * @return
     */
    public boolean removeAccountBookById(String account_book_id) {
        try {
            int deleteCount = accountBookMapper.deleteByPrimaryKey(account_book_id);
            if(deleteCount == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("根据主键删除accountBook失败");
            return false;
        }
    }

    /**
     * 修改单条accountBook
     * @param accountBook
     * @return
     */
    public boolean modefyAccountBookById(AccountBook accountBook) {
        try {
            int updateCount = accountBookMapper.updateByPrimaryKey(accountBook);
            if(updateCount == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("根据主键修改accountBook失败");
            return false;
        }
    }

    /**
     * 批量删除缴费明细
     * @param list
     * @return
     */
    public boolean removeAccountBooksByIds(ArrayList<String> list) {
        try {
            int count = 0;
            for(String account_book_id : list) {
                count += accountBookMapper.deleteByPrimaryKey(account_book_id);
            }
            if(count == list.size()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("批量删除accountBook失败");
        }
        return false;
    }

    /**
     * 添加accountBook
     * @param accountBook
     * @return
     */
    public boolean addAccountBook(AccountBook accountBook) {
        try {
            int addCount = accountBookMapper.insertSelective(accountBook);
            if(addCount == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加AccountBook失败");
        }
        return false;
    }

    /**
     * AccountBook + PayOrder分页显示
     * @param conditionMap
     * @return
     */
    public Map<String, Object> selectAccountBookAndPayOrderByConfition(Map<String, Object> conditionMap) {
        Map<String, Object> retMap = new HashMap<>();
        // 1、查询AccountBook + PayOrder数据
        List<AccountBook> accountBookList = accountBookMapper.selectPageAccountBookAndPayOrderByCondition(conditionMap);
        if(accountBookList.size() < 1) {
            retMap.put("total", 0);
            retMap.put("accountBookList", new HashMap<>());
        }
        ///2、查询AccountBook + PayOrder总数
        int total = accountBookMapper.selectTotalCountAccountBookAndPayOrderByCondition(conditionMap);
        retMap.put("total", total);
        retMap.put("pageList", accountBookList);
        return retMap;
    }

    /**
     * 根据accountBook的ids查询联合查询accountBook+payOrder
     * @param list
     * @return
     */
    public List<AccountBook> queryAccountBookAndPayOrder(ArrayList<String> list) {
        List<AccountBook> accountBookList = accountBookMapper.selectAccountBookAndPayOrderByAccountBookIds(list);
        return accountBookList;
    }

    /**
     * 根据项目编号、商户号查询
     * @param mch_id
     * @param items_id
     * @return
     */
    public List<AccountBook> queryAccountBookListByMchIdAndItemsId(String mch_id, String items_id) {
//        List<AccountBook> accountBookList =  accountBookMapper.selectByMchIdAndItemsId(mch_id, items_id);
        List<AccountBook> accountBookList =  accountBookMapper.selectAccountBookByMchIdAndItemsId(mch_id, items_id);
        return accountBookList;
    }
}
