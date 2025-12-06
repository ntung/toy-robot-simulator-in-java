package com.cellularorigins.simulation;

import com.cellularorigins.core.Command;
import com.cellularorigins.core.Direction;
import com.cellularorigins.core.Position;
import com.cellularorigins.exception.IllegalActionException;
import com.cellularorigins.exception.InvalidRobotException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Simulator {
    private final Logger LOGGER = LogManager.getLogger(Simulator.class);

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
            case MOVE -> {
                if (canMove()) {
                    LOGGER.info("Moving Robot");
                    robot.move();
                } else {
                    LOGGER.info("Robot cannot move");
                    robot.report();
                }
            }
            case LEFT -> {
                LOGGER.info("Turning left Robot");
                robot.left();
            }
            case RIGHT -> {
                LOGGER.info("Turning right Robot");
                robot.right();
            }
            default ->  {}
        }

        return robot.report();
    }

    /**
     * Verifies the current position of the robot to determine whether its movement is legal or not.
     * When the robot is positioning at the edges, the movement cannot be performed.
     * In other words, a valid position should be inside the game board.
     *
     * @return true if the next movement is allowed. Otherwise, it returns false.
     */
    public boolean canMove() {
        Position currentPosition = robot.getPosition();
        Position tempPosition = Position.builder()
                .x(currentPosition.getX())
                .y(currentPosition.getY())
                .direction(currentPosition.getDirection())
                .build();
        Position newPosition = robot.tryMove(tempPosition);
        int x = newPosition.getX(),  y = newPosition.getY();
        // after moving, the robot is still inside the board
        return x >= 0 && x <= gameboard.getRows() - 1
                && y >= 0 && y <= gameboard.getCols() - 1;
    }

    /**
     * Creates a single simulator with the default values.
     *
     * @return {@link Simulator}
     */
    public static Simulator createDefaultSimulator() {
        GameBoard gameBoard = new GameBoard(5, 5);
        Position startPosition = new Position(0, 0, Direction.NORTH);
        Robot robot = new Robot(startPosition);
        return new Simulator(gameBoard, robot);
    }
}
