package com.zh.oapi_userapi.feign;

import com.zh.oapi_common.vo.R;
import com.zh.oapi_entity.entity.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "oapi-userprovider")
public interface UserService {

    @PostMapping("provider/user/save.do")
    R save(@RequestBody UserDto userDto);

    @PostMapping("provider/user/checkName.do")
    R checkName(@RequestBody UserDto userDto);

    @PostMapping("provider/user/checkPhone.do")
    R checkPhone(@RequestBody UserDto userDto);

}
