package com.java.xdd.system.domain;

import com.java.xdd.common.domain.BaseDomain;

import java.io.Serializable;
import java.util.Date;

public class SystemLog extends BaseDomain implements Serializable{

    private static final long serialVersionUID = -4355748110745971577L;

    private Long systemLogId;
    private String ip;
    private String url;
    private String method;
    private Long userId;
    private Date methodStartTime;
    private Date methodEndTime;
    private String params;
    private String result;
    private String throwable;
    private String throwableMessage;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public Date getMethodStartTime() {
        return methodStartTime;
    }

    public void setMethodStartTime(Date methodStartTime) {
        this.methodStartTime = methodStartTime;
    }

    public Date getMethodEndTime() {
        return methodEndTime;
    }

    public void setMethodEndTime(Date methodEndTime) {
        this.methodEndTime = methodEndTime;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getThrowable() {
        return throwable;
    }

    public void setThrowable(String throwable) {
        this.throwable = throwable;
    }

    public String getThrowableMessage() {
        return throwableMessage;
    }

    public void setThrowableMessage(String throwableMessage) {
        this.throwableMessage = throwableMessage;
    }

    public Long getSystemLogId() {
        return systemLogId;
    }

    public void setSystemLogId(Long systemLogId) {
        this.systemLogId = systemLogId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    @Override
    public String toString() {
        return "SystemLog{" +
                "systemLogId=" + systemLogId +
                ", ip='" + ip + '\'' +
                ", method='" + method + '\'' +
                ", userId=" + userId +
                ", methodStartTime=" + methodStartTime +
                ", methodEndTime=" + methodEndTime +
                ", params='" + params + '\'' +
                ", result='" + result + '\'' +
                ", throwable='" + throwable + '\'' +
                ", throwableMessage='" + throwableMessage + '\'' +
                '}';
    }
}
