package fr.stayze.web.pages;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import fi.iki.elonen.NanoHTTPD;
import fr.stayze.MCWebPanelConfig;
import fr.stayze.utils.ResourceLoader;
import fr.stayze.utils.Utils;
import fr.stayze.web.View;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static fi.iki.elonen.NanoHTTPD.newFixedLengthResponse;

public abstract class Page extends View {

    private final String renderFile = "html/render.html";
    private final String templatesDir = "html/templates/";

    protected Map<String, String> headers;
    protected NanoHTTPD.Response response;
    protected NanoHTTPD.IHTTPSession session;
    protected NanoHTTPD.Method method;

    public Page(NanoHTTPD.IHTTPSession session) {
        this.session = session;
        this.method = this.session.getMethod();
        this.response = null;
        this.headers = session.getHeaders();

        this.data = new HashMap<String, String>();
        this.data.put("TITLE", MCWebPanelConfig.PL_NAME.getConfig());

        switch (method.toString()) {
            case "GET" -> {
                this.GET();
            }
            case "POST" -> {
                this.POST();
            }
        }
    }

    protected void GET() {
        if (!Utils.haveSession(this.session)) {
            this.redirect("/login");
        }
    }
    protected abstract void POST();

    protected void redirect(String path) {
        this.response = newFixedLengthResponse("Redirection...");
        this.response.addHeader("Location", path);
        this.response.setStatus(NanoHTTPD.Response.Status.REDIRECT);
    }

    protected String construct(Map<String, Object> data, String main, String... items) {
        String mainHTML = ResourceLoader.loadFile(this.templatesDir + main);
        StringBuilder subHTML = new StringBuilder();
        MustacheFactory mustacheFactory = new DefaultMustacheFactory();
        try {
            for (String item : items) {
                StringWriter writer = new StringWriter();
                Mustache render = mustacheFactory.compile(this.templatesDir + item);
                render.execute(writer, data).flush();
                subHTML.append(writer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return mainHTML.replace("--MAIN--", (subHTML.toString().length() > 0 ? subHTML.toString() : ""));
    }


    public NanoHTTPD.Response getResponse() {
        if (this.response == null) this.response = this.render();
        return this.response;
    }

}
