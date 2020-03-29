package com.myspringbt.demo.util;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public SpringUtil() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            this.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> tClass) {
        return applicationContext == null ? null : applicationContext.getBean(tClass);
    }

    public static <T> T getBean(String name, Class<T> tClass) {
        return applicationContext == null ? null : applicationContext.getBean(name, tClass);

    }

    public static String getActiveProfile() {
        return applicationContext.getEnvironment().getActiveProfiles()[0];
    }

    public static Boolean ifDeveLopmentMode() {
        return "dev".equals(applicationContext.getEnvironment().getActiveProfiles()[0]);
    }

    public static Boolean iswindow() {
        String osName = System.getProperties().getProperty("os.name");
        return osName == null ? false : osName.toLowerCase().contains("windows");

    }


}
