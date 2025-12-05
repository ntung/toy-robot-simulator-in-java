package com.cellularorigins.simulation;

import com.cellularorigins.core.Command;
import com.cellularorigins.core.Direction;
import com.cellularorigins.core.Position;
import com.cellularorigins.exception.IllegalActionException;
import com.cellularorigins.exception.InvalidRobotException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimulatorTest {
    private Simulator simulator;

    @BeforeEach
    public void setup() {
        GameBoard gameBoard = new GameBoard(5, 5);
        Robot robot = new Robot();
        Position position = new Position(0, 0, Direction.NORTH);
        robot.setPosition(position);
        simulator = new Simulator(gameBoard, robot);
    }

    @Test
    public void testStart() {
        Assertions.assertNotNull(simulator);
    }

    @Test
    public void testPlaceRobotInValidPosition() {
        Position position = new Position(0, 0, Direction.NORTH);
        boolean isValidPlacement = simulator.placeRobot(position);
        Assertions.assertTrue(isValidPlacement);
    }

    @Test
    public void testPlaceRobotInInvalidDirection() {
        Position position = new Position(-1, -1, Direction.NORTH);
        boolean isValidPlacement = simulator.placeRobot(position);
        Assertions.assertFalse(isValidPlacement);
    }

    @Test
    public void testPlayMethodWhenGameBoardNull() {
        simulator.setGameboard(null);
        InvalidRobotException thrown = assertThrows(
            InvalidRobotException.class,
            () -> simulator.play(Command.MOVE),
            "Expected play() to throw an exception when gameboard is null"
        );
        Assertions.assertTrue(thrown.getMessage().contains("Gameboard is null"));
    }

    @Test
    public void testPlayMethodWhenRobotNull() {
        simulator.setRobot(null);
        InvalidRobotException thrown = assertThrows(
            InvalidRobotException.class,
            () -> simulator.play(Command.LEFT),
            "Expected play() to throw an exception when robot is null"
        );
        Assertions.assertTrue(thrown.getMessage().contains("Robot is null"));
    }

    @Test
    public void testPlayMethodWhenCommandNull() {
        IllegalActionException thrown = assertThrows(
                IllegalActionException.class,
            () -> simulator.play(null),
            "Expected play() to throw an exception when command is null"
        );
        Assertions.assertTrue(thrown.getMessage().contains("Command is null"));
    }

    @Test
    public void testPlayMethodWhenRobotPositionNull() {
        simulator.getRobot().setPosition(null);
        Assertions.assertTrue(simulator.play(Command.LEFT).isEmpty());
    }

    @Test
    public void testPlayMethodWhenEverythingIsValid() {
        Robot robot = simulator.getRobot();
        robot.setPosition(new Position(0, 0, Direction.NORTH));
        String report = simulator.play(Command.MOVE);
        Assertions.assertEquals("0, 1, NORTH", report);
        report = simulator.play(Command.LEFT);
        Assertions.assertEquals("0, 1, WEST", report);
        report = simulator.play(Command.RIGHT);
        Assertions.assertEquals("0, 1, NORTH", report);
        report = simulator.play(Command.RIGHT);
        Assertions.assertEquals("0, 1, EAST", report);
    }

    @Test
    public void testCanMoveMethodAtLeftBottomCorner() {
        Assertions.assertEquals(Direction.NORTH, simulator.getRobot().getPosition().getDirection());
        Assertions.assertTrue(simulator.canMove());
    }

    @Test
    public void testCanMoveMethodAtRightBottomCorner() {
        Position position = new Position(4, 0, Direction.SOUTH);
        simulator.getRobot().setPosition(position);
        Assertions.assertEquals(Direction.SOUTH, simulator.getRobot().getPosition().getDirection());
        Assertions.assertFalse(simulator.canMove());
    }
}
