package com.linc.muddy.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

public class NettyStarter {
    public void start(int port) {
        EventLoopGroup boss = new DefaultEventLoopGroup(1);
        EventLoopGroup worker = new DefaultEventLoopGroup(nearlyPowOfTwo(Runtime.getRuntime().availableProcessors()));


        try {
            new ServerBootstrap()
                    .group(boss, worker)
                    .channel(ServerSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new MuddyRequestDecoder()).addLast(new Inhandler());
                        }
                    })
                    .bind(port).sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException("启动端口失败:" + port);
        }

    }

    private int nearlyPowOfTwo(int num) {
        int res = 1;
        while (res < num) {
            res <<= 1;
        }
        return res;
    }
}
