package com.java.xdd.system.aspect;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.xdd.common.util.DateUtil;
import com.java.xdd.common.util.PrincipalUtil;
import com.java.xdd.common.util.RequestUtil;
import com.java.xdd.shiro.domain.User;
import com.java.xdd.system.domain.SystemLog;
import com.java.xdd.system.service.SystemLogService;
import org.apache.poi.ss.usermodel.Cell;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;
import java.util.HashMap;

public class SystemLogAspect {

    private final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private SystemLogService systemLogService;

    private SystemLog systemLog;

    private void init(){
        systemLog = new SystemLog();
        systemLog.setMethodStartTime(DateUtil.getCurrentDate());
        systemLog.setInsertTime(DateUtil.getCurrentDate());
        systemLog.setUpdateTime(DateUtil.getCurrentDate());
    }

    /**
     * 调用之前
     * @param joinPoint 目标代理类
     */
    public void doBefore(JoinPoint joinPoint){
        this.init();
        //参数
        Object[] args = joinPoint.getArgs(); //参数
        try {
            if (null != args && args.length > 0){
                Map<Integer, Object> map = new HashMap<>();
                for (int i = 0; i < args.length; i++) {
                    map.put(i, null == args[i] ? null : args[i].toString());
                }
                systemLog.setParams(mapper.writeValueAsString(map));
            } else {
                systemLog.setParams(mapper.writeValueAsString(args));
            }
        } catch (JsonProcessingException e) {
            logger.error("解析json出错->【{}】", e.getMessage());
            systemLog.setParams("解析json出错->" + args.toString());
        }
        String target = joinPoint.getTarget().getClass().getName(); //类
        String signature = joinPoint.getSignature().getName(); //方法名
        systemLog.setMethod(target + "." + signature);
        this.setRequestInfo();
    }

    /**
     * 调用之后(调用方法之后走该方法，不管该方法是否报错)
     * @param joinPoint 目标代理类
     */
    public  void doAfter(JoinPoint joinPoint) {
        System.out.println(joinPoint);
    }

    /**
     * 调用正常返回(调用方法正常返回走该方法)
     * @param joinPoint 目标代理类
     * @param obj 返回的数据
     */
    public void doAfterReturning(JoinPoint joinPoint, Object obj) {
        try {
            systemLog.setResult(mapper.writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            logger.error("解析json出错->【{}】", e.getMessage());
            systemLog.setResult("解析json出错->" + obj.toString());
        }
        systemLog.setMethodEndTime(DateUtil.getCurrentDate());
        systemLogService.insertSelective(systemLog);
    }

    /**
     * 调用方法报错(调用方法报错走该方法)
     * @param joinPoint 目标代理类
     * @param e 抛出的异常
     */
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        systemLog.setThrowable(e.getClass().getName());
        systemLog.setThrowableMessage(e.getMessage());
        systemLog.setMethodEndTime(DateUtil.getCurrentDate());
        systemLogService.insertSelective(systemLog);
    }

    private void setRequestInfo(){
        HttpServletRequest request = RequestUtil.getHttpServletRequest();
        if (null == request) return;
        systemLog.setIp(this.setIp(request));
        systemLog.setUrl(request.getRequestURL().toString());
        Principal principal = request.getUserPrincipal();//当前登陆人
        User user = PrincipalUtil.getUserPrincipal(principal);
        if (null != user) {
            systemLog.setUserId(user.getId());
            systemLog.setInsertAuthor(user.getId());
            systemLog.setUpdateAuthor(user.getId());
        }
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

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setId(4793L);
        user.setPassword("flsfjl");
        Object o = user;
        String s = mapper.writeValueAsString(o);
        String s1 = JSONObject.toJSONString(o);
        System.out.println(s);

    }

}
