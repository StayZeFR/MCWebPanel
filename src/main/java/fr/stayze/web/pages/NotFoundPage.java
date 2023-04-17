package fr.stayze.web.pages;

import fi.iki.elonen.NanoHTTPD;

public class NotFoundPage extends Page {

    public NotFoundPage(NanoHTTPD.IHTTPSession session) {
        super(session);
        this.templateName = "notFound.html";
    }

    @Override
    protected void GET() {

    }

    @Override
    protected void POST() {

    }

}
