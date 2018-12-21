package com.blog.user.entity.dto.user;

/**
 * Created by Dell on 2018/10/6.
 */
public class TokenModel {
    //用户id
    private Long userId;
    //随机生成的token
    private String token;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenModel() {
    }

    public TokenModel(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
