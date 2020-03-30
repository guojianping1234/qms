package com.myspringbt.demo;

import javafx.application.Application;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;


//首先要将spring boot自带的DataSourceAutoConfiguration禁掉，
// 因为它会读取application.properties文件的spring.datasource.*属性并自动配置单数据源。在@SpringBootApplication注解中添加exclude属性即可：

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class})
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = null;
        try {
            SpringApplication sp = new SpringApplication(DemoApplication.class);
            applicationContext = sp.run();
            //
        } catch (Throwable e) {
            if (applicationContext == null) {
                return;
            }

            SpringApplication.exit(applicationContext, new ExitCodeGenerator() {
                @Override
                public int getExitCode() {
                    return 0;
                }
            });

        }

    }


    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.CHINA);
        cookieLocaleResolver.setCookieName("lang");
        cookieLocaleResolver.setCookiePath("/");
        return cookieLocaleResolver;
    }
}
