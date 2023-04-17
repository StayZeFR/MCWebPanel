package fr.stayze.web.pages;

import fi.iki.elonen.NanoHTTPD;
import fr.stayze.database.Database;

import java.util.Map;

public class LoginPage extends Page {

    public LoginPage(NanoHTTPD.IHTTPSession session) {
        super(session);
        this.templateName = "login.html";
    }

    @Override
    protected void GET() {

    }

    @Override
    protected void POST() {

        Map<String, String> data = this.session.getParms();
        System.out.println(data);

    }

}
