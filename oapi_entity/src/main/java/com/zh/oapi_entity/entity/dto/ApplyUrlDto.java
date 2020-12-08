package com.zh.oapi_entity.entity.dto;

import lombok.Data;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-11-07 09:53
 */
@Data
public class ApplyUrlDto {
    private Integer uid;
    private String apply_key;
    private String redirect_url;
}
