package com.blog.user.dao;

import com.blog.user.entity.bean.User;
import com.blog.user.entity.dto.user.UserDto;
import com.blog.user.entity.qo.user.UserQo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Dell on 2018/7/18.
 */
@Mapper
public interface UserDao {
    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User queryUserById(int id);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    int saveUser(UserQo user);

    /**
     * 根据用户信息查询用户
     * @param user
     * @return
     */
    List<UserDto> queryUserBySelective(UserQo user);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int updateUser(UserQo user);
}