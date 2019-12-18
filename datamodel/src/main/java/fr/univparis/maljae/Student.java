package fr.univparis.maljae.datamodel;

import org.json.*;

public class Student {
    private String  email;
    private Boolean confirmed;

    /** Json contructor for student. */
    Student (JSONObject json) {
    	email = json.getString ("email");
    	confirmed = json.getBoolean ("confirmed");
    }

    /** Simple constructor for student. */
    Student (String email0, Boolean confirmed0) {
    	email = email0;
    	confirmed = confirmed0;
    }

    /** Returns the current student's email. */
    public String getEmail () {
    	return email;
    }

    /** Returns the current student's status, whether he is confirmed or not. */
    public Boolean getConfirmed () {
	     return confirmed;
    }

    /** Changes the current student's status to confirmed if b equals true, or not confirmed if b equals false. */
    public void setConfirmed (Boolean b) {
    	confirmed = b;
    }

    /** Changes the toString method so that it returns a string that shows the current student's email and status. */
    public String toString () {
    	return email + "/" + confirmed + "/";
    }

    /** Creating a student from a string entry. */
    public static Student fromString (String s) {
    	String[] fields = s.split ("/");
    	return new Student (fields[0], Boolean.parseBoolean (fields[1]));
    }

    /** Returns a JSONObject containing the current student's email and status. */
    public JSONObject toJSON () {
    	JSONObject json = new JSONObject ();
    	json.put ("email", email);
    	json.put ("confirmed", confirmed);
    	return json;
    }

}
