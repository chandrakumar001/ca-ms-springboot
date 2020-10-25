package com.chstore.ca.ms.feign;


import feign.Response;
import feign.codec.ErrorDecoder;


public class CHFeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {

        return errorDecoder.decode(s, response);
    }
}