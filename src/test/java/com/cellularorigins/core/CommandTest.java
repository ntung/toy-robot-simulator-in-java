package com.cellularorigins.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommandTest {
    @Test
    public void testExecute() {
        Assertions.assertDoesNotThrow(() -> {});
    }

    @Test
    public void testFromMethod() {
        Assertions.assertEquals(Command.PLACE, Command.from("place"));
        Assertions.assertEquals(Command.MOVE, Command.from("move"));
        Assertions.assertEquals(Command.LEFT, Command.from("left"));
        Assertions.assertEquals(Command.RIGHT, Command.from("right"));
        Assertions.assertEquals(Command.REPORT, Command.from("report"));
        Assertions.assertEquals(Command.ABOUT, Command.from("about"));
        Assertions.assertEquals(Command.RESET, Command.from("reset"));
        Assertions.assertNull(Command.from("unknown"));
    }
}
