package org.epay.mgr.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.epay.dal.dao.model.AccountBookAndPayOrder;
import org.epay.dal.dao.model.ModelKey;

public class WriteExcel<T> {
	public static void main(String[] args) {
		String fieldName = "account_book_id";
		String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		System.out.println(getMethodName);
	}
	/**
	 * 获取excel的工作簿对象(accountBook)
	 * @param dataList 数据
	 * @param sheetName 表格的名称
	 * @param c 数据dataList集合中元素的类型
	 * @return 工作簿对象
	 */
	public HSSFWorkbook getWorkbook(List<T> dataList , String sheetName , Class c) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 创建一个sheet
			HSSFSheet hssfSheet = workbook.createSheet(sheetName);
			// 创建第一行
			HSSFRow row0 = hssfSheet.createRow(0);
			// 获取实体类的所有字段
			Field[] fields = c.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				// 获取第一行标题列名
				String fieldName = fields[i].getName();
				if("account_book_id".equals(fieldName)) {
					fieldName = "账单编号";
				} else if("mch_id".equals(fieldName)) { // 1
					fieldName = "商户号";
				} else if("items_id".equals(fieldName)) {
					fieldName = "项目编号";
				} else if("user_id".equals(fieldName)) { // 2
					fieldName = "个人标识";
				} else if("user_name".equals(fieldName)) { // 3
					fieldName = "客户姓名";
				} else if("currency".equals(fieldName)) { // 4
					fieldName = "货币代码,人民币:cny";
				} else if("items_money".equals(fieldName)) { // 5
					fieldName = "应缴金额/分";
				} else if("pay_time".equals(fieldName)) {
					fieldName = "支付时间";
				} else if("pay_status".equals(fieldName)) {
					fieldName = "支付状态";
				} else {
					continue; // 其他字段不做处理（备用字段1~5、serialVersionUID等）
				}

				// 创建第一行标题列
				HSSFCell cell0 = row0.createCell(i);
				// 设置标题列的内容
				cell0.setCellValue(fieldName);
			}
			for (int i = 0; i < dataList.size(); i++) {
				// dataList是实体类的list，获取list中具体的实体类
				T obj = dataList.get(i);
				// 创建所有的内容行
				HSSFRow row = hssfSheet.createRow(i + 1);
				for (int j = 0; j < fields.length; j++) {
					// 获取列明
					String fieldName = fields[j].getName();
					// 只处理上面的9个字段
					if( !"account_book_id".equals(fieldName) &&
						!"mch_id".equals(fieldName) &&
						!"items_id".equals(fieldName) &&
						!"user_id".equals(fieldName) &&
						!"user_name".equals(fieldName) &&
						!"currency".equals(fieldName) &&
						!"items_money".equals(fieldName) &&
						!"pay_time".equals(fieldName) &&
						!"pay_status".equals(fieldName)) {
						continue;
					}

					// 拼出列的get方法
					String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					// 调用get方法
					Method getMethod = c.getDeclaredMethod(getMethodName);
					Object value = getMethod.invoke(obj);
					// 创建内容列
					HSSFCell cell = row.createCell(j);
					// 设置内容列的内容
					String v = null;
					if(value != null) {
						v = value.toString();
					}
					//单独处理 支付状态,0-未支付,1-支付成功,2-支付失败,3-账单退款,4-支付中
					//
					if ("getPay_status".equals(getMethodName)) {
						if("0".equals(v)) {
							v = "未缴费";
						} else if("1".equals(v)) {
							v = "缴费成功";
						} else if("2".equals(v)) {
							v = "缴费失败";
						} else if("3".equals(v)) {
							v = "账单退款";
						} else if("4".equals(v)) {
							v = "支付中";
						}
					}
					cell.setCellValue(value == null ? "" : v);
				}
			}
			return workbook;
		} catch (SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 下载交易回单
	 * @param dataList
	 * @param sheetName
	 * @param c
	 * @return
	 */
	public HSSFWorkbook getdealReceipt(List<AccountBookAndPayOrder> dataList , String sheetName , Class c) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();

            //生成一个样式
			HSSFCellStyle style = workbook.createCellStyle();
            //设置这些样式
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //生成一个字体
            HSSFFont font=workbook.createFont();
            font.setFontHeightInPoints((short)16);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            //把字体应用到当前的样式
            style.setFont(font);

			// 创建一个sheet
			HSSFSheet hssfSheet = workbook.createSheet(sheetName);
			// 设置列宽
			hssfSheet.setColumnWidth(0, 20 * 350);
			hssfSheet.setColumnWidth(1, 20 * 350);

            // 设置第一列的格式
			//生成一个样式
			HSSFCellStyle style2 = workbook.createCellStyle();
			//设置这些样式
			style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 水平方向的对齐方式
			//style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);// 垂直方向的对齐方式
			//生成一个字体
			HSSFFont font2=workbook.createFont();
			font2.setFontHeightInPoints((short)10);
			font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			//把字体应用到当前的样式
			style2.setFont(font2);

			// 循环accountBookAndPayOrderList
			int titleCount = 0;//0、11、22
			int count = 1; //1、14、27
			for(int k = 0; k < dataList.size(); k++) {
				// 创建标题行
				HSSFRow row = hssfSheet.createRow(titleCount);
				row.setHeight((short)800);//n为行高的像素数。
				HSSFCell cell = row.createCell(0);
				// 设置标题列的内容
				cell.setCellValue("交易回单");
				//设置单元格的样式
				cell.setCellStyle(style);

                // 下标从0开始 起始行号，终止行号， 起始列号，终止列号
                CellRangeAddress region = new CellRangeAddress(titleCount, titleCount, 0, 1);
                //在sheet里增加合并单元格
                hssfSheet.addMergedRegion(region);

				// 实体对象AccountBookAndPayOrder
				AccountBookAndPayOrder ap = dataList.get(k);
				// 获取实体类的所有字段
				Field[] fields = c.getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					// 创建行
					HSSFRow row0 = hssfSheet.createRow(count++);// 第一行、第二行……
					// 获取第一行标题列名
					String fieldName = fields[i].getName();
					String fieldNameOld = fieldName;
					if("account_book_id".equals(fieldName)) {
						fieldName = "账单号:";
					} else if("mch_id".equals(fieldName)) { // 1
						fieldName = "商户号:";
					} else if("items_id".equals(fieldName)) {
						fieldName = "项目编号:";
					} else if("user_id".equals(fieldName)) { // 2
						fieldName = "客户标识:";
					} else if("user_name".equals(fieldName)) { // 3
						fieldName = "客户姓名:";
					} else if("pay_time".equals(fieldName)) { // 4
						fieldName = "缴费时间:";
					} else if("pay_status".equals(fieldName)) { // 5
						fieldName = "缴费状态:";
					} else if("settle_status".equals(fieldName)) {
						fieldName = "结算状态:";
					} else if("pay_channel".equals(fieldName)) {
						fieldName = "支付渠道:";
					} else if("user_channel_account".equals(fieldName)) {
						fieldName = "客户渠道账号:";
					} else {
						count--;
						continue; // 其他字段不做处理（备用字段1~5、serialVersionUID等）
					}
					// 第一列
					HSSFCell cell0 = row0.createCell(0);// 固定为0
					//设置单元格的样式
					cell0.setCellStyle(style2);
					// 设置标题列的内容
					cell0.setCellValue(fieldName);
					// 第二列
					HSSFCell cell1 = row0.createCell(1);// 固定为1
					// 拼出列的get方法
					String getMethodName = "get" + fieldNameOld.substring(0, 1).toUpperCase() + fieldNameOld.substring(1);
					// 调用get方法
					Method getMethod = c.getDeclaredMethod(getMethodName);
					Object value = getMethod.invoke(ap);
					// 设置内容列的内容
					String v = null;
					if(value != null) {
						v = value.toString();
					}
					// 支付状态,0-未支付,1-支付成功,2-支付失败,3-账单退款,4-支付中
					if("pay_status".equals(fieldNameOld)) {
						if("0".equals(v)) {
							v = "未缴费";
						} else if("1".equals(v)) {
							v = "缴费成功";
						} else if("2".equals(v)) {
							v = "缴费失败";
						} else if("3".equals(v)) {
							v = "账单退款";
						} else if("4".equals(v)) {
							v = "缴费中";
						}
					} else if("settle_status".equals(fieldNameOld)) {// 结算状态,0-未结算,1-结算成功,2-结算失败
						if("0".equals(v)) {
							v = "未结算";
						} else if("1".equals(v)) {
							v = "结算成功";
						} else if("2".equals(v)) {
							v = "结算失败";
						}
					}
					cell1.setCellValue(value == null ? "" : v);
				}
				count += 1;
				titleCount += 11;
			}

			return workbook;
		} catch (SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 下载账单模板
	 * @param sheetName
	 * @return
	 */
	public HSSFWorkbook getWorkbookModel(String sheetName) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 创建一个sheet
			HSSFSheet hssfSheet = workbook.createSheet(sheetName);
			// 创建第一行
			HSSFRow row1 = hssfSheet.createRow(0);
			// 创建第一行标题列
			HSSFCell cell1 = row1.createCell(0);//唯一标识
			HSSFCell cell2 = row1.createCell(1);//姓名
			HSSFCell cell3 = row1.createCell(2);//应缴金额
			HSSFCell cell4 = row1.createCell(3);//电话
			HSSFCell cell5 = row1.createCell(4);//证件号码
			// 设置标题列的内容
			cell1.setCellValue("学号id");
			cell2.setCellValue("姓名s1");
			cell3.setCellValue("应缴金额(元)m");
			cell4.setCellValue("电话s2");
			cell5.setCellValue("身份证号s3");

			// 创建第2行
			HSSFRow row2 = hssfSheet.createRow(1);
			// 创建第2行标题列
			HSSFCell cellTwo1 = row2.createCell(0);
			// 设置标题列的内容
			cellTwo1.setCellValue("本模板中各列名中后缀不可删除,否则无法上传!");

			return workbook;
		} catch (SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 自定义账单模板
	 * @param modelKey
	 * @param sheetName
	 * @param c
	 * @return
	 */
	public HSSFWorkbook getEditWorkbookModel(ModelKey modelKey, Map<String, Integer> positionMap, String sheetName , Class c) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 创建一个sheet
			HSSFSheet hssfSheet = workbook.createSheet(sheetName);
			// 创建第一行
			HSSFRow row0 = hssfSheet.createRow(0);
			// 获取实体类ModelKey的所有字段 13个
			Field[] fields = c.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				// 获取第一行标题列名
				String fieldName = fields[i].getName();
				if("userId".equals(fieldName)) {
					fieldName = modelKey.getUserId() + "id";
					// 创建第一行标题列
					HSSFCell cell0 = row0.createCell(positionMap.get("userIdPosition"));
					// 设置标题列的内容
					cell0.setCellValue(fieldName);
				} else if("itemsMoney".equals(fieldName)) {
					fieldName = modelKey.getItemsMoney() + "m";
					HSSFCell cell0 = row0.createCell(positionMap.get("items_money_position"));
					cell0.setCellValue(fieldName);
				} else if("select1".equals(fieldName) && StringUtils.isNotBlank(modelKey.getSelect1())) {
					fieldName = modelKey.getSelect1() + "s1";
					HSSFCell cell0 = row0.createCell(positionMap.get("position1"));
					cell0.setCellValue(fieldName);
				} else if("select2".equals(fieldName) && StringUtils.isNotBlank(modelKey.getSelect2())) {
					fieldName = modelKey.getSelect2() + "s2";
					HSSFCell cell0 = row0.createCell(positionMap.get("position2"));
					cell0.setCellValue(fieldName);
				} else if("select3".equals(fieldName) && StringUtils.isNotBlank(modelKey.getSelect3())) {
					fieldName = modelKey.getSelect3() + "s3";
					HSSFCell cell0 = row0.createCell(positionMap.get("position3"));
					cell0.setCellValue(fieldName);
				} else if("select4".equals(fieldName) && StringUtils.isNotBlank(modelKey.getSelect4())) {
					fieldName = modelKey.getSelect4() + "s4";
					HSSFCell cell0 = row0.createCell(positionMap.get("position4"));
					cell0.setCellValue(fieldName);
				} else if("select5".equals(fieldName) && StringUtils.isNotBlank(modelKey.getSelect5())) {
					fieldName = modelKey.getSelect5() + "s5";
					HSSFCell cell0 = row0.createCell(positionMap.get("position5"));
					cell0.setCellValue(fieldName);
				} else if("select6".equals(fieldName) && StringUtils.isNotBlank(modelKey.getSelect6())) {
					fieldName = modelKey.getSelect6() + "s6";
					HSSFCell cell0 = row0.createCell(positionMap.get("position6"));
					cell0.setCellValue(fieldName);
				} else if("select7".equals(fieldName) && StringUtils.isNotBlank(modelKey.getSelect7())) {
					fieldName = modelKey.getSelect7() + "s7";
					HSSFCell cell0 = row0.createCell(positionMap.get("position7"));
					cell0.setCellValue(fieldName);
				} else if("select8".equals(fieldName) && StringUtils.isNotBlank(modelKey.getSelect8())) {
					fieldName = modelKey.getSelect8() + "s8";
					HSSFCell cell0 = row0.createCell(positionMap.get("position8"));
					cell0.setCellValue(fieldName);
				} else if("select9".equals(fieldName) && StringUtils.isNotBlank(modelKey.getSelect9())) {
					fieldName = modelKey.getSelect9() + "s9";
					HSSFCell cell0 = row0.createCell(positionMap.get("position9"));
					cell0.setCellValue(fieldName);
				} else if("select10".equals(fieldName) && StringUtils.isNotBlank(modelKey.getSelect10())) {
					fieldName = modelKey.getSelect10() + "s10";
					HSSFCell cell0 = row0.createCell(positionMap.get("position10"));
					cell0.setCellValue(fieldName);
				} else {
					continue; // 其他字段不做处理
				}
			}
            // 创建第2行
            HSSFRow row2 = hssfSheet.createRow(1);
            // 创建第2行标题列
            HSSFCell cell2 = row2.createCell(0);
            // 设置标题列的内容
            cell2.setCellValue("本模板中各列名中后缀不可删除,否则无法上传!");

			return workbook;
		} catch (SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

}
