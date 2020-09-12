package com.linc.muddy.server.netty;

import com.linc.muddy.server.core.entity.RequestHeader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.*;
import java.util.List;

public class MuddyRequestDecoder extends ByteToMessageDecoder {
    private static final int HEAD_LENGTH = testLength();

    private static int testLength() {
        RequestHeader requestHeader = new RequestHeader();
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(requestHeader);
            return bos.size();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] bytes = new byte[HEAD_LENGTH];
        int fromIndex = 0;
        while (in.readableBytes() > HEAD_LENGTH) {
            in.getBytes(fromIndex, bytes);
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            RequestHeader requestHeader = (RequestHeader) ois.readObject();
            int bodyLength = requestHeader.getBodyLength();
            if(in.readableBytes()<HEAD_LENGTH+bodyLength){
                return;
            }
            in.readBytes(bytes);
            byte[] bodyBytes = new byte[bodyLength];
            in.readBytes(bodyBytes);
            ByteArrayInputStream bodyBis = new ByteArrayInputStream(bodyBytes);
            ObjectInputStream bodyOis = new ObjectInputStream(bodyBis);
            Object o = bodyOis.readObject();
            out.add(o);
            fromIndex+=HEAD_LENGTH+bodyLength;
        }
    }
}
