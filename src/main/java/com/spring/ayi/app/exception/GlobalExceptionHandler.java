package com.spring.ayi.app.exception;

import com.spring.ayi.app.dto.response.error.ErrorResponse;
import com.spring.ayi.app.exception.custom.AddressNotFoundException;
import com.spring.ayi.app.exception.custom.MailAlreadyExistException;
import com.spring.ayi.app.exception.custom.UserAccountNotFoundException;
import com.spring.ayi.app.exception.custom.ClientNotFoundException;
import com.spring.ayi.app.exception.custom.DocumentNumberAlreadyExistException;
import com.spring.ayi.app.exception.custom.DocumentNumberNotFoundException;
import com.spring.ayi.app.exception.custom.EmptyListException;
import com.spring.ayi.app.exception.custom.PageDoesNotExistException;
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

    @ExceptionHandler(MailAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleMailAlreadyExist(MailAlreadyExistException ex) {
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

    @ExceptionHandler(UserAccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserAccountNotFound(UserAccountNotFoundException ex) {
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
