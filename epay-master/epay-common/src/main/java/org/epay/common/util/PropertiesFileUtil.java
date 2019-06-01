package org.epay.common.util;

import java.util.ResourceBundle;

/**
 * 名称: 属性文件工具类
 * 作者: HappyDan
 * 版本: V1.0
 */
public class PropertiesFileUtil {

	private ResourceBundle rb = null;

	public PropertiesFileUtil(String bundleFile) {
		rb = ResourceBundle.getBundle(bundleFile);
	}

	public String getValue(String key) {
		return rb.getString(key);
	}

	public static void main(String[] args) {


	}
}
