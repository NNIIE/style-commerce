package com.style.common.exception.request;

import lombok.Getter;

@Getter
public class RequestParameterException extends RuntimeException {

    private final String message;

    public RequestParameterException(final String message) {
        this.message = message;
    }

}
