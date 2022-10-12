package com.spring.ayi.app.exception.custom;

public class MailAlreadyExistException extends RuntimeException{
    public MailAlreadyExistException(String message) {
        super(message);
    }
}
