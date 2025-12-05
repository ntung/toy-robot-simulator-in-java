package com.cellularorigins.simulation;

import com.cellularorigins.core.Direction;
import com.cellularorigins.core.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Robot {
    private Position position;

    /**
     * Places the robot at a specific position
     */
    public void place(final Position position) {
        setPosition(position);
    }

    /**
     * This action moves the robot forward. The toy robot moves one unit forward in the direction it is currently
     * facing.
     */
    public void move() {
        if (position.getDirection().equals(Direction.NORTH)) {
            position.updatePosition(0, 1);
        } else if (position.getDirection().equals(Direction.SOUTH)) {
            position.updatePosition(0, -1);
        } else if (position.getDirection().equals(Direction.EAST)) {
            position.updatePosition(1, 0);
        } else if (position.getDirection().equals(Direction.WEST)) {
            position.updatePosition(-1, 0);
        }
    }

    /**
     * Turns the robot to the left side. It will rotate the robot 90 degrees in the specified direction without
     * changing the position of the robot.
     */
    public void left() {
        turn(-1);
    }

    /**
     * Turns the robot to the right side. It will rotate the robot 90 degrees in the specified direction without
     * changing the position of the robot.
     */
    public void right() {
        turn(1);
    }

    /**
     * Returns the current position and direction of the robot.
     *
     * @return A {@link String} representing the current postion and direction of the robot
     */
    public String report() {
        return position.toString();
    }

    private void  turn(final int side) {
        Direction newDirection = getPosition().getDirection().changeDirection(side);
        getPosition().setDirection(newDirection);

    }
}
