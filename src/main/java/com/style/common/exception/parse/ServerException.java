package com.style.common.exception.parse;

import lombok.Getter;

@Getter
public class ServerException extends RuntimeException {

    private final ServerExceptionCode code;

    public ServerException(final ServerExceptionCode code) {
        this.code = code;
    }

}
