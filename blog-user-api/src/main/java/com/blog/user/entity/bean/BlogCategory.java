package com.blog.user.entity.bean;

import java.io.Serializable;

/**
 * Created by Dell on 2018/8/18.
 */
public class BlogCategory implements Serializable {
    private Long id;
    private Long blogId;
    private Long categoryId;

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
