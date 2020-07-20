package com.myspringbt.demo.event;

import com.myspringbt.demo.domain.AuditLog;
import com.myspringbt.demo.service.AuditLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

@Slf4j
@Component
public class AuditLogListenner {

    @Autowired
    private AuditLogService sysLogService;


    @Async
    @Order
    @EventListener(AuditLogEvent.class)
    public void saveSysLog(AuditLogEvent event) {
        AuditLog auditLog = (AuditLog) event.getSource();
        // 保存日志
        System.out.println("保存日志");
        // sysLogService.save(sysLog);
    }

}
