package fr.stayze.web;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import fi.iki.elonen.NanoHTTPD;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Server extends NanoHTTPD {

    public Server(int port) throws IOException {
        super(port);
        this.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("Server started on port 8080");
    }

    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        String method = session.getMethod().toString();
        Map<String, String> headers = session.getHeaders();
        Map<String, String> params = new HashMap<String, String>();
        try {
            session.parseBody(params);
        } catch (IOException | ResponseException e) {}

        // Data for the Mustache template
        Map<String, String> model = new HashMap<String, String>();
        model.put("players", String.valueOf(Bukkit.getOnlinePlayers().size()));

        return newFixedLengthResponse(Response.Status.OK, "text/html", Template.getTemplate("templates/index.html", model));

        /*String uri = session.getUri();
        String method = session.getMethod().toString();
        Map<String, String> headers = session.getHeaders();
        Map<String, String> params = new HashMap<String, String>();
        try {
            session.parseBody(params);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ResponseException e) {
            e.printStackTrace();
        }
        String body = "<html><head><title>NanoHTTPD Demo</title></head><body><h1>Hello, World!</h1></body></html>";
        return newFixedLengthResponse(Response.Status.OK, "text/html", body);*/

    }
}
