package com.cellularorigins.simulation;

import com.cellularorigins.core.Command;
import com.cellularorigins.core.Direction;
import com.cellularorigins.core.Position;
import com.cellularorigins.exception.IllegalActionException;
import com.cellularorigins.exception.InvalidRobotException;
import lombok.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A class for creating a Simulator composing a GameBoard and a Robot.
 *
 * @author <a href="mailto:nvntung@gmail.com">Tung Nguyen</a>
 */
@Value
@Builder(access = AccessLevel.PUBLIC)
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
        if (gameboard == null && robot == null) {
            throw new IllegalActionException("Cannot play with null robot and gameboard");
        }
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
            case ABOUT -> {
                LOGGER.info("Showing about the current simulator");
                about();
            }
            case RESET -> {
                LOGGER.info("Resetting the simulator");
                reset();
            }
            default ->  {}
        }

        return robot.report();
    }

    private void reset() {
        LOGGER.info("Unsupported yet");
        System.out.println("Unsupported yet");
    }

    /**
     * Shows the information of the current Simulator
     */
    private void about() {
        String about = "Game Board[" + gameboard.rows() + "," + gameboard.cols() + "]";
        about +=" and Robot: " + robot.report();
        System.out.println(about);
    }

    /**
     * Executes the PLACE command including the position coordinates and the direction.
     *
     * @param commands {@link String} denoting the position coordinates following by the direction.
     * @return true if the execution is successful. Otherwise, it returns false.
     */
    public boolean placeCommand(String commands) {
        int xValue;
        int yValue;
        String[] commandArgs = commands.split(",");
        try {
            xValue = Integer.parseInt(commandArgs[0]);
            yValue = Integer.parseInt(commandArgs[1]);
        } catch (Exception ex) {
            throw new InvalidRobotException("Invalid argument");
        }
        Direction direction = Direction.from(commandArgs[2]);
        Position position = new Position(xValue, yValue, direction);
        if (gameboard.validatePosition(position)) {
            return placeRobot(position);
        } else  {
            return false;
        }
    }

    /**
     * Executes a given command. The method returns instructions if the command is invalid.
     *
     * @param commandLine {@link String} denoting the requested command.
     * @return {@link String} denoting as the output of the command.
     */
    public String execute(String commandLine) {
        String[] commandArgs = commandLine.split(" ");
        String output = "";
        try {
            Command command = Command.from(commandArgs[0]);
            if (command == Command.PLACE) {
                try {
                    if (commandArgs.length == 1) {
                        return "Invalid command";
                    }
                    boolean result = placeCommand(commandArgs[1]);
                    LOGGER.info("Place command {} {} {}", command.getDescription(), commandArgs[1], (result ?
                            "successfully" : "unsuccessfully"));
                    output = "Placed " + (result ? "successfully" : "unsuccessfully");
                }  catch (IllegalActionException e) {
                    throw new IllegalActionException(e.getMessage());
                }
            } else {
                try {
                    output = play(command);
                } catch (InvalidRobotException | IllegalActionException exception) {
                    System.out.println(exception.getMessage() + ". Try again with the built-in commands!");
                }
            }
        } catch (IllegalArgumentException ex) {
            throw new InvalidRobotException("Invalid command");
        }
        return output;
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
        return x >= 0 && x <= gameboard.rows() - 1
                && y >= 0 && y <= gameboard.cols() - 1;
    }

    /**
     * Creates a single simulator with the default values.
     *
     * @return {@link Simulator} to run the simulation.
     */
    public static Simulator createDefaultSimulator() {
        GameBoard gameBoard = new GameBoard(5, 5);
        Position startPosition = new Position(0, 0, Direction.NORTH);
        Robot robot = new Robot(startPosition);
        return new Simulator(gameBoard, robot);
    }
}
