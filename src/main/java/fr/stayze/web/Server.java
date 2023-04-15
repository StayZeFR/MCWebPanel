package fr.stayze.web;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import fi.iki.elonen.NanoHTTPD;
import fr.stayze.database.DBUser;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Server extends NanoHTTPD {

    public Server(int port) throws IOException {
        super(port);
        this.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("Server started on port 8080");
    }

    @Override
    public Response serve(IHTTPSession session) {

        try {

            Map<String, String> model = new HashMap<String, String>();
            model.put("TITLE", "MCWebPanel");

            String uri = session.getUri();
            String method = session.getMethod().toString();
            System.out.println(method);
            Map<String, String> headers = session.getHeaders();
            Map<String, String> params = new HashMap<String, String>();

            session.parseBody(params);

            if (!isUserLoggedIn(session) && !"/login".equals(uri)) return redirect("/login");
            if ("/".equals(uri)) {
                return newFixedLengthResponse(Response.Status.OK, "text/html", Template.getTemplate("templates/index.html", model));
            } else if ("/login".equals(uri)) {
                if (isUserLoggedIn(session)) return newFixedLengthResponse(Response.Status.OK, "text/html", Template.getTemplate("templates/index.html", model));
                else return newFixedLengthResponse(Response.Status.OK, "text/html", Template.getTemplate("templates/login.html", model));
            } else {
                return newFixedLengthResponse(Response.Status.NOT_FOUND, MIME_PLAINTEXT, "404 Not Found");
            }
        } catch (IOException | ResponseException | SQLException ex) { throw new RuntimeException(ex); }

    }

    public static boolean isUserLoggedIn(IHTTPSession session) throws SQLException {
        return session != null && session.getParms().containsKey("id") && (DBUser.isUserHaveSession((Integer.parseInt(session.getParms().get("id"))), session.getParms().get("token")));
    }

    public static Response redirect(String path) {
        Response response = newFixedLengthResponse(Response.Status.REDIRECT, "text/plain", "Redirection en cours");
        response.addHeader("Location", path);
        return response;
    }

}
