package com.blog.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.blog.manage.dao.UserDao;
import com.blog.manage.entity.bean.User;
import com.blog.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Dell on 2018/7/13.
 */
@Service
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserDao userDao;

    @Override
    public User querUserById(int id) {
        return this.userDao.queryUserById(id);
    }

    @Override
    public int saveUser(User user) {
        return this.userDao.saveUser(user);
    }
}
