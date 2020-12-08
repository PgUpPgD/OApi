package com.zh.oapi_common.myenum;

public enum SmsTemCode {
    YanZhengMa("SMS_176528610");  //验证码模板类型
    private String code;
    SmsTemCode(String c){
        code=c;
    }
    public String getCode(){
        return code;
    }
}
