package com.github.watertreestar.http.async;

import java.nio.ByteBuffer;

enum HttpStateType {
    NOT_CONNECTED,
    CONNECTED,
    HEADER_SENT,
    ENTITY_SENT,
    HEADER_RECEIVED;
}

public class HttpState {
    ByteBuffer buf;
    HttpHandler httpHandler;
    HttpStateType state;
    HttpRequest httpRequest;
    long read;

    public HttpState(HttpRequest httpRequest, HttpHandler httpHandler) {
        this.state = HttpStateType.NOT_CONNECTED;
        this.httpRequest = httpRequest;
        this.httpHandler = httpHandler;
        this.buf = ByteBuffer.allocate(1024);
        this.read = 0;
    }
}
