package com.zh.oapi_entity.entity.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private Integer id;
    private String  name;
    private String  pass;
    private String  phone;

}
