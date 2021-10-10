package com.github.watertreestar.io;

import com.github.watertreestar.Predications;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A InputStream that concat multi sub InputStreams.
 * The {@link #close()} method close all sub InputStreams.
 */
class ConcatenatedInputStream extends InputStream {

    private final List<InputStream> inputs;
    private int index = 0;

    ConcatenatedInputStream(List<InputStream> inputs) {
        this.inputs = new ArrayList<>(inputs);
    }

    @Override
    public synchronized int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override
    public synchronized int read(byte[] b, int off, int len) throws IOException {
        Predications.checkArrayRange(b, off, len);
        int totalRead = 0;
        while (true) {
            int read = current().read(b, off + totalRead, len - totalRead);
            if (read == -1) {
                if (!next()) {
                    if (totalRead == 0) {
                        totalRead = -1;
                    }
                    break;
                }
            } else if (read == 0) {
                break;
            } else {
                totalRead += read;
                if (totalRead == len) {
                    break;
                }
            }
        }
        return totalRead;
    }

    @Override
    public synchronized long skip(long n) throws IOException {
        return super.skip(n);
    }

    @Override
    public synchronized int available() throws IOException {
        return current().available();
    }

    @Override
    public synchronized void close() throws IOException {
        Throwable t = null;
        for (InputStream input : inputs) {
            try {
                input.close();
            } catch (Throwable e) {
                if (t == null) {
                    t = e;
                } else {
                    t.addSuppressed(e);
                }
            }
        }
        if (t != null) {
            throw new IOException(t);
        }
    }

    @Override
    public synchronized int read() throws IOException {
        int value;
        do {
            value = current().read();
        } while (value == -1 && next());
        return value;
    }

    private InputStream current() {
        return inputs.get(index);
    }

    private boolean next() {
        if (index >= inputs.size() - 1) {
            return false;
        }
        index++;
        return true;
    }
}
