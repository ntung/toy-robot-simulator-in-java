package com.cellularorigins.production;

import com.cellularorigins.Main;
import com.cellularorigins.exception.InvalidRobotException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MainTest {
    @Test
    public void testMainClassWithNullArguments() {
        Main.start(new String[]{});
    }

    @Test
    public void testMainClassWithArguments() {
        InvalidRobotException thrown = assertThrows(
            InvalidRobotException.class,
            () -> Main.start(new String[]{ "-f input.txt" }),
            "Expected start() to throw an exception when the file path doesn't exist"
        );
        Assertions.assertTrue(thrown.getMessage().equals("Input file does not exist"));
    }
}
