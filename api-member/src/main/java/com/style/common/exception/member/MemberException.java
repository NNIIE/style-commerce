package com.style.common.exception.member;

import lombok.Getter;

@Getter
public class MemberException extends RuntimeException {

    private final MemberExceptionCode code;

    public MemberException(final MemberExceptionCode code) {
        this.code = code;
    }

}
