package fr.univparis.maljae;
import java.util.*;
import java.io.*;
import java.lang.Math;
import org.apache.commons.io.FileUtils;
import org.json.*;
import fr.univparis.maljae.Configuration;
import fr.univparis.maljae.Teams;
import fr.univparis.maljae.Team;

public final class Token {

    private static HashMap<Integer, Token> tokens = new HashMap<Integer, Token>();

    private Integer raw = 0;
    private Team team;
    private String email;

    Token (Team team0, String email0, Integer raw0) {
	this.team = team0;
	this.email = email0;
	this.raw = raw0;
	tokens.put (raw0, this);
    }

    public static Token createToken (Team team0, String email0)
	throws IOException
    {
	Integer raw = Math.abs (team0.hashCode () + email0.hashCode ());
	Token token = new Token (team0, email0, raw);
	token.saveToFile ();
	return token;
    }

    public static Token getToken (String raw)
	throws IOException
    {
	Integer i = Integer.parseInt (raw);
	Token result = null;
	result = tokens.get (i);
	if (result == null)
	    result = loadFromFile (i);
	return result;
    }

    public static Token loadFromFile (Integer raw) throws IOException {
	File f = new File (Configuration.getDataDirectory ()
			   + "/token" + raw + ".json");
	JSONObject json = new JSONObject (FileUtils.readFileToString (f, "utf-8"));
	Team team = Teams.getTeam (json.getString ("team"));
	String email = json.getString ("email");
	return new Token (team, email, raw);
    }

    public void saveToFile () throws IOException {
	File f = new File (Configuration.getDataDirectory ()
			   + "/token" + raw + ".json");
	FileWriter fw = new FileWriter (f);
	JSONObject json = new JSONObject ();
	json.put ("team", team.getIdentifier ());
	json.put ("email", email);
	fw.write (json.toString ());
	fw.close ();
    }

    String getEmail () { return email; }

    Team getTeam () { return team; }

    @Override
    public String toString () { return raw.toString (); }

}
