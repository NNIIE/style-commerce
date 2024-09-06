package com.style.common.exception.brand;

import lombok.Getter;

@Getter
public class BrandException extends RuntimeException {

    private final BrandExceptionCode code;

    public BrandException(final BrandExceptionCode code) {
        this.code = code;
    }

}
