package com.zh.oapi_resource.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-30 14:55
 */
public class TimeUtil {
    public static Date getDateByYear(int years){
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.YEAR,years);
        return calendar.getTime();
    }
    public static Date getDateByMonth(int months){
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.MONTH,months);
        return calendar.getTime();
    }
}
