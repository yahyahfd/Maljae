package fr.univparis.maljae.datamodel;
public class ErrorTooMuchStudent extends RuntimeException{
  public ErrorTooMuchStudent(){
    super();
  }
  public String toString(){
    return "You are trying to delete or add more than one students";
  }
}
