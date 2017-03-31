package com.java.xdd.shiro.domain;

import com.java.xdd.common.domain.BaseDomain;

import java.io.Serializable;

/**
 * 角色
 */
public class Role extends BaseDomain implements Serializable{

    private static final long serialVersionUID = -3253424740081641571L;

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
