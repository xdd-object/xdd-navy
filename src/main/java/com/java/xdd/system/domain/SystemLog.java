package com.java.xdd.system.domain;

import com.java.xdd.common.domain.BaseDomain;

import java.io.Serializable;

public class SystemLog extends BaseDomain implements Serializable{

    private static final long serialVersionUID = -4355748110745971577L;

    private Long systemLogId;
    private String ip;
    private String method;
    private Long userId;

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
                '}';
    }
}
