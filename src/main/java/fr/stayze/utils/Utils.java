package fr.stayze.utils;

import fi.iki.elonen.NanoHTTPD;
import fr.stayze.database.Database;

import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static Map<String, String> parseCookie(String cookieString) {
        Map<String, String> cookieMap = new HashMap<>();
        if (cookieString != null) {
            String[] cookies = cookieString.split(";");
            for (String cookie : cookies) {
                String[] cookiePair = cookie.trim().split("=");
                if (cookiePair.length == 2) {
                    cookieMap.put(cookiePair[0], cookiePair[1]);
                }
            }
        }
        return cookieMap;
    }

    public static String generateToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[192];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    public static boolean haveSession(NanoHTTPD.IHTTPSession session) {
        try {
            Map<String, String> cookies = Utils.parseCookie(session.getHeaders().get("cookie"));
            if (cookies.containsKey("TOKEN")) {
                Map<String, String> _session = Database.session().find("TOKEN", cookies.get("TOKEN"));
                return !_session.isEmpty();
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return false;
    }

    public static Map<String, String> getUserByToken(NanoHTTPD.IHTTPSession session) {
        try {
            Map<String, String> cookies = Utils.parseCookie(session.getHeaders().get("cookie"));
            if (cookies.containsKey("TOKEN")) {
                Map<String, String> _session = Database.session().find("TOKEN", cookies.get("TOKEN"));
                return Database.user().find("ID", Integer.parseInt(_session.get("ID_USER")));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }

}
