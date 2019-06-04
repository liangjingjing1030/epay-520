package org.epay.mgr.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.epay.common.util.DateUtils;
import org.epay.dal.dao.model.AccountBook;
import org.epay.dal.dao.model.ModelKey;
import org.epay.dal.dao.model.ModelValue;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountBookUtil {

    /**
     * 读取账单模板中的内容
     * @param fileName
     * @param file
     * @param itemsId
     * @return
     */
    public static Map<String, Object> readAccountBook(String fileName, MultipartFile file, String itemsId, String mch_id){

        try {
            boolean notNull = false;

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
            int count = sheet.getLastRowNum();// 获取总行数
            int coloumNum=sheet.getRow(0).getPhysicalNumberOfCells();//获得总列数,中间有空值也算数,开始的空值不算数
            // 1、获取标题行并保存到key表
            Row rowTitile = sheet.getRow(0);// 获取标题行
            String user_id_title = "";
            String items_money_title = "";
            String select1 = "", select2 = "", select3 = "", select4 = "", select5 = "";
            String select6 = "", select7 = "", select8 = "", select9 = "", select10 = "";
            Map<String, Integer> map = new HashMap<>();
            int nullCount = 0;
            // 循环总列数,保存列值---------------------------------start
            for(int i = 0; i < coloumNum + nullCount; i++) {
                String coloum = "";

                try {
                    // 如果报空指针说明当前列位置为空
                    coloum = rowTitile.getCell(i).getStringCellValue();
                } catch (NullPointerException e) {
                    nullCount++;
                    continue;
                }

                if(StringUtils.isBlank(coloum)) {
                    continue;
                }
                if(coloum.endsWith("id")) {
                    user_id_title = coloum;
                    map.put("user_id", i);
                } else if(coloum.endsWith("m")) {
                    items_money_title = coloum;
                    map.put("items_money", i);
                } else if(coloum.endsWith("s1")) {
                    select1 = coloum;
                    map.put("select1", i);
                } else if(coloum.endsWith("s2")) {
                    select2 = coloum;
                    map.put("select2", i);
                } else if(coloum.endsWith("s3")) {
                    select3 = coloum;
                    map.put("select3", i);
                } else if(coloum.endsWith("s4")) {
                    select4 = coloum;
                    map.put("select4", i);
                } else if(coloum.endsWith("s5")) {
                    select5 = coloum;
                    map.put("select5", i);
                } else if(coloum.endsWith("s6")) {
                    select6 = coloum;
                    map.put("select6", i);
                } else if(coloum.endsWith("s7")) {
                    select7 = coloum;
                    map.put("select7", i);
                } else if(coloum.endsWith("s8")) {
                    select8 = coloum;
                    map.put("select8", i);
                } else if(coloum.endsWith("s9")) {
                    select9 = coloum;
                    map.put("select9", i);
                } else if(coloum.endsWith("s10")) {
                    select10 = coloum;
                    map.put("select10", i);
                }
            }
            ModelKey modelKey = new ModelKey();
            String keyId = "key" + DateUtils.getCurrentTimeStrDefault() + (int)((Math.random()*9+1)*100000);
            modelKey.setModelKeyId(keyId);
            modelKey.setUserId(user_id_title);
            modelKey.setItemsMoney(items_money_title);
            modelKey.setSelect1(select1);
            modelKey.setSelect2(select2);
            modelKey.setSelect3(select3);
            modelKey.setSelect4(select4);
            modelKey.setSelect5(select5);
            modelKey.setSelect6(select6);
            modelKey.setSelect7(select7);
            modelKey.setSelect8(select8);
            modelKey.setSelect9(select9);
            modelKey.setSelect10(select10);
            // 循环总列数,保存列值---------------------------------end

            // 2、获取内容行，其中唯一标识与应缴金额存accountBook表；其余存value表，value主键与accountBook主键相同
            List<AccountBook> accountBooksList = new ArrayList<AccountBook>();
            List<ModelValue> modelValueList = new ArrayList<ModelValue>();
            AccountBook accountBook;
            ModelValue modelValue;

            // 获取所有位置----------------------------------start
            int user_id_position = map.get("user_id");
            int items_money_position = map.get("items_money");
            int select1_position = -99;
            int select2_position = -99;
            int select3_position = -99;
            int select4_position = -99;
            int select5_position = -99;
            int select6_position = -99;
            int select7_position = -99;
            int select8_position = -99;
            int select9_position = -99;
            int select10_position = -99;
            if(map.get("select1") != null) {// 如果没有这一列则为-99
                select1_position = map.get("select1");
            }
            if(map.get("select2") != null) {
                select2_position = map.get("select2");
            }
            if(map.get("select3") != null) {
                select3_position = map.get("select3");
            }
            if(map.get("select4") != null) {
                select4_position = map.get("select4");
            }
            if(map.get("select5") != null) {
                select5_position = map.get("select5");
            }
            if(map.get("select6") != null) {
                select6_position = map.get("select6");
            }
            if(map.get("select7") != null) {
                select7_position = map.get("select7");
            }
            if(map.get("select8") != null) {
                select8_position = map.get("select8");
            }
            if(map.get("select9") != null) {
                select9_position = map.get("select9");
            }
            if(map.get("select10") != null) {
                select10_position = map.get("select10");
            }
            // 获取所有位置----------------------------------end

            // 循环总行数=========================================start
            for (int r = 1; r <= count; r++) {
                Row row = sheet.getRow(r);// 获取行
                if (row == null){
                    continue;
                }
                /**
                 * CELL_TYPE_NUMERIC 数值型 0
                 * CELL_TYPE_STRING 字符串型 1
                 * CELL_TYPE_FORMULA 公式型 2
                 * CELL_TYPE_BLANK 空值 3
                 * CELL_TYPE_BOOLEAN 布尔型 4
                 * CELL_TYPE_ERROR 错误 5
                 */
                String user_id = "";
                String items_money = "";
                String value1 = "", value2 = "", value3 = "", value4 = "", value5 = "";
                String value6 = "", value7 = "", value8 = "", value9 = "", value10 = "";

                // 循环列数,取到每个值----------------------------------------------start
                for(int j = 0; j < coloumNum + nullCount; j++) {
                    String value = null;
                    int valueType = -99;
                    try {
                        valueType = row.getCell(j).getCellType();
                    } catch (NullPointerException e) {
                        continue;
                    }
                    if(valueType == 0) {
                        int valueNum = (int)row.getCell(j).getNumericCellValue();
                        value = "" + valueNum;
                    } else if(valueType == 1) {
                        value = row.getCell(j).getStringCellValue();
                    }

                    // 判断当前取的是什么字段的值
                    if(user_id_position == j) {
                        user_id = value;
                    } else if(items_money_position == j) {
                        items_money = value;
                    } else if(select1_position != -99 && select1_position == j) {
                        value1 = value;
                    } else if(select2_position != -99 && select2_position == j) {
                        value2 = value;
                    } else if(select3_position != -99 && select3_position == j) {
                        value3 = value;
                    } else if(select4_position != -99 && select4_position == j) {
                        value4 = value;
                    } else if(select5_position != -99 && select5_position == j) {
                        value5 = value;
                    } else if(select6_position != -99 && select6_position == j) {
                        value6 = value;
                    } else if(select7_position != -99 && select7_position == j) {
                        value7 = value;
                    } else if(select8_position != -99 && select8_position == j) {
                        value8 = value;
                    } else if(select9_position != -99 && select9_position == j) {
                        value9 = value;
                    } else if(select10_position != -99 && select10_position == j) {
                        value10 = value;
                    }

                }
                // 循环列数,取到每个值----------------------------------------------end

                accountBook = new AccountBook();
                modelValue = new ModelValue();//主键与accountBook主键相同
                // 获取当前时间字符串(默认格式:yyyyMMddHHmmssSSS)
                String time = "ZD0" + DateUtils.getCurrentTimeStrDefault() + (int)((Math.random()*9+1)*100000);
                accountBook.setAccount_book_id(time);
                // 项目编号不应由用户提供，应从accountFile取值
                accountBook.setItems_id(itemsId);
                accountBook.setMch_id(mch_id);
                accountBook.setUser_id(user_id);
                accountBook.setItems_money(Long.parseLong(items_money));

                modelValue.setModelValueId(time);//主键与accountBook主键相同
                modelValue.setModelKeyId(modelKey.getModelKeyId());// ModelKey的主键
                modelValue.setUserIdValue(user_id);
                modelValue.setItemsMoneyValue(Long.parseLong(items_money));
                if(value1 != "") {
                    modelValue.setValue1(value1);
                }
                if(value2 != "") {
                    modelValue.setValue2(value2);
                }
                if(value3 != "") {
                    modelValue.setValue3(value3);
                }
                if(value4 != "") {
                    modelValue.setValue4(value4);
                }
                if(value5 != "") {
                    modelValue.setValue5(value5);
                }
                if(value6 != "") {
                    modelValue.setValue6(value6);
                }
                if(value7 != "") {
                    modelValue.setValue7(value7);
                }
                if(value8 != "") {
                    modelValue.setValue8(value8);
                }
                if(value9 != "") {
                    modelValue.setValue9(value9);
                }
                if(value10 != "") {
                    modelValue.setValue10(value10);
                }

                accountBooksList.add(accountBook);
                modelValueList.add(modelValue);

            }
            // 循环总行数=========================================end

            Map<String, Object> retMap = new HashMap<>();
            retMap.put("modelKey", modelKey);
            retMap.put("accountBooksList", accountBooksList);
            retMap.put("modelValueList", modelValueList);
//            retMap.put("titlePositionMap", map);
            return retMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
