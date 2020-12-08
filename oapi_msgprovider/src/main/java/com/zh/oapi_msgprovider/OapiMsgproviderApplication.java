package com.zh.oapi_msgprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.zh.oapi_msgprovider.dao")
public class OapiMsgproviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OapiMsgproviderApplication.class, args);
    }

}
