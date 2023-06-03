package fr.stayze.app.controller;

import fi.iki.elonen.NanoHTTPD;
import fr.stayze.system.template.Render;

import java.util.Map;

public abstract class Controller extends Render {

    public abstract NanoHTTPD.Response index(Map<String, String> data);

}
