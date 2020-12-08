package com.zh.oapi_msgprovider.service;

import com.zh.oapi_common.vo.R;
import com.zh.oapi_entity.entity.dto.SmsCodeDto;
import com.zh.oapi_entity.entity.dto.SmsDto;

public interface SmsService {
    //发送短信
    R sendMsg(SmsDto smsDto);
    //查询某个手机号的短信记录
    R querySms(String phone);
    //查询所有的短信信息 分页
    R queryPage(int page,int size);
    //校验验证码
    R checkCode(SmsCodeDto codeDto);
}
