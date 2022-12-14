package com.spring.ayi.app.exception;

import com.spring.ayi.app.dto.response.error.ErrorResponse;
import com.spring.ayi.app.exception.custom.MarkerNotFoundException;
import com.spring.ayi.app.exception.custom.EmptyListException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private int codeStatus;

    private String status;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        codeStatus = HttpStatus.BAD_REQUEST.value();
        status = HttpStatus.BAD_REQUEST.name();

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        ErrorResponse response = ErrorResponse.builder()
                .status(status)
                .code(codeStatus)
                .message(errors.toString())
                .build();

        logger.error(response.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        codeStatus = HttpStatus.BAD_REQUEST.value();
        status = HttpStatus.BAD_REQUEST.name();

        ErrorResponse response = ErrorResponse.builder()
                .status(status)
                .code(codeStatus)
                .message(ex.getMessage())
                .build();

        logger.error(response.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        codeStatus = HttpStatus.BAD_REQUEST.value();
        status = HttpStatus.BAD_REQUEST.name();

        ErrorResponse response = ErrorResponse.builder()
                .status(status)
                .code(codeStatus)
                .message(ex.getMessage())
                .build();

        logger.error(response.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<ErrorResponse> handleEmptyListException(EmptyListException ex) {
        codeStatus = HttpStatus.NOT_FOUND.value();
        status = HttpStatus.NOT_FOUND.name();
        ErrorResponse response = ErrorResponse.builder()
                .status(status)
                .code(codeStatus)
                .message(ex.getMessage())
                .build();

        logger.error(response.toString());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MarkerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMarkerNotFound(MarkerNotFoundException ex) {
        codeStatus = HttpStatus.NOT_FOUND.value();
        status = HttpStatus.NOT_FOUND.name();
        ErrorResponse response = ErrorResponse.builder()
                .status(status)
                .code(codeStatus)
                .message(ex.getMessage())
                .build();

        logger.error(response.toString());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
