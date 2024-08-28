package com.core.exception.parse;

import lombok.Getter;

@Getter
public class ParseException extends RuntimeException {

    private final ParseExceptionCode code;

    public ParseException(final ParseExceptionCode code) {
        this.code = code;
    }

}
