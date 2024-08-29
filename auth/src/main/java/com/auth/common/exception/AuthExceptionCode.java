package com.auth.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AuthExceptionCode {

    MEMBER_NOT_FOUNT(HttpStatus.BAD_REQUEST, 1000, "존재하지 않는 유저입니다."),
    INVALID_CREDENTIALS(HttpStatus.BAD_REQUEST, 1001, "비밀번호가 일치하지 않습니다.");

    private final HttpStatus status;
    private final int code;
    private final String message;

    AuthExceptionCode(final HttpStatus status, final int code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
