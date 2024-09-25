package com.style.common.exception.request;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum RequestExceptionCode {

    PARAMETER_BINDING(HttpStatus.BAD_REQUEST, 2000, "Request parameter is invalid."),
    REQUEST_PARAMETER_INVALID(HttpStatus.BAD_REQUEST, 2001, "Request parameter is invalid."),
    REQUEST_CAN_NOT_READ(HttpStatus.BAD_REQUEST, 2002, "Request can not read."),
    NO_SEARCH_CRITERIA(HttpStatus.BAD_REQUEST, 2003, "검색 기준이 없습니다."),
    DUPLICATE_KEY(HttpStatus.CONFLICT, 2004, "Duplicate key.");
    ;

    private final HttpStatus status;
    private final int code;
    private final String message;

    RequestExceptionCode(final HttpStatus status, final int code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
