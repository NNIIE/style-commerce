package com.style.common.exception.brand;

import com.style.common.exception.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BrandExceptionHandler {

    @ExceptionHandler(BrandException.class)
    public ResponseEntity<ExceptionResponse> brandExceptionHandler(final BrandException ex, final HttpServletRequest request) {
        final ExceptionResponse response = ExceptionResponse.builder()
                .code(ex.getCode().getCode())
                .message(ex.getCode().getMessage())
                .build();

        log.warn("[BrandException] URI: {}, CODE: {}", request.getRequestURI(), response.getCode());

        return ResponseEntity
                .status(ex.getCode().getStatus())
                .body(response);
    }

}
