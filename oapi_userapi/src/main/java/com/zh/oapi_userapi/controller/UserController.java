package com.zh.oapi_userapi.controller;

import com.zh.oapi_common.vo.R;
import com.zh.oapi_entity.entity.dto.UserDto;
import com.zh.oapi_userapi.service.WebService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "新用户注册",tags = "新用户注册")
public class UserController {

    @Autowired
    private WebService webService;

    @PostMapping("api/user/save.do")
    @ApiOperation(value = "实现新用户的注册",notes = "实现新用户的注册")
    public R save(@RequestBody UserDto userDto){
        return webService.save(userDto);
    }

    @PostMapping("provider/api/checkName.do")
    public R checkName(@RequestBody UserDto userDto){
        return webService.checkName(userDto);
    }

    @PostMapping("provider/api/checkPhone.do")
    public R checkPhone(@RequestBody UserDto userDto){
        return webService.checkPhone(userDto);
    }
}
