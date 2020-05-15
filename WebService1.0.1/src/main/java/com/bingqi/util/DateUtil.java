package com.bingqi.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 根据时间字符串获取年月日时分秒,比如2019-1-21 13:06:06
 * written by 张弓
 */
public class DateUtil {
    private static Date parseTimeString2Date(String timeString) {
        if ((timeString == null) || (timeString.equals(""))) {
            return null;
        }
        Date date = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = new Date(dateFormat.parse(timeString).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private static String convertDate2String(Date date, String pattern) {
        if (date == null)
            return null;
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public int getYear(String timeString) {
        String timeStr = convertDate2String(parseTimeString2Date(timeString), "yyyy-MM-dd HH:mm:ss");
        return Integer.parseInt(timeStr.substring(0, 4));
    }

    public int getMonth(String timeString) {
        String timeStr = convertDate2String(parseTimeString2Date(timeString), "yyyy-MM-dd HH:mm:ss");
        return Integer.parseInt(timeStr.substring(5, 7));
    }

    public int getDay(String timeString) {
        String timeStr = convertDate2String(parseTimeString2Date(timeString), "yyyy-MM-dd HH:mm:ss");
        return Integer.parseInt(timeStr.substring(8, 10));
    }

    public int getHour(String timeString) {
        String timeStr = convertDate2String(parseTimeString2Date(timeString), "yyyy-MM-dd HH:mm:ss");
        return Integer.parseInt(timeStr.substring(11, 13));
    }

    public int getMinute(String timeString) {
        String timeStr = convertDate2String(parseTimeString2Date(timeString), "yyyy-MM-dd HH:mm:ss");
        return Integer.parseInt(timeStr.substring(14, 16));
    }

    public int getSecond(String timeString) {
        String timeStr = convertDate2String(parseTimeString2Date(timeString), "yyyy-MM-dd HH:mm:ss");
        return Integer.parseInt(timeStr.substring(17, 19));
    }
}
