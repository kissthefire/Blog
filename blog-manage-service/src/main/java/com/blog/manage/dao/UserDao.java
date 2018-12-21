package com.blog.manage.dao;

import com.blog.common.base.BaseDao;
import com.blog.manage.entity.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Dell on 2018/7/13.
 */
@Mapper
public interface UserDao extends BaseDao<User> {
    User queryUserById(int id);

    int saveUser(User user);
}