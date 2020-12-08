package com.zh.oapi_userapi.config;

import springfox.documentation.service.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

//http://localhost:8101/swagger-ui.html  访问地址
@Configuration
public class SwaggerConfig {

    private ApiInfo createAI(){
        return new ApiInfoBuilder().title("开放平台数据接口")
                .description("基于SpringCloud实现的开放平台微服务项目")
                .version("0.1")
                .contact(new Contact("Thc","http://111","tang_thc@alinyun.com"))
                .build();
    }

    @Bean
    public Docket createD(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(createAI())
                .select().apis(RequestHandlerSelectors.basePackage("com.zh.oapi_userapi.controller"))
                .build();
    }
}
