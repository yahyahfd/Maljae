package fr.univparis.maljae.assignement;
import fr.univparis.maljae.datamodel.*;
import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Hello world!
 *
 */
public class Assign
{
    public static void main(String[] args)
    {
	System.out.println("maljae assigner version " + Configuration.version + " is not implemented yet.");
    }

    public static void alphabetical_order(Teams a)
 {
   Teams.Tnitialize_Seed_for_each_teams();//we initialize the seed before every sorting alghorithm
   Collections.sort(a.getTeams(),new SortbyMail());//we sort each team by alphabetical_order of their mail
   String trace="";
   for(int i=0; i<a.getTeams().size();i++){
     trace+="Identifier: "+a.getTeams().get(i).getIdentifier()+" Mail :"+a.getTeams().get(i).mail+"\n";
   }
   Assignment.addTraceStep("This is the alphabetical order of the teams :\n                       " +trace);//this will show in the trace the final alphabetical order with the mail of each team
 }

 public static void SortingAlgo(Teams a)
 {
   alphabetical_order(a);
   for(int i=0; i<a.getTeams().size();i++)
   {
     int indexForTrace=(i+(a.getTeams().get(i).getSeed()%(a.getTeams().size()-i)))-1;
     Assignment.addTraceStep("exchange "+a.getTeams().get(i)+" with the team in index i + (n_i%(k-i-1)) = "+ String.valueOf(indexForTrace)+ " wich is team "+a.getTeams().get(i).getIdentifier());//add a trace for the transposition
     Collections.swap(a.getTeams(), i, indexForTrace);//here we do the swapping using the alghorithm
   }
 }

 public static void AssignTask(Teams a)//we call all the sorting method in this and then assign each team a task
 {
   if(a.getTeams().size()==0)return;//just to check
   SortingAlgo(a);
   ArrayList<Task> tasks = a.getTeams().get(0).getPreferences(); // This is an arraylist created to contain all the task available, for me we put all the task in the preferecences

   for(int i=0; i<a.getTeams().size();i++)
   {
     for (int j=0;j<a.getTeams().get(i).getPreferences().size();j++) {
       if(tasks.contains(a.getTeams().get(i).getPreferences().get(j))){//if the task preferred is still available we can assign it and then delete the task frome the list
         Assignment.addTraceStep("The task "+a.getTeams().get(i).getPreferences().get(j)+" has been assign to this team ");//we show this when the task has been assigned
         Assignment.assignTask(a.getTeams().get(i),a.getTeams().get(i).getPreferences().get(j));
         tasks.remove(a.getTeams().get(i).getPreferences().get(j));
         break;//we only assign one task so we break the loop
       }
     }
     Assignment.addTraceStep("Team "+a.getTeams().get(i).getIdentifier()+" has "+tasks.size()+" tasks left to be assigned: "+ tasks.toString());//we show the number of task left for each team
   }
 }


}
