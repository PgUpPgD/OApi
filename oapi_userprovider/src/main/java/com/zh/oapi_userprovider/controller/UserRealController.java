package com.zh.oapi_userprovider.controller;

import com.zh.oapi_common.vo.R;
import com.zh.oapi_entity.entity.dto.UserRealDto;
import com.zh.oapi_userprovider.service.UserRealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-31 14:30
 */
@RestController
public class UserRealController {
    @Autowired
    private UserRealService realService;

    //查询
    @GetMapping("provider/user/queryreal.do")
    public R query(HttpServletRequest request){
        return realService.queryReal(request);
    }
    //新增
    @PostMapping("provider/user/savereal.do")
    public R save(@RequestBody UserRealDto realDto, HttpServletRequest request){
        return realService.save(realDto, request);
    }
}
