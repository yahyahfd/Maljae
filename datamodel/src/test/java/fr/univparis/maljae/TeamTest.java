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
    
    Team.miseajourmail(testingteam);
    
    assertEquals(testingteam.mail,"alphonseelric@gmail.com");
    
  }
}
