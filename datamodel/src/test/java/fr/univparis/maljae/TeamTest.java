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
    URL url = this.getClass ().getResource ("/config.json");
    File input = new File (url.getFile());
    Configuration.loadFrom (input);
    Team testingteam = new Team(new Student("zoeelric@gmail.com"));
    testingteam.addStudent(new Student("alphonseelric@gmail.com"));
    testingteam.addStudent(new Student("edwardelric@gmail.com"));
    testingteam.addStudent(new Student("pereelric@gmail.com"));

    testingteam.updatemail();

    assertEquals(testingteam.mail,"alphonseelric@gmail.com");

  }

  @Test
  public void updateStudentsFromStringTest() throws Exception{
    URL url = this.getClass ().getResource ("/config.json");
    File input = new File (url.getFile());
    Configuration.loadFrom (input);
    Team testingteam = new Team(new Student("zoeelric@gmail.com"));
    testingteam.addStudent(new Student("alphonseelric@gmail.com"));
    testingteam.addStudent(new Student("edwardelric@gmail.com"));
    testingteam.addStudent(new Student("pereelric@gmail.com"));
    testingteam.removeStudent("alphonseelric@gmail.com");
    testingteam.removeStudent("edwardelric@gmail.com");
    testingteam.removeStudent("pereelric@gmail.com");
    testingteam.removeStudent("zoeelric@gmail.com");
    //team is empty normally
    //checking
    assertEquals(testingteam.getStudents().size(),0);
  }
}
