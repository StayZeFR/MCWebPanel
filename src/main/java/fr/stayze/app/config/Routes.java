package fr.stayze.app.config;

import fr.stayze.app.controller.errors.NotFoundController;
import fr.stayze.app.controller.home.HomeController;
import fr.stayze.app.controller.login.LoginController;
import fr.stayze.app.controller.widgets.NavigatorController;
import fr.stayze.system.RouteSys;

public class Routes extends RouteSys {

    public Routes() throws InstantiationException, IllegalAccessException {

        // APP ROUTES
        this.addRoute("/", HomeController.class);
        this.addRoute("/login", LoginController.class);

        // APP WIDGETS ROUTES
        this.addRoute("/widgets/navigator", NavigatorController.class);

        // APP ERRORS ROUTES
        this.addRoute("/404", NotFoundController.class);

    }

}
