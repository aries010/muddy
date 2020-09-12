package com.linc.muddy.server.core.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RequestHeader implements Serializable {
    public int bodyLength;
    public final byte version=1;
    public long requestId;
}
