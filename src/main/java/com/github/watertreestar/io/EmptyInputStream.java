package com.github.watertreestar.io;

import java.io.InputStream;

class EmptyInputStream extends InputStream {
    static InputStream instance = new EmptyInputStream();

    private EmptyInputStream() {
    }

    @Override
    public int read(byte[] b) {
        return -1;
    }

    @Override
    public int read(byte[] b, int off, int len) {
        return -1;
    }

    @Override
    public long skip(long n) {
        return 0;
    }

    @Override
    public int available() {
        return 0;
    }

    @Override
    public int read() {
        return -1;
    }
}

