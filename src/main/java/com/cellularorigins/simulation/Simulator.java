package com.cellularorigins.simulation;

import com.cellularorigins.core.Position;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Simulator {
    private GameBoard gameboard;
    private Robot robot;

    public void placeRobot(final Position position) {
        robot.place(position);
    }


}
