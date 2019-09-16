package fr.univparis.maljae;

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

}
