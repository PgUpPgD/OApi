package com.zh.oapi_entity.entity.msg;


import lombok.Data;

import java.util.Date;

@Data     //记录发送了短信的实体类
public class Sms {
    private Integer id;
    private String recphone;        //要被发送验证码的手机号
    private String msg;             //发送的验证码信息
    private Date ctime;             //发送的时间
    private int flag; //1 未发送 2 发送成功 3 发送失败
}
