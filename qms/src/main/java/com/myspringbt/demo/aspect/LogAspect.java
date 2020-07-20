package com.myspringbt.demo.aspect;

import com.myspringbt.demo.domain.AuditLog;
import com.myspringbt.demo.event.AuditLogEvent;
import com.myspringbt.demo.util.R;
import groovy.util.logging.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * @Classname LogAspect
 * @Description 系统日志切面
 * @Version 1.0
 * ①切面注解得到请求数据 -> ②发布监听事件 -> ③异步监听日志入库
 */
@Slf4j
@Aspect
@Component
public class LogAspect {
    private ThreadLocal<AuditLog> AuditLogThreadLocal = new ThreadLocal<>();
    /**
     * 事件发布是由ApplicationContext对象管控的，我们发布事件前需要注入ApplicationContext对象调用publishEvent方法完成事件发布
     **/

    @Autowired
    private ApplicationContext applicationContext;

    /***
     * 定义controller切入点拦截规则，拦截AuditLog注解的方法
     */
    @Pointcut("@annotation(com.myspringbt.demo.annotation.AuditLog)")
    public void auditLogAspect() {
    }

    /***
     * 拦截控制层的操作日志
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Before(value = "auditLogAspect()")
    public void recordLog(JoinPoint joinPoint) throws Throwable {
        AuditLog auditLog = new AuditLog();
        //将当前实体保存到threadLocal
        AuditLogThreadLocal.set(auditLog);
        // 开始时间
        long beginTime = Instant.now().toEpochMilli();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        //   PreSecurityUser securityUser = SecurityUtil.getUser();
        auditLog.setUserName("Test");
//        auditLog.setActionUrl(URLUtil.getPath(request.getRequestURI()));
//        auditLog.setStartTime(LocalDateTime.now());
//        String ip = ServletUtil.getClientIP(request);
//        auditLog.setIp(ip);
//        auditLog.setLocation(IPUtil.getCityInfo(ip));
//        auditLog.setRequestMethod(request.getMethod());
//        String uaStr = request.getHeader("user-agent");
//        auditLog.setBrowser(UserAgentUtil.parse(uaStr).getBrowser().toString());
//        auditLog.setOs(UserAgentUtil.parse(uaStr).getOs().toString());

        //访问目标方法的参数 可动态改变参数值
        Object[] args = joinPoint.getArgs();
        //获取执行的方法名
        auditLog.setActionMethod(joinPoint.getSignature().getName());
        // 类名
        auditLog.setClassPath(joinPoint.getTarget().getClass().getName());
        auditLog.setActionMethod(joinPoint.getSignature().getName());
        auditLog.setFinishTime(LocalDateTime.now());
        // 参数
        auditLog.setParams(Arrays.toString(args));
        // auditLog.setDescription(LogUtil.getControllerMethodDescription(joinPoint));
        long endTime = Instant.now().toEpochMilli();
        auditLog.setConsumingTime(endTime - beginTime);


    }

    /**
     * 返回通知
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "auditLogAspect()")
    public void doAfterReturning(Object ret) {
        //得到当前线程的log对象
        AuditLog log = AuditLogThreadLocal.get();
        applicationContext.publishEvent(new AuditLogEvent(log));
        //移除当前log实体
        AuditLogThreadLocal.remove();
    }

    /**
     * 异常通知
     *
     * @param e
     */
    @AfterThrowing(pointcut = "auditLogAspect()", throwing = "e")
    public void doAfterThrowable(Throwable e) {


    }
}
