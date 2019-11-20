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
   Collections.sort(a.getTeams(),new SortbyMail());//we sort each team by alphabetical_order of their mail
 }

 public static void SortingAlgo(Teams a)
 {
   alphabetical_order(a);
   for(int i=0; i<a.getTeams().size();i++)
   {
     int indexForTrace=(i+(a.getTeams().get(i).getSeed()%(a.getTeams().size()-i)))-1;
     Assignment.addTraceStep("exchange "+a.getTeams().get(i)+" with the team in index i + (n_i%(k-i-1)) = "+ String.valueOf(indexForTrace)+ " wich is team "+a.getTeams().get(i).getIdentifier());
     Collections.swap(a.getTeams(), i, indexForTrace);//here we do the swapping using the alghorithm
   }
 }

 public static void AssignTask(Teams a)//we call all the sorting method in this and then assign each team a task
 {
   if(a.getTeams().size()==0)return;//just to check
   SortingAlgo(a);
   ArrayList<Task> tasks = a.getTeams().get(0).getPreferences(); // This is an arraylist created to contain all the task available

   for(int i=0; i<a.getTeams().size();i++)
   {
     for (int j=0;j<a.getTeams().get(i).getPreferences().size();j++) {
       if(tasks.contains(a.getTeams().get(i).getPreferences().get(j))){//if the task preferred is still available we can assign it and then delete the task frome the list
         Assignment.assignTask(a.getTeams().get(i),a.getTeams().get(i).getPreferences().get(j));
         tasks.remove(a.getTeams().get(i).getPreferences().get(j));
         break;
       }
     }
   }
 }


}
