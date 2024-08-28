package com.core.exception.request;

import lombok.Getter;

@Getter
public class RequestParameterException extends RuntimeException {

    private final String message;

    public RequestParameterException(final String message) {
        this.message = message;
    }

}
