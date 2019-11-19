package fr.univparis.maljae;

import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import org.apache.commons.io.FileUtils;
import org.json.*;

/** This module gives access to the configuration of the maljae instance.
 *
 *
 */
public class Configuration
{
    /** Version. We following semantic versioning conventions. */
    public static final String version = "0.1";

    /** Data directory. This is the place where we will put data files. */
    /* FIXME: This should be configurable! */
     private static String dataDirectory = "./maljae-data";

    public static String getDataDirectory () { return dataDirectory; }

    /** Dates are supposed to be written with the following format. */
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /** Opening date. Before this date, nobody can create a team. */
    private static Date openingDate = null;

    /** Closing date. After this date, nobody can change team-related data. */
    private static Date closingDate = null;

    /** Display the date range */
    public static String showDateRange () {
	return df.format (openingDate) + " - " + df.format (closingDate);
    }

    /** Minimal number of users per team. */
    private static int minNbUsersPerTeam = -1;
    public static int getMinNbUsersPerTeam () { return minNbUsersPerTeam; }

    /** Maximal number of users per team. Must be greater than minNbUsersPerTeam. */
    private static int maxNbUsersPerTeam = -1;
    public static int getMaxNbUsersPerTeam () { return maxNbUsersPerTeam; }

    /** Default number of teams per subject. */
    private static int defaultNbTeamsPerSubject = -1;
    public static int getDefaultNbTeamsPerSubject () { return defaultNbTeamsPerSubject; }

    /** Task descriptions. */
    private static Task[] tasks;

    public static Task getTask (String identifier) {
	for (int i = 0; i < tasks.length; i++) {
	    String tid = tasks[i].getIdentifier ();
	    if (tid.equals (identifier))
		return tasks[i];
	}
	return null;
    }

    public static Task[] getTasks () {
	return tasks;
    }

    public static void setDates(JSONObject j){
      try{
        openingDate = df.parse (j.getString ("opening_date"));
        closingDate = df.parse (j.getString ("closing_date"));
      }catch(Exception e){
        System.out.println("Incorrect Date(s)");
      }
    }
    public static void setNbUsersPerTeam(JSONObject j){
      minNbUsersPerTeam = j.getInt ("min");
    	maxNbUsersPerTeam = j.getInt ("max");
    }
    public static void setdefaultNbTeamsPerSubject(JSONObject j){
      defaultNbTeamsPerSubject = j.getInt ("default");
    }
    public static void tasksArray(JSONArray j){
      tasks = new Task [j.length ()];
      for (int index = 0; index < j.length (); index++) {
          tasks[index] = new Task (j.getJSONObject (index));
      }
    }
    /** Load configuration file in memory. */
    /* FIXME: This function is badly written! */
    public static void loadFrom (File f) throws Exception {
    	JSONObject json = new JSONObject (FileUtils.readFileToString (f, "utf-8"));
      setDates(json);
    	JSONObject rangeNbUsersPerTeam = json.getJSONObject ("nb_users_per_team");
      setNbUsersPerTeam(rangeNbUsersPerTeam);
    	JSONObject rangeNbTeamsPerSubject = json.getJSONObject ("nb_teams_per_subject");
      setdefaultNbTeamsPerSubject(rangeNbTeamsPerSubject);
    	JSONArray json_tasks = json.getJSONArray ("tasks");
      tasksArray(json_tasks);
    }
}
