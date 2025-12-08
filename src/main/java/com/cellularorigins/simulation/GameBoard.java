package com.cellularorigins.simulation;

import com.cellularorigins.core.Position;

/**
 * <p>A class representing the game board.</p>
 * <p>The game board is defined several rows and columns. By default, it is a square table.</p>
 *
 * @param rows the amount rows
 * @param cols the amount columns
 * @author <a href="mailto:nvntung@gmail.com">Tung Nguyen</a>
 */
public record GameBoard(int rows, int cols) {
    /**
     * Validates a specific position.
     * Remember that the position's coordinates is based on (0, 0).
     * Therefore, a valid position should be the one which the x-ordinate and
     * y-ordinate sit in the ranges of [0, rows-1] and [0, cols-1] respectively.
     *
     * @param position A {@link Position} representing the current location of the robot.
     * @return true if the position is in the ranges. Otherwise, it returns false.
     */
    public boolean validatePosition(Position position) {
        int xCoordinate = position.getX();
        int yCoordinate = position.getY();
        return xCoordinate <= rows && xCoordinate >= 0 &&
                yCoordinate <= cols && yCoordinate >= 0;
    }
}
