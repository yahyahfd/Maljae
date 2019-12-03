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
public class TeamTest{
  @Test
  public void UpdateMailTest()throws Exception{//Testing that the update of mail in a team is working
    Team testingteam = new Team(new Student("zoeelric@gmail.com",true));
    testingteam.addStudent(new Student("alphonseelric@gmail.com",true));
    testingteam.addStudent(new Student("edwardelric@gmail.com",true));
    testingteam.addStudent(new Student("pereelric@gmail.com",true));
    
    testingteam.miseajourmail(testingteam);
    
    assertEquals(testingteam.mail,"alphonseelric@gmail.com");
    
  }
  /*
  @Test
  public void updateStudentsFromStringTest()throws Exception{
	  Team testingteam = new Team(new Student("zoeelric@gmail.com",true));
	  testingteam.addStudent(new Student("alphonseelric@gmail.com",true));
	  testingteam.addStudent(new Student("edwardelric@gmail.com",true));
	  testingteam.addStudent(new Student("pereelric@gmail.com",true));
	  
	  Team testingteam2 = new Team(new Student("aarovisoe@gmail.com",true));
	  
	  ArrayList<Team> teams = new ArrayList<Team>();
	  teams.add(testingteam);
	  Teams.setTeams(teams);
	  
	  String studs = "chalice@gmail.com/true;michou@gmail.com/true;beber@gmail.com/true;alphonseelric@gmail.com/true";
	  
	  
	  testingteam2.updateStudentsFromString("TeamLeader",studs);
	  testingteam2.miseajourmail(testingteam2);
	  assertEquals(testingteam2.mail,"chalice@gmail.com");
	  
	  
  }*/
}
