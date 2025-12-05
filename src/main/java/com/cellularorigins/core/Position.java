package com.cellularorigins.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A class representing the robot position.
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Position {
    private int x;
    private int y;
    private Direction direction;

    /**
     * Updates the given position
     *
     * @param x the horizontal position
     * @param y the vertical position
     */
    public void updatePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("%d, %d, %s", x, y,  direction.getDescription());
    }
}
