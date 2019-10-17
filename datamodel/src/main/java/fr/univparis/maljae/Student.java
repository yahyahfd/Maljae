package fr.univparis.maljae;

import org.json.*;

public class Student {
    private String  email;
    private Boolean confirmed;

    Student (JSONObject json) {
	email = json.getString ("email");
	confirmed = json.getBoolean ("confirmed");
    }

    Student (String email0, Boolean confirmed0) {
	email = email0;
	confirmed = confirmed0;
    }

    public String getEmail () {
	return email;
    }

    public Boolean getConfirmed () {
	return confirmed;
    }


    public void setConfirmed (Boolean b) {
	confirmed = b;
    }

    public String toString () {
	return email + "/" + confirmed + "/";
    }

    public static Student fromString (String s) {
	String[] fields = s.split ("/");
	return new Student (fields[0], Boolean.parseBoolean (fields[1]));//expected to be a third field for groupeTd
    }

    public JSONObject toJSON () {
	JSONObject json = new JSONObject ();
	json.put ("email", email);
	json.put ("confirmed", confirmed);
	return json;
    }

}
