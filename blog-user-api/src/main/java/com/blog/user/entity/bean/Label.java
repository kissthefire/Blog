package com.blog.user.entity.bean;

import java.io.Serializable;

/**
 * Created by Dell on 2018/8/18.
 */
public class Label implements Serializable{
    private Long id;
    private String label;
    private Long pid;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
