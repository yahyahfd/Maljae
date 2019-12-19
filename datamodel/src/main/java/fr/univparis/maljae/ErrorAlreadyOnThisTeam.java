package fr.univparis.maljae.datamodel;
public class ErrorAlreadyOnThisTeam extends RuntimeException{
  public ErrorAlreadyOnThisTeam(){
    super();
  }
  public String toString(){
    return "You are already on this team so you can't add yourself";
  }
}
