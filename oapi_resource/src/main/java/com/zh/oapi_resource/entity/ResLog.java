package com.zh.oapi_resource.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_reslog")  //日志记录
public class ResLog {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String info;
    private Date ctime;
    private String ip;
    private int rid; //资源地址的主键id 可能存在，也可能不存在
}
