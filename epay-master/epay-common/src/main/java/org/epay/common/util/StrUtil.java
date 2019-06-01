package org.epay.common.util;

/**
 * 名称: 字符串工具类
 * 作者: HappyDan
 * 版本: V1.0
 */
public class StrUtil {

    public static String toString(Object obj) {
        return obj == null?"":obj.toString();
    }

    public static String toString(Object obj, String nullStr) {
        return obj == null?nullStr:obj.toString();
    }

}
