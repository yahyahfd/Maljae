package fr.univparis.maljae.assignement;
import fr.univparis.maljae.datamodel;
class Sortbyname implements Comparator<Student> 
{ 
    // Used for sorting in ascending order of 
    // roll name 
    public int compare(Student a, Student b) 
    { 
        return a.mail.compareTo(b.name); 
    } 
}
