package com.cdavis.errorhandling;

public class ResponseError {
    private final String code;
    private final String description;
    private final int httpResponseCode;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public ResponseError(String code, String description, int httpResponseCode) {
        this.code = code;
        this.description = description;
        this.httpResponseCode = httpResponseCode;
    }

    public int getHttpResponseCode() {
        return httpResponseCode;
    }
}
