package com.style.common.exception.order;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum OrderExceptionCode {

    LOW_STOCK(HttpStatus.BAD_REQUEST, 6000, "상품 수량이 부족합니다.");

    private final HttpStatus status;
    private final int code;
    private final String message;

    OrderExceptionCode(final HttpStatus status, final int code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
