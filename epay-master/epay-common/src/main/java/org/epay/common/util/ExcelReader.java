package org.epay.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader<T> {

	public List<T> read(String excelPath, Class c) throws Exception {
		List<T> dataList = new ArrayList<T>();
		File excelFile = new File(excelPath);
		FileInputStream is = new FileInputStream(excelFile);
		Workbook workbook = WorkbookFactory.create(is);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.rowIterator();
		String[] propertyNames = null;
		while (rows.hasNext()) {
			Row row = (Row) rows.next();
			if (row.getRowNum() == 0) {
				// 把第一行标题行上的每一个标题拿到，作为javabean的属性名
				int count = row.getPhysicalNumberOfCells();
				propertyNames = new String[count];
				for (int i = 0; i < count; i++) {
					Cell cell = row.getCell(i);
					propertyNames[i] = cell.getStringCellValue();
				}
				continue;
			}
			T obj = (T) c.newInstance();
			// 通过反射机制调用set方法给book对象的属性赋值
			for (int i = 0; i < propertyNames.length; i++) {
				Cell cell = row.getCell(i);
				if (cell != null) {
					String propertyValue = cell.getStringCellValue();
					String setMethodName = "set" + propertyNames[i].substring(0, 1).toUpperCase()
							+ propertyNames[i].substring(1);
					Method setMethod = c.getDeclaredMethod(setMethodName, String.class);
					setMethod.invoke(obj, propertyValue);
				}
			}
			dataList.add(obj);
		}
		return dataList;
	}
}
