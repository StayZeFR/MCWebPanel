package fr.stayze.system;

import fr.stayze.app.controller.Controller;

public class Route {

    private String uri;
    private Controller controller;

    public Route(String uri, Controller controller) {
        this.uri = uri;
        this.controller = controller;
    }

    public String getUri() {
        return uri;
    }

    public Controller getController() {
        return controller;
    }
}
