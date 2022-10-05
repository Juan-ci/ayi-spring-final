package com.spring.ayi.app.controller.impl;

import com.spring.ayi.app.dto.response.ErrorResponse;
import com.spring.ayi.app.exception.DocumentNumberAlreadyExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private int codeStatus;

    private String status;

    @ExceptionHandler(DocumentNumberAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleDocumentNumberAlreadyExist(DocumentNumberAlreadyExistException ex) {
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
}
