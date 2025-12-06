package com.cellularorigins.production;

import com.cellularorigins.simulation.Simulator;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
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
            String report = simulator.execute(commandLine);
            LOGGER.info("Report for the command {}: {}", commandLine, report);
            System.out.println(report);
            LOGGER.info(report);
        }
    }
}
