package com.chstore.ca.ms.tracking;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;


@Configuration
public class CHTrackingAutoConfig {

    private static final int ORDER = 1;


    @Bean
    @RequestScope
    public CHRequestContext chRequestContext() {
        return new CHRequestContext();
    }

    @Bean
    public FilterRegistrationBean<CHTrackingFilter> chTrackingFilterRegistrar(
            CHRequestContext chRequestContext
    ) {

        final FilterRegistrationBean<CHTrackingFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new CHTrackingFilter(chRequestContext));
        registration.setOrder(ORDER);
        return registration;
    }
}