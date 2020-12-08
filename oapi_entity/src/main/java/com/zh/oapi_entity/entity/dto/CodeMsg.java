package com.zh.oapi_entity.entity.dto;

import lombok.Data;

/**
 * @program: OpenApi
 * @description: 消息中间件的消息对象
 * @author: Feri
 * @create: 2019-11-07 10:25
 */
@Data
public class CodeMsg {
    private String url;
    private String code;
}
