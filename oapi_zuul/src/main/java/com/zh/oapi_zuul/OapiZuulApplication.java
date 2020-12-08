package com.zh.oapi_zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class OapiZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(OapiZuulApplication.class, args);
    }

}
