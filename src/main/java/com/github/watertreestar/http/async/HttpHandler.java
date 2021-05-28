package com.github.watertreestar.http.async;

import java.nio.ByteBuffer;

public interface HttpHandler {
    void onHeader(HttpResponse response);

    void onBody(ByteBuffer byteBuffer);
}
