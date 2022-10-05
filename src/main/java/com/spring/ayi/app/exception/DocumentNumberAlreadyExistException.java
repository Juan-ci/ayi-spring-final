package com.spring.ayi.app.exception;

public class DocumentNumberAlreadyExistException extends RuntimeException {
    public DocumentNumberAlreadyExistException(String message) {
        super(message);
    }
}
