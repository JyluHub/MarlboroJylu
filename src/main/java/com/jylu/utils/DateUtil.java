package com.jylu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * ClassName: DateUtil <br/>
 * Description: 日期工具类 <br/>
 * Date: 17-3-23 下午9:32 <br/>
 * Company: Jylu <br/>
 *
 * @author lujiayun(1170798780@qq.com) <br/>
 * @version 1.0 <br/>
 */
public class DateUtil {

    private DateUtil(){}

    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 字符串转日期
     * @param dateString 字符串
     * @return 日期
     */
    public static Date String2Date(String dateString){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期转字符串
     * @param date 日期
     * @return String
     */
    public static String Date2String(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN, Locale.ENGLISH);
        return dateFormat.format(date);
    }
}