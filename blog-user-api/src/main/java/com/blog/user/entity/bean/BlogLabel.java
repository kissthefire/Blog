package com.blog.user.entity.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 2018/8/18.
 */
public class BlogLabel implements Serializable{
    private Long id;
    private Long blogId;
    private Long labelId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }
}
