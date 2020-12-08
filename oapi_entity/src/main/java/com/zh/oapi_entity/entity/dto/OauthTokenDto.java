package com.zh.oapi_entity.entity.dto;

import lombok.Data;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-11-06 10:22
 */
@Data
public class OauthTokenDto {
    private String code;//授权码
    private String applyKey;  //key
    private String applySecret;    //秘钥
    private String token;//令牌信息
    private String recUrl;//回调地址

}
