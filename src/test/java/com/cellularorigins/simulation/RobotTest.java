package com.cellularorigins.simulation;

import com.cellularorigins.core.Direction;
import com.cellularorigins.core.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RobotTest {
    private Robot robot;

    @BeforeEach
    public void setUp() {
        Position position = new Position(0, 0, Direction.NORTH);
        robot = new Robot(position);
    }

    @Test
    public void testMoveCommand() {
        robot.move();
        String expected = "0, 1, NORTH";
        String actual = robot.report();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testReportCommand() {
        robot.setPosition(new Position(2, 1, Direction.WEST));
        Assertions.assertEquals("2, 1, WEST", robot.report());
    }
}
