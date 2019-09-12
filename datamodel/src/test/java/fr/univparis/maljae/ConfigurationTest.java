package fr.univparis.maljae;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for the Configuration module.
 */
public class ConfigurationTest
{
    /**
     * The maljae version is part of the configuration.
     */
    @Test
    public void versionCanBeAccessed()
    {
        assertTrue(! Configuration.version.equals (""));
    }
}
