package com.myspringbt.demo.controller;

import com.alibaba.druid.util.StringUtils;
import com.myspringbt.demo.util.RedisUtils;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    public Logger logger = LoggerFactory.getLogger(this.getClass());
    //redis 通用配置
    @Autowired
    public RedisUtils redisUtils;


    /**
     * 获取PicaUser
     *
     * @return
     */
//    public PicaUser getCurrentPicaUser() {
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = servletRequestAttributes.getRequest();
//        String token = request.getHeader("token");
//        PicaUser picaUser = null;
//        if (StringUtils.isEmpty(token)) {
//            return null;
//        } else {
//            //封装redis中的方法
//            picaUser = redisUtils.getToken(token, PicaUser.class);
//            if (picaUser != null) {
//                picaUser.setToken(token);
//            }
//        }
//        return picaUser;
//    }

    /**
     * 从请求头中获取token
     *
     * @return
     */
    public String getTokenFromHeader() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest().getHeader("token");
    }

    /**
     * 从请求头中获取系统code
     *
     * @return
     */
    public String getSysCodeFromHeader() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest().getHeader("sysCode");
    }

    /**
     * 获取请求的ip地址
     *
     * @return
     */
    protected final String getIpAddr() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    /**
     * 获取request
     */
    protected HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            return requestAttributes.getRequest();
        }

        return new Request(null);
    }

    /*

    获取responese
     */
    protected HttpServletRequest getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            return (HttpServletRequest) requestAttributes.getResponse();
        }
        return (HttpServletRequest) new Response(0);
    }

    /**
     * 获取语言
     */
//    protected String getLangue() {
//        LocalResolver localResolver = SpringUtil.getBean(LocalResolver.class);
//    }


}
