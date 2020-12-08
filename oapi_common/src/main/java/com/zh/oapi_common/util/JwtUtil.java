package com.zh.oapi_common.util;

import com.zh.oapi_common.config.SystemConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

public class JwtUtil {
    //生成令牌
    /*
     * @param json 要生成的令牌内容*/
    public static String createJWT(String json){
        //1、创建加密规则
        SignatureAlgorithm signatureAlgorithm= SignatureAlgorithm.HS256;
        //2、创建JWT对象
        JwtBuilder jwtBuilder= Jwts.builder();
        //3、设置令牌内容
        jwtBuilder.setSubject(json);
        jwtBuilder.setIssuedAt(new Date());//设置起始时间
        jwtBuilder.setExpiration(DateUtil.addTime(SystemConfig.TOKEN_TIME)); //10分钟后过期
        jwtBuilder.signWith(signatureAlgorithm,createKey());//设置加密规则和秘钥
        //4、生成令牌
        return jwtBuilder.compact();
    }
    //生成秘钥
    private static Key createKey(){
        //SystemConfig.JWT_KEY   秘钥常量
        return new SecretKeySpec(SystemConfig.JWT_KEY.getBytes(),"AES");
    }
    //解析令牌
    /*
     * 解析令牌
     * @param token jwt的令牌字符串*/
    public static String parseJWT(String token){
        Claims claim=Jwts.parser().setSigningKey(createKey()).parseClaimsJws(token).getBody();
        return claim.getSubject();
    }
    //校验令牌
    public static boolean checkJWT(String token){
        return parseJWT(token)!=null;
    }

    public static void main(String[] args) {
        String jwt = createJWT("123456");
        System.out.println(jwt);
        String s = parseJWT(jwt);
        System.out.println(s);
        try {
            String s1 = parseJWT("s");
            System.out.println(s1);
        }catch (Exception e){

        }

    }
}
