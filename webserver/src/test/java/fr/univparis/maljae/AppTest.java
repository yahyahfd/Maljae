package fr.univparis.maljae;

import static org.junit.Assert.*;

import org.junit.*;

import java.io.*;

/**
 * Unit test for webserver.
 */
public class AppTest
{

    /*

      Setup console redirection.

    */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
	System.setOut(new PrintStream(outContent));
	System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
	System.setOut(originalOut);
	System.setErr(originalErr);
    }

    @Test
    public void mainShouldDisplayInvite()
    {
	String[] args = {};
	App.main (args);
        assertEquals("maljae server version 0.1 running.\n", outContent.toString());
    }
}
