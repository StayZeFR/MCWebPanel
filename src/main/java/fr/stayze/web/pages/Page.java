package fr.stayze.web.pages;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import fi.iki.elonen.NanoHTTPD;
import fr.stayze.MCWebPanelConfig;
import fr.stayze.utils.ResourceLoader;
import fr.stayze.utils.Utils;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static fi.iki.elonen.NanoHTTPD.newFixedLengthResponse;

public abstract class Page {

    private final String templatesDir = "html/";

    protected Map<String, String> headers;
    protected NanoHTTPD.Response response;
    protected NanoHTTPD.IHTTPSession session;
    protected NanoHTTPD.Method method;
    protected Map<String, String> data;
    protected String templateName;

    public Page(NanoHTTPD.IHTTPSession session) {
        this.session = session;
        this.method = this.session.getMethod();
        this.response = null;
        this.headers = session.getHeaders();

        this.data = new HashMap<String, String>();
        this.data.put("TITLE", MCWebPanelConfig.PL_NAME.getConfig());
        this.data.put("STYLE", ResourceLoader.loadFile("html/static/styles.css"));

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
        if (!Utils.haveSession(this.session)) this.redirect("login.html", "/login");
    }
    protected abstract void POST();

    protected NanoHTTPD.Response render() {
        MustacheFactory mustacheFactory = new DefaultMustacheFactory();
        Mustache mustache = mustacheFactory.compile(this.templatesDir + this.templateName);
        StringWriter writer = new StringWriter();
        mustache.execute(writer, this.data);
        return newFixedLengthResponse(NanoHTTPD.Response.Status.OK, "text/html", writer.toString());
    }

    protected void redirect(String templateName, String path) {
        this.templateName = templateName;
        this.response = this.render();
        this.response.addHeader("Location", path);
        this.response.setStatus(NanoHTTPD.Response.Status.REDIRECT);
    }

    public NanoHTTPD.Response getResponse() {
        if (this.response == null) this.response = this.render();
        return this.response;
    }

}
