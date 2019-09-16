package fr.univparis.maljae;

import org.json.*;

public class Student {
    private String  email;
    private Boolean confirmed;

    Student (JSONObject json) {
	email = json.getString ("email");
	confirmed = json.getBoolean ("confirmed");
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
	return email + "[" + confirmed + "]";
    }

    public JSONObject toJSON () {
	JSONObject json = new JSONObject ();
	json.put ("email", email);
	json.put ("confirmed", confirmed);
	return json;
    }

}
