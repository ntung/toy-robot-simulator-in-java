package com.cellularorigins.production;

import com.cellularorigins.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    @Test
    public void testMainClassWithNullArguments() {
        int actual = Main.start(new String[]{});
        Assertions.assertEquals(0, actual);
        actual = Main.start(new String[]{""});
        assertEquals(0, actual);
        actual = Main.start(new String[]{"-h"});
        assertEquals(1, actual);
    }

    @Test
    public void testMainClassWithArguments() {
        int result = Main.start(new String[]{ "-f input.txt" });
        Assertions.assertEquals(0, result);
    }
}
