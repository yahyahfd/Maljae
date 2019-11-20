package fr.univparis.maljae.assignement;
import fr.univparis.maljae.datamodel.*;
import java.util.*;
import java.lang.*;
import java.io.*;
class SortbyMail implements Comparator<Team>
{
    // Used for sorting in ascending order of
    // roll name
    public int compare(Team a, Team b)
    {
        return a.mail.compareTo(b.mail);
    }
}
