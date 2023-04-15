package fr.stayze.web;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.StringWriter;
import java.util.Map;

public class Template {

    public static String getTemplate(String path, Map<String, String> params) {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(path);
        StringWriter writer = new StringWriter();
        mustache.execute(writer, params);
        return writer.toString();
    }

}
