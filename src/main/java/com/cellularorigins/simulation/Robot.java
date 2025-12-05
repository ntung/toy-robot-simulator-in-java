package com.cellularorigins.simulation;

import com.cellularorigins.core.Position;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Robot {
    private Position position;

    /**
     * Places the robot at a specific position
     */
    public void place(final Position position) {
        setPosition(position);
    }

    /**
     * This action moves the robot forward. It moves one unit at a time.
     */
    public void move() {
    }

    /**
     * Turns the robot to the left side
     */
    public void left() {
    }

    /**
     * Turns the robot to the right side
     */
    public void right() {
    }

    /**
     * Returns the current position and direction of the robot.
     *
     * @return A {@link String} representing the current postion and direction of the robot
     */
    public String report() {
        return position.toString();
    }
}
