package fr.univparis.maljae.datamodel;

import java.io.*;
import java.util.*;
import org.json.*;
import java.util.ArrayList;

/** This module collects teams. */
public class Teams {

    /* FIXME: This may be not the right data structure... */
    private static final ArrayList<Team> teams = new ArrayList<Team> ();
    /** This method is used for an unitary test only. */
    public static void setTeams(ArrayList<Team> t){teams.addAll(t);}

    /** Load a team from a file. */
    public static void loadFrom (File d) throws IOException {
    	try{
    		for (File f : d.listFiles ()) {
            System.out.println(f.getName ());
    		    if (Team.isValidTeamFileName (f.getName ()))
    			teams.add (new Team (f));
    		}
    	}catch(Exception e){
        System.out.println(e);
    		System.out.println("Team was not found on this server");
        throw e;
    	}
    }

    /** Returns an arraylist of team containing teams. */
    public static ArrayList<Team> getTeams(){
      return teams;
    }

    /** Returns a team by its identifier. */
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

    /** Creates a team using an email as its creator's email. */
    public static Team createTeam (String email) throws IOException {
      Team newTeam = new Team (new Student (email, true));;
      for(Team team: teams){
        ArrayList<Student> t= team.getStudents();
        for(Student student : t ){
          if(student.getEmail().equals(email)){
            return null;
          }
        }
      }
      teams.add (newTeam);
      String filename = Configuration.getDataDirectory () + "/" + newTeam.getIdentifier () + "-team.json";
      newTeam.saveTo (new File (filename));
      return newTeam;
    }

    /** Saves a team into filename, which is a json. */
    public static void saveTeam (Team team) throws IOException {
	    String filename =
	    Configuration.getDataDirectory () + "/" +
	    team.getIdentifier () + "-team.json";
    	team.saveTo (new File (filename));
    }

    /** Initilazing seed for each team.
    * sum is used to sum all teams' secret numbers
    * The seed is calculated using the team's secret number
    */
    public static void Tnitialize_Seed_for_each_teams(){
      int Sum=0;
      for(int i=0; i<teams.size();i++){
        Sum+=teams.get(i).getSecret();
      }
      for(int i=0; i<teams.size();i++){
        teams.get(i).setSeed(Sum-teams.get(i).getSecret());
      }
    }

    /** removes the team t from the teams list. */
    public static void deleteTeam(Team t){
      teams.remove(t);
      String path=Configuration.getDataDirectory()+"/"+t.getIdentifier()+"-team.json";
      File f=new File(path);
      System.out.print(f.exists());
      f.delete();
    }
}
