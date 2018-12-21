package com.blog.user.service;

import com.blog.common.result.Response;

/**
 * Created by Dell on 2018/10/10.
 */
public interface CommonService {
    /**
     * 发送验证码邮件
     * @param email
     */
    Response sendVerifyEmail(String email);
}
