package com.linc.muddy.client.core;

import com.linc.muddy.common.core.RollbackStatus;
import org.springframework.stereotype.Component;

//@RequestMapping("/xxoo")
public interface MuddyHandler<Request,Response> {
     Response handle(Request request);
     RollbackStatus rollback(Request request);
}
