package com.cellularorigins;

import com.cellularorigins.production.ToyRobotFactory;
import com.cellularorigins.production.ToyRobotReader;
import com.cellularorigins.utils.ArgumentHandler;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        int result = start(args);
        System.exit(result);
    }

    public static int start(String[] args) {
        System.out.print("Welcome to Cellular Origins!\n");
        Options options = ArgumentHandler.createOptions();
        try {
            if (args.length == 0) {
                ArgumentHandler.help(options);
                return 0;
            }
            CommandLine cmd = ArgumentHandler.getCliParser().parse(options, args);
            if (cmd.hasOption("f")) {
                String filePath = cmd.getOptionValue("f");
                LOGGER.info("Parsing and running the toy robot simulator from the input file: {}", filePath);
                ToyRobotReader.simulateToyRobot(filePath);
            } else if (cmd.hasOption("i")) {
                LOGGER.info("Interactive mode enabled.");
                if (!cmd.hasOption("c")) {
                    System.out.println("No config file provided. The toy robot simulator will be created.");
                    ToyRobotFactory.createToyRobot();
                } else {
                    // create a customised toy robot simulator
                    System.out.println("Build a custom toy robot simulator from a configuration file " +
                            "hasn't been supported yet!");
                }
            } else if (cmd.hasOption("h")) {
                ArgumentHandler.help(options);
                return 1;
            } else {
                System.out.println("Wrong syntax! Bye!");
                ArgumentHandler.help(options);
                LOGGER.debug("Wrong syntax! Bye! Exited!");
                return 0;
            }
        } catch (ParseException e) {
            LOGGER.debug("An error happened: {}", e.getMessage());
            ArgumentHandler.help(options);
            return 1;
        }
        return 0;
    }
}