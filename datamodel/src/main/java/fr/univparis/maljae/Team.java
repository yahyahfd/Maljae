package fr.univparis.maljae.datamodel;

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

    public static int[] lestock=new int[100];
    public static int ba=0;
    private String    identifier;
    public String     getIdentifier () { return identifier; }

    private ArrayList<Task>    preferences;
    public ArrayList<Task>     getPreferences() { return preferences; }

    private static ArrayList<Student> students;
    public ArrayList<Student> getStudents(){return students;} 
    public static String mail ;
    private Integer            secret;
    public Integer getSecret () { return secret; }
    public Integer seed;
    public Integer getSeed () { return seed; }
    public void setSeed(Integer seed) {
  		this.seed = seed;
  	}


    public void updateSecretFromString (String s) {
	secret = Integer.parseInt (s);
    }

    Team (Student creator) {           //Team constructor with a first student who is the creator
	identifier = generateRandomTeamIdentifier ();
	preferences = new ArrayList<Task> (Arrays.asList (Configuration.getTasks ()));
	students = new ArrayList<Student> (Configuration.getMaxNbUsersPerTeam ());
	students.add (creator);
	this.mail=creator.getEmail();
	secret = ThreadLocalRandom.current().nextInt(10, 100);
    }

    /* FIXME: The following code is ugly! */
    Team (File f) throws IOException {         //Creating team from json file
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
    public void saveTo (File f) throws IOException {         //We save the team with the preferences in a file
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

    public String preferencesToString () {        // If you want to print all the preferences from a team
	String result = "";
	for (int i = 0; i < preferences.size (); i++) {
	    result += preferences.get (i).getIdentifier () + ";";
	}
	return result;
    }

    public void updatePreferencesFromString (String s) {
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

    public String studentsToString () {     //If you want to print all the students from a team
	String result = "";
	for (int i = 0; i < students.size (); i++) {
	    result += students.get (i).toString () + ";";
	}
	return result;
    }

    public void updateStudentsFromString (String who, String s) {
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

    public String toString () {
	String description = identifier + "\n";
	description += preferencesToString () + "\n";
	description += studentsToString () + "\n";
	description += "secret:" + secret;
	return description;
    }

    private static String generateRandomTeamIdentifier () {
	String res="";
	int g=ThreadLocalRandom.current().nextInt(10000, Integer.MAX_VALUE);
	for(int a=0;a<lestock.length;a++){
		if(g==lestock[a]){
			generateRandomTeamIdentifier();
		}
	}
	lestock[ba]=g;
	ba=ba+1;
	res=res+"maljae"+g;
	return res;
    }

    public static boolean isValidTeamFileName (String fname) {
	Pattern p = Pattern.compile (".*-team.json");
	Matcher m = p.matcher (fname);
	return m.find ();
    }

    public void addStudent (Student eleve) {
      ArrayList<Team> n = Teams.getTeams();
      for (Team team : n) {
        ArrayList<Student> t= team.students;
        for(Student student : t ){
          if(student.getEmail().equals(eleve.getEmail())){
            break;
          }
        }
      }
      if(eleve!=null){
        this.students.add(eleve);
      }
    }


    public void removeStudent (String email) {
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
    public static void miseajourmail(Team equipe){
      for (Student student : students){
        if(student.getEmail().compareTo(equipe.mail)<0){
          equipe.mail=student.getEmail();
        }
      }
    }

}
