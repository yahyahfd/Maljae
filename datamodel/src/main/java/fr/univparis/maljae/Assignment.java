package fr.univparis.maljae.datamodel;

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
    private static ArrayList<String> trace=new ArrayList<String> ();

    /** Returns a string representation for the trace. */
    public static ArrayList<String>  getTrace () { return trace; }

    /** Adds a string to trace after removing all the specified characters inside replaceAll. */
    public static void addTraceStep (String key,String value){
      if(key==null||value==null||key.length()<1)return;
      String s=key+" : "+value;
      s=s.replaceAll("[\\[\\](){}]","");
      trace.add(s);
    }

    /** Assign a task to a team if it doesn't have any yet. */
    public static void assignTask (Team team, Task task) {
    	if (team_tasks.containsKey (team)) {
    	    throw new RuntimeException ("Team " + team.getIdentifier () + " already has a task.");
    	}
    	team_tasks.put (team, task);
    }

    /** Returns a string representation for each team and its assigned task then the trace. */
    public static String show () {
    	String description = "";
    	description += team_tasks.toString () + "\n";
    	description += trace;
    	return description;
    }

    /** We load the file f where we are stocking each team and the task that was assigned to it. */
    public static void loadFrom (File f) throws IOException {
    	JSONObject json = new JSONObject (FileUtils.readFileToString (f, "utf-8"));
      JSONArray a=json.getJSONArray("trace");
      for(int i=0;i<a.length();i++){
        trace.add(a.optString(i));
      }
    }

    /** We save the team and the task assigned inside the file f. */
    public static void saveTo (File f) throws IOException {
    	JSONObject json = new JSONObject ();
    	JSONArray assignment_json = new JSONArray ();
    	for (HashMap.Entry<Team, Task> assignment : team_tasks.entrySet ()) {
    	    JSONArray team_task = new JSONArray ();
    	    team_task.put (assignment.getKey ().getIdentifier ());
    	    team_task.put (assignment.getValue ().getIdentifier ());
          assignment_json.put(team_task);
    	}
    	JSONArray trace_json = new JSONArray ();
    	for (String  tr: trace) {
          trace_json.put(tr);
    	}
      json.put ("trace", trace_json);
    	json.put ("assignment", assignment_json);
    	FileWriter fw = new FileWriter (f);
    	fw.write (json.toString (2));
    	fw.close ();
    }

}
