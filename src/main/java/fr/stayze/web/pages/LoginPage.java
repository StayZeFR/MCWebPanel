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
        this.templateName = "login.html";
    }

    @Override
    protected void GET() {
        if (Utils.haveSession(this.session)) {
            System.out.println("s ok");
            this.redirect("index.html", "/");
        }
    }

    @Override
    protected void POST()  {

        try {
            Map<String, String> data = this.session.getParms();
            Map<String, String> user = Database.user().find("USERNAME", data.get("username"));
            if (!user.containsKey("ID")) user = Database.user().find("EMAIL", data.get("username"));
            if (user.containsKey("ID")) {
                if (PasswordEncoder.compare(data.get("password"), user.get("PASSWORD"))) {
                    this.redirect("index.html", "/");
                    String token = Utils.generateToken();
                    this.response.addHeader("Set-Cookie", "ID=" + user.get("ID"));
                    this.response.addHeader("Set-Cookie", "TOKEN=" + token);
                    Database.session().insert(Integer.parseInt(user.get("ID")), token);
                    System.out.println("OK");
                } else {
                    // Error password !
                }
            } else {
                //Error username !
            }
        } catch (SQLException | UnsupportedEncodingException | NoSuchAlgorithmException e) { e.printStackTrace(); }

    }

}
