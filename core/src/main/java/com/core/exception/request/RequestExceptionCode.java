package com.core.exception.request;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum RequestExceptionCode {

    PARAMETER_BINDING(HttpStatus.BAD_REQUEST, 3000, "Request parameter is invalid."),
    REQUEST_PARAMETER_INVALID(HttpStatus.BAD_REQUEST, 3001, "Request parameter is invalid."),
    REQUEST_CAN_NOT_READ(HttpStatus.BAD_REQUEST, 3002, "Request can not read."),
    DUPLICATE_KEY(HttpStatus.CONFLICT, 3003, "Duplicate key.");

    private final HttpStatus status;
    private final int code;
    private final String message;

    RequestExceptionCode(final HttpStatus status, final int code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
