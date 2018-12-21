package com.blog.user.service;

import com.blog.user.entity.dto.user.TokenModel;

/**
 * Created by Dell on 2018/10/6.
 */
public interface TokenService {
    /**
     * 创建一个用户token
     * @param userId
     * @return
     */
    TokenModel createToken(Long userId);

    /**
     * 检查token是否有效
     * @param model
     * @return
     */
    boolean checkToken(TokenModel model);

    /**
     * 从字符串中解析token
     * @param authentication
     * @return
     */
    TokenModel getToken(String authentication);

    /**
     * 删除token
     * @param userId
     */
    void deleteToken(Long userId);
}
