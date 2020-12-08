package com.zh.oapi_userprovider.config;


import com.alibaba.fastjson.JSON;
import com.zh.oapi_common.config.SystemConfig;
import com.zh.oapi_common.util.JwtUtil;
import com.zh.oapi_entity.entity.dto.UserTokenDto;

import javax.servlet.http.HttpServletRequest;

public class UserTokenConfig {          //解析令牌
    //登录的状态：
    // 1、无状态 指登录的令牌在服务器没有存储 每次都是通过解析令牌获取用户信息  服务器压力小
    //2、有状态  指的是登录的令牌存储在服务器 每一次通过服务器获取用户信息  Redis:key--令牌 值--用户信息
    public static int parseUser(HttpServletRequest request){
        //1.获取令牌
        String token = request.getHeader(SystemConfig.USER_TOKEN);
        //2.从令牌获取用户对象
        UserTokenDto dto = JSON.parseObject(JwtUtil.parseJWT(token), UserTokenDto.class);
        //3.返回内容
        if (dto != null){
            return dto.getUid();
        }else {
            return -1;
        }
    }
}












