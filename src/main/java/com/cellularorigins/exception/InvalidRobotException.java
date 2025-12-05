package com.cellularorigins.exception;

public class InvalidRobotException extends RuntimeException {
    private String message;

    public InvalidRobotException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
