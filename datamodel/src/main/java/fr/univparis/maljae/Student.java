package fr.univparis.maljae.datamodel;

import org.json.*;

public class Student {
    private String  email;
    private Boolean confirmed;
    private int groupeTd;

    Student (JSONObject json) {      //Json contructor for student
	email = json.getString ("email");
	confirmed = json.getBoolean ("confirmed");
  if (json.has("groupeTd")) groupeTd = json.getInt("groupeTd");
    }

    Student (String email0, Boolean confirmed0) {        //Simple constructor for student without specified TD group
	email = email0;
	confirmed = confirmed0;
    }
    Student (String email0, Boolean confirmed0,int groupeTd0) {      //Same constructor with a specified TD group
 email = email0;
 confirmed = confirmed0;
 groupeTd = groupeTd0;
   }

    public String getEmail () {
	return email;
    }

    public Boolean getConfirmed () {
	return confirmed;
    }

    public int getGroupeTd(){
  return groupeTd;
    }

    public void setConfirmed (Boolean b) {
	confirmed = b;
    }

    public void setGroupeTd(int g){
 groupeTd=g;
   }

    public String toString () {
  if(groupeTd>0&&groupeTd<5)return  email + "/" + confirmed + "/"+String.valueOf(groupeTd);
	return email + "/" + confirmed + "/";
    }

    public static Student fromString (String s) {            //Creating a student from a string entry
	String[] fields = s.split ("/");
	return new Student (fields[0], Boolean.parseBoolean (fields[1]));
    }

    public JSONObject toJSON () {          //Converting into json file
	JSONObject json = new JSONObject ();
	json.put ("email", email);
	json.put ("confirmed", confirmed);
  if(groupeTd>0&&groupeTd<5) json.put("groupeTd",groupeTd);
	return json;
    }

}
