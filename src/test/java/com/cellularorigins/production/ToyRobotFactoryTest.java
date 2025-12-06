package com.cellularorigins.production;

import com.cellularorigins.core.Direction;
import com.cellularorigins.core.Position;
import com.cellularorigins.simulation.GameBoard;
import com.cellularorigins.simulation.Robot;
import com.cellularorigins.simulation.Simulator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void testCreateToyRobot() {
        ToyRobotFactory.createToyRobot();
        Assertions.assertNotNull(simulator.getRobot());
    }
}
