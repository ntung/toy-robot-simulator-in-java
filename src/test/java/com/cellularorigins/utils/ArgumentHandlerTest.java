package com.cellularorigins.utils;

import org.apache.commons.cli.Options;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArgumentHandlerTest {

    @Test
    public void testCreateOptions() {
        Options options = ArgumentHandler.createOptions();
        Assertions.assertNotNull(options);
    }

    @Test
    public void testPrintHelp() {
        Options options = ArgumentHandler.createOptions();
        ArgumentHandler.help(options);
        Assertions.assertNotNull(options);
    }
}
