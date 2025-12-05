package com.cellularorigins;

import com.cellularorigins.production.ToyRobotFactory;
import com.cellularorigins.utils.ArgumentHandler;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.print("Welcome to Cellular Origins!\n");
        Options options = ArgumentHandler.createOptions();
        try {
            if (args.length == 0) {
                ArgumentHandler.help(options);
                System.exit(0);
            }
            CommandLine cmd = ArgumentHandler.getCliParser().parse(options, args);
            if (cmd.hasOption("f")) {
                String filePath = cmd.getOptionValue("f");
                LOGGER.info("Parsing and running the toy robot simulator from the input file: {}", filePath);
            } else if (cmd.hasOption("i")) {
                LOGGER.info("Interactive mode enabled.");
                ToyRobotFactory.usage();
                ToyRobotFactory.createToyRobot();
            } else if (cmd.hasOption("h")) {
                ArgumentHandler.help(options);
                System.exit(0);
            } else {
                System.out.println("Wrong syntax! Bye!");
                LOGGER.debug("Wrong syntax! Bye! Exited!");
                System.exit(0);
            }
        } catch (ParseException e) {
            LOGGER.debug("An error happened: {}", e.getMessage());
            ArgumentHandler.help(options);
            System.exit(0);
        }
    }
}