package com.java.xdd.system.aspect;

import com.java.xdd.common.util.PrincipalUtil;
import com.java.xdd.shiro.domain.User;
import org.aspectj.lang.JoinPoint;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

public class SystemLogAspect {
    private String ip;
    private String url; // 请求地址
    private String username; // 用户名
    private Map<?,?> params; // 传入参数
    private Map<String, Object> result; // 存放输出结果
    private long startTimeMillis; // 开始时间(毫秒)
    private long endTimeMillis; // 结束时间(毫秒)

    /**
     * 调用之前
     * @param joinPoint 目标代理类
     */
    public void doBefore(JoinPoint joinPoint){
        //参数
        Object[] args = joinPoint.getArgs(); //参数
        String target = joinPoint.getTarget().getClass().getName(); //类
        String signature = joinPoint.getSignature().getName(); //方法名
        System.out.println(joinPoint);
        this.setRequestInfo();
        startTimeMillis = System.currentTimeMillis(); //记录方法开始执行的时间
    }

    /**
     * 调用之后(调用方法之后走该方法，不管该方法是否报错)
     * @param joinPoint 目标代理类
     */
    public  void doAfter(JoinPoint joinPoint) {
        System.out.println(joinPoint);
        endTimeMillis = System.currentTimeMillis(); // 记录方法执行完成的时间
    }

    /**
     * 调用正常返回(调用方法正常返回走该方法)
     * @param joinPoint 目标代理类
     * @param obj 返回的数据
     */
    public void doAfterReturning(JoinPoint joinPoint, Object obj) {
        System.out.println(joinPoint);
        System.out.println(obj);
        System.out.println();
    }

    /**
     * 调用方法报错(调用方法报错走该方法)
     * @param joinPoint 目标代理类
     * @param e 抛出的异常
     */
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        System.out.println(joinPoint);
        System.out.println(e);
        System.out.println();

    }

    private HttpServletRequest getHttpServletRequest(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    private void setRequestInfo(){
        HttpServletRequest request = this.getHttpServletRequest();
        this.ip = this.setIp(request);
        this.url = request.getRequestURL().toString();
        Principal principal = request.getUserPrincipal();//当前登陆人
        User user = PrincipalUtil.getUserPrincipal(principal);
    }

    private String setIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


}
