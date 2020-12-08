package com.zh.oapi_userapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient //@EnableEurekaClient
@EnableSwagger2
public class OapiUserapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OapiUserapiApplication.class, args);
    }

}
