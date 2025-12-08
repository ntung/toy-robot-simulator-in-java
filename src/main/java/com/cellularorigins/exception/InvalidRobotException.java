package com.cellularorigins.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * This class defines the invalid robot exception.
 *
 * @author <a href="mailto:nvntung@gmail.com">Tung Nguyen</a>
 */
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
