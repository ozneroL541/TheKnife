package it.uninsubria;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class ServerTKTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        outContent.reset();
    }

    @Test
    void sampleTest() {
        System.out.print("Hello, Server!");
        assertEquals("Hello, Server!", outContent.toString());
    }
    @Test
    void testShowHelpOption() {
        ServerTK.main(new String[]{"--help"});
        String output = outContent.toString();
        assertTrue(output.contains("Usage: java -jar TheKnifeServer.jar"));
        assertTrue(output.contains("--help"));
        assertTrue(output.contains("--version"));
    }

    @Test
    void testShowHelpShortOption() {
        ServerTK.main(new String[]{"-h"});
        String output = outContent.toString();
        assertTrue(output.contains("Usage: java -jar TheKnifeServer.jar"));
    }

    @Test
    void testShowVersionOption() {
        ServerTK.main(new String[]{"--version"});
        String output = outContent.toString();
        assertTrue(output.contains("TheKnife Server"));
        assertTrue(output.contains("1.0.0"));
    }

    @Test
    void testShowVersionShortOption() {
        ServerTK.main(new String[]{"-v"});
        String output = outContent.toString();
        assertTrue(output.contains("TheKnife Server"));
        assertTrue(output.contains("1.0.0"));
    }
}
