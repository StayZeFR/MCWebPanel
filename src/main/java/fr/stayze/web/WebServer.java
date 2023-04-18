package fr.stayze.web;

import fi.iki.elonen.NanoHTTPD;
import fr.stayze.database.Database;
import fr.stayze.utils.Utils;
import fr.stayze.web.pages.IndexPage;
import fr.stayze.web.pages.LoginPage;
import fr.stayze.web.pages.NotFoundPage;
import fr.stayze.web.pages.Page;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class WebServer extends NanoHTTPD {

    public WebServer(int port) {
        super(port);
    }

    @Override
    public Response serve(IHTTPSession session) {

        try {

            String uri = session.getUri();
            Page page = null;

            Map<String, String> headers = session.getHeaders();

            session.parseBody(new HashMap<>());

            switch (uri) {
                case "/" -> {
                    page = new IndexPage(session);
                }
                case "/login" -> {
                    page = new LoginPage(session);
                }
                case "/exit" -> {
                    System.out.println(1);
                    page = new LoginPage(session);
                    Map<String, String> cookies = Utils.parseCookie(session.getHeaders().get("cookie"));
                    Database.session().delete("ID_USER", Integer.parseInt(cookies.get("ID")));
                    page.getResponse().addHeader("Set-Cookie", "ID=;expires=Thu, 01 Jan 1970 00:00:00 GMT");
                    page.getResponse().addHeader("Set-Cookie", "TOKEN=;expires=Thu, 01 Jan 1970 00:00:00 GMT");
                    System.out.println("OK");
                }
                default -> {
                    page = new NotFoundPage(session);
                }
            }

            return page.getResponse();

        } catch (IOException | ResponseException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
