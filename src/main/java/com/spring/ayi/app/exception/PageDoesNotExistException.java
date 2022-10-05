package com.spring.ayi.app.exception;

public class PageDoesNotExistException extends RuntimeException{
    public PageDoesNotExistException(String message) {
        super(message);
    }
}
