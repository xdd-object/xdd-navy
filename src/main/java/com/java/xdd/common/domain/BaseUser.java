package com.java.xdd.common.domain;

public interface BaseUser {

    public String getSalt();

    public void setSalt(String salt);

    public Long getId();

    public void setId(Long id);

    public String getUsername();

    public void setUsername(String username);

    public String getPassword();

    public void setPassword(String password);
}
