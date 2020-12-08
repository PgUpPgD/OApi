package com.zh.oapi_entity.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class GoodsTypeDto {
    private int id;
    private String name;
    private int level;
    private List<GoodsTypeBeanDto> childs;
}
