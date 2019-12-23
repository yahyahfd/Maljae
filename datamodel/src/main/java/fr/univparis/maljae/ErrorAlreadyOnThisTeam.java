package fr.univparis.maljae.datamodel;
public class ErrorAlreadyOnThisTeam extends RuntimeException{
  public ErrorAlreadyOnThisTeam(){
    super();
  }
  public String toString(){
    return "This student is already in this team.";
  }
}
