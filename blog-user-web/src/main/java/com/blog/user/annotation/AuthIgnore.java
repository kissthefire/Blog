package com.blog.user.annotation;

import java.lang.annotation.*;

/**
 * Created by Dell on 2018/10/22.
 * 定义注解，部分接口可以不登录访问
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthIgnore {
}