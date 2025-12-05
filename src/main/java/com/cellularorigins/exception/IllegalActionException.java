package com.cellularorigins.exception;

public class IllegalActionException extends RuntimeException {
    private String message;

    public IllegalActionException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
