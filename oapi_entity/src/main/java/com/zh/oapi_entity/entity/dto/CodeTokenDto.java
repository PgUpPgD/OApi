package com.zh.oapi_entity.entity.dto;

import lombok.Data;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-11-06 14:49
 */
@Data
public class CodeTokenDto {
    private String code; //授权码
    private String key; //门户网站--应用的key
    private String secret;//门户网站--应用的秘钥

}
