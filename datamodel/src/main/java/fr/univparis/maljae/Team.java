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
    /** Returns a string containing the current team's identifier. */
    public String     getIdentifier () { return identifier; }

    private ArrayList<Task>    preferences;
    /** Returns an arraylist of taks containing the current team's preferences. */
    public ArrayList<Task>     getPreferences() { return preferences; }

    private ArrayList<Student> students;
    /** Returns an arraylist of students, containing the current team's student list. */
    public ArrayList<Student> getStudents(){return students;}
    public String mail ;

    private Integer            secret;
    /** Returns the int secret. */
    public Integer getSecret () { return secret; }
    public Integer seed;
    /** Returns the int seed. */
    public Integer getSeed () { return seed; }
    /** Sets the int seed. */
    public void setSeed(Integer seed) {
  		this.seed = seed;
  	}

    /** Updates the int secret using the string s. */
    public void updateSecretFromString (String s) {
    	secret = Integer.parseInt (s);
    }

    /** Team constructor with a first student who is considered as the creator. */
    Team (Student creator) {
    	identifier = generateRandomTeamIdentifier ();
    	preferences = new ArrayList<Task> (Arrays.asList (Configuration.getTasks ()));
    	students = new ArrayList<Student> (Configuration.getMaxNbUsersPerTeam ());
    	students.add (creator);
    	this.mail=creator.getEmail();
    	secret = ThreadLocalRandom.current().nextInt(10, 100);
    }

    /** Creating a team from the file f. */
    /* FIXME: The following code is ugly! */
    Team (File f) throws IOException {
    	JSONObject json = new JSONObject (FileUtils.readFileToString (f, "utf-8"));
    	identifier = json.getString ("identifier");
    	if (! f.getName ().equals (identifier + "-team.json")) {
    	    throw new RuntimeException ("Inconsistency in the data model: " + f.getName ());
    	}
      mail =json.getString("mail");
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

    /** We save the team with the preferences in a file. */
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
      json.put("mail",mail);
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
     /**  Returns a string with all the preferences' identifiers separated by a ";". */
    public String preferencesToString () {
    	String result = "";
    	for (int i = 0; i < preferences.size (); i++) {
    	    result += preferences.get (i).getIdentifier () + ";";
    	}
    	return result;
    }

    /** Updates a team's preferences with the ones from the string s. */
    public boolean updatePreferencesFromString (String s) {
      int permut =0;
    	System.out.println ("Prefs: " + s);
    	String[] fields = s.split (";");
    	ArrayList<Task> newPreferences = new ArrayList<Task> ();
    	for (int i = 0; i < fields.length; i++) {
    	    newPreferences.add (Configuration.getTask (fields[i]));
    	}

      for(int b=0;b<this.preferences.size();b++){
        int a=0;
        while(a!=newPreferences.size()){
          if(this.preferences.get(b).getIdentifier()==newPreferences.get(a).getIdentifier()){
            permut++;
            break;
          }
        a++;
        }
      }

            if (permut==this.preferences.size()){
              this.preferences = newPreferences;
              return true;
            }else{
              return false;
            }
    }

    /** Returns a string with all the students from the current team. */
    public String studentsToString () {
    	String result = "";
    	for (int i = 0; i < students.size (); i++) {
    	    result += students.get (i).getEmail() + ";";
    	}
    	return result;
    }

      /** This check that we only add one students and that the students is not on the team already, we say that you only add a students at the end of the string*/
    public Student addingOneStudent(ArrayList<Student> n){
      if(n.size()-this.students.size()!=1)return null;
      boolean ok=true;
      for(int i=0;i<this.students.size();i++){
        if(!n.get(i).getEmail().equals(this.students.get(i).getEmail()))ok=false;//we check that all the students excepts from the one you add is on the team
        if(n.get(i).getEmail().equals(n.get(this.students.size()).getEmail()))ok=false;//we check that the students we add is not already on this list
      }
      if(ok==true)return n.get(students.size());
      return null;
    }

    /** This check that we only delete one students and that the other students are still on the team*/
    public Student deletingOneStudent(ArrayList<Student> n){
      if(n.size()-this.students.size()!=-1)return null;
      int indexOfChange=0;
      for(int i=0;i<this.students.size();i++){
        if(i==n.size()){
          indexOfChange=i;//this means we deleted the last students
          break;
        }
        if(!n.get(i).getEmail().equals(this.students.get(i).getEmail())){
          indexOfChange=i;//we get the index where the students is not on the team anymore
          break;
        }
      }
      boolean ok=true;
      for(int i=indexOfChange;i<n.size();i++){
        if(!n.get(i).getEmail().equals(this.students.get(i-1).getEmail()))ok=false;//we check that apart from the students deleted  everything is the same
      }
      if(ok==true)return this.students.get(indexOfChange);
      return null;
    }

/** Return the action to perform true=add false=delete and also the students that is added or deleted, we can only delete or add one student at the time*/
    public Edit who(String s){
      ArrayList<Student> newStudents = new ArrayList<Student> ();
      if(!s.isEmpty()){
        String[] fields = s.split (";");
        newStudents = new ArrayList<Student> ();
        for (int i = 0; i < fields.length; i++) {
          newStudents.add(Student.fromString (fields[i]));
        }
      }
      Student add=addingOneStudent(newStudents);
      Student del=deletingOneStudent(newStudents);
      if(add==null&&del==null)return null;//we aren't changing the team at all
      else if(add!=null)return new Edit(add,true);
      //this is the case if we add a students
      else return new Edit(del,false);
      //this is  the case if we delete a students
    }
    /** Updates the students in the current team with the string s. This is used so that "who" deletes himself from his team. */
    public void updateStudentsFromString (String student,boolean action) {
      if(action==true){
        this.addStudent(new Student(student));
      }else{
        this.removeStudent(student);
        if(this.students.isEmpty()){
          Teams.deleteTeam(this);
        }
      }
    }

    /** Changes the toString method so that it returns a string that shows the current team's identifier, preferences, students' list and the int secret. */
    public String toString () {
    	String description = identifier + "\n";
    	description += preferencesToString () + "\n";
    	description += studentsToString () + "\n";
    	description += "secret:" + secret;
    	return description;
    }

    /** Generates a random team Identifier. */
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

    /** Checks if fname is a valid team file name. */
    public static boolean isValidTeamFileName (String fname) {
    	Pattern p = Pattern.compile (".*-team.json");
    	Matcher m = p.matcher (fname);
    	return m.find ();
    }

    /** Adds the student stud to this team. */
    public void addStudent (Student stud) {
      ArrayList<Team> n = Teams.getTeams();
      for (Team team : n) {
        ArrayList<Student> t= team.students;
        for(Student student : t ){
          if(student.getEmail().equals(stud.getEmail())){
            throw new ErrorAlreadyOnThisTeam();
          }
        }
      }
      if(stud!=null){
        this.students.add(stud);
      }
    }

    /** Removes the student with email as his email from this team. */
    public void removeStudent (String email) {
	     Student found = null;
	      for (Student student : students) {
	         if (student.getEmail ().equals (email)) {
		           found = student;
		           break;
	         }
	  }
        if(found==null)throw new ErrorNotOnThisTeamAnymore();
      	if (found != null){
          ArrayList<Team> tlist= Teams.getTeams();
      	   students.remove (found);
           if(students.isEmpty()){
             tlist.remove(this);
           }
        }
    }

    /** Updates this team's email by using the longest one in the team. */
    public void updatemail(){
      for (Student student : students){
        if(student.getEmail().compareTo(this.mail)<0){
          this.mail=student.getEmail();
        }
      }
    }

}
