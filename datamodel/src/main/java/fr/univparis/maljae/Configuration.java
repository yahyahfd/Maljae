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

    private static String dataDirectory = "./maljae-data";
    
    public static void setDataDirectory(String n){
      dataDirectory=n;
    }

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

    /** Load configuration file in memory. */
    /* FIXME: This function is badly written! */
    public static void loadFrom (File f) throws Exception {
	JSONObject json = new JSONObject (FileUtils.readFileToString (f, "utf-8"));
	openingDate = df.parse (json.getString ("opening_date"));
	closingDate = df.parse (json.getString ("closing_date"));
	JSONObject rangeNbUsersPerTeam = json.getJSONObject ("nb_users_per_team");
	minNbUsersPerTeam = rangeNbUsersPerTeam.getInt ("min");
	maxNbUsersPerTeam = rangeNbUsersPerTeam.getInt ("max");
	JSONObject rangeNbTeamsPerSubject = json.getJSONObject ("nb_teams_per_subject");
	defaultNbTeamsPerSubject = rangeNbTeamsPerSubject.getInt ("default");
	JSONArray json_tasks = json.getJSONArray ("tasks");
	tasks = new Task [json_tasks.length ()];
	for (int index = 0; index < json_tasks.length (); index++) {
	    tasks[index] = new Task (json_tasks.getJSONObject (index));
	}
    }
}
