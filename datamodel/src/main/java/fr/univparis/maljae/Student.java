package fr.univparis.maljae.datamodel;

import org.json.*;

public class Student {
    private String  email;

    /** Json contructor for student. */
    Student (JSONObject json) {
    	email = json.getString ("email");
    }

    /** Simple constructor for student. */
    Student (String email0) {
    	email = email0;
    }

    /** Returns the current student's email. */
    public String getEmail () {
    	return email;
    }

    /** Creating a student from a string entry. */
    public static Student fromString (String s) {
    	return new Student (s);
    }

    /** Returns a JSONObject containing the current student's email and status. */
    public JSONObject toJSON () {
    	JSONObject json = new JSONObject ();
    	json.put ("email", email);
    	return json;
    }

}
