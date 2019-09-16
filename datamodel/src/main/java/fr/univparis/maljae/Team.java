package fr.univparis.maljae;

import java.util.regex.*;
import java.util.concurrent.ThreadLocalRandom;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import org.apache.commons.io.FileUtils;
import org.json.*;

/*** The team of students. */
public class Team {

    private String    identifier;
    public String     getIdentifier () { return identifier; }

    private Task[]    preferences;
    public Task[]     getPreferences () { return preferences; }

    private Student[] students;
    private Integer   secret;

    Team (Student creator) {
	identifier = generateRandomTeamIdentifier ();
	preferences = Configuration.getTasks ();
	students = new Student[Configuration.getMaxNbUsersPerTeam ()];
	secret = null;
    }

    /* FIXME: The following code is ugly! */
    Team (File f) throws IOException {
	JSONObject json = new JSONObject (FileUtils.readFileToString (f, "utf-8"));
	identifier = json.getString ("identifier");
	if (! f.getName ().equals (identifier + "-team.json")) {
	    throw new RuntimeException ("Inconsistency in the data model: " + f.getName ());
	}
	secret     = json.getInt ("secret");
	JSONArray preferences_json = json.getJSONArray ("preferences");
	preferences = new Task [preferences_json.length ()];
	for (int i = 0; i < preferences_json.length (); i++) {
	    String pid = preferences_json.getString (i);
	    preferences[i] = Configuration.getTask (pid);
	}
	JSONArray students_json = json.getJSONArray ("students");
	students = new Student [students_json.length ()];
	for (int i = 0; i < students_json.length (); i++)
	    students[i] = new Student (students_json.getJSONObject (i));
    }

    /* FIXME: The following code is ugly! */
    public void saveTo (File f) throws IOException {
	JSONObject json = new JSONObject ();
	json.put ("identifier", identifier);
	json.put ("secret", secret);
	JSONArray preferences_json = new JSONArray ();
	for (int i = 0; i < preferences.length; i++) {
	    preferences_json.put (preferences[i].getIdentifier ());
	}
	json.put ("preferences", preferences_json);
	JSONArray students_json = new JSONArray ();
	for (int i = 0; i < students.length; i++) {
	    students_json.put (students[i].toJSON ());
	}
	json.put ("students", students_json);
	FileWriter fw = new FileWriter (f);
	fw.write (json.toString (2));
	fw.close ();
    }

    public String preferencesToString () {
	String result = "";
	for (int i = 0; i < preferences.length; i++) {
	    result += preferences[i].getIdentifier () + " ";
	}
	return result;
    }

    public String studentsToString () {
	String result = "";
	for (int i = 0; i < students.length; i++) {
	    result += students[i].toString () + " ";
	}
	return result;
    }

    public String toString () {
	String description = identifier + "\n";
	description += preferencesToString () + "\n";
	description += studentsToString () + "\n";
	description += "secret:" + secret;
	return description;
    }

    private static String generateRandomTeamIdentifier () {
	return "team" + ThreadLocalRandom.current().nextInt(10000, 1 << 31);
    }

    public static boolean isValidTeamFileName (String fname) {
	Pattern p = Pattern.compile (".*-team.json");
	Matcher m = p.matcher (fname);
	return m.find ();
    }

}
