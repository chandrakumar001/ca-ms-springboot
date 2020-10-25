package com.chstore.ca.ms.logging;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class CHMDCAutoConfig {

    private static final int ORDER = 0;
    private static final int MAX_PAYLOAD_LENGTH_VALUE = 10000;


    @Bean
    public FilterRegistrationBean<CHMDCCleanUpFilter> chmMDCFilterRegistrar(
    ) {

        final FilterRegistrationBean<CHMDCCleanUpFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new CHMDCCleanUpFilter());
        registration.setOrder(ORDER);
        return registration;
    }

    @Bean
    public CommonsRequestLoggingFilter requestLogFilter() {

        final CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setIncludeHeaders(true);
        filter.setMaxPayloadLength(MAX_PAYLOAD_LENGTH_VALUE);
        filter.setBeforeMessagePrefix("REQUEST DATA Begin");
        filter.setAfterMessagePrefix("REQUEST DATA End: ");
        return filter;
    }
}