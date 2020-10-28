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
            chRequestContext.getJwt()
                    .ifPresent(jwtToken -> requestTemplate.header(CHRequestHeader.JWT_TOKEN.getHeaderName(), String.format("%s", chRequestContext.getJwt())));
        }

        if (chRequestContext.isForwardTracking()) {

            chRequestContext.getExternalId()
                    .ifPresent(externalId -> requestTemplate.header(CHRequestHeader.EXTERNAL_ID.getHeaderName(), externalId));
            requestTemplate.header(CHRequestHeader.REQUEST_ID.getHeaderName(), chRequestContext.getRequestId());
            //TODO lang
        }
    }
}