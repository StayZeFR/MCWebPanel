package fr.stayze.web.pages;

import fi.iki.elonen.NanoHTTPD;
import fr.stayze.database.Database;
import fr.stayze.utils.PasswordEncoder;
import fr.stayze.utils.Utils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;

public class LoginPage extends Page {

    public LoginPage(NanoHTTPD.IHTTPSession session) {
        super(session);
        //this.data.put("MAIN", this.construct(this.subData, "login.html"));
    }

    @Override
    protected void GET() {
        if (Utils.haveSession(this.session)) {this.redirect("/");}
    }

    @Override
    protected void POST()  {

        try {
            Map<String, String> data = this.session.getParms();
            Map<String, String> user = Database.user().find("USERNAME", data.get("username"));
            if (!user.containsKey("ID")) user = Database.user().find("EMAIL", data.get("username"));
            if (user.containsKey("ID")) {
                if (PasswordEncoder.compare(data.get("password"), user.get("PASSWORD"))) {
                    this.redirect("/");
                    String token = Utils.generateToken();
                    this.response.addHeader("Set-Cookie", "TOKEN=" + token + ";");
                    Database.session().delete("ID_USER", Integer.parseInt(user.get("ID")));
                    Database.session().insert(Integer.parseInt(user.get("ID")), token);
                } else {
                    // Error password !
                }
            } else {
                //Error username !
            }
        } catch (SQLException | UnsupportedEncodingException | NoSuchAlgorithmException e) { e.printStackTrace(); }

    }

}
