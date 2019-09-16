package fr.univparis.maljae;

import java.util.regex.*;
import java.util.concurrent.ThreadLocalRandom;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import org.apache.commons.io.FileUtils;
import org.json.*;

/*** This module stores tasks assignment. */
public class Assignment {

    /** For each team, there must be exactly one task. */
    private static HashMap<Team, Task> team_tasks = new HashMap<Team, Task> ();

    /** We must provide a trace of the assignment algorithm. */
    private static String trace;

    /** Returns a string representation for the trace. */
    public static String getTrace () { return trace; }

    public static void addTraceStep (String s) { trace += s + "\n"; }

    public static void assignTask (Team team, Task task) {
	if (team_tasks.containsKey (team)) {
	    throw new RuntimeException ("Team " + team.getIdentifier ()
					+ " already has a task.");
	}
	team_tasks.put (team, task);
	addTraceStep (team.getIdentifier ()
		      + " is assigned task "
		      + task.getIdentifier ());
    }

    public static String show () {
	String description = "";
	description += team_tasks.toString () + "\n";
	description += trace;
	return description;
    }

    public static void loadFrom (File f) throws IOException {
	JSONObject json = new JSONObject (FileUtils.readFileToString (f, "utf-8"));
	trace = json.getString ("trace");
	JSONArray assignment_json = json.getJSONArray ("assignment");
	for (int i = 0; i < assignment_json.length (); i++) {
	    JSONArray team_task = assignment_json.getJSONArray (i);
	    Team team = Teams.getTeam (team_task.getString (0));
	    Task task = Configuration.getTask (team_task.getString (1));
	    assignTask (team, task);
	}
    }

    public static void saveTo (File f) throws IOException {
	JSONObject json = new JSONObject ();
	json.put ("trace", trace);
	JSONArray assignment_json = new JSONArray ();
	for (HashMap.Entry<Team, Task> assignment : team_tasks.entrySet ()) {
	    JSONArray team_task = new JSONArray ();
	    team_task.put (assignment.getKey ().getIdentifier ());
	    team_task.put (assignment.getValue ().getIdentifier ());
	}
	json.put ("assignment", assignment_json);
	FileWriter fw = new FileWriter (f);
	fw.write (json.toString (2));
	fw.close ();
    }

}
