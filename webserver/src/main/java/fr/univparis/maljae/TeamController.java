package fr.univparis.maljae.webserver;
import fr.univparis.maljae.datamodel.Configuration;
import fr.univparis.maljae.datamodel.Team;
import fr.univparis.maljae.datamodel.Teams;
import io.javalin.*;
import io.javalin.plugin.rendering.FileRenderer;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinFreemarker;
import io.javalin.plugin.rendering.template.TemplateUtil;
import java.io.*;

/** API for team management. */
public class TeamController {

    public static void installTeamCreate (Javalin app) {
	app.post("/team/create", ctx -> {
		String email = ctx.formParam ("email");
		Team newteam = Teams.createTeam (email);
		Token newtoken = Token.createToken (newteam, email);
		String host = ctx.host ();
		Notifier.sendTeamCreation (host, newtoken);
		ctx.redirect("/team-creation-done.html");
	    });
    }

    public static void installTeamEdit (Javalin app) {
	app.get("/team/edit/:token", ctx -> {
		Token token = Token.getToken (ctx.pathParam ("token"));
		String teamName = token.getTeam ().getIdentifier ();
		Team team = Teams.getTeam (teamName);
		ctx.render("/public/edit-team.ftl", TemplateUtil.model
			   ("teamName", teamName,
			    "secret", team.getSecret (),
			    "students", team.studentsToString (),
			    "preferences", team.preferencesToString (),
			    "token", token.toString ()));
	    });
    }

    public static void installTeamUpdate (Javalin app) {
	app.post("/team/update/:token", ctx -> {
		Token token = Token.getToken (ctx.pathParam ("token"));
		String who = token.getEmail ();
		String teamName = token.getTeam ().getIdentifier ();
		Team team = Teams.getTeam (teamName);
		team.updateSecretFromString (ctx.formParam ("secret"));
		team.updateStudentsFromString (who, ctx.formParam ("students"));
		team.updatePreferencesFromString (ctx.formParam ("preferences"));
		Teams.saveTeam (team);
    String host = ctx.host ();
    Notifier.sendUpdate(host,token,who);
		ctx.redirect("/team-update-done.html");
	    });
    }

    public static void install (Javalin app) {
	JavalinRenderer.register(JavalinFreemarker.INSTANCE, ".ftl");
	installTeamCreate (app);
	installTeamEdit   (app);
	installTeamUpdate (app);
    }

}
