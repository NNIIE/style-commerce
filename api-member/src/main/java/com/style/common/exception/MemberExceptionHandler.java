package com.style.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MemberExceptionHandler {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<ExceptionResponse> memberExceptionHandler(final MemberException ex, final HttpServletRequest request) {
        final ExceptionResponse response = ExceptionResponse.builder()
                .code(ex.getCode().getCode())
                .message(ex.getCode().getMessage())
                .build();

        log.warn("[MemberException] URI: {}, CODE: {}", request.getRequestURI(), response.getCode());

        return ResponseEntity
                .status(ex.getCode().getStatus())
                .body(response);
    }

}
