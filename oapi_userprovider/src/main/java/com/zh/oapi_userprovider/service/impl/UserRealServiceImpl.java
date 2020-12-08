package com.zh.oapi_userprovider.service.impl;

import com.zh.oapi_common.util.RUtil;
import com.zh.oapi_common.vo.R;
import com.zh.oapi_entity.entity.dto.UserRealDto;
import com.zh.oapi_entity.entity.user.UserReal;
import com.zh.oapi_entity.entity.user.UserRealLog;
import com.zh.oapi_userprovider.config.UserTokenConfig;
import com.zh.oapi_userprovider.dao.UserRealDao;
import com.zh.oapi_userprovider.service.UserRealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserRealServiceImpl implements UserRealService {

    @Autowired
    private UserRealDao realDao;

    @Override
    @Transactional
    public R save(UserRealDto realDto, HttpServletRequest request) {
        //1.获取登录用户对象
        int uid = UserTokenConfig.parseUser(request);
        //2.组装对象
        UserReal real = new UserReal();
        real.setBackurl(realDto.getBurl());
        real.setCardno(realDto.getNo());
        real.setFronturl(realDto.getFurl());
        real.setHandurl(realDto.getHurl());
        real.setRealname(realDto.getRname());
        real.setUid(uid);

        //3.执行新增
        if (realDao.insert(real)>0){
            //记录日志
            UserRealLog log = new UserRealLog();
            log.setUrid(real.getId());
            log.setResult(1);
            log.setInfo("第一次提交实名认证的信息");
            return RUtil.setOK();
        }
        return RUtil.setERROR("实名资料提交失败");
    }

    @Override
    public R queryReal(HttpServletRequest request) {
        //1、获取登录用户信息
        int uid= UserTokenConfig.parseUser(request);
        //2、获取对象
        UserReal real=realDao.selectByUid(uid);
        if(real==null){
            return RUtil.setERROR("暂无实名信息，请认证");
        }else {
            return RUtil.setOK(real);
        }
    }
}
