package com.zh.oapi_entity.entity.user;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Data
public class UserLog implements Serializable {

    private BigInteger id;
    private int uid;
    private String info;
    private int type;
    private Date ctime;

}
