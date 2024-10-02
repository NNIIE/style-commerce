package com.style.common.exception.order;

import com.style.common.exception.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ExceptionResponse> orderExceptionHandler(final OrderException ex, final HttpServletRequest request) {
        final ExceptionResponse response = ExceptionResponse.builder()
                .code(ex.getCode().getCode())
                .message(ex.getCode().getMessage())
                .build();

        log.warn("[OrderException] URI: {}, CODE: {}", request.getRequestURI(), response.getCode());

        return ResponseEntity
                .status(ex.getCode().getStatus())
                .body(response);
    }

}
