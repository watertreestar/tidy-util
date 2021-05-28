package com.github.watertreestar.http.async;

import java.io.IOException;
import java.net.URI;
import java.nio.channels.SocketChannel;

class HttpRequest {
    private URI uri;
    private String method;
    private HttpHeaders headers;

    public HttpRequest(String method, URI uri) {
        this.uri = uri;
        this.method = method;
        this.headers = new HttpHeaders();
        // should we add port number to host header?
        headers.add("Host", uri.getHost());
    }

    public static HttpRequest get(URI uri) {
        return new HttpRequest("GET", uri);
    }

    private void setMethod(String method) {
        this.method = method;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public String getHost() {
        return uri.getHost();
    }

    public int getPort() {
        int port = uri.getPort();
        if (port == -1) {
            switch (uri.getScheme()) {
                case "http":
                    return 80;
                case "https":
                    return 443;
                default:
                    throw new IllegalStateException("Invalid scheme: " + uri);
            }
        } else {
            return port;
        }
    }

    public String getMethod() {
        return method;
    }

    public String getHeaderPartAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(method).append(" ").append(uri.getRawPath()).append(" HTTP/1.0\r\n");
        for (String key : headers.keySet()) {
            for (String val : headers.get(key)) {
                stringBuilder.append(key + " : " + val + "\r\n");
            }
        }
        stringBuilder.append("\r\n");
        return stringBuilder.toString();
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public boolean writeEntity(SocketChannel sch) throws IOException {
        return false; // finished.
    }
}
