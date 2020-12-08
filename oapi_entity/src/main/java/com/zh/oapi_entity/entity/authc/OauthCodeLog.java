package com.zh.oapi_entity.entity.authc;

import lombok.Data;

import java.util.Date;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-11-06 10:22
 */
@Data
public class OauthCodeLog {
    private Integer id;
    private String no;
    private String info;
    private Date ctime;
    private String addr;  //地址 记录ip等
}
