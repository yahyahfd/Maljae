package fr.univparis.maljae;
import fr.univparis.maljae.Configuration;

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

    public static String[] alphabetical_order(Teams a)
	{
		String[] TeamsNamesLocalVariable= a.getTeamsNames();
	    Arrays.sort(TeamsNamesLocalVariable);
	    return TeamsNamesLocalVariable;
	}

	public static ArrayList<Team> SortingAlgo(Teams a)
	{
		ArrayList<Team> Numbers= a.getTeams();
		for(int i=0; i<Numbers.size();i++) // Browse all Arraylist element
		{
			Collections.swap(Numbers, i, (i+(Numbers.get(i).getSeed()%(Numbers.size()-i)))-1);
		}
		return Numbers;
	}
}
