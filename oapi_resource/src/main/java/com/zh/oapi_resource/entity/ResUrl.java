package com.zh.oapi_resource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_resurl")  //文件上传
public class ResUrl {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String objName;
    private String objUrl;
    private String proname; //项目名称
    private int flagMonths;//有效期 单位月
    private Date ctime; //创建日期

}
