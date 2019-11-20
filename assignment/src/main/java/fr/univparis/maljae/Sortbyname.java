package fr.univparis.maljae.assignement;
import fr.univparis.maljae.datamodel;
import java.util.*; 
import java.lang.*; 
import java.io.*; 
class Sortbyname implements Comparator<Student> 
{ 
    // Used for sorting in ascending order of 
    // roll name 
    public int compare(Student a, Student b) 
    { 
        return a.mail.compareTo(b.name); 
    } 
}
