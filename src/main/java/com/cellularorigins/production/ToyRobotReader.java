package com.cellularorigins.production;

import com.cellularorigins.exception.InvalidRobotException;
import com.cellularorigins.simulation.Simulator;

import java.io.*;
import java.util.ArrayList;

/**
 * <p>A class for reading the input file and simulating a toy robot.</p>
 * <p>Scan README.md to see the format of an input file.</p>
 *
 * @author <a href="mailto:nvntung@gmail.com">Tung Nguyen</a>
 */
public class ToyRobotReader {

    /**
     * Simulates a toy robot by parsing a input file including all commands
     *
     * @param filePath {@link String} denoting the location of the file
     * @return {@link ArrayList} of all the statements as considered the output
     */
    public static ArrayList<String> simulateToyRobot(final String filePath) {
        if (filePath == null) {
            throw new InvalidRobotException("Invalid file path");
        }
        File file = new File(filePath);
        ArrayList<String> result  = new ArrayList<>();
        try {
            result = readFromFile(file);
        } catch (InvalidRobotException e) {
            System.out.println(filePath + " does not exist");
        }
        result.stream().forEach(r -> System.out.println("Output: " + r));
        return result;
    }

    /**
     * Reads the given file and executes the commands given in the files. Then, returns a list of all the outputs.
     *
     * @param file {@link File} denoting the file including all the commands for the robot
     * @return {@link ArrayList} list of all the outputs
     */
    private static ArrayList<String> readFromFile(final File file) {
        if (!file.exists()) {
            throw new InvalidRobotException("Input file does not exist");
        }
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Simulator simulator = Simulator.createDefaultSimulator();
            String line, output;
            while ((line = br.readLine()) != null) {
                output = simulator.execute(line);
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
