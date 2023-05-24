package fr.stayze.app.systems.routes;

import fr.stayze.app.models.Model;
import fr.stayze.app.views.View;

public class Route {

    private String _ID;
    private View _VIEW;
    private String _URI;

    public Route(String _ID, View _VIEW, String _URI) {
        this._ID = _ID;
        this._VIEW = _VIEW;
        this._URI = _URI;
    }

    public String getID() {
        return _ID;
    }

    public View getVIEW() {
        return _VIEW;
    }

    public String getURI() {
        return _URI;
    }
}
