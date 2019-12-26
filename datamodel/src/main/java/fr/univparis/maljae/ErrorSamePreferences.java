package fr.univparis.maljae.datamodel;
public class ErrorSamePreferences extends RuntimeException{
  public ErrorSamePreferences(){
    super();
  }
  public String toString(){
    return "No changes were made to preferences.";
  }
}
