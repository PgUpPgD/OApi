package com.zh.oapi_resource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.zh.oapi_resource.dao")
public class OapiResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OapiResourceApplication.class, args);
    }

}
