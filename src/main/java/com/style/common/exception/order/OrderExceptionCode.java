package com.style.common.exception.order;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum OrderExceptionCode {

    NOT_FOUND_ORDER(HttpStatus.BAD_REQUEST, 6000, "존재하지 않는 주문 입니다."),
    LOW_STOCK(HttpStatus.BAD_REQUEST, 6001, "상품 수량이 부족합니다."),
    INVALID_ADDRESS(HttpStatus.BAD_REQUEST, 6002, "잘못된 주소 입니다."),
    NOT_CANCEL_ORDER(HttpStatus.BAD_REQUEST, 6003, "취소할 수 없는 주문 입니다.");

    private final HttpStatus status;
    private final int code;
    private final String message;

    OrderExceptionCode(final HttpStatus status, final int code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
