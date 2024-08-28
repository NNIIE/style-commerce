package com.style.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MemberExceptionCode {

    EXISTS_USER_EMAIL(HttpStatus.BAD_REQUEST, 2000,  "이미 존재하는 이메일 입니다."),
    EXISTS_USER_NICKNAME(HttpStatus.BAD_REQUEST, 2001, "이미 존재하는 닉네임 입니다."),
    USER_NOT_FOUNT(HttpStatus.BAD_REQUEST, 2002, "존재하지 않는 유저입니다."),
    INVALID_CREDENTIALS(HttpStatus.BAD_REQUEST, 2003, "이메일 또는 비밀번호가 일치하지 않습니다.");

    private final HttpStatus status;
    private final int code;
    private final String message;

    MemberExceptionCode(final HttpStatus status, final int code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
