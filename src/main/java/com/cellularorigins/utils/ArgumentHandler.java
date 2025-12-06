package com.cellularorigins.utils;

import lombok.Getter;
import org.apache.commons.cli.*;

/**
 * This class is used to handle the arguments of the static {@code main(String[] args)} method
 * in the {@link com.cellularorigins.Main} class.
 *
 * @author <a href="mailto:nvntung@gmail.com">Tung Nguyen</a>
 */
public class ArgumentHandler {
    /**
     * The default CLI Parser
     */
    @Getter
    private static final CommandLineParser cliParser = new DefaultParser();

    /**
     * The options holding all arguments of the main program.
     */
    private static final Options options = new Options();

    /**
     * Prints the help
     * @param options {@link  Options} representing all the options of the program.
     */
    public static void help(Options options) {
        HelpFormatter helper = new HelpFormatter();
        helper.printHelp("java -jar ToyRobotSimulator.jar", options, true);
    }

    /**
     * Creates the options of the program.
     *
     * @return {@link Options} object
     */
    public static Options createOptions() {
        String description = "Interactive mode - talk to the program at the console";
        Option optText = new Option("i", "interactive", false, description);
        optText.setRequired(false);
        description = "Input File instead of interacting with the program at the console";
        Option optFile = Option.builder("f")
                .longOpt("input-file")
                .argName("file")
                .desc(description)
                .hasArg(true)
                .build();
        optFile.setRequired(false);
        OptionGroup group = new OptionGroup();
        group.addOption(optText);
        group.addOption(optFile);
        options.addOptionGroup(group);
        return options;
    }
}
