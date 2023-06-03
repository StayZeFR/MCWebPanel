package fr.stayze.system;

import fr.stayze.app.controller.Controller;

import java.util.HashMap;

public abstract class RouteSys {

    private HashMap<String, Route> _ROUTES;

    public RouteSys() {
        this._ROUTES = new HashMap<>();
    }

    protected void addRoute(String uri, Class<?> clazz) throws InstantiationException, IllegalAccessException {
        Controller controller = (Controller) clazz.newInstance();
        Route route = new Route(uri, controller);
        this._ROUTES.put(uri, route);
    }

    public Controller getController(String uri) {
        return (this._ROUTES.containsKey(uri) ? this._ROUTES.get(uri).getController() : this._ROUTES.get("/404").getController());
    }

}
