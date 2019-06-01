package org.epay.service.service;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.util.Base64;
import org.epay.common.util.DateUtils;
import org.epay.dal.dao.mapper.AccountBookMapper;
import org.epay.dal.dao.mapper.AccountFileMapper;
import org.epay.dal.dao.mapper.ChannelInfoMapper;
import org.epay.dal.dao.mapper.PayOrderMapper;
import org.epay.dal.dao.model.*;
import org.epay.service.utils.BASE64DecodedMultipartFile;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商户账单分页查询
 * GaoLiang
 * 2019年4月28日
 */
@Component
public class BillService {
    @Autowired
    private AccountFileMapper accountFileMapper;
    @Autowired
    private AccountBookMapper accountBookMapper;
    @Autowired
    private PayOrderMapper payOrderMapper;

    // 判断账单是否重复上传
    public int queryByMchIdAndItemsId(String mchId, String xmbh) {
        return accountFileMapper.selectByMchIdAndItemsId(mchId, xmbh);
    }

    // accountFile分页显示
    public Map<String, Object> selectAccountFilePageByMchId(String mchId, String startIndex, String pageSize) {
        Map<String, Object> dataMap = new HashMap<>();
        // 查询当前商户的所有上传账单总数
        dataMap.put("total", accountFileMapper.getTotalByMchId(mchId));
        // 查询当前商户的所有账单
        dataMap.put("pageList", accountFileMapper.getPageList(mchId, Integer.parseInt(startIndex), Integer.parseInt(pageSize)));
        return dataMap;
    }

    // accountBook分页显示
    public Map<String, Object> selectAccountBookByConfition(Map<String, Object> conditionMap) {
        Map<String, Object> dataMap = new HashMap<>();
        // 1、<模糊查询>查询符合要求的accountFile
        List<AccountFile> accountFileList = accountFileMapper.selectByConditionMapOfBillQuery(conditionMap);
        // another method———————————————————————start—————————————————————————————
        /*String mch_id2 = (String) conditionMap.get("mch_id");
        String items_id2 = (String) conditionMap.get("items_id");
        String items_name2 = (String) conditionMap.get("items_name");
        String items_filename2 = (String) conditionMap.get("items_filename");
        String upload_date2 = (String) conditionMap.get("upload_date");

        AccountFileExample example = new AccountFileExample();
        AccountFileExample.Criteria criteria = example.createCriteria();
        criteria.andMch_idEqualTo(mch_id2);
        if(StringUtils.isNotBlank(items_id2)) criteria.andItems_idLike(items_id2);
        if(StringUtils.isNotBlank(items_name2)) criteria.andItems_nameLike(items_name2);
        if(StringUtils.isNotBlank(items_filename2)) criteria.andItems_filenameLike(items_filename2);
        if(StringUtils.isNotBlank(upload_date2)) criteria.andUpload_dateEqualTo(upload_date2);

        List<AccountFile> accountFileList = accountFileMapper.selectByExample(example);*/
        // another method—————————————————————————end—————————————————————————————

        // 如果没有符合的数据，直接返回null
        if(accountFileList.size() < 1) {
            dataMap.put("total", 0);
            dataMap.put("pageList",new ArrayList<>());
            dataMap.put("accountFileListForPageShow",new ArrayList<>());
            return dataMap;
        }

        int totalBookCount = 0;
        List<String> itemlist = new ArrayList<>();
        String status = (String) conditionMap.get("status");
        String user_id = (String) conditionMap.get("user_id");
        String user_name = (String) conditionMap.get("user_name");

        for(AccountFile accountFile : accountFileList) {
            // 2、<模糊查询>查询符合要求的accountFile所对应的AccountBook总条数（项目编号 + 商户号 + 状态可有可无 + 用户标识可有可无 + 用户名称可有可无）
            totalBookCount += accountBookMapper.selectCountByMchIdAndItemsIdAndStatus(accountFile.getMch_id(),accountFile.getItems_id(),status,user_id,user_name);
            // 3、获取符合要求的accountBook的项目编号
            itemlist.add(accountFile.getItems_id());
        }
        // 4、<模糊查询>查询当前（项目编号s + 商户号 + 状态可有可无 + 用户标识可有可无 + 用户名称可有可无）所对应的accountBook
        String mch_id = (String) conditionMap.get("mch_id");
        Integer startIndex = Integer.parseInt(conditionMap.get("startIndex").toString());
        Integer pageSize = Integer.parseInt(conditionMap.get("pageSize").toString());
        List<AccountBook> booklist = accountBookMapper.selectAccountBookByItemsIdAndMchId(itemlist,mch_id,startIndex,pageSize,status,user_id,user_name);
        // 查询accountBook所属的accountFile
        List<AccountFile> accountFileListForPageShow = new ArrayList<>();
        for(AccountBook accountBook : booklist) {
            AccountFile af = accountFileMapper.selectAccountFileByMchIdAndItemsId(accountBook.getMch_id(), accountBook.getItems_id());
            accountFileListForPageShow.add(af);
        }
        dataMap.put("total", totalBookCount);
        dataMap.put("pageList",booklist);
        dataMap.put("accountFileListForPageShow",accountFileListForPageShow);
        return dataMap;
    }


    /**
     * accountFile分页显示+统计
     * @param conditionMap
     * @return
     */
    public Map<String, Object> selectAccountFileAddCount(Map<String, Object> conditionMap) {
        String mch_id = (String) conditionMap.get("mch_id");
        // 1、查询本次分页AccountFile
        List<AccountFile> accountFileList = accountFileMapper.selectPageByMchId(conditionMap);
        if(accountFileList.size() < 1) {
            // TODO
        }
        // 2、查询AccountFile总数
        int total = accountFileMapper.selectCountByMchId(mch_id);
        // 3、遍历AccountFile下的AccountBook
        List<AccountFileForCount> accountFileForCountList = new ArrayList<>();
        for(AccountFile accountFile : accountFileList) {
            AccountFileForCount accountFileForCount = new AccountFileForCount();
            BeanUtils.copyProperties(accountFile, accountFileForCount);
            String items_id = accountFile.getItems_id();
            long totalMoney = 0L;
            long readMoney = 0L;
            long backMoney = 0L;
            List<AccountBook> accountBookList = accountBookMapper.selectAccountBookByMchIdAndItemsId(mch_id, accountFile.getItems_id());
            for(AccountBook accountBook : accountBookList) {
                totalMoney += accountBook.getItems_money();
                // 支付状态,0-未支付,1-支付成功,2-支付失败,3-账单退款,4-支付中
                if(accountBook.getPay_status() == 1) {
                    readMoney += accountBook.getItems_money();
                } else if(accountBook.getPay_status() == 3) {
                    backMoney += accountBook.getItems_money();
                }
            }
            accountFileForCount.setTotalMoney(totalMoney + "");
            accountFileForCount.setReadMoney(readMoney + "");
            accountFileForCount.setBackMoney(backMoney + "");

            accountFileForCountList.add(accountFileForCount);
        }
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("total", total);
        retMap.put("pageList", accountFileForCountList);

        return retMap;
    }

    // 判断是否可以删除账单
    public int checkIfCanDeleteAccountFile(ArrayList<String> AccountFileIds) {
        int count = 1;
        for(String accountFileId : AccountFileIds) {
            // 1、遍历出要删除的accountFile
            AccountFile accountFile = accountFileMapper.selectByPrimaryKey(accountFileId);
            // 2、根据accountFile的商户号+项目编号查询accountBook
            List<AccountBook> accountBookList = accountBookMapper.selectByMchIdAndItemsId(accountFile.getMch_id(), accountFile.getItems_id());
            // 3、判断accountBook中有无已经缴费的订单
            for(AccountBook ab : accountBookList) {
                // 支付状态,0-未支付,1-支付成功,2-支付失败,3-账单退款
                Byte status = ab.getPay_status();
                if(0 != status) {
                    count++;
                    return count;
                }
            }
        }
        return count;
    }

    // 删除账单
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public boolean deleteAccountFile(ArrayList<String> list) {
        boolean deleteFileOk = false;
        boolean deleteBookOk = false;
        int count = 0;
        int total = 0;
        try {
            List<AccountFile> accountFileList = new ArrayList<AccountFile>();
            for (String accountFileId : list) {
                AccountFile accountFile = accountFileMapper.selectByPrimaryKey(accountFileId);
                // 获取要删除的accountBook条数
                total += accountBookMapper.selectCountByMchIdAndItemsId(accountFile.getMch_id(), accountFile.getItems_id());
            }
            for (String accountFileId : list) {
                // 1、遍历出要删除的accountFile的商户号+项目编号
                AccountFile accountFile = accountFileMapper.selectByPrimaryKey(accountFileId);
                // 查询accountFile获取项目编号（根据id）
                AccountFile af = new AccountFile();
                af.setMch_id(accountFile.getMch_id());
                af.setItems_id(accountFile.getItems_id());
                accountFileList.add(af);
                // /2、删除accountFile表
                int i = accountFileMapper.deleteByPrimaryKey(accountFileId);
                if(i == 1) {
                    count++;
                }
            }
            if(count == list.size()) {
                deleteFileOk = true;
            }
            // 3、删除accountBook表(根据商户号+项目编号）
            int deleteBookCount = 0;
            for(AccountFile accountFile : accountFileList) {
                int removeBookCount = accountBookMapper.deleteByMchIdAndItemsId(accountFile.getMch_id(), accountFile.getItems_id());
                if(removeBookCount >= 0) {
                    deleteBookCount += removeBookCount;
                }
            }
            if(total == deleteBookCount) {
                deleteBookOk = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // try catch捕获异常会让注解@Transactional失效
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        if(deleteFileOk && deleteBookOk) {
            return true;
        } else {
            return false;
        }
    }

    // 导入账单start--------------------------------------------------------------------------
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public boolean importAccountFileAndAccountBook(AccountFile accountFile, List<AccountBook> accountBookList) {
        boolean saveFileOk = false;
        boolean saveBookOk = false;
        try {
            // 1.保存文件信息到accountFile表中
            saveFileOk = insertAccountFile(accountFile);
            if(!saveFileOk) {
                int i = 1 / 0;// 进入catch，无实际意义
            }
            // 2.解析表中的数据保存到accountBook表中
            saveBookOk = insertAccountBook(accountBookList);
            if(!saveBookOk) {
                int i = 1 / 0;// 进入catch，无实际意义
            }
        } catch (Exception e) {
            // try catch捕获异常会让注解@Transactional失效
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        if(saveFileOk && saveBookOk) {
            return true;
        } else {
            return false;
        }
    }

    public boolean insertAccountFile(AccountFile a) {
        // 分批导入，只插入一次
        AccountFile af = accountFileMapper.selectByAllCondition(a.getMch_id(),a.getItems_id(),a.getItems_name(),a.getAffect_date(),a.getExpiry_date());
        if(af == null) {
            int insertCount = accountFileMapper.insertSelective(a);
            if(1 == insertCount) {
                return true;
            }
            return false;
        }
        return true;
    }

    public boolean insertAccountBook(List<AccountBook> accountBookList) {
        int count = 0, exist = 0;
        for(AccountBook accountBook : accountBookList) {
            // 已经存在的数据不再重复插入
            AccountBook a = accountBookMapper.selectByMchIdAndItemsIdAndUserId(accountBook.getMch_id(), accountBook.getItems_id(), accountBook.getUser_id());
            if(a == null) {
                count += accountBookMapper.insertSelective(accountBook);
            } else {
                exist++;
            }

        }
        if((count + exist) == accountBookList.size()) {
            System.out.println("count:" + count + ",exist:" + exist);
            return true;
        }
        return false;
    }

    /*public MultipartFile base64ToMultipart(String base64) {
        try {
            String[] baseStrs = base64.split(",");

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = new byte[0];
            b = decoder.decodeBuffer(baseStrs[1]);

            for(int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return new BASE64DecodedMultipartFile(b, baseStrs[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }*/

    public static File base64ToFile(String base64) {
        if(base64==null||"".equals(base64)) {
            return null;
        }
        byte[] buff= Base64.decode(base64.getBytes());
        File file=null;
        FileOutputStream fout=null;
        try {
            file = File.createTempFile("tmp", null);
            fout=new FileOutputStream(file);
            fout.write(buff);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fout!=null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }
    // 导入账单end--------------------------------------------------------------------------

    // 判断有无可以导出的账单
    public int checkHaveAccountCanBeExport(String mch_id) {
        List<AccountFile> accountFileList = accountFileMapper.selectByMchId(mch_id);

        return accountFileList.size() + 1;
    }

    // 查询当前商户下所有的accountBook
    public List<AccountBook> exportAllAccountBook(String mch_id) {
        return accountBookMapper.getAllAccountBookByMchId(mch_id);
    }

    // 根据ids查询
    public List<AccountBook> exportSelectedAccountBook(List<String> list) {
        return accountBookMapper.selectByPrimaryKeys(list);
    }

    /**
     * 根据商户号查询accountFile的items_id集合
     * @param mch_id
     * @return
     */
    public List<String> queryItemsIdListByMchId(String mch_id) {
        List<String> itemsIdList = new ArrayList<>();
        List<AccountFile> accountFileList = accountFileMapper.selectByMchId(mch_id);
        for(AccountFile accountFile : accountFileList) {
            itemsIdList.add(accountFile.getItems_id());
        }
        return itemsIdList;
    }

}
