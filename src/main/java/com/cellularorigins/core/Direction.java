package com.cellularorigins.core;

/**
 * An enum class representing four universal directions which a robot can move.
 */
public enum Direction {
    NORTH("NORTH"),
    EAST("EAST"),
    SOUTH("SOUTH"),
    WEST("WEST");

    private String description;

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
     * @param rotatePoint it can be either 1 or -1
     * @return a {@link Direction} one of the four universal directions
     */
    public Direction changeDirection(int rotatePoint) {
        int resultIndex = ((ordinal() + rotatePoint) + 4) % 4;
        return values()[resultIndex];
    }

    /**
     * This method returns the description of an individual {@link Direction} instance.
     * @return A {@link String} representing the name of the given direction.
     */
    public String getDescription() {
        return description;
    }
}
