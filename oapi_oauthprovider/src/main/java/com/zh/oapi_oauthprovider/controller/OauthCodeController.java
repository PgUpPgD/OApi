package com.zh.oapi_oauthprovider.controller;

import com.zh.oapi_common.vo.R;
import com.zh.oapi_entity.entity.dto.CodeMsg;
import com.zh.oapi_entity.entity.dto.CodeTokenDto;
import com.zh.oapi_entity.entity.dto.LoginDto;
import com.zh.oapi_entity.entity.dto.OauthTokenDto;
import com.zh.oapi_oauthprovider.service.OauthCodeService;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.request.OAuthRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-11-07 14:01
 */
@Controller
public class OauthCodeController {
    @Autowired
    private OauthCodeService codeService;

//    //1、授权方法-- 传统实现 借助Oauth
//    @PostMapping("/api/oauth/authorize.do")
//    public Object oauth(Model model, HttpServletRequest request) throws OAuthProblemException, OAuthSystemException, URISyntaxException {
//        //1、创建授权请求对象
//        OAuthRequest oAuthAuthzRequest=new OAuthAuthzRequest(request);
//
//        //2、校验应用是否存在
//        if(oAuthAuthzRequest.getClientId()==null ){  //key
//            OAuthResponse response=OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).setError(OAuthError.TokenResponse.INVALID_CLIENT).
//                    setErrorDescription("无效的应用id").buildBodyMessage();
//            return new ResponseEntity<>(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
//        }
//        //3、校验是否登录
//
//        Subject subject= SecurityUtils.getSubject();
//        if(!subject.isAuthenticated()){
//            //登录成功 授权码
//            R r=codeService.createCode(null);
//            CodeMsg codeMsg=(CodeMsg)r.getData();
//            //获取响应对象
//            OAuthASResponse.OAuthAuthorizationResponseBuilder builder=OAuthASResponse
//                    .authorizationResponse(request, HttpServletResponse.SC_FOUND);
//            //设置信息--
//            //设置授权码
//            builder.setCode(codeMsg.getCode());
//            //设置重定向地址  并获取响应对象
//            OAuthResponse oAuthResponse=builder.location(codeMsg.getUrl()).buildBodyMessage();
//            HttpHeaders headers=new HttpHeaders();
//            headers.setLocation(new URI(codeMsg.getUrl()));
//            return new ResponseEntity<>(oAuthResponse,headers, HttpStatus.valueOf(oAuthResponse.getResponseStatus()));
//        }else {
//            //没有登录
//            //跳转到登录页面
//            model.addAttribute("app_key",oAuthAuthzRequest.getClientId());
//            return new ModelAndView("login.html");
//        }
//    }
    //登录获取授权码
    @PostMapping("/api/oauth/createCode.do")
    public Object login(@RequestBody LoginDto loginDto, Model model, HttpServletRequest request) throws OAuthProblemException, OAuthSystemException, URISyntaxException {
        OAuthRequest oAuthRequest=new OAuthAuthzRequest(request);
        R r=codeService.createCode(loginDto);
        if(r.getCode()==0){
            CodeMsg codeMsg=(CodeMsg)r.getData();
            //授权码、应用信息
            OAuthResponse response=OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND)
                    .setCode(codeMsg.getCode()).location("/api/oauth/createToken.do").buildBodyMessage();
            HttpHeaders headers=new HttpHeaders();
            headers.setLocation(new URI(codeMsg.getUrl()));
            return new ResponseEntity<>(response,headers, HttpStatus.valueOf(response.getResponseStatus()));
        }else {
            model.addAttribute("app_key",oAuthRequest.getClientId());
            return new ModelAndView("login.html");
        }

    }

    @PostMapping("/api/oauth/createToken.do")
    public Object createToken(Model model, HttpServletRequest request) throws OAuthProblemException, OAuthSystemException, URISyntaxException {
        //授权码---应用信息-----令牌(有效期)
        OAuthRequest oAuthRequest=new OAuthAuthzRequest(request);
        CodeTokenDto ctd= (CodeTokenDto) model.addAttribute("code");
        R r=codeService.createToken(ctd);
        if(r.getCode()==0){
            //令牌成功
            OauthTokenDto tokenDto=(OauthTokenDto)r.getData();

            //授权码、应用信息
            OAuthResponse response=OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND)
                    .setAccessToken(tokenDto.getToken())
                    .location("/api/oauth/createToken.do").buildBodyMessage();
            HttpHeaders headers=new HttpHeaders();
            headers.setLocation(new URI(tokenDto.getRecUrl()));
            return new ResponseEntity<>(response,headers, HttpStatus.valueOf(response.getResponseStatus()));
        }else {
            model.addAttribute("app_key",oAuthRequest.getClientId());
            return new ModelAndView("login.html");
        }
    }
    //令牌刷新---过滤器技术---请求之前就是验证请求消息头有没有包含令牌--包含--校验（无状态）---->有效刷洗令牌---通知应用  实时令牌
    //通过http把令牌通过回调路径传过去  过滤器校验刷新在发送



}
