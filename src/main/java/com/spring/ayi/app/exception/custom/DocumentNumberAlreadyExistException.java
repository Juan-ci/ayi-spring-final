package com.spring.ayi.app.exception.custom;

public class DocumentNumberAlreadyExistException extends RuntimeException {
    public DocumentNumberAlreadyExistException(String message) {
        super(message);
    }
}
