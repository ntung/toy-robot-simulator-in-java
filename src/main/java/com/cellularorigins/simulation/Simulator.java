package com.cellularorigins.simulation;

import com.cellularorigins.core.Command;
import com.cellularorigins.core.Position;
import com.cellularorigins.exception.IllegalActionException;
import com.cellularorigins.exception.InvalidRobotException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Simulator {
    private GameBoard gameboard;
    private Robot robot;

    public boolean placeRobot(final Position position) {
        robot.place(position);
        return gameboard.validatePosition(robot.getPosition());
    }

    /**
     * Plays the toy robot after placing it at the original position.
     *
     * @param command A {@link Command} representing the action to the robot.
     * @return A {@link String} representing the report after executing the command.
     */
    public String play(final Command command)  {
        if  (gameboard == null) {
            throw new InvalidRobotException("Gameboard is null");
        }
        if (command == null) {
            throw new IllegalActionException("Command is null");
        }
        if (robot == null) {
            throw new InvalidRobotException("Robot is null");
        }
        String report = "";
        if (robot.getPosition() == null) {
            return report;
        }
        switch (command) {
            case MOVE -> robot.move();
            case LEFT -> robot.left();
            case RIGHT -> robot.right();
            default ->  {}
        }

        return robot.report();
    }
}
