package fr.stayze.app.controller.errors;

import fi.iki.elonen.NanoHTTPD;
import fr.stayze.app.controller.Controller;

import java.util.Map;

import static fi.iki.elonen.NanoHTTPD.newFixedLengthResponse;

public class NotFoundController extends Controller {

    @Override
    public NanoHTTPD.Response index(Map<String, String> data) {
        return this.render("errors/404.html");
    }

}

