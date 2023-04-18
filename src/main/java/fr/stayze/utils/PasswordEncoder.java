package fr.stayze.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordEncoder {

    public static String encode(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes("UTF-8"));
        String encoded = Base64.getEncoder().encodeToString(hash);
        return encoded;
    }

    public static boolean compare(String password, String hash) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String encodedPassword = encode(password);
        return encodedPassword.equals(hash);
    }

}
