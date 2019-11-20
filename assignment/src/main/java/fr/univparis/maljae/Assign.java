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

  public static HashMap<Team, Task> AssignTask(Teams a)
	{
		ArrayList<Team> loc = SortingAlgo(a); // We create ArrayList with the final order of teams
		HashMap<Team, Task> HashMapLocal = new HashMap<Team,Task>(); // We create local Hash map which will contain the final assignment
		ArrayList<Task> takslock = null; // This is an arraylist created to contain all preference that are not available anymore

		for(int i=0; i<loc.size();i++)
		{
			int compteur =0; // We add compteur to identify which preference task going to be assign for each team
			while(takslock.contains(loc.get(i).getPreferences().get(compteur))) // While tasklock contain preference of the team
			{
				compteur+=1; // We add 1 to take the next preference of the team
			}
			HashMapLocal.put(loc.get(i),loc.get(i).getPreferences().get(compteur) ); //We add to HashMapLocal the final choose
			takslock.add(loc.get(i).getPreferences().get(compteur)); // Ans we add to the taksLock ArrayList the preference that we just took
		}

		return HashMapLocal;
	}







}
