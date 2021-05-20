package com.github.watertreestar.retry;

import java.util.List;

public class RetryException extends RuntimeException {
    List<Throwable> throwables;

    public RetryException(Throwable t) {
        super(t);
    }

    public RetryException(List<Throwable> throwables) {
        this.throwables = throwables;
    }

    public List<Throwable> getThrowables() {
        return throwables;
    }
}
