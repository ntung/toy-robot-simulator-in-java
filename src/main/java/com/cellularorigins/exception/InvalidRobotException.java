package com.cellularorigins.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class InvalidRobotException extends RuntimeException {
    private final String message;

    public InvalidRobotException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
