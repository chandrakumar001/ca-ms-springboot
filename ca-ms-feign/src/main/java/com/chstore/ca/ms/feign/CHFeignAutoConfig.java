package com.chstore.ca.ms.feign;

import com.chstore.ca.ms.tracking.CHRequestContext;
import feign.Feign;
import feign.Logger;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CHFeignAutoConfig {

    @Bean
    Logger.Level chFeignLoggerLevel() {
        return Logger.Level.BASIC;
    }

    @Bean
    @LoadBalanced
    Feign.Builder chFeignBuilder(final CHRequestContext chRequestContext) {
        return Feign.builder()
                .errorDecoder(new CHFeignErrorDecoder())
                .requestInterceptor(new CHFeignInterceptor(
                        chRequestContext
                ));
    }
}