package com.linc.muddy.common.utils.async;

import java.util.concurrent.CountDownLatch;

public class Feature<T> {

    private CountDownLatch latch = new CountDownLatch(1);

    private volatile T result;

    void done(T result) {
        this.result = result;
        latch.countDown();

    }

    public T get() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
