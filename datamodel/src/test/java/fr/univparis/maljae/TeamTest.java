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

    testingteam.updatemail();

    assertEquals(testingteam.mail,"alphonseelric@gmail.com");

  }

  @Test
  public void updateStudentsFromStringTest() throws IOException{
    Team testingteam = new Team(new Student("zoeelric@gmail.com",true));
    testingteam.addStudent(new Student("alphonseelric@gmail.com",true));
    testingteam.addStudent(new Student("edwardelric@gmail.com",true));
    testingteam.addStudent(new Student("pereelric@gmail.com",true));
    testingteam.removeStudent("alphonseelric@gmail.com");
    testingteam.removeStudent("edwardelric@gmail.com");
    testingteam.removeStudent("pereelric@gmail.com");
    testingteam.removeStudent("zoeelric@gmail.com");
    //team is empty normally
    //checking
    assertEquals(Teams.getTeam("testingteam"),null);
  }
}
