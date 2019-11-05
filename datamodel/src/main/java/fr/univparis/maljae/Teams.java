package fr.univparis.maljae.datamodel;

import java.io.*;
import java.util.*;
import org.json.*;

/** This module collects teams. */
public class Teams {

    /* FIXME: This may be not the right data structure... */
    private static final ArrayList<Team> teams = new ArrayList<Team> ();

    public static void loadFrom (File d) throws IOException {
	for (File f : d.listFiles ()) {
	    if (Team.isValidTeamFileName (f.getName ()))
		teams.add (new Team (f));
	}
    }

    /* FIXME: Shouldn't we throw an exception when the team is not found? */
    public static Team getTeam (String identifier) {
	for (Team team : teams) {
	    if (team.getIdentifier ().equals (identifier))
		return team;
	}
	return null;
    }

    public static void removeFromExistingTeam (String email) {
	for (Team team : teams) {
	    team.removeStudent (email);
	}
    }

    public static Team createTeam (String email) throws IOException {
	removeFromExistingTeam (email);
	Team newTeam = new Team (new Student (email, true));
	teams.add (newTeam);
	String filename =
	    Configuration.getDataDirectory () + "/" +
	    newTeam.getIdentifier () + "-team.json";
	newTeam.saveTo (new File (filename));
	return newTeam;
    }

    public static void saveTeam (Team team) throws IOException {
	String filename =
	    Configuration.getDataDirectory () + "/" +
	    team.getIdentifier () + "-team.json";
	team.saveTo (new File (filename));
    }

}
