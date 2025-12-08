package com.cellularorigins.core;

import lombok.*;

/**
 * <p>A class representing the robot position.</p>
 *
 * @author <a href="mailto:nvntung@gmail.com">Tung Nguyen</a>
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
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
        this.x += x;
        this.y += y;
    }

    @Override
    public String toString() {
        return String.format("%d,%d,%s", x, y,  direction.getDescription());
    }
}
