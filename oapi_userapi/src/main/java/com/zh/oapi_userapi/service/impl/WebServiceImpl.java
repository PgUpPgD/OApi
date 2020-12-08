package com.zh.oapi_userapi.service.impl;

import com.zh.oapi_common.vo.R;
import com.zh.oapi_entity.entity.dto.UserDto;
import com.zh.oapi_userapi.feign.UserService;
import com.zh.oapi_userapi.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebServiceImpl implements WebService {
    @Autowired
    private UserService userService;

    @Override
    public R save(UserDto userDto) {
        return userService.save(userDto);
    }

    @Override
    public R checkName(UserDto userDto) {
        return userService.checkName(userDto);
    }

    @Override
    public R checkPhone(UserDto userDto) {
        return userService.checkPhone(userDto);
    }

}
