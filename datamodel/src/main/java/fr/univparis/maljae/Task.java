package fr.univparis.maljae;

import java.net.*;
import org.json.JSONObject;

/** This class of objects represent task description. */
public class Task {

    private String identifier;
    private String title;
    private String url;
    private String description;

    Task (JSONObject o) {
	this.identifier = o.getString ("identifier");
	this.title = o.getString ("title");
	this.url = o.getString ("url");
	this.description = o.getString ("description");
    }

    public String toString () {
	return
	    this.identifier + "\n" +
	    this.title + "\n" +
	    this.url + "\n" +
	    this.description;
    }

    public String getIdentifier () {
	return identifier;
    }

}
