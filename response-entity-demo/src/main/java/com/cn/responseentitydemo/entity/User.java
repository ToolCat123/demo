package com.cn.responseentitydemo.entity;


import java.io.Serializable;

/**
 * @author yc
 * @date 2020/9/17
 */
public class User implements Serializable{

    private static final long serialVersionUID = -2297772197001894375L;
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

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
