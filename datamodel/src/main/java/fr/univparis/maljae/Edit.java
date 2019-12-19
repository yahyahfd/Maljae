package fr.univparis.maljae.datamodel;
public class Edit{
  /** This is a class used to stock a students and and an action in the form of a boolean
  This class is used for updating a teams students*/
  private Student student;
  public Student getStudent(){
    return student;
  }
  public boolean getAction(){
    return action;
  }
  private boolean action;
  public Edit(Student st,boolean a){
    student=st;
    action=a;
  }
}
