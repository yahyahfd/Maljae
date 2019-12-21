package fr.univparis.maljae.webserver;
import fr.univparis.maljae.datamodel.*;
import io.javalin.*;
import io.javalin.plugin.rendering.FileRenderer;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinFreemarker;
import io.javalin.plugin.rendering.template.TemplateUtil;
import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import org.apache.commons.io.FileUtils;
import java.io.*;
import java.util.*;
import org.json.*;

/** API for team management. */
public class TeamController {

    public static void installTeamCreate (Javalin app) {
      app.post("/team/create", ctx -> {
        try{
          String email = ctx.formParam ("email");
          Team newteam = Teams.createTeam (email);
          Token newtoken = Token.createToken (newteam, email);
          String host = ctx.host ();
          Notifier.sendTeamCreation (host, newtoken);
          ctx.redirect("/team-creation-done.html");
        }catch(NullPointerException e){
          ctx.redirect("/team-creation-impossible.html");
        }
      });
    }

    public static void installTeamEdit (Javalin app) {
      app.get("/team/edit/:token", ctx -> {
        try{
          Token token = Token.getToken (ctx.pathParam ("token"));
          File f = new File (Configuration.getDataDirectory ()+ "/token" + token + ".json");
          String teamName = token.getTeam ().getIdentifier ();
          Team team = Teams.getTeam (teamName);
          ctx.render("/public/edit-team.ftl", TemplateUtil.model
            ("teamName", teamName,
            "secret", team.getSecret (),
            "students", team.studentsToString (),
            "preferences", team.preferencesToString (),
            "token", token.toString ()));
        }catch(Exception e){
          ctx.redirect("/team-exist-error.html");
        }
      });
    }

    public static void installTeamUpdate (Javalin app) {
      app.post("/team/update/:token", ctx -> {
        try{
          Token token = Token.getToken (ctx.pathParam ("token"));
          String who = token.getEmail ();
          String teamName = token.getTeam ().getIdentifier ();
          Team team = Teams.getTeam (teamName);
          String host = ctx.host ();
          team.updateSecretFromString (ctx.formParam ("secret"));
          if(team.updatePreferencesFromString (ctx.formParam ("preferences"))){
            ctx.redirect("/team-update-done.html");
            Teams.saveTeam (team);
            if(team.who(ctx.formParam ("students"))!=null){
              Edit editToConfirm=team.who(ctx.formParam ("students"));
              Notifier.sendTeamEditConfirm(host,token,editToConfirm);
            }
            Notifier.sendUpdate(host,token,who);
          }else{
            ctx.redirect("/preferences-error.html");
          }
        }catch(Exception e){
          ctx.redirect("/team-update-error.html");
        }
      });
    }

    public static void installConfirmationUpdate(Javalin app){
      app.get("/team/update-confirmed/:token/:student/:action",ctx ->{
        try{
          Token token = Token.getToken (ctx.pathParam ("token"));
          boolean act=Boolean.valueOf(ctx.pathParam ("action"));
          String st=ctx.pathParam ("student");
          String teamName = token.getTeam ().getIdentifier ();
          Team team = Teams.getTeam (teamName);
            try {
              team.updateStudentsFromString(st,act);//true if added or false if deleted
              if(team.studentsToString().length()!=0){
                Teams.saveTeam (team);
              }else{
                File f = new File (Configuration.getDataDirectory ()+ "/token" + token + ".json");
                Token.deleteTokenfile(f);
              }
            }catch(ErrorNotOnThisTeamAnymore e) {
              ctx.redirect("/team-update-errorDel.html");
            }catch(ErrorAlreadyOnThisTeam ee){
              ctx.redirect("/team-update-errorAdd.html");
            }
        }catch(Exception eee){
          ctx.redirect("/team-update-done.html");
        }
      });
    }

    public static void displayAssignementTrace(Javalin app){
      app.after ("/team/trace", ctx->{
        ArrayList<String> trace = new ArrayList<String>();
        try{
          File f=new File("datamodel/src/test/resources/assignment1.json");//For the moment we take the assignemnt file of the test but in the futur we should put the file location that will hold the data
          Assignment.loadFrom(f);
          trace=Assignment.getTrace();
        }catch(Exception e){
          System.out.println(e);
          trace.add("The tasks have not been assigned yet !");
        }
          ctx.render("public/display-trace.ftl",TemplateUtil.model
          ("trace",trace));
      });
    }

    public static void install (Javalin app) {
      JavalinRenderer.register(JavalinFreemarker.INSTANCE, ".ftl");
      installTeamCreate (app);
      installTeamEdit   (app);
      installTeamUpdate (app);
      installConfirmationUpdate(app);
      displayAssignementTrace(app);
    }
}
