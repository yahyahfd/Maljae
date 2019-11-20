package fr.univparis.maljae.assignement;

import static org.junit.Assert.*;

import org.junit.*;

import java.io.*;

/**
 * Unit test for webserver.
 */
public class AssignTest
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
	Assign.main (args);
        assertEquals("maljae assigner version 0.1 is not implemented yet.\n", outContent.toString());
    }
}
