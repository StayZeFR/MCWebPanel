package fr.stayze.app;

import fi.iki.elonen.NanoHTTPD;
import fr.stayze.app.systems.routes.RoutesManager;
import fr.stayze.app.views.Index;

public class Application extends NanoHTTPD {

    private RoutesManager _ROUTES;

    public Application(int port) throws InstantiationException, IllegalAccessException {
        super(port);
        //this._ROUTES = new RoutesManager();
        //this.initRoutes();
        //System.out.println("OKAYYYYY");
    }

    private void initRoutes() throws InstantiationException, IllegalAccessException {
        this._ROUTES.get("/", Index.class);
        this._ROUTES.get("/test", Index.class);
    }

    @Override
    public Response serve(IHTTPSession session) {

        String uri = session.getUri();

        //Map<String, String> headers = session.getHeaders();

        //session.parseBody(new HashMap<>());

        System.out.println("uri -> " + uri);

        return newFixedLengthResponse(this._ROUTES.check(uri));

    }

}
