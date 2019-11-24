package fr.univparis.maljae.webserver;
import fr.univparis.maljae.datamodel.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Notifier {
    public static void sendEmail (String email, String subject, String messageSend)throws IOException{
      String username = "maljae.sendmail@gmail.com";
      String password = "maljae123";
      Properties prop = new Properties();
  		prop.put("mail.smtp.host", "smtp.gmail.com");
      prop.put("mail.smtp.port", "465");
      prop.put("mail.smtp.auth", "true");
      prop.put("mail.smtp.socketFactory.port", "465");
      prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

      Session session = Session.getInstance(prop,new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(username,password);
        }
      });
      try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("maljae.sendmail@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
        message.setSubject(subject);
        message.setText(messageSend);
        Transport.send(message);
      } catch (MessagingException e) {
        e.printStackTrace();
      }
    }

    public static void sendTeamCreation (String host, Token token)
	throws IOException
    {
	String message =
	    "Hello!\n" +
	    "You have requested the creation of a team.\n" +
	    "Here are the links to perform actions on this team.\n" +
	    "- To edit your team information :\n" +
	    "  http://" + host + "/team/edit/" + token.toString () + "\n" +
	    "- To delete your team :\n" +
	    "  http://" + host + "/team/delete/" + token.toString () + "\n";
	sendEmail (token.getEmail (), "[maljae] Team Creation", message);
    }
    public static void sendUpdate(String host,Token token,String who)throws IOException{
      String message="Hello!\n" +
  	    who+" have edited the team.\n" +
  	    "Here are the links to perform actions on this team.\n" +
  	    "- To edit your team information :\n" +
  	    "  http://" + host + "/team/edit/" + token.toString () + "\n" +
  	    "- To delete your team :\n" +
  	    "  http://" + host + "/team/delete/" + token.toString () + "\n";
      for(Student student:token.getTeam().getStudents()){
        sendEmail(student.getEmail(),"[maljae] Team Updated",message);
      }
    }
}
