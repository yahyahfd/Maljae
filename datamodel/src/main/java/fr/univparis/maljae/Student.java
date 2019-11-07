package fr.univparis.maljae;

import org.json.*;

public class Student {              
    private String  email;
    private Boolean confirmed;
    private String groupeTd;//New adding

    Student (JSONObject json) {    //Java Script Object Notation
    groupeTd = groupeTd0;
	email = json.getString ("email");
	confirmed = json.getBoolean ("confirmed");
    }

    Student (String email0, Boolean confirmed0, String groupeTd0) {     //Constructor for Student
	this.email = email0;
	this.confirmed = confirmed0;
	this.groupeTd = groupeTd0;
    }

    public String getEmail () {     //Getter for email
	return email;
    }

    public Boolean getConfirmed () {    //Getter for confirmed
	return confirmed;
    }
    
    public String getGroupeTd(){//new Adding      //Getter for Groupe de TD
    return groupeTd;
    }

    public void setConfirmed (Boolean b) {     //Setter for confirmed
	confirmed = b;
    }

    public String toString () {              //toString method for email, confirmed and groupeTD
	return email + "/" + confirmed + "/" + groupeTd;
    }

    public static Student fromString (String s) {            //method to create a new student from strings
	String[] fields = s.split ("/");
	return new Student (fields[0], Boolean.parseBoolean (fields[1]),fields[2]);//expected to be a third field for groupeTd
    }

    public JSONObject toJSON () {
	JSONObject json = new JSONObject ();
	json.put ("email", email);
	json.put ("confirmed", confirmed);
	json.put("groupeTd",groupeTd);
	return json;
    }

}
