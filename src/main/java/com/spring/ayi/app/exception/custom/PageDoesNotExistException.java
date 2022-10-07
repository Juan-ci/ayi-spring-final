package com.spring.ayi.app.exception.custom;

public class PageDoesNotExistException extends RuntimeException{
    public PageDoesNotExistException(String message) {
        super(message);
    }
}
