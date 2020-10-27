package com.chstore.ca.ms.tracking;

import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

public class CHRequestContext {

    public static final String USER_ID = "userId";

    private boolean isInitialised;
    private String requestId;
    private String externalId;
    private String businessTxId;

    private String jwt;

    private String requestUri;
    private String verb;

    private String userId;

    private boolean isForwardJwt;
    private boolean isForwardTracking;

    public void init(final HttpServletRequest request,
                     final HttpServletResponse response) {
        if (isInitialised) {
            return;
        }
        this.isInitialised = true;
        this.requestId = getRequestId(request, response);
        this.externalId = getExternalId(request, response);
        this.businessTxId = getBusinessTxId(request, response);

        this.requestUri = request.getRequestURI();
        this.verb = request.getMethod();

        this.isForwardJwt = true;
        this.isForwardTracking = true;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isInitialised() {
        return isInitialised;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public String getVerb() {
        return verb;
    }

    public Optional<String> getJwt() {
        return Optional.ofNullable(jwt);
    }

    public boolean isUnknownUser() {
        return !getJwt().isPresent();
    }

    public boolean isForwardJwt() {
        return isForwardJwt;
    }

    public boolean isForwardTracking() {
        return isForwardTracking;
    }

    public String getRequestId() {
        return requestId;
    }

    private String getBusinessTxId(final HttpServletRequest httpServletRequest,
                                   final HttpServletResponse httpServletResponse) {
        return defineHeader(httpServletRequest, httpServletResponse,
                CHRequestHeader.BUSINESS_ID, UUID.randomUUID().toString());
    }

    private String getExternalId(final HttpServletRequest httpServletRequest,
                                 final HttpServletResponse httpServletResponse) {

        return defineHeader(httpServletRequest, httpServletResponse,
                CHRequestHeader.EXTERNAL_ID, null);
    }

    private String getRequestId(final HttpServletRequest httpServletRequest,
                                final HttpServletResponse httpServletResponse) {

        return defineHeader(httpServletRequest, httpServletResponse,
                CHRequestHeader.REQUEST_ID, UUID.randomUUID().toString());
    }

    private static String defineHeader(final HttpServletRequest httpServletRequest,
                                       final HttpServletResponse httpServletResponse,
                                       final CHRequestHeader chRequestHeader,
                                       final String defaultValue) {

        final String headerName = chRequestHeader.getHeaderName();
        final String requestValue = httpServletRequest.getHeader(headerName);

        final String resultingValue;
        if (StringUtils.isEmpty(requestValue)) {
            resultingValue = defaultValue;
        } else {
            resultingValue = requestValue;
        }

        //result value null check: by default external ID is null
        if (resultingValue != null) {
            MDC.put(headerName, resultingValue);
            httpServletResponse.setHeader(headerName, resultingValue);
        }
        return resultingValue;
    }

    private CHRequestLanguage getLang(HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) {

        final String headerName = CHRequestHeader.LANGUAGE.getHeaderName();
        // language is not mandatory
        final String inputLanguage = httpServletRequest.getHeader(headerName);
        CHRequestLanguage chRequestLanguage = CHRequestLanguage.EN;
        if (inputLanguage != null) {
            try {
                chRequestLanguage = chRequestLanguage.valueOf(inputLanguage.toUpperCase(Locale.ENGLISH));
            } catch (final IllegalArgumentException exception) {
                // use default english
            }
        }
        httpServletResponse.setHeader(headerName, chRequestLanguage.getLocale().getLanguage());
        return chRequestLanguage;
    }

}