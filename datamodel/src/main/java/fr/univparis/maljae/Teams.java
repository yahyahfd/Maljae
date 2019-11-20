package fr.univparis.maljae;

import java.io.*;
import java.util.*;
import org.json.*;
import java.util.ArrayList;

/** This module collects teams. */
public class Teams {

    /* FIXME: This may be not the right data structure... */
    private static final ArrayList<Team> teams = new ArrayList<Team> ();

    public static void loadFrom (File d) throws IOException {
	try{
		for (File f : d.listFiles ()) {
		    if (Team.isValidTeamFileName (f.getName ()))
			teams.add (new Team (f));
		}
	}catch(Exception e){
		System.out.println("Team was not found on this server");
	}
    }

    public static Team getTeam (String identifier) throws IOException{
      try{
        for(Team team : teams){
          if(team.getIdentifier().equals(identifier))
          return team;
        }
      }catch(Exception e){
        System.out.println("Team was not found on this server");
      }
      return null;
    }

    public static void removeFromExistingTeam (String email) {
	for (Team team : teams) {
	    team.removeStudent (email);
	}
    }

    public static Team createTeam (String email) throws IOException {
	removeFromExistingTeam (email);
	Team newTeam = new Team (new Student (email, true));
	teams.add (newTeam);
	String filename =
	    Configuration.getDataDirectory () + "/" +
	    newTeam.getIdentifier () + "-team.json";
	newTeam.saveTo (new File (filename));
	return newTeam;
    }

    public static void saveTeam (Team team) throws IOException {
	String filename =
	    Configuration.getDataDirectory () + "/" +
	    team.getIdentifier () + "-team.json";
	team.saveTo (new File (filename));
    }

    public static String[] alphabetical_order(String[] TeamsNames)
 	   {
 	    	Arrays.sort(TeamsNames);
 	    	return TeamsNames;
 	   }

     public void Tnitialize_Seed_for_each_teams()
     {
        int Sum=0; // I created variakble to sum all secret numbers
        for(int i=0; i<teams.size();i++)
        {
          Sum+=teams.get(i).getSecret();
        }
        for(int i=0; i<teams.size();i++) // For each teams in the ArrayList I calculate their seed with their secret numbers
        {
            teams.get(i).setSeed(Sum-teams.get(i).getSecret());
        }
     }

  public static void SortingAlgo(ArrayList<Team> Numbers) // Method using sorting algorithm
	{
		for(int i=0; i<Numbers.size();i++) // Browse all Arraylist element
		{
			Collections.swap(Numbers, i, (i+(Numbers.get(i).getSeed()%(Numbers.size()-i)))-1);
		}
	}

}
