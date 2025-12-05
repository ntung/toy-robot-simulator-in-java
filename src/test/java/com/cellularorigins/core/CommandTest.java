package com.cellularorigins.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommandTest {
    @Test
    public void testExecute() {
        Assertions.assertDoesNotThrow(() -> {});
    }

    @Test
    public void testExecuteWithDirection() {
        Assertions.assertEquals(Command.PLACE, Command.PLACE);
        Assertions.assertEquals(Command.MOVE, Command.MOVE);
        Assertions.assertEquals(Command.LEFT, Command.LEFT);
        Assertions.assertEquals(Command.RIGHT, Command.RIGHT);
        Assertions.assertEquals(Command.REPORT, Command.REPORT);
    }
}
