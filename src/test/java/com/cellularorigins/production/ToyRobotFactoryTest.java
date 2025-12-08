package com.cellularorigins.production;

import com.cellularorigins.core.Direction;
import com.cellularorigins.core.Position;
import com.cellularorigins.simulation.GameBoard;
import com.cellularorigins.simulation.Robot;
import com.cellularorigins.simulation.Simulator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class ToyRobotFactoryTest {
    @Test
    void testRunToyRobot_ProcessesCommandsAndQuits() {
        // 0. Init a Simulator
        GameBoard gameboard = new GameBoard(5, 5);
        Position startPosition = new Position(0, 0, Direction.NORTH);
        Robot robot = new Robot(startPosition);
        Simulator simulator = Simulator.builder()
                .gameboard(gameboard)
                .robot(robot)
                .build();

        // 1. Setup Mock Dependencies and Controlled I/O
        String input = "PLACE 0,0,NORTH\nMOVE\nquit\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // 2. Execute the Testable Logic
        ToyRobotFactory.runToyRobot(simulator, inputStream, printStream);

        // 3. Verify Results
        String actualOutput = outputStream.toString().trim();
        String expectedOutput = "Placed successfully\n0,1,NORTH";

        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}
