package com.linc.muddy.server;

import com.linc.muddy.common.utils.async.AsyncCall;
import com.linc.muddy.server.netty.NettyStarter;

import java.util.concurrent.Future;

public class Application {
    public static void main(String[] args) {
        Future<Object> future = AsyncCall.async(() -> {
            new NettyStarter().start(8080);
            return null;
        });

        System.err.println(1);
    }
}
