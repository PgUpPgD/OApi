package com.zh.oapi_userprovider.service;

import com.zh.oapi_common.vo.R;
import com.zh.oapi_entity.entity.dto.UserDto;

public interface UserService {
    R save(UserDto userDto);
    R checkPhone(String pone);
    R checkName(String name);

}
