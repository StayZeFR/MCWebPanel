package fr.stayze.web;

import fi.iki.elonen.NanoHTTPD;
import fr.stayze.web.pages.IndexPage;
import fr.stayze.web.pages.LoginPage;
import fr.stayze.web.pages.NotFoundPage;
import fr.stayze.web.pages.Page;

import java.io.IOException;
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

            session.parseBody(new HashMap<>());

            switch (uri) {
                case "/" -> {
                    page = new IndexPage(session);
                }
                case "/login" -> {
                    page = new LoginPage(session);
                }
                default -> {
                    page = new NotFoundPage(session);
                }
            }

            return page.getResponse();

        } catch (IOException | ResponseException e) {
            throw new RuntimeException(e);
        }
    }
}
