package com.zh.oapi_entity.entity.user;


import lombok.Data;

import java.util.Date;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-31 14:12
 */
@Data
public class UserRealLog {
    private int id;
    private int urid;
    private String info;
    private int result;
    private Date ctime;
}
