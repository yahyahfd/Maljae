package fr.univparis.maljae.datamodel;

import java.net.*;
import org.json.JSONObject;

/** This class of objects represent task description. */
public class Task {

    private String identifier;
    private String title;
    private String url;
    private String description;

    /** JSONObject contructor for a task. */
    Task (JSONObject o) {
    	this.identifier = o.getString ("identifier");
    	this.title = o.getString ("title");
    	this.url = o.getString ("url");
    	this.description = o.getString ("description");
    }

    /** Changes the toString method so that it returns a string that shows the current tasks' identifier, title, url and description. */
    public String toString () {
	     return this.identifier + "\n" + this.title + "\n" + this.url + "\n" + this.description;
    }

    /** Returns a string that contains the current tasks' identifier. */
    public String getIdentifier () {
    	return identifier;
    }

}
