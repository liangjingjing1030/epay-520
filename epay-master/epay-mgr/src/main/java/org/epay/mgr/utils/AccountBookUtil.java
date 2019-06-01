package org.epay.mgr.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.epay.common.util.DateUtils;
import org.epay.dal.dao.model.AccountBook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AccountBookUtil {

    public static List<AccountBook> readAccountBook(String fileName, MultipartFile file, String itemsId){

        try {
            boolean notNull = false;

            List<AccountBook> accountBooksList = new ArrayList<AccountBook>();

            if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
                System.out.println("上传文件格式不正确");
                int i = 1 / 0;// 进入catch，无实际意义
            }
            boolean isExcel2003 = true;
            if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
                isExcel2003 = false;
            }

            InputStream is = file.getInputStream();
            Workbook wb = null;
            if (isExcel2003) {
                wb = new HSSFWorkbook(is);
            } else {
                wb = new XSSFWorkbook(is);
            }
            // 获取第一个sheet
            Sheet sheet = wb.getSheetAt(0);
            if(sheet!=null){
                notNull = true;
            }
            AccountBook accountBook;
            // 循环第一个sheet
            int count = sheet.getLastRowNum();
            for (int r = 1; r <= count; r++) {
                Row row = sheet.getRow(r);// 获取行
                if (row == null){
                    continue;
                }
                accountBook = new AccountBook();
                /**
                 * CELL_TYPE_NUMERIC 数值型 0
                 * CELL_TYPE_STRING 字符串型 1
                 * CELL_TYPE_FORMULA 公式型 2
                 * CELL_TYPE_BLANK 空值 3
                 * CELL_TYPE_BOOLEAN 布尔型 4
                 * CELL_TYPE_ERROR 错误 5
                 */
                /*if( row.getCell(0).getCellType() !=1){
                    System.out.println("导入失败(第"+(r+1)+"行,商户号请设为文本格式)");
                    int i = 1 / 0;// 进入catch，无实际意义
                }

                String mchId = row.getCell(0).getStringCellValue();
                if(mchId == null || mchId.isEmpty()){
                    System.out.println("导入失败(第"+(r+1)+"行,商户号未填写)");
                    int i = 1 / 0;// 进入catch，无实际意义
                }*/
                String userId = null;
                int userIdType = row.getCell(0).getCellType();
                if(userIdType == 0) {
                    int userIdNum = (int)row.getCell(0).getNumericCellValue();
                    userId = "" + userIdNum;
                } else if(userIdType == 1) {
                    userId = row.getCell(0).getStringCellValue();
                }
                if(userId==null || userId.isEmpty()){
                    System.out.println("导入失败(第"+(r+1)+"行,客户唯一标识未填写)");
                    int i = 1 / 0;// 进入catch，无实际意义
                }

                String userName = row.getCell(1).getStringCellValue();
                if(userName==null || userName.isEmpty()){
                    System.out.println("导入失败(第"+(r+1)+"行,客户姓名未填写)");
                    int i = 1 / 0;// 进入catch，无实际意义
                }

                String currency = row.getCell(2).getStringCellValue();
                if(currency==null || currency.isEmpty()){
                    System.out.println("导入失败(第"+(r+1)+"行,货币代码未填写)");
                    int i = 1 / 0;// 进入catch，无实际意义
                }
                // 数值型
                double itemsMoney = 0L;
                int cellType = row.getCell(3).getCellType();
                if(cellType == 1) {
                    String itemsMoneystr = row.getCell(3).getStringCellValue();
                    itemsMoney = Double.parseDouble(itemsMoneystr);
                    int i = (int) (itemsMoney / 1);// 确保是数值
                }
                if(cellType ==0){
                    itemsMoney = row.getCell(3).getNumericCellValue();
//                    System.out.println("导入失败(第"+(r+1)+"行,金额格式不正确或未填写)");
//                    int i = 1 / 0;// 进入catch，无实际意义
                }
                // 获取当前时间字符串(默认格式:yyyyMMddHHmmssSSS)
                String time = "ZD0" + DateUtils.getCurrentTimeStrDefault() + (int)((Math.random()*9+1)*100000);
                accountBook.setAccount_book_id(time);
//                accountBook.setMch_id(mchId);
                // 项目编号不应由用户提供，应从accountFile取值
                accountBook.setItems_id(itemsId);
                accountBook.setUser_id(userId);
                accountBook.setUser_name(userName);
                accountBook.setCurrency(currency);
                accountBook.setItems_money((long) itemsMoney);
                accountBooksList.add(accountBook);
//                Thread.sleep(1);
            }
            return accountBooksList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
