package com.zh.oapi_entity.entity.dto;

import lombok.Data;

/**
 * @program: OpenApi
 * @description: 授权码--用户信息和应用信息
 * @author: Feri
 * @create: 2019-11-06 14:46
 */
@Data
public class LoginDto {
    private String name;
    private String password;
    private String key;
}
