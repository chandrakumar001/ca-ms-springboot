package com.chstore.ca.ms.tracking;

public enum CHRequestHeader {

    LANGUAGE("lang"),
    EXTERNAL_ID("X-External-ID"),
    REQUEST_ID("X-Request-ID"),
    BUSINESS_ID("X-BusinessTx-ID"),
    JWT_TOKEN("Authorization");

    private final String headerName;

    CHRequestHeader(final String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderName() {
        return headerName;
    }
}