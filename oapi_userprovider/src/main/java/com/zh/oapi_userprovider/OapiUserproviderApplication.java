package com.zh.oapi_userprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.zh.oapi_userprovider.dao")
@EnableTransactionManagement  //启用注解事务
public class OapiUserproviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OapiUserproviderApplication.class, args);
    }

}
