package com.cellularorigins.exception;

import lombok.Getter;

@Getter
public class IllegalActionException extends RuntimeException {
    private final String message;

    public IllegalActionException(String message) {
        this.message = message;
    }

}
