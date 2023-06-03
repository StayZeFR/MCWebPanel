package fr.stayze.system.template;

import fr.stayze.utils.ResourceLoader;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Template {

    private final String regexVARIABLE = "\\{\\{\\s*([^\\s]+)\\s*\\}\\}";
    private final String regexPATH = "\\{\\{>\\s*([^\\s]+)\\s*\\}\\}";

    public String replaceMarker(String input, Map<String, String> data) {
        StringBuilder output = new StringBuilder();

        Pattern pattern = Pattern.compile(this.regexVARIABLE + "|" + this.regexPATH);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String variable1 = matcher.group(1);
            String variable2 = matcher.group(2);

            if (variable1 != null) {
                String replacement = data.get(variable1);
                if (replacement != null) {
                    matcher.appendReplacement(output, replacement);
                }
            } else if (variable2 != null) {
                String replacement = ResourceLoader.loadFile("views/" + variable2);
                replacement = replacement.isEmpty() ? "[FILE NOT FOUND]" : replacement;
                matcher.appendReplacement(output, replacement);
            }
        }
        matcher.appendTail(output);

        return output.toString();
    }

}
