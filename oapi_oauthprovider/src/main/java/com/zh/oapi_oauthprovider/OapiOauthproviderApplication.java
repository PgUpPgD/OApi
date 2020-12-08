package com.zh.oapi_oauthprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zh.oapi_oauthprovider.dao")
public class OapiOauthproviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OapiOauthproviderApplication.class, args);
    }

}
