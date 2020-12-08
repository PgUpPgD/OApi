package com.zh.oapi_entity.entity.user;

import lombok.Data;

import java.util.Date;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-31 14:12
 */
@Data
public class UserReal {
    private int id;
    private int uid;
    private String realname;
    private String cardno;
    private String fronturl;
    private String backurl;
    private String handurl;
    private Date ctime;
    private int flag;
}
