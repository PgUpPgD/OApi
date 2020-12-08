package com.zh.oapi_entity.entity.dto;

import lombok.Data;

@Data    //隐藏数据库具体的实现细节
public class UserRealDto {
    private String rname;
    private String no;
    private String furl;
    private String burl;
    private String hurl;

}
