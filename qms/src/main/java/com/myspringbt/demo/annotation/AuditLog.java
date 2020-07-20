package com.myspringbt.demo.annotation;

import java.lang.annotation.*;

/**
 * 日志注解
 *
 * @author g3006378
 * @date 2017-09-04
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
//1、RetentionPolicy.SOURCE 注解只保留在源文件中，在编译成class文件的时候被遗弃
//2、RetentionPolicy.CLASS 注解被保留在class中，但是在jvm加载的时候北欧抛弃，这个是默认的声明周期
//3、RetentionPolicy.RUNTIME 注解在jvm加载的时候仍被保留
@Target(ElementType.METHOD)
//定义了注解声明在哪些元素之前
public @interface AuditLog {
    //定义成员
    String descrption() default "" ;//描述
}
