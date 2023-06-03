package fr.stayze.system.template;

import fi.iki.elonen.NanoHTTPD;
import fr.stayze.utils.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

import static fi.iki.elonen.NanoHTTPD.newFixedLengthResponse;

public abstract class Render extends Template {

    protected NanoHTTPD.Response render(String template) {
        return render(template, new HashMap<String, String>());
    }

    protected NanoHTTPD.Response render(String template, Map<String, String> data) {

        String input = ResourceLoader.loadFile("views/" + template, true);
        String output = this.replaceMarker(input, data);

        return newFixedLengthResponse(NanoHTTPD.Response.Status.OK, "text/html", output);
    }

}
