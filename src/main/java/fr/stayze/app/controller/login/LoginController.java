package fr.stayze.app.controller.login;

import fi.iki.elonen.NanoHTTPD;
import fr.stayze.app.controller.Controller;

import java.util.Map;

public class LoginController extends Controller {

    @Override
    public NanoHTTPD.Response index(Map<String, String> data) {
        System.out.println(data);
        return this.render("login/login.html");
    }

}
