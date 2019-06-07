package org.epay.mgr.utils;

public class DateUtil {

    public static void main(String[] args) {
        String s = formatDate("20190602051558");
        System.out.println(s);
    }

    /**
     * 处理时间格式20190602051558---->2019年06月02日 05:15:58
     */
    public static String formatDate(String dateStr) {
        String year = dateStr.substring(0, 4);
        String month = dateStr.substring(4, 6);
        String day = dateStr.substring(6, 8);
        String hour = dateStr.substring(8, 10);
        String minus = dateStr.substring(10, 12);
        String second = dateStr.substring(12);
        return year + "年" + month + "月" + day + "日 " + hour + ":" + minus + ":" + second;
    }

}
