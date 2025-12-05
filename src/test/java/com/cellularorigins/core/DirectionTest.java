package com.cellularorigins.core;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DirectionTest {
    private Direction direction;
    @BeforeEach
    public void setUp() {
        direction = Direction.NORTH;
    }

    @Test
    public void testFrom() {
        Assertions.assertEquals(Direction.NORTH, direction);
    }

    /**
     * Test an invalid direction
     */
    @Test
    public void testFromNoWhere_InvalidDirectionEnum() {
        Direction nullDirection = Direction.from("nowhere");
        Assertions.assertNull(nullDirection);
    }

    @Test
    public void testChangeDirectionFromNorthToWest() {
        Direction westDirection = direction.changeDirection(-1);
        Assertions.assertEquals(Direction.WEST, westDirection);
    }

    @Test
    public void testChangeDirectionFromNorthToEast() {
        Direction eastDirection = direction.changeDirection(-1);
        Assertions.assertEquals(Direction.WEST, eastDirection);
    }

    @Test
    public void testGetDirection() {
        Direction direction = Direction.from("south");
        Assertions.assertNotNull(direction);
        Assertions.assertEquals(direction.getDescription(), Direction.SOUTH.getDescription());
    }
}
