package fr.univparis.maljae.datamodel;

import org.json.*;

public class Student {
    private String  email;
    private Boolean confirmed;
    private int groupeTd;

    /** Json contructor for student. */
    Student (JSONObject json) {
    	email = json.getString ("email");
    	confirmed = json.getBoolean ("confirmed");
      if (json.has("groupeTd")) groupeTd = json.getInt("groupeTd");
    }

    /** Simple constructor for student without specified TD group. */
    Student (String email0, Boolean confirmed0) {
    	email = email0;
    	confirmed = confirmed0;
    }

    /** Simple constructor for student with a specified TD group. */
    Student (String email0, Boolean confirmed0,int groupeTd0) {
     email = email0;
     confirmed = confirmed0;
     groupeTd = groupeTd0;
   }

    /** Returns the current student's email. */
    public String getEmail () {
    	return email;
    }

    /** Returns the current student's status, whether it is confirmed that he is registered in a team or not. */
    public Boolean getConfirmed () {
	     return confirmed;
    }

    /** Returns the curent student's group number. */
    public int getGroupeTd(){
      return groupeTd;
    }

    /** Changes the current student's status to confirmed if b equals true, or not confirmed if b equals false. */
    public void setConfirmed (Boolean b) {
    	confirmed = b;
    }

    /** Changes the current student's group number to g. */
    public void setGroupeTd(int g){
      groupeTd=g;
    }

    /** Changes the toString Method so that it returns a string that shows the current student's email, status and group number. */
    public String toString () {
      if(groupeTd>0 && groupeTd<5) return  email + "/" + confirmed + "/"+String.valueOf(groupeTd);
    	return email + "/" + confirmed + "/";
    }

    /** Creating a student from a string entry. */
    public static Student fromString (String s) {
    	String[] fields = s.split ("/");
    	return new Student (fields[0], Boolean.parseBoolean (fields[1]));
    }

    /** Returns a JSONObject containing the current student's email, status and group number. */
    public JSONObject toJSON () {
    	JSONObject json = new JSONObject ();
    	json.put ("email", email);
    	json.put ("confirmed", confirmed);
      if(groupeTd>0 && groupeTd<5) json.put("groupeTd",groupeTd);
    	return json;
    }

}
