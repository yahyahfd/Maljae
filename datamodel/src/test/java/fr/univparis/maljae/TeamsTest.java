package fr.univparis.maljae.datamodel;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.*;

import java.net.*;

/**
 * Unit test for simple App.
 */
public class TeamsTest{
  @Test
  public void seedInitialization(){
    try{
      Teams.createTeam("test@gmail.com");
    }
    catch (IOException e) {
        fail(e.getMessage());
    }
    Teams.Tnitialize_Seed_for_each_teams();
    int sum=0;
    for(Team t:Teams.getTeams()){
      sum+=t.getSecret();
    }
    int seed;
    for(Team t:Teams.getTeams()){
      seed=sum-t.getSecret();
      assertEquals(seed,(int)t.getSeed());
    }
  }
}
