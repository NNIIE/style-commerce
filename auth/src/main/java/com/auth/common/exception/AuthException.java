package com.auth.common.exception;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {

    private final AuthExceptionCode code;

    public AuthException(final AuthExceptionCode code) {
        this.code = code;
    }

}
