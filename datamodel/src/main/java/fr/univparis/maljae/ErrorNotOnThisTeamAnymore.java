package fr.univparis.maljae.datamodel;
public class ErrorNotOnThisTeamAnymore extends RuntimeException{
  public ErrorNotOnThisTeamAnymore(){
    super();
  }
  public String toString(){
    return "You are not on this team anymore so you can't delete yourself";
  }
}
