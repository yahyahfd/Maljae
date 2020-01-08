package fr.univparis.maljae.assignement;
import fr.univparis.maljae.datamodel.*;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.net.*;
/**
 * Hello world!
 *
 */
public class Assign
{
    public static void main(String[] args)
    {
      File input = new File("datamodel/src/test/resources/config.json");

      try {

        Configuration.loadFrom (input);
      } catch(Exception e) {
        System.out.print(e);
      }
      AssignTask();
      try{
        Assignment.saveTo(new File("datamodel/src/test/resources/assignment1.json"));
      }catch(Exception e){
        System.out.print(e);
      }
    }

    public static void alphabetical_order()
 {

   Teams.Tnitialize_Seed_for_each_teams();//we initialize the seed before every sorting alghorithm
   Collections.sort(Teams.getTeams(),new SortbyMail());//we sort each team by alphabetical_order of their mail
   String trace="";
   for(int i=0; i<Teams.getTeams().size();i++){
     trace+=" Identifier: "+Teams.getTeams().get(i).getIdentifier()+" ,Mail: "+Teams.getTeams().get(i).mail+"<br>";
   }
   Assignment.addTraceStep("<br>Alphabetical Sorting","This is the alphabetical order of the teams :                   "+"<br>"+trace+"<br>");//this will show in the trace the final alphabetical order with the mail of each team
 }

 public static void SortingAlgo()
 {
   alphabetical_order();
   for(int i=0; i<Teams.getTeams().size()-1;i++)
   {
     int indexForTrace=i+(Teams.getTeams().get(i).getSeed()%(Teams.getTeams().size()-i-1));
     System.out.println(indexForTrace);
     Assignment.addTraceStep("<br>Transposition","exchange "+Teams.getTeams().get(i).getIdentifier()+" with the team in index i + (n_i%(k-i-1)) = "+ String.valueOf(indexForTrace)+ " wich is team "+Teams.getTeams().get(i).getIdentifier());//add a trace for the transposition
     Collections.swap(Teams.getTeams(), i, indexForTrace);//here we do the swapping using the alghorithm
   }
 }
 public static void AssignTask()//we call all the sorting method in this and then assign each team a task
 {
   loadFrom();
   if(Teams.getTeams().size()==0)return;//just to check
   SortingAlgo();
   ArrayList<Task> tasks = Teams.getTeams().get(0).getPreferences(); // This is an arraylist created to contain all the task available, for me we put all the task in the preferecences

   for(int i=0; i<Teams.getTeams().size();i++)
   {
     for (int j=0;j<Teams.getTeams().get(i).getPreferences().size();j++) {
       if(tasks.contains(Teams.getTeams().get(i).getPreferences().get(j))){//if the task preferred is still available we can assign it and then delete the task frome the list
         Assignment.addTraceStep("Assign","The task "+Teams.getTeams().get(i).getPreferences().get(j).getIdentifier()+" has been assign to team "+Teams.getTeams().get(i).getIdentifier());//we show this when the task has been assigned
         Assignment.assignTask(Teams.getTeams().get(i),Teams.getTeams().get(i).getPreferences().get(j));
         tasks.remove(Teams.getTeams().get(i).getPreferences().get(j));
         break;//we only assign one task so we break the loop
       }
     }
   }
 }
 public static void loadFrom(){
   try {
     Teams.loadFrom(new File("./maljae-data/"));

   } catch(Exception e) {
     System.out.print(e);
   }
 }

}
