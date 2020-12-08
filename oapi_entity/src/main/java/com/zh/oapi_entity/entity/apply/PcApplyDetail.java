package com.zh.oapi_entity.entity.apply;

import lombok.Data;

import java.util.Date;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-11-05 15:00
 */
@Data   //应用的详细信息
public class PcApplyDetail {
    private Integer id;
    private Integer aid;
    private String pcurl;
    private String redirect_url;
    private String provider;
    private String recordcode;//备案地址
    private String img;//
    private Date ctime;
}
