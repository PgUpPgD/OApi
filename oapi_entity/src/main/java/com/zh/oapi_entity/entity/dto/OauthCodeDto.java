package com.zh.oapi_entity.entity.dto;

import lombok.Data;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-11-07 09:33
 */
@Data
public class OauthCodeDto {
    private Integer uid;
    private String phone;//手机号
    private long times;//单位毫秒
    private String appKey;//应用的唯一标记
}
