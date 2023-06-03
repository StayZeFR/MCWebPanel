package fr.stayze.system;

import fi.iki.elonen.NanoHTTPD;
import fr.stayze.app.config.Routes;
import fr.stayze.app.controller.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class App extends NanoHTTPD {

    private Routes _ROUTES;

    public App() throws InstantiationException, IllegalAccessException {
        super(3333);
        this._ROUTES = new Routes();
    }

    @Override
    public Response serve(IHTTPSession session) {

        try {
            String uri = session.getUri();
            System.out.println("URI -> " + uri);
            Controller controller = this._ROUTES.getController(uri);
            Map<String, String> data = new HashMap<>();
            if (session.getMethod() == Method.GET) {
                data = parseQueryParams(session.getQueryParameterString());
            } else if (session.getMethod() == Method.POST) {
                session.parseBody(new HashMap<>());
                data = session.getParms();
            }

            return controller.index(data);
        } catch (ResponseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, String> parseQueryParams(String queryString) {
        Map<String, String> queryParams = new HashMap<>();

        if (queryString != null) {
            String[] params = queryString.split("&");

            for (String param : params) {
                String[] keyValue = param.split("=");
                if (keyValue.length == 2) {
                    String key = keyValue[0];
                    String value = keyValue[1];
                    queryParams.put(key, value);
                }
            }
        }

        return queryParams;
    }

}
