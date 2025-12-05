package com.cellularorigins.production;

import com.cellularorigins.core.Command;
import com.cellularorigins.core.Direction;
import com.cellularorigins.core.Position;
import com.cellularorigins.exception.IllegalActionException;
import com.cellularorigins.exception.InvalidRobotException;
import com.cellularorigins.simulation.GameBoard;
import com.cellularorigins.simulation.Robot;
import com.cellularorigins.simulation.Simulator;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

@Getter @Setter
public class ToyRobotFactory {
    /**
     * Declares the {@link Logger} instance for logging
     */
    private static final Logger LOGGER = LogManager.getLogger(ToyRobotFactory.class);

    public static void createToyRobot() {
        GameBoard gameBoard = new GameBoard(5, 5);
        Position startPosition = new Position(0, 0, Direction.NORTH);
        Robot robot = new Robot(startPosition);
        Simulator simulator = new Simulator(gameBoard, robot);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // receive input
            String commandLine = scanner.nextLine();
            if (commandLine.equalsIgnoreCase("quit")) {
                break;
            }
            // parse the input and execute the command
            String report = executeCommand(simulator, commandLine);
            System.out.println(report);
        }
    }

    public static String executeCommand(Simulator simulator, String commandLine) {
        String[] commandArgs = commandLine.split(" ");
        String output = "";
        try {
            Command command = Command.from(commandArgs[0]);
            if (command == Command.PLACE) {
                try {
                    if (commandArgs.length == 1) {
                        return "Invalid command";
                    }
                    boolean result = placeCommand(simulator, commandArgs[1]);
                    LOGGER.info("Place command {} {} {}", command.getDescription(), commandArgs[1], (result ?
                            "successfully" : "unsuccessfully"));
                }  catch (IllegalActionException e) {
                    throw new IllegalActionException(e.getMessage());
                }
            } else {
                try {
                    output = simulator.play(command);
                } catch (InvalidRobotException | IllegalActionException exception) {
                    System.out.println(exception.getMessage() + ". Try again with the built-in commands!");
                }
            }
        } catch (IllegalArgumentException ex) {
            throw new InvalidRobotException("Invalid command");
        }
        return output;
    }

    public static boolean placeCommand(Simulator simulator, String commands) {
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
        return simulator.placeRobot(position);
    }
}
