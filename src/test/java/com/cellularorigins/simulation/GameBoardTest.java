package com.cellularorigins.simulation;

import com.cellularorigins.core.Direction;
import com.cellularorigins.core.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameBoardTest {
    private GameBoard gameBoard;
    @BeforeEach
    public void setUp() {
        gameBoard = new GameBoard(5, 5);
    }
    @Test
    public void testGameBoard() {
        Assertions.assertNotNull(gameBoard);
    }

    @Test
    public void testPositionInsideBoard() {
        Position position = new Position(0, 0, Direction.NORTH);
        boolean isValid = gameBoard.validatePosition(position);
        Assertions.assertTrue(isValid);
    }

    @Test
    public void testPositionOutsideBoard() {
        Position position = new Position(-1, -1, Direction.SOUTH);
        boolean isValid = gameBoard.validatePosition(position);
        Assertions.assertFalse(isValid);
    }
}
