package fr.stayze.web.pages;

import fi.iki.elonen.NanoHTTPD;

public class NotFoundPage extends Page {

    public NotFoundPage(NanoHTTPD.IHTTPSession session) {
        super(session);
        //this.data.put("MAIN", this.construct(this.subData, "errors/notFound.html"));
    }

    @Override
    protected void POST() {

    }

}
