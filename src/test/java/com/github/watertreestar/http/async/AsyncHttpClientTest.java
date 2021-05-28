package com.github.watertreestar.http.async;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;


public class AsyncHttpClientTest {
    @Test
    public void test() throws IOException {
        AsyncHttpClient httpClient = new AsyncHttpClient();
        for (int i = 0; i < 100; ++i) {
            httpClient.get(URI.create("http://64p.org/"), new HttpHandler() {
                @Override
                public void onHeader(HttpResponse response) {
                    System.out.println(response);
                }

                @Override
                public void onBody(ByteBuffer buf) {
                    String got = new String(buf.array(), buf.arrayOffset() + buf.position(),
                            buf.remaining(), StandardCharsets.UTF_8);

                    System.out.println("GOT: " + got);
                }
            });
        }
        httpClient.waitAll();
    }
}
