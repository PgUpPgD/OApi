package com.zh.oapi_oauthprovider.service;


import com.zh.oapi_common.vo.R;
import com.zh.oapi_entity.entity.dto.CodeTokenDto;
import com.zh.oapi_entity.entity.dto.LoginDto;

public interface OauthCodeService {
    //生成授权码
    R createCode(LoginDto dto);
    //校验授权码
    R checkCode(String code);
    //生成令牌
    R createToken(CodeTokenDto tokenDto);
    //刷新令牌
    R refreshToken(String token);  //空闲时间
}
