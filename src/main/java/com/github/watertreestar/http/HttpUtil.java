package com.github.watertreestar.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class HttpUtil {
    public static final String DEFAULT_USER_AGENT = "AsyncLiteHttp/1.3";
    public static final String UTF8 = "UTF-8";
    private int connectionTimeout = 30000;
    private int dataRetrievalTimeout = 30000;
    private boolean followRedirects = true;
    private Map<String, String> headers;

    public enum Method {
        GET,
        POST
    }

    public HttpUtil() {
        headers = Collections.synchronizedMap(new LinkedHashMap<String, String>());
        setUserAgent(DEFAULT_USER_AGENT);
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getDataRetrievalTimeout() {
        return dataRetrievalTimeout;
    }

    public void setDataRetrievalTimeout(int dataRetrievalTimeout) {
        this.dataRetrievalTimeout = dataRetrievalTimeout;
    }

    public boolean getFollowRedirects() {
        return followRedirects;
    }

    public void setFollowRedirects(boolean followRedirects) {
        this.followRedirects = followRedirects;
    }

    public String getUserAgent() {
        return headers.get("User-Agent");
    }

    public void setUserAgent(String userAgent) {
        headers.put("User-Agent", userAgent);
    }

    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    public void removeHeader(String name) {
        headers.remove(name);
    }

    private HttpURLConnection buildURLConnection(String url, Method method) throws IOException {
        URL resourceUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) resourceUrl.openConnection();

        // Settings
        connection.setConnectTimeout(connectionTimeout);
        connection.setReadTimeout(dataRetrievalTimeout);
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(followRedirects);
        connection.setRequestMethod(method.toString());
        connection.setDoInput(true);

        // Headers
        for (Map.Entry<String, String> header : headers.entrySet()) {
            connection.setRequestProperty(header.getKey(), header.getValue());
        }
        return connection;
    }

    private byte[] encodeParameters(Map<String, String> map) {
        if (map == null) {
            map = new TreeMap<>();
        }
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (encodedParams.length() > 0) {
                    encodedParams.append("&");
                }
                encodedParams.append(URLEncoder.encode(entry.getKey(), UTF8));
                encodedParams.append('=');
                String v = entry.getValue() == null ? "" : entry.getValue();
                encodedParams.append(URLEncoder.encode(v, UTF8));
            }
            return encodedParams.toString().getBytes(UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Encoding not supported: " + UTF8, e);
        }
    }

    public void get(final String url, final ResponseHandler handler) {
        request(url, Method.GET, null, handler);
    }


    public void post(final String url, final Map<String, String> map, final ResponseHandler handler) {
        request(url, Method.POST, map, handler);
    }

    void request(final String url, final Method method, final Map<String, String> map,
                 final ResponseHandler handler) {

        try {
            handler.onStart();
            HttpURLConnection urlConnection = buildURLConnection(url, method);
            urlConnection.setRequestMethod(method.name().toString());

            if (method == Method.POST) {
                // Send content as form-urlencoded
                byte[] content = encodeParameters(map);
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + UTF8);
                urlConnection.setRequestProperty("Content-Length", Long.toString(content.length));

                // Stream the data so we don't run out of memory
                urlConnection.setFixedLengthStreamingMode(content.length);
                OutputStream os = urlConnection.getOutputStream();
                os.write(content);
                os.flush();
                os.close();
            }
            long contentLength = urlConnection.getContentLength();
            byte[] data = readFrom(urlConnection.getInputStream(), contentLength);
            handler.onSuccess(data);
        } catch (Exception e) {
            handler.onFailure(e);
        } finally {
            handler.onFinish();
        }
    }

    byte[] readFrom(InputStream inputStream, long length) throws IOException {
        if (inputStream == null) {
            return new byte[0];
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer, 0, buffer.length)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.flush();
        os.close();
        return os.toByteArray();
    }
}
