package com.cellularorigins.core;

import lombok.Getter;

/**
 * <p>An enum class representing four universal directions which a robot can move.</p>
 *
 * @author <a href="mailto:nvntung@gmail.com">Tung Nguyen</a>
 */
@Getter
public enum Direction {
    NORTH("NORTH"),
    EAST("EAST"),
    SOUTH("SOUTH"),
    WEST("WEST");

    /**
     * -- GETTER --
     *  This method returns the description of an individual
     *  instance.
     *
     */
    private final String description;

    /**
     * Default constructor
     * @param description A {@link String} object denoting the direction,
     *                    for example, "north" or "NORTH"
     */
    Direction(String description) {
        this.description = description;
    }

    /**
     * This static method returns a direction based on the string representing it.
     * @param description A {@link String} object denoting the direction,
     *                    for example, "north" or "NORTH"
     * @return a {@link Direction} if the description is one of the four universal directions. Otherwise, it returns
     * null.
     */
    public static Direction from(String description) {
        for (Direction direction : values()) {
            if (direction.getDescription().equalsIgnoreCase(description)) {
                return direction;
            }
        }
        return null;
    }

    /**
     * This method returns a direction after executing a command.
     * @param rotatePoint it can be either 1 or -1. The value -1 means that we rotate the robot 90°
     *                    anticlockwise/counterclockwise. Otherwise, it will rotate the robot 90° clockwise.
     * @return a {@link Direction} one of the four universal directions
     */
    public Direction changeDirection(int rotatePoint) {
        int resultIndex = ((ordinal() + rotatePoint) + 4) % 4;
        return values()[resultIndex];
    }

}
