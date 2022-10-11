package com.spring.ayi.app.exception.custom;

public class ClientDetailNotFoundException extends RuntimeException {
    public ClientDetailNotFoundException(String message) {
        super(message);
    }
}
