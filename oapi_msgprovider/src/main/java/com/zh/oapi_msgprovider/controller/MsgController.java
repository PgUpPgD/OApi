package com.zh.oapi_msgprovider.controller;

import com.zh.oapi_common.vo.R;
import com.zh.oapi_entity.entity.dto.SmsCodeDto;
import com.zh.oapi_entity.entity.dto.SmsDto;
import com.zh.oapi_msgprovider.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgController {
    @Autowired
    private SmsService smsService;

    //发送
    @PostMapping("provider/sms/sendCode.do")
    public R sendSms(@RequestBody SmsDto dto){
        return smsService.sendMsg(dto);
    }

    //查找
    @PostMapping("provider/sms/findSms.do")
    public R senfindSms(@RequestBody SmsCodeDto dto){
        return smsService.querySms(dto.getPhone());
    }

}
