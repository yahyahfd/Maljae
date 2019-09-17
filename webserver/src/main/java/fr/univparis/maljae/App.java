package fr.univparis.maljae;
import fr.univparis.maljae.Configuration;
import io.javalin.*;
import java.io.*;

/**
 * A webserver
 *
 */
public class App
{
    public static void about () {
        System.out.println("maljae server version "
			   + Configuration.version
			   + " running.");
    }

    public static Javalin launch () {
	Javalin app = Javalin.create (config -> {
		config.addStaticFiles ("public/");
		config.enableWebjars ();
	    }).start (8080);
	return app;
    }

    public static void loadConfiguration (String configFile)
    throws Exception
    {
	Configuration.loadFrom (new File (configFile));
    }

    public static void initialize ()
	throws IOException
    {
	new File (Configuration.getDataDirectory ()).mkdirs();
	Teams.loadFrom (new File (Configuration.getDataDirectory ()));
    }

    public static void main (String[] args) throws Exception
    {
	about ();
	loadConfiguration (args[0]);
	initialize ();
	Javalin app = launch ();
	TeamController.install (app);
    }
}
