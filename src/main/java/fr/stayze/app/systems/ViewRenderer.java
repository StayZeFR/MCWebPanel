package fr.stayze.app.systems;

import fr.stayze.utils.ResourceLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ViewRenderer {

    private final String _PATH = "views/";

    protected String render(String template, HashMap<String, String> data) {
        template = (template.contains(".html") ? template : template + ".html");
        String html = this.format(ResourceLoader.loadFile(this._PATH + template, true), data);
        System.out.println(html);
        return html;
    }

    protected String render(String template) {
        return this.render(template, null);
    }

    private String format(String html, Map<String, String> data) {
        Map<String, Integer> markers = this.findMarkers(html);
        if (markers.isEmpty()) return html;
        for (String marker : markers.keySet()) {
            String str = (markers.get(marker) == 1 ? this.format(ResourceLoader.loadFile(this.getMarkerValue(marker), true), data) : this.format(data.get(this.getMarkerValue(marker)), data));
            html.replaceAll(marker, str);
        }
        return html;
    }

    private String getMarkerValue(String marker) {
        marker = marker.replaceAll(" ", "");
        return marker.substring(3, marker.length() - 2);
    }

    private Map<String, Integer> findMarkers(String html) {
        String regexFile = "\\[\\[>\\s*(.*?)\\s*\\]\\]";
        String regexVar = "\\[\\[-\\s*(.*?)\\s*\\]\\]";
        Map<String, Integer> markers = new HashMap<>();
        Scanner scanner = new Scanner(html);
        Pattern patternFile = Pattern.compile(regexFile);
        Pattern patternVar = Pattern.compile(regexVar);
        int lineNumber = 0;
        while (scanner.hasNextLine()) {
            lineNumber++;
            String line = scanner.nextLine();
            Matcher matcherFile = patternFile.matcher(line);
            Matcher matcherVar = patternVar.matcher(line);
            while (matcherFile.find()) {
                markers.put(matcherFile.group(), 1);
            }
            while (matcherVar.find()) {
                System.out.println(1);
                markers.put(matcherVar.group(), 2);
                System.out.println(2);
            }
        }
        scanner.close();
        return markers;
    }

}
