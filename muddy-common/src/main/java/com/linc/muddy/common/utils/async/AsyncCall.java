package com.linc.muddy.common.utils.async;

import java.util.concurrent.*;

public class AsyncCall {
    static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors(),
            0,
            TimeUnit.MILLISECONDS,
            new SynchronousQueue<>(),
            (r, executor) -> new Thread(r).start());

    public static <T> Future<T> async(Callable<T> callable) {
        return threadPoolExecutor.submit(callable);


    }
}
