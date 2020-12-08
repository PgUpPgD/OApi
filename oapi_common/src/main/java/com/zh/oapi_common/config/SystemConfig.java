package com.zh.oapi_common.config;

public class SystemConfig {
    //登录令牌的请求消息头    名称
    public static final String USER_TOKEN = "on:usertoken";

    //JWT 秘钥
    public static final String JWT_KEY = "openapi:nb";

    //授权码的秘钥
    public static final String CODE_KEY="ZJhpApFU4SGuPKyr1qTFYg==";

    //令牌的默认失效时间
    public static final int TOKEN_TIME=10;  //10分钟
}
