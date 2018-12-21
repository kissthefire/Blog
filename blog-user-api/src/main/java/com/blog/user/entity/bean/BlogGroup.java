package com.blog.user.entity.bean;

import java.io.Serializable;

/**
 * Created by Dell on 2018/8/18.
 */
public class BlogGroup implements Serializable{
    private Long id;
    private String groupName;
    private Long pid;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
