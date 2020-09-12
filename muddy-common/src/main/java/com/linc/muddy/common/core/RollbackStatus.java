package com.linc.muddy.common.core;

import lombok.Data;
import lombok.NonNull;

@Data
public class RollbackStatus {
    private int status;
    private String msg;

    RollbackStatus(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static final RollbackStatus SUCCESS = new RollbackStatus(200, "success");

    public final RollbackStatus fail(@NonNull String reason) {
        return new RollbackStatus(500, reason);
    }
}
