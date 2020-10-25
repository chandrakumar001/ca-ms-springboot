package com.chstore.ca.ms.tracking;

public enum CHRequestHeader {

    LANGUAGE("lang"),
    CO_RELATION_ID("Correlation-id"),
    JWT_TOKEN("Authorization");

    private final String headerName;

    CHRequestHeader(final String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderName() {
        return headerName;
    }
}