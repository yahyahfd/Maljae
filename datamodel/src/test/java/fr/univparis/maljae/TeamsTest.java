package fr.univparis.maljae.datamodel;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.*;

import java.net.*;
import java.util.*;
import org.json.*;
import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class TeamsTest{
  @Test
  public void seedInitialization()throws Exception{//we check that the seed are initialized correctly following the algorithm
    URL url = this.getClass ().getResource ("/config.json");
    File input = new File (url.getFile());
    Configuration.loadFrom (input);
    ArrayList<Team> teams=new ArrayList<Team>();
    ArrayList<Student> students=new ArrayList<Student>();
    for(int i=0;i<5;i++){
      students.add(new Student("test"+String.valueOf(i)+"@gmail.com"));
    }
    for(int i=0;i<5;i++){
      teams.add(new Team(students.get(i)));
    }
    Teams.setTeams(teams);
    Teams.Tnitialize_Seed_for_each_teams();
    int sum=0;
    for(Team t:Teams.getTeams()){
      sum+=t.getSecret();
    }
    int seed;
    if(Teams.getTeams().size()==0){
      fail("size=0");
    }
    for(Team t:Teams.getTeams()){
      seed=sum-t.getSecret();
      assertEquals(seed,(int)t.getSeed());
    }
  }
}
