package fr.univparis.maljae.webserver;
import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;
import javax.mail.*;
public class NotifierTest{
  @Test
  public void should_throw_exception_when_emailTarget_doesNot_exist(){
    assertThrows(MessagingException.class,()->{
      Notifier.sendEmail("123230493IZENFEJDJNXcdjkccsc@fzofne.com","testsubject","testmessage");
    });
  }
}
