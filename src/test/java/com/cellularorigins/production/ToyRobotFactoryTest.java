package com.cellularorigins.production;

import com.cellularorigins.core.Command;
import com.cellularorigins.core.Direction;
import com.cellularorigins.core.Position;
import com.cellularorigins.exception.InvalidRobotException;
import com.cellularorigins.simulation.GameBoard;
import com.cellularorigins.simulation.Robot;
import com.cellularorigins.simulation.Simulator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ToyRobotFactoryTest {
    private final Simulator simulator = new Simulator();
    @BeforeEach
    public void setUp() {
        GameBoard gameboard = new GameBoard(5, 5);
        Position startPosition = new Position(0, 0, Direction.NORTH);
        Robot robot = new Robot(startPosition);
        simulator.setRobot(robot);
        simulator.setGameboard(gameboard);
    }

    @Test
    public void testExecuteCommand() {
        String report = ToyRobotFactory.executeCommand(simulator, "REPORT");
        Assertions.assertNotNull(report);
        Assertions.assertEquals("0, 0, NORTH", report);
        report = ToyRobotFactory.executeCommand(simulator, "PLACE 0,0,EAST");
        Assertions.assertTrue(report.contains("Placed successfully"));
    }

    @Test
    public void testExecuteInvalidCommand() {
        String result = ToyRobotFactory.executeCommand(simulator, "PLACE");
        Assertions.assertEquals("Invalid command", result);
        result = ToyRobotFactory.executeCommand(simulator, "UNKNOW");
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testExecuteInvalidCommandThrowException() {
        InvalidRobotException thrown = assertThrows(
                InvalidRobotException.class,
                () -> ToyRobotFactory.placeCommand(simulator, "PLACE"),
                "Expected play() to throw an exception when the command is invalid"
        );
        Assertions.assertFalse(thrown.getMessage().contains("Invalid command"));
    }

    @Test
    public void testPlaceCommand() {
        boolean actual = ToyRobotFactory.placeCommand(simulator, "0,0,NORTH");
        Assertions.assertTrue(actual);
        // this place command tries to put the robot outside the board
        actual = ToyRobotFactory.placeCommand(simulator, "0,-1,NORTH");
        Assertions.assertFalse(actual);
    }

    @Test
    public void testPlaceInvalidCommand() {
        InvalidRobotException thrown = assertThrows(
            InvalidRobotException.class,
            () -> ToyRobotFactory.placeCommand(simulator, "PLACE 0,0,NORTH"),
            "Expected play() to throw an exception when the command is invalid"
        );
        Assertions.assertFalse(thrown.getMessage().contains("Invalid command"));
    }
}
