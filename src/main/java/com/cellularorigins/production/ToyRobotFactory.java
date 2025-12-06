package com.cellularorigins.production;

import com.cellularorigins.core.Command;
import com.cellularorigins.core.Direction;
import com.cellularorigins.core.Position;
import com.cellularorigins.exception.IllegalActionException;
import com.cellularorigins.exception.InvalidRobotException;
import com.cellularorigins.simulation.Simulator;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 */
@Getter @Setter
public class ToyRobotFactory {
    /**
     * Declares the {@link Logger} instance for logging
     */
    private static final Logger LOGGER = LogManager.getLogger(ToyRobotFactory.class);

    public static void usage() {
        System.out.println("Play robot game now! Commands: PLACE X,Y,[NORTH, EAST, SOUTH, WEST]; MOVE; LEFT; " +
                "RIGHT; REPORT. Type `quit` to exit the game.");
    }

    public static void createToyRobot() {
        Simulator simulator = Simulator.createDefaultSimulator();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // receive input
            String commandLine = scanner.nextLine();
            if (commandLine.equalsIgnoreCase("quit")) {
                LOGGER.info("Quitting...");
                break;
            }
            // parse the input and execute the command
            String report = executeCommand(simulator, commandLine);
            LOGGER.info("Report for the command {}: {}", commandLine, report);
            System.out.println(report);
            LOGGER.info(report);
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
                    output = "Placed " + (result ? "successfully" : "unsuccessfully");
                }  catch (IllegalActionException e) {
                    throw new IllegalActionException(e.getMessage());
                }
            } else {
                try {
                    output = simulator.play(command);
                } catch (InvalidRobotException | IllegalActionException exception) {
                    System.out.print(exception.getMessage() + ". Try again with the built-in commands!");
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

    public static ArrayList<String> simulateToyRobot(final String filePath) throws FileNotFoundException {
        if (filePath == null) {
            throw new InvalidRobotException("Invalid file path");
        }
        File file = new File(filePath);
        ArrayList<String> result = readFromFile(file);
        result.stream().forEach(r -> {
            System.out.println("Output: " + r);
        });
        return result;
    }

    private static ArrayList<String> readFromFile(final File file) {
        if (!file.exists()) {
            throw new InvalidRobotException("Input file does not exist");
        }
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Simulator simulator = Simulator.createDefaultSimulator();
            String line, output;
            while ((line = br.readLine()) != null) {
                output = executeCommand(simulator, line);
                if (line.equalsIgnoreCase("report")) {
                    result.add(output);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
