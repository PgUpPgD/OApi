package com.zh.oapi_common.config;

public class RedisKeyConfig {
    //短信验证码
    public static final String SMS_CODE = "sms:code:"; //key的前缀

    //短信发送频率
    public static final String SMS_MIN = "sms:pl:min:";  //设置一分钟1次
    public static final String SMS_HOU = "sms:pl:hou:";  //设置一小时3次
    public static final String SMS_DAY = "sms:pl:day:";  //设置一天10次
}
