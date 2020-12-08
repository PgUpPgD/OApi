package com.zh.oapi_common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-11-07 15:07
 */
public class DateUtil {    //日历模式
    //获取指定分钟之后
    public static Date addTime(int minute){
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.MINUTE,minute);
        return calendar.getTime();

    }

    public static String format(Date date, String str){
        return new SimpleDateFormat(str).format(date);
    }

}
