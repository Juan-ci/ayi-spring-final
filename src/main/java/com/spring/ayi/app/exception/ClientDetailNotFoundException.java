package com.spring.ayi.app.exception;

public class ClientDetailNotFoundException extends RuntimeException {
    public ClientDetailNotFoundException(String message) {
        super(message);
    }
}
