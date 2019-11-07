package fr.univparis.maljae;

import java.util.StringTokenizer;
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

    private ArrayList<Task>    preferences;
    public ArrayList<Task>     getPreferences () { return preferences; }

    private ArrayList<Student> students;
    private Integer            secret;
    public Integer getSecret () { return secret; }
    public void updateSecretFromString (String s) {
	secret = Integer.parseInt (s);
    }

    Team (Student creator) {            // constructor for Team
	identifier = generateRandomTeamIdentifier ();
	preferences = new ArrayList<Task> (Arrays.asList (Configuration.getTasks ()));
	students = new ArrayList<Student> (Configuration.getMaxNbUsersPerTeam ());
	students.add (creator);
	secret = ThreadLocalRandom.current().nextInt(10, 100);
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
	preferences = new ArrayList<Task> (preferences_json.length ());
	for (int i = 0; i < preferences_json.length (); i++) {
	    String pid = preferences_json.getString (i);
	    preferences.add (i, Configuration.getTask (pid));
	}
	JSONArray students_json = json.getJSONArray ("students");
	students = new ArrayList<Student> (students_json.length ());
	for (int i = 0; i < students_json.length (); i++)
	    students.add (i, new Student (students_json.getJSONObject (i)));
    }

    /* FIXME: The following code is ugly! */
    public void saveTo (File f) throws IOException {
	JSONObject json = new JSONObject ();
	json.put ("identifier", identifier);
	json.put ("secret", secret);
	JSONArray preferences_json = new JSONArray ();
	for (int i = 0; i < preferences.size (); i++) {
	    if (preferences.get (i) != null) {
		preferences_json.put (preferences.get (i).getIdentifier ());
	    }
	}
	json.put ("preferences", preferences_json);
	JSONArray students_json = new JSONArray ();
	for (int i = 0; i < students.size (); i++) {
	    students_json.put (students.get (i).toJSON ());
	}
	json.put ("students", students_json);
	FileWriter fw = new FileWriter (f);
	fw.write (json.toString (2));
	fw.close ();
    }

    public String preferencesToString () {           //printing the preferences
	String result = "";
	for (int i = 0; i < preferences.size (); i++) {
	    result += preferences.get (i).getIdentifier () + ";";
	}
	return result;
    }

    public void updatePreferencesFromString (String s) {      // making new preferences list from string
	System.out.println ("Prefs: " + s);
	String[] fields = s.split (";");
	ArrayList<Task> newPreferences = new ArrayList<Task> ();
	for (int i = 0; i < fields.length; i++) {
	    newPreferences.add (Configuration.getTask (fields[i]));
	}
	// FIXME: We should check that newPreferences is a permutation
	// FIXME: of all task identifiers.
	this.preferences = newPreferences;
    }

    public String studentsToString () {       // Printing members of the team
	String result = "";
	for (int i = 0; i < students.size (); i++) {
	    result += students.get (i).toString () + ";";
	}
	return result;
    }

    public void updateStudentsFromString (String who, String s) {     //making new students list from string
	System.out.println (who + " " + s);
	String[] fields = s.split (";");
	ArrayList<Student> newStudents = new ArrayList<Student> ();
	for (int i = 0; i < fields.length; i++) {
	    newStudents.add (Student.fromString (fields[i]));
	}
	// FIXME: We should check that [who] did not change the status of
	// FIXME: other team members.
	this.students = newStudents;
    }

    public String toString () {                 //Printing a description of the team with the preferences and the members etc...
	String description = identifier + "\n";
	description += preferencesToString () + "\n";
	description += studentsToString () + "\n";
	description += "secret:" + secret;
	return description;
    }

    private static String generateRandomTeamIdentifier () {          
	return "maljae" + ThreadLocalRandom.current().nextInt(10000, Integer.MAX_VALUE);
    }

    public static boolean isValidTeamFileName (String fname) {     //checking file name
	Pattern p = Pattern.compile (".*-team.json");
	Matcher m = p.matcher (fname);
	return m.find ();
    }

    public void removeStudent (String email) {      //removing a student from a team
	Student found = null;
	for (Student student : students) {
	    if (student.getEmail ().equals (email)) {
		found = student;
		break;
	    }
	}
	if (found != null)
	    students.remove (found);
    }

}
