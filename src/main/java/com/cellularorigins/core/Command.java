package com.cellularorigins.core;

import lombok.Getter;

/**
 * <p>An enum class representing the commands of the robot.</p>
 * As of writing this code, players can execute the following commands: <strong>PLACE, MOVE, LEFT, RIGHT</strong> and
 * <strong>REPORT</strong>.</p>
 *
 * @author <a href="mailto:nvntung@gmail.com">Tung Nguyen</a>
 */
@Getter
public enum Command {
    PLACE("PLACE"),
    MOVE("MOVE"),
    LEFT("LEFT"),
    RIGHT("RIGHT"),
    REPORT("REPORT");

    private final String description;

    Command(String description) {
        this.description = description;
    }

    /**
     * This static method returns a value of the {@link Command} enums based on the string representing its value.
     *
     * @param string A {@link String} denoting the description of a {@link Command} value.
     * @return An enum value of {@link Command}.
     */
    public static Command from(String string) {
        for (Command command : values()) {
            if (command.getDescription().equalsIgnoreCase(string)) {
                return command;
            }
        }
        return null;
    }
}
