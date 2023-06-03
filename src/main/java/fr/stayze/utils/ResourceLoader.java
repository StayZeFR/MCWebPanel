package fr.stayze.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceLoader {

    public static String loadFile(String resource) { return loadFile(resource, false); }
    public static String loadFile(String resource, Boolean jump) {

        ClassLoader classLoader = ResourceLoader.class.getClassLoader();

        InputStream inputStream = classLoader.getResourceAsStream(resource);
        if (inputStream == null) {
            System.out.println("Fichier de ressources introuvable");
            return null;
        }

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        try {
            line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                if (jump) stringBuilder.append("\n");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) { throw new RuntimeException(e); }

        return stringBuilder.toString();

    }

}
