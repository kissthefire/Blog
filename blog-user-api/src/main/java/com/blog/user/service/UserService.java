package com.blog.user.service;

import com.blog.common.result.Response;
import com.blog.user.entity.bean.User;
import com.blog.user.entity.qo.user.UserQo;

/**
 * Created by Dell on 2018/7/18.
 */
public interface UserService {
    /**
     * 用户注册
     * @param userQo
     * @return
     */
    Response register(UserQo userQo);

    /**
     * 登录
     * @param userQo
     * @return
     */
    Response login(UserQo userQo);

    /**
     * 注销
     * @param userQo
     * @return
     */
    Response logout(UserQo userQo);

    Response updateUserInfo(UserQo userQo);
}