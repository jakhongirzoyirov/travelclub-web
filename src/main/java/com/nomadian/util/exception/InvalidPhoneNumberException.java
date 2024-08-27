package com.nomadian.util.exception;

public class InvalidPhoneNumberException extends RuntimeException {
    private static final long serialVersionUID = -8812955226330753784L;

    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
