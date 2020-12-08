package com.zh.oapi_entity.entity.apply;

import lombok.Data;

import java.util.Date;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-11-06 15:46
 */
@Data  //会员应用信息
public class Apply {
    private Integer id;
    private String firstType;//大类型
    private String secondType;//小类型
    private String name;
    private String info;
    private String applyKey;   //应用的key
    private String applySecret;    //秘钥
    private int flag;//审核状态 1审核中 2审核通过 3审核拒绝
    private Date ctime;
    private int type;//1 pc 2app 应用的类型
    private int uid;
}
