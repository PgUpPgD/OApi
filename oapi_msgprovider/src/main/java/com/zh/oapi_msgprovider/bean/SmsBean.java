package com.zh.oapi_msgprovider.bean;

import lombok.Data;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-29 10:25
 */
@Data
public class SmsBean {      //oss系统发送短信后返回的信息
    private String Message;
    private String RequestId;
    private String BizId;
    private String Code;
}
