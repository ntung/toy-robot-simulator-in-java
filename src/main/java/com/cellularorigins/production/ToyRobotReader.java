package com.cellularorigins.production;

import com.cellularorigins.exception.InvalidRobotException;
import com.cellularorigins.simulation.Simulator;

import java.io.*;
import java.util.ArrayList;

public class ToyRobotReader {
    public static ArrayList<String> simulateToyRobot(final String filePath) {
        if (filePath == null) {
            throw new InvalidRobotException("Invalid file path");
        }
        File file = new File(filePath);
        ArrayList<String> result = readFromFile(file);
        result.stream().forEach(r -> System.out.println("Output: " + r));
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
