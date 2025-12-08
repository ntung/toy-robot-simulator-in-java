package com.cellularorigins.production;

import com.cellularorigins.simulation.GameBoard;
import com.cellularorigins.simulation.Robot;
import com.cellularorigins.simulation.Simulator;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

/**
 * A class for manufacturing toy robots.
 *
 * @author <a href="mailto:nvntung@gmail.com">Tung Nguyen</a>
 */
@Getter
@Setter
public class ToyRobotFactory {
    /**
     * Declares the {@link Logger} instance for logging
     */
    private static final Logger LOGGER = LogManager.getLogger(ToyRobotFactory.class);

    public static void usage() {
        System.out.println("Play robot game now! Commands: PLACE X,Y,[NORTH, EAST, SOUTH, WEST]; MOVE; LEFT; " +
                "RIGHT; REPORT; ABOUT. Type `quit` or press `Ctrl+C` to exit the game.");
    }

    /**
     * Executes the main application logic, receiving all dependencies externally.
     * This method contains the core logic that needs testing.
     * * @param simulator The simulation engine (Injected Dependency)
     *
     * @param inputStream  The source of commands (e.g., System.in)
     * @param outputStream The destination for reports (e.g., System.out)
     */
    public static void runToyRobot(
            Simulator simulator,
            InputStream inputStream,
            PrintStream outputStream) {

        Scanner scanner = new Scanner(inputStream);

        while (true) {
            // Check if there is more input to process
            if (!scanner.hasNextLine()) {
                break; // Exit if input stream closes or has no more lines
            }

            // receive input
            String commandLine = scanner.nextLine().trim();

            if (commandLine.equalsIgnoreCase("quit")) {
                LOGGER.info("Quitting...");
                break;
            }

            // Avoid executing empty lines
            if (commandLine.isEmpty()) {
                continue;
            }

            // parse the input and execute the command
            String report = simulator.execute(commandLine);

            // Output to the user and log
            if (!Objects.equals(report, "")) {
                outputStream.println(report);
            }
            LOGGER.info("Report for the command {}: {}", commandLine, report);
            LOGGER.info(report);
        }
    }

    /**
     * The original entry point, now responsible only for wiring up the dependencies.
     */
    public static void createToyRobot() {
        // Concrete dependencies are instantiated here (The Composition Root)
        Simulator simulator = Simulator.createDefaultSimulator();
        usage();
        // Delegates the actual work to the testable method
        runToyRobot(simulator, System.in, System.out);
    }
}
