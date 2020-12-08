package com.zh.oapi_oauthprovider.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-11-07 10:19
 */
public class RabbitMQConfig {

    public static String qname="code_queue";
    @Bean
    public Queue createQueue(){
       return new Queue(qname);
    }

    @Bean
    public RabbitTemplate createRT(){
        return new RabbitTemplate();
    }
//    @Bean
//    public Exchange createEx(){
////        new FanoutExchange();
////        new DirectExchange();
////        new HeadersExchange();
//        return new TopicExchange("codes");
//    }
}
