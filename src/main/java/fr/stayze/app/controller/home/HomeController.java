package fr.stayze.app.controller.home;

import fi.iki.elonen.NanoHTTPD;
import fr.stayze.app.controller.Controller;

import java.util.Map;

public class HomeController extends Controller {

    @Override
    public NanoHTTPD.Response index(Map<String, String> data) {
        System.out.println(data);
        return this.render("home/index.html", data);
    }

}
