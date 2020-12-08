package com.zh.oapi_zuul.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zh.oapi_common.config.RedisKeyConfig;
import com.zh.oapi_common.redis.RedissonUtil;
import com.zh.oapi_common.util.RUtil;
import com.zh.oapi_entity.entity.dto.SmsDto;
import org.springframework.stereotype.Component;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SmsUrlFilter extends ZuulFilter {

    @Override
    public String filterType() {
        //类型 前：pre  后：post  转发中：routing  错误：error
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    //核心 过滤处理
    /**
     *  1分钟内短信发送条数不超过：1
     * 	1小时内短信发送条数不超过：5
     * 	1个自然日内短信发送条数不超过：10
     * 	Redis
     * 	*/
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        HttpServletResponse response = requestContext.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        //校验是否在请求短信发送接口
        if (request.getRequestURI().endsWith("provider/sms/sendCode.do")){
            try {
                ServletInputStream is = request.getInputStream();
                StringBuffer buffer = new StringBuffer();
                byte[] data = new byte[1024];
                int len;
                while ((len = is.read(data)) != -1){
                    buffer.append(new String(data,0,len));
                }
                //获取手机号
                SmsDto smsDto = JSON.parseObject(buffer.toString(), SmsDto.class);
                String phone = smsDto.getReceivePhone();
                //校验大小 天 小时 分钟
                if (RedissonUtil.checkKey(RedisKeyConfig.SMS_DAY + phone)){
                    //校验今日有没有超出
                    int c=Integer.parseInt(RedissonUtil.getStr(RedisKeyConfig.SMS_DAY + phone));
                    if(c >= 10){
                        //今日已达上限 拦截
                        requestContext.setSendZuulResponse(false);
                        response.getWriter().println(JSON.toJSONString(RUtil.setERROR("今日短信发送已达上限")));
                    }
                }
                if (RedissonUtil.checkKey(RedisKeyConfig.SMS_HOU + phone)){
                    //校验一小时内有没有超出
                    int c=Integer.parseInt(RedissonUtil.getStr(RedisKeyConfig.SMS_HOU + phone));
                    if(c >= 2){
                        requestContext.setSendZuulResponse(false);
                        response.getWriter().println(JSON.toJSONString(RUtil.setERROR("当前小时短信发送已达上限")));
                    }
                }
                if (RedissonUtil.checkKey(RedisKeyConfig.SMS_MIN + phone)){
                    //校验一分钟内有没有发送
                    requestContext.setSendZuulResponse(false);
                    response.getWriter().println(JSON.toJSONString(RUtil.setERROR("一分钟内只能发一次")));
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
