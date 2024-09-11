package com.style.common.exception.brand;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BrandExceptionCode {

    BRAND_NOT_FOUND(HttpStatus.BAD_REQUEST, 4000, "존재하지 않는 브랜드 입니다."),
    BRAND_NOT_MATCH(HttpStatus.BAD_REQUEST, 4001, "자신의 브랜드 이어야 합니다.");

    private final HttpStatus status;
    private final int code;
    private final String message;

    BrandExceptionCode(final HttpStatus status, final int code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
