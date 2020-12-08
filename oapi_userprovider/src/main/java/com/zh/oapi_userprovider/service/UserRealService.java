package com.zh.oapi_userprovider.service;

import com.zh.oapi_common.vo.R;
import com.zh.oapi_entity.entity.dto.UserRealDto;

import javax.servlet.http.HttpServletRequest;

public interface UserRealService {

    //新增实名认证
    R save(UserRealDto realDto, HttpServletRequest request);
    //查询  用户的实名认证
    R queryReal(HttpServletRequest request);
}
