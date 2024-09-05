package com.style.common.exception.member;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MemberExceptionCode {

    EXISTS_MEMBER_EMAIL(HttpStatus.BAD_REQUEST, 3000,  "이미 존재하는 이메일 입니다."),
    EXISTS_MEMBER_NICKNAME(HttpStatus.BAD_REQUEST, 3001, "이미 존재하는 닉네임 입니다."),
    MEMBER_NOT_FOUNT(HttpStatus.BAD_REQUEST, 3002, "존재하지 않는 유저입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, 3003, "비밀번호가 일치하지 않습니다."),
    UNAUTHORIZED_MEMBER(HttpStatus.UNAUTHORIZED, 3004, "승인되지 않은 유저입니다."),
    MAX_ADDRESS(HttpStatus.BAD_REQUEST, 3005, "주소는 2개 이상 추가할 수 없습니다."),
    ADDRESS_NOT_FOUND(HttpStatus.BAD_REQUEST, 3006, "존재하지 않는 주소입니다.");

    private final HttpStatus status;
    private final int code;
    private final String message;

    MemberExceptionCode(final HttpStatus status, final int code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
