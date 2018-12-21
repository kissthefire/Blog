package com.blog.manage.service;

import com.blog.manage.entity.bean.User;

/**
 * Created by Dell on 2018/7/13.
 */
public interface UserService {
    User querUserById(int id);

    int saveUser(User user);
}
