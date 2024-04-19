package com.redstream.webdav.service;

import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpPropfind extends HttpRequestBase {
    public static final String METHOD_NAME = "PROPFIND";

    public HttpPropfind(String uri) {
        super();
        setURI(URI.create(uri));
    }

    public HttpPropfind(URI uri) {
        super();
        setURI(uri);
    }

    public HttpPropfind(String uri, int depth) {
        this(uri);
        setDepth(depth);
    }

    public HttpPropfind(URI uri, int depth) {
        this(uri);
        setDepth(depth);
    }

    @Override
    public String getMethod() {
        return METHOD_NAME;
    }

    public void setDepth(int depth) {
        // WebDAV Depth header: "0", "1" or "infinity"
        String depthValue = (depth == 0) ? "0" : (depth == 1) ? "1" : "infinity";
        this.setHeader("Depth", depthValue);
    }

    public void setURI(String uri) {
        try {
            setURI(new URIBuilder(uri).build());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid URI: " + uri, e);
        }
    }
}
