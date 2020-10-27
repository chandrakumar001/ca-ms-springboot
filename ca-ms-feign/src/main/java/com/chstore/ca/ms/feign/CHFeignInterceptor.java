package com.chstore.ca.ms.feign;

import com.chstore.ca.ms.tracking.CHRequestContext;
import com.chstore.ca.ms.tracking.CHRequestHeader;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.MediaType;

public class CHFeignInterceptor implements RequestInterceptor {

    private final CHRequestContext chRequestContext;

    public CHFeignInterceptor(final CHRequestContext chRequestContext) {
        this.chRequestContext = chRequestContext;
    }

    @Override
    public void apply(final RequestTemplate requestTemplate) {

        requestTemplate.header("Accept", MediaType.APPLICATION_JSON_VALUE);
        if (chRequestContext.isForwardJwt()) {
            requestTemplate.header("authorization", String.format("%s", chRequestContext.getJwt()));
        }
        if (chRequestContext.isForwardTracking()) {
            requestTemplate.header(CHRequestHeader.BUSINESS_ID.getHeaderName(), chRequestContext.getRequestId());
        }
    }
}