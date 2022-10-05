package com.spring.ayi.app.exception;

public class DocumentNumberNotFoundException extends RuntimeException{
    public DocumentNumberNotFoundException(String message) {
        super(message);
    }
}
