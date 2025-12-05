package com.cellularorigins.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositionTest {
    private Position position;

    @BeforeEach
    public void setUp() {
        position = new Position(0,0, Direction.NORTH);
    }

    @Test
    public void testUpdatePosition() {
        position.updatePosition(1, 0);
        Assertions.assertEquals(1, position.getX(), 0);
    }

    @Test
    public void testToString() {
        String expectedString = "0, 0, NORTH";
        Assertions.assertEquals(expectedString, position.toString());
    }
}
