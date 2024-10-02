package com.style.common.exception.order;

import lombok.Getter;

@Getter
public class OrderException extends RuntimeException {

    private final OrderExceptionCode code;

    public OrderException(final OrderExceptionCode code) {
        this.code = code;
    }

}
