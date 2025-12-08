package com.cellularorigins.exception;

import lombok.Getter;

/**
 * This class defines the illegal action exception.
 *
 * @author <a href="mailto:nvntung@gmail.com">Tung Nguyen</a>
 */
@Getter
public class IllegalActionException extends RuntimeException {
    private final String message;

    public IllegalActionException(String message) {
        this.message = message;
    }

}
