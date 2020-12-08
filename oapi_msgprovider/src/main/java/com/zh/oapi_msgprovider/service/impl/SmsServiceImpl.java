package com.zh.oapi_msgprovider.service.impl;

import com.alibaba.fastjson.JSON;
import com.zh.oapi_common.config.RedisKeyConfig;
import com.zh.oapi_common.myenum.SmsTemCode;
import com.zh.oapi_common.redis.RedissonUtil;
import com.zh.oapi_common.util.RUtil;
import com.zh.oapi_common.util.RandomNumUtil;
import com.zh.oapi_common.vo.R;
import com.zh.oapi_entity.entity.dto.SmsCodeDto;
import com.zh.oapi_entity.entity.dto.SmsDto;
import com.zh.oapi_entity.entity.msg.Sms;
import com.zh.oapi_msgprovider.bean.SmsBean;
import com.zh.oapi_msgprovider.dao.SmsDao;
import com.zh.oapi_msgprovider.service.SmsService;
import com.zh.oapi_msgprovider.util.AliSmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SmsServiceImpl implements SmsService {
    @Autowired
    private SmsDao smsDao;

    @Override
    public R sendMsg(SmsDto smsDto) {
        //验证码发送记录
        Sms sms = new Sms();
        sms.setFlag(1);
        sms.setRecphone(smsDto.getReceivePhone());
        //调用发送方法
        if (smsDto.getType() == 1){
            //短信验证码
            int code = 0;
            //验证码未失效
            if (RedissonUtil.checkKey(RedisKeyConfig.SMS_CODE + smsDto.getReceivePhone())){
                code = Integer.parseInt(RedissonUtil.getStr(RedisKeyConfig.SMS_CODE + smsDto.getReceivePhone()));
                sms.setMsg("重新发送短信验证码：" + code);
            }else {
                //生成验证码
                code= RandomNumUtil.createNum(6);
                sms.setMsg("发送短信验证码：" + code);
            }
            String res = AliSmsUtil.sendSms(SmsTemCode.YanZhengMa.getCode(),smsDto.getReceivePhone(),"{\"code\":\""+code+"\"}");
            if (StringUtils.isEmpty(res)){
                return RUtil.setOK("短信发送失败，但返回成功，用户没收到会再次点击发送");
            }
            SmsBean bean= JSON.parseObject(res,SmsBean.class);
            if (bean.getCode().equals("OK")){
                //发送成功
                sms.setFlag(2);
                //存储验证码---Redis
                RedissonUtil.setStr(RedisKeyConfig.SMS_CODE + smsDto.getReceivePhone(),code + "",600);

                if (RedissonUtil.checkKey(RedisKeyConfig.SMS_DAY + smsDto.getReceivePhone())){
                    int i = Integer.parseInt(RedissonUtil.getStr(RedisKeyConfig.SMS_DAY + smsDto.getReceivePhone()));
                    i += 1;
                    RedissonUtil.setStr(RedisKeyConfig.SMS_DAY + smsDto.getReceivePhone(), i + "",86400);
                }else {
                    RedissonUtil.setStr(RedisKeyConfig.SMS_DAY + smsDto.getReceivePhone(), "1",86400);
                }
                if (RedissonUtil.checkKey(RedisKeyConfig.SMS_HOU + smsDto.getReceivePhone())){
                    int i = Integer.parseInt(RedissonUtil.getStr(RedisKeyConfig.SMS_HOU + smsDto.getReceivePhone()));
                    i += 1;
                    RedissonUtil.setStr(RedisKeyConfig.SMS_HOU + smsDto.getReceivePhone(), i + "",3600);
                }else {
                    RedissonUtil.setStr(RedisKeyConfig.SMS_HOU + smsDto.getReceivePhone(), "1",3600);
                }
                RedissonUtil.setStr(RedisKeyConfig.SMS_MIN + smsDto.getReceivePhone(), "1",60);
                return RUtil.setOK();
            }else {
                sms.setFlag(3);
                return RUtil.setERROR(bean.getMessage());
            }
        }else if (smsDto.getType() == 2){
            // 其他类型
        }
        //记录数据到数据库
        smsDao.insert(sms);
        return RUtil.setERROR();
    }

    @Override
    public R querySms(String phone) {
        Sms sms = smsDao.selectOne(phone);
        if (StringUtils.isEmpty(sms)){
            return RUtil.setERROR();
        }
        return RUtil.setOK(sms);
    }

    @Override
    public R queryPage(int page, int size) {
        return null;
    }

    @Override
    public R checkCode(SmsCodeDto codeDto) {
        return null;
    }
}
