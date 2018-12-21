package com.blog.user.entity.bean;

import java.io.Serializable;

/**
 * Created by Dell on 2018/8/18.
 */
public class Category implements Serializable{
    private Long id;
    private String category;
    private Long pid;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
