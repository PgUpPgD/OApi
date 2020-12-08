package com.zh.oapi_userapi.service;

import com.zh.oapi_common.vo.R;
import com.zh.oapi_entity.entity.dto.UserDto;

public interface WebService {
    R save(UserDto userDto);
    R checkName(UserDto userDto);
    R checkPhone(UserDto userDto);
}
