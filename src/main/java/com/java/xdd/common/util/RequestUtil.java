package com.java.xdd.common.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RequestUtil {
    private RequestUtil(){}

    public static ServletRequestAttributes getServletRequestAttributes(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = null;
        if (requestAttributes != null && requestAttributes instanceof ServletRequestAttributes) {
            attributes = (ServletRequestAttributes) requestAttributes;
        }
        return attributes;
    }
    
    public static HttpServletRequest getHttpServletRequest(){
        ServletRequestAttributes attributes = RequestUtil.getServletRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }
        return request;
    }

    public static HttpSession getHttpSession(){
        HttpServletRequest request = getHttpServletRequest();
        HttpSession session = null;
        if (request != null) {
            session = request.getSession();
        }
        return session;
    }
    public static HttpServletResponse getHttpServletResponse(){
        ServletRequestAttributes attributes = RequestUtil.getServletRequestAttributes();
        HttpServletResponse response = null;
        if (attributes != null) {
            response = attributes.getResponse();
        }
        return response;
    }

    
    
}
