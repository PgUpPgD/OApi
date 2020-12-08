package com.zh.oapi_userprovider.controller;

import com.zh.oapi_common.vo.R;
import com.zh.oapi_entity.entity.dto.UserDto;
import com.zh.oapi_userprovider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    
    @PostMapping("provider/user/save.do")
    public R save(@RequestBody UserDto userDto){
        return userService.save(userDto);
    }

    @PostMapping("provider/user/checkName.do")
    public R checkName(@RequestBody UserDto userDto){
        return userService.checkName(userDto.getUsername());
    }

    @PostMapping("provider/user/checkPhone.do")
    public R checkPhone(@RequestBody UserDto userDto){
        return userService.checkPhone(userDto.getPhone());
    }

}
