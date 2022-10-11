package com.spring.ayi.app.exception.custom;

public class EmptyListException extends RuntimeException {
    public EmptyListException(String message) {
        super(message);
    }
}
