package com.style.common.exception.request;

import com.style.common.exception.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class RequestExceptionHandler {

    private static final String DELIMITER = ", ";

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ExceptionResponse> requestInvalidExceptionHandler(final BindException e, final HttpServletRequest request) {
        final ExceptionResponse response = ExceptionResponse.builder()
                .code(RequestExceptionCode.PARAMETER_BINDING.getCode())
                .message(RequestExceptionCode.PARAMETER_BINDING.getMessage())
//                .description(getBindExceptionDescription(e))
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(RequestParameterException.class)
    public ResponseEntity<ExceptionResponse> requestParameterExceptionHandler(final RequestParameterException e, final HttpServletRequest request) {
        final ExceptionResponse response = ExceptionResponse.builder()
                .code(RequestExceptionCode.REQUEST_PARAMETER_INVALID.getCode())
                .message(RequestExceptionCode.REQUEST_PARAMETER_INVALID.getMessage())
//                .description(e.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> requestNotReadExceptionHandler(final HttpMessageNotReadableException e, final HttpServletRequest request) {
        final ExceptionResponse response = ExceptionResponse.builder()
                .code(RequestExceptionCode.REQUEST_CAN_NOT_READ.getCode())
                .message(RequestExceptionCode.REQUEST_CAN_NOT_READ.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> duplicateExceptionHandler(final SQLIntegrityConstraintViolationException e, final HttpServletRequest request) {
        final ExceptionResponse response = ExceptionResponse.builder()
                .code(RequestExceptionCode.DUPLICATE_KEY.getCode())
                .message(RequestExceptionCode.DUPLICATE_KEY.getMessage())
                .build();

        log.error("[DUPLICATE Error] [{} {}] {}",
                request.getMethod(),
                request.getRequestURI(),
                e.getMessage(), e);

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    private String getBindExceptionDescription(final BindException e) {
        return e.getFieldErrors().stream()
                .map(FieldError::getField)
                .collect(Collectors.joining(DELIMITER));
    }

}
