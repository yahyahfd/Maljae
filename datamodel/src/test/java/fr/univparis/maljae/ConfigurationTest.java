package fr.univparis.maljae;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ConfigurationTest
{
    /**
     * Version must be accesssible.
     */
    @Test
    public void versionMustBeAccessible()
    {
        assertTrue(! Configuration.version.equals (""));
    }
}
