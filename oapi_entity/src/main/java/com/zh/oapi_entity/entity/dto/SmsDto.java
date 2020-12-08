package com.zh.oapi_entity.entity.dto;

import lombok.Data;

@Data
public class SmsDto {
    private String receivePhone;//接收手机号
    private String msg;//短信内容
    private int type;  //模板类型 1 验证码 2 推广信息 3 找回密码 4 异地登录
}
