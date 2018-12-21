package com.blog.user.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.blog.common.constant.Constants;
import com.blog.common.converter.TypeConvertUtils;
import com.blog.redis.service.JedisClient;
import com.blog.user.entity.dto.user.TokenModel;
import com.blog.user.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by Dell on 2018/10/6.
 */
@Service
@Component
public class TokenServiceImpl implements TokenService {

    @Reference
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private JedisClient jedisClient;

    @Override
    public TokenModel createToken(Long userId) {
        String token= UUID.randomUUID().toString().replace("-","");
        TokenModel model=new TokenModel(userId,token);
        //设置缓存时间为15天
        jedisClient.setAndExpire(Constants.TOKEN+userId,token,60*60*24*15L);
        return model;
    }

    @Override
    public boolean checkToken(TokenModel model) {
        if(model==null){
            return false;
        }
        String token= TypeConvertUtils.toString(jedisClient.get(Constants.TOKEN+model.getUserId()));
        if(token==null || !model.getToken().equals(token)){
            return false;
        }
        //验证通过，重新设置超时时间
        jedisClient.expire(Constants.TOKEN+model.getUserId(),60*60*24*15L);
        return true;
    }

    @Override
    public TokenModel getToken(String authentication) {
        if(StringUtils.isNotEmpty(authentication)){
            return null;
        }
        String[] param=authentication.split("-");
        if(param.length!=2){
            return null;
        }
        Long userId=TypeConvertUtils.toLong(param[0]);
        String token=param[1];
        return new TokenModel(userId,token);
    }

    @Override
    public void deleteToken(Long userId) {
        jedisClient.delete(Constants.TOKEN+userId);
    }
}
