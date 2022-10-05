package com.spring.ayi.app.controller.impl;

import com.spring.ayi.app.dto.response.ErrorResponse;
import com.spring.ayi.app.exception.AddressNotFoundException;
import com.spring.ayi.app.exception.ClientDetailNotFoundException;
import com.spring.ayi.app.exception.ClientNotFoundException;
import com.spring.ayi.app.exception.DocumentNumberAlreadyExistException;
import com.spring.ayi.app.exception.DocumentNumberNotFoundException;
import com.spring.ayi.app.exception.EmptyListException;
import com.spring.ayi.app.exception.PageDoesNotExistException;
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

    @ExceptionHandler(PageDoesNotExistException.class)
    public ResponseEntity<ErrorResponse> handlePageDoesNotExists(PageDoesNotExistException ex) {
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

    @ExceptionHandler(DocumentNumberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDocumentNumberNotFound(DocumentNumberNotFoundException ex) {
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

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleClientNotFound(ClientNotFoundException ex) {
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

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAddressNotFound(AddressNotFoundException ex) {
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

    @ExceptionHandler(ClientDetailNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleClientDetailNotFound(ClientDetailNotFoundException ex) {
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
