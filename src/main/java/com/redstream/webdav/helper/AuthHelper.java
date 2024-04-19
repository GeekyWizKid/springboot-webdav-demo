package com.redstream.webdav.helper;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;

public class AuthHelper {
    public static String getBasicAuthHeader(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("ISO-8859-1")));
        return "Basic " + new String(encodedAuth);
    }
}
