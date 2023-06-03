package fr.stayze.app.controller.widgets;

import fi.iki.elonen.NanoHTTPD;
import fr.stayze.app.controller.Controller;

import java.util.Map;

public class NavigatorController extends Controller {

    @Override
    public NanoHTTPD.Response index(Map<String, String> data) {
        return this.render("templates/assets/sidebar.html", data);
    }

}
