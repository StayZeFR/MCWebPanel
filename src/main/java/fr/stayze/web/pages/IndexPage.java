package fr.stayze.web.pages;

import fi.iki.elonen.NanoHTTPD;

public class IndexPage extends Page {

    public IndexPage(NanoHTTPD.IHTTPSession session) {
        super(session);
        this.templateName = "index.html";
    }

    @Override
    protected void GET() {

    }

    @Override
    protected void POST() {

    }

}
