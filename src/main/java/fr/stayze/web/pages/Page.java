package fr.stayze.web.pages;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import fi.iki.elonen.NanoHTTPD;
import fr.stayze.MCWebPanelConfig;
import fr.stayze.utils.ResourceLoader;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static fi.iki.elonen.NanoHTTPD.newFixedLengthResponse;

public abstract class Page {

    private final String templatesDir = "html/";

    protected NanoHTTPD.Response response;
    protected NanoHTTPD.IHTTPSession session;
    protected NanoHTTPD.Method method;
    protected Map<String, String> data;
    protected String templateName;

    public Page(NanoHTTPD.IHTTPSession session) {
        this.session = session;
        this.method = this.session.getMethod();
        this.response = null;

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

    protected abstract void GET();
    protected abstract void POST();

    private NanoHTTPD.Response render() {
        MustacheFactory mustacheFactory = new DefaultMustacheFactory();
        Mustache mustache = mustacheFactory.compile(this.templatesDir + this.templateName);
        StringWriter writer = new StringWriter();
        mustache.execute(writer, this.data);
        return newFixedLengthResponse(NanoHTTPD.Response.Status.OK, "text/html", writer.toString());
    }

    public NanoHTTPD.Response getResponse() {
        this.response = this.render();
        return this.response;
    }

}
