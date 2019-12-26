package fr.univparis.maljae.datamodel;
public class ErrorNotOnThisTeamAnymore extends RuntimeException{
  public ErrorNotOnThisTeamAnymore(){
    super();
  }
  public String toString(){
    return "This student was either already removed from this team or never was in it, you can't delete something that doesn't exist.";
  }
}
