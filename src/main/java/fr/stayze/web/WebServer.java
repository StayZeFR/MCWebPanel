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
            Response response = null;

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
                    response = newFixedLengthResponse("");
                    response.addHeader("Location", "/login");
                    response.setStatus(NanoHTTPD.Response.Status.REDIRECT);
                    Map<String, String> cookies = Utils.parseCookie(session.getHeaders().get("cookie"));
                    Map<String, String> _session = Database.session().find("TOKEN", cookies.get("TOKEN"));
                    Database.session().delete("ID_USER", Integer.parseInt(_session.get("ID_USER")));
                }
                default -> {
                    page = new NotFoundPage(session);
                }
            }

            response = (page == null ? response : page.getResponse());
            return response;

        } catch (IOException | ResponseException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
