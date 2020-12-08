package com.zh.oapi_common.myenum;

public enum  UserLogType {   //用户日志记录
    Register(1),Login(2);
    private int value;
    UserLogType(int v){
        value=v;
    }
    public int getValue(){
        return value;
    }
}
