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
public class OauthCode {
    private Integer id;
    private String code;
    private String applyKey;
    private String redirectUrl;
    private String status;
    private Date ctime;
}
