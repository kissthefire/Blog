package com.blog.user.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.blog.common.constant.Constants;
import com.blog.common.result.Response;
import com.blog.common.utils.MailUtils;
import com.blog.common.utils.VerifyCodeUtils;
import com.blog.redis.service.JedisClient;
import com.blog.user.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Dell on 2018/10/10.
 */
@Component
@Service
public class CommonServiceImpl implements CommonService{

    @Reference
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private JedisClient jedisClient;

    /**
     * 发送验证码，并保存在redis中
     * @param email
     */
    @Override
    public Response sendVerifyEmail(String email) {
        jedisClient.setAndExpire(Constants.REGISTER_VERIFYCODE+email, VerifyCodeUtils.genVerifyCode(),60*15L);
        try{
            MailUtils.sendMail("注册验证码",email,"InetCommunity",email,"您的注册验证码为："+VerifyCodeUtils.genVerifyCode()+"，请在15分钟内完成填写");
            return  Response.success();
        }catch (Exception ex){
            ex.printStackTrace();
            return Response.error("验证码发送失败，请重试");
        }
    }
}
