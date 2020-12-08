package com.zh.oapi_oauthprovider.service.impl;

import com.alibaba.fastjson.JSON;
import com.zh.oapi_common.util.CodeUtil;
import com.zh.oapi_common.util.HttpUtil;
import com.zh.oapi_common.util.JwtUtil;
import com.zh.oapi_common.util.RUtil;
import com.zh.oapi_common.vo.R;
import com.zh.oapi_entity.entity.authc.OauthCode;
import com.zh.oapi_entity.entity.dto.*;
import com.zh.oapi_entity.entity.user.User;
import com.zh.oapi_oauthprovider.dao.ApplyDao;
import com.zh.oapi_oauthprovider.dao.OauthCodeDao;
import com.zh.oapi_oauthprovider.dao.UserDao;
import com.zh.oapi_oauthprovider.service.OauthCodeService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-11-06 14:54
 */
@Service
public class OauthCodeServiceImpl implements OauthCodeService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ApplyDao applyDao;
    @Autowired
    private OauthCodeDao codeDao;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public R createCode(LoginDto dto) {
        //1、校验账号信息
        User user = userDao.selectByName(dto.getName());
        if(user!=null) {
            //账号信息是否正确
            if (user.getPass().equals(dto.getPassword())) {

                //2、生成授权码
                OauthCodeDto code = new OauthCodeDto();
                code.setUid(user.getId());
                code.setPhone(user.getPhone());
                code.setTimes(System.currentTimeMillis());
                code.setAppKey(dto.getKey());
                String c = CodeUtil.createCode(JSON.toJSONString(code));

                //3、Shiro认证，获取token信息
//                Subject subject = SecurityUtils.getSubject();
//                UsernamePasswordToken token = new UsernamePasswordToken(dto.getName(), dto.getPassword());
//                subject.login(token);

                //4、回调----将授权码写出
                // key为该app的(类似)id，获取应用的详细信息，返回该应用的路径
                ApplyUrlDto applyUrlDto = applyDao.selectKey(dto.getKey());
                String url = applyUrlDto.getRedirect_url();

                //5、记录授权码
                OauthCode oauthCode = new OauthCode();
                oauthCode.setApplyKey(dto.getKey());
                oauthCode.setCode(c);
                oauthCode.setRedirectUrl(url);
                oauthCode.setStatus("");
                oauthCode.setCtime(new Date());
                codeDao.insert(oauthCode);

                //6、java中请求外部接口---RabbitMQ(异步)发送消息--RabbitMQ
                CodeMsg codeMsg=new CodeMsg();
                codeMsg.setCode(c);
                codeMsg.setUrl(url);  //回调路径
                //通过消息队列发送授权码给‘百度’ （用户用第三方qq登录百度）
//                rabbitTemplate.convertAndSend(RabbitMQConfig.qname,JSON.toJSONString(codeMsg));
                //或者 直接通过通讯协议以回调路径发授权码到对方应用
                String s = HttpUtil.postSend(url, c);
                //7、成功
                return RUtil.setOK(codeMsg);
            }
        }
        return RUtil.setERROR("用户或密码信息不正确");
    }

    @Override
    public R checkCode(String code) {
        return null;
    }

    @Override           //生成令牌
    public R createToken(CodeTokenDto tokenDto) {
        //1、验证授权码是否正确
        OauthCode oauthCode=codeDao.selectByKey(tokenDto.getCode(),tokenDto.getKey());
        if(oauthCode!=null){
            //正确
            //生成令牌       令牌 = 授权码 + 秘钥 + 应用的id；
            String token= JwtUtil.createJWT(JSON.toJSONString(tokenDto));
            //组装信息 ：授权码、应用、回调、令牌
            OauthTokenDto oauthTokenDto=new OauthTokenDto();
            oauthTokenDto.setApplyKey(tokenDto.getKey());      //key
            oauthTokenDto.setApplySecret(tokenDto.getSecret());    //秘钥
            oauthTokenDto.setCode(tokenDto.getCode());      //授权码
            oauthTokenDto.setRecUrl(oauthCode.getRedirectUrl());      //回调路径
            return RUtil.setOK(oauthTokenDto);
        }else {
            return RUtil.setERROR("授权码和应用不一致");
        }
    }

    @Override
    public R refreshToken(String token) {
        return null;
    }
}
