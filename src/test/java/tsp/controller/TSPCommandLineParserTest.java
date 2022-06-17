package tsp.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TSPCommandLineParserTest {

    @Test
    void testEmptyParameters() {

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> TSPCommandLineParser.parse(new String[]{}));

        assertTrue(e.getMessage().contains("Either GUI or cmdline (or both) must be specified!"));
    }

    @Test
    void testBadNumberOfLocations() {

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> TSPCommandLineParser.parse(new String[]{"--problem", "random", "--cmdline", "--locations", "asd"}));

        assertTrue(e.getMessage().contains("Parsing number of locations failed."));
    }

}
