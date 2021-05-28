package com.github.watertreestar.http;

public abstract class ResponseHandler {
    public ResponseHandler() {

    }

    public void onStart() {
        // Do nothing by default
    }

    public void onProgress(long bytesReceived, long totalBytes) {
        // Do nothing by default
    }

    public void onFinish() {
        // Do nothing by default
    }

    public abstract void onSuccess(byte[] response);

    public abstract void onFailure(Throwable e);


}