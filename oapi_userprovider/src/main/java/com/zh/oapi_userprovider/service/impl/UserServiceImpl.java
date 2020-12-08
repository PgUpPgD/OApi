package com.zh.oapi_userprovider.service.impl;

import com.zh.oapi_common.myenum.UserLogType;
import com.zh.oapi_common.util.RUtil;
import com.zh.oapi_common.vo.R;
import com.zh.oapi_entity.entity.dto.UserDto;
import com.zh.oapi_entity.entity.user.User;
import com.zh.oapi_entity.entity.user.UserLog;
import com.zh.oapi_userprovider.dao.UserDao;
import com.zh.oapi_userprovider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public R save(UserDto userDto) {
        //实现用户的新增
        User user=new User();
        user.setName(userDto.getUsername());
        user.setPass(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        int i = userDao.insert(user);
        if(i > 0){
            //记录日志
            UserLog log=new UserLog();
            log.setInfo("用户实现了注册");
            log.setUid(user.getId());
            System.out.println("11111111");
            System.out.println(log.getUid());
            log.setType(UserLogType.Register.getValue());
            int i1 = userDao.add(log);
            return RUtil.setOK(i1);
        }
        return RUtil.setERROR();
    }

    @Override
    public R checkPhone(String pone) {
        User user = userDao.selectOne(pone);
        if (StringUtils.isEmpty(user)){
            return RUtil.setOK();
        }
        return RUtil.setERROR("手机号重复");
    }

    @Override
    public R checkName(String name) {
        User user = userDao.selectOne(name);
        if (StringUtils.isEmpty(user)){
            return RUtil.setOK();
        }
        return RUtil.setERROR("您的昵称已重复");
    }

}
