package com.auth.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ExceptionResponse> memberExceptionHandler(final AuthException ex, final HttpServletRequest request) {
        final ExceptionResponse response = ExceptionResponse.builder()
                .code(ex.getCode().getCode())
                .message(ex.getCode().getMessage())
                .build();

        return ResponseEntity
                .status(ex.getCode().getStatus())
                .body(response);
    }

}
