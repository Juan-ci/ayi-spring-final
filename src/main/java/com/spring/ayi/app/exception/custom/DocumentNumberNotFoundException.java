package com.spring.ayi.app.exception.custom;

public class DocumentNumberNotFoundException extends RuntimeException{
    public DocumentNumberNotFoundException(String message) {
        super(message);
    }
}
