package com.zh.oapi_entity.entity.dto;

import lombok.Data;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-29 14:05
 */
@Data
public class SmsCodeDto {
    private String phone;
    private int code;
}