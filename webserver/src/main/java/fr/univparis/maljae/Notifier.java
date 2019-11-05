package fr.univparis.maljae.webserver;
import fr.univparis.maljae.datamodel.Configuration;
import fr.univparis.maljae.datamodel.Teams;
import java.io.*;

public class Notifier {

    /* FIXME: Email sending has to be implemented. For the moment, we store message
       FIXME: in files */
    public static void sendEmail (String email, String subject, String message)
	throws IOException
    {
	String filename = message.hashCode () + subject.hashCode () + "-email.txt";
	File f = new File (Configuration.getDataDirectory () + "/" + filename);
	FileWriter fw = new FileWriter (f);
	fw.write ("email: " + email + "\n" +
		  "subject: " + subject + "\n" +
		  "message:\n" + message + "\n");
	fw.close ();
    }

    public static void sendTeamCreation (String host, Token token)
	throws IOException
    {
	String message =
	    "Hello!\n" +
	    "You have requested the creation of a team.\n" +
	    "Here are the links to perform actions on this team.\n" +
	    "- To edit your team information :\n" +
	    "  http://" + host + "/team/edit/" + token.toString () + "\n" +
	    "- To delete your team :\n" +
	    "  http://" + host + "/team/delete/" + token.toString () + "\n";
	sendEmail (token.getEmail (), "[maljae] Team Creation", message);
    }
}
