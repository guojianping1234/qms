package com.myspringbt.demo.event;

import com.myspringbt.demo.domain.AuditLog;
import org.springframework.context.ApplicationEvent;

public class AuditLogEvent extends ApplicationEvent {
    public AuditLogEvent(AuditLog source) {
        super(source);
    }
}
