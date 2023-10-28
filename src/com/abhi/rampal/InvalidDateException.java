package com.abhi.rampal;

public class InvalidDateException extends Exception {
    private String errorCode;

    public InvalidDateException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}
