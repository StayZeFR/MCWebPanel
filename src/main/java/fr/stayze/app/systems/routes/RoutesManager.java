package fr.stayze.app.systems.routes;

import fr.stayze.app.models.Model;
import fr.stayze.app.views.View;
import fr.stayze.app.views.errors.NotFound;

import java.util.HashMap;

public class RoutesManager {

    private HashMap<String, Route> _ROUTES;

    public RoutesManager() {
        this._ROUTES = new HashMap<String, Route>();
    }

    public String check(String uri) {
        uri = uri.toLowerCase();
        return (this._ROUTES.containsKey(uri) ? this._ROUTES.get(uri).getVIEW().get() : new NotFound().get());
    }

    public void get(String uri, Class<?> clazz) throws InstantiationException, IllegalAccessException {
        this.get(uri, clazz, "");
    }
    public void get(String uri, Class<?> clazz, String id) throws InstantiationException, IllegalAccessException {
        uri = uri.toLowerCase();
        this._ROUTES.put(uri, new Route(id, (View) clazz.newInstance(), uri));
    }

}
