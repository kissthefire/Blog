package com.blog.user.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.blog.common.constant.Constants;
import com.blog.common.converter.JsonConverter;
import com.blog.common.converter.TypeConvertUtils;
import com.blog.common.result.Response;
import com.blog.common.utils.EncryptUtils;
import com.blog.redis.service.JedisClient;
import com.blog.user.dao.UserDao;
import com.blog.user.entity.bean.User;
import com.blog.user.entity.dto.user.UserDto;
import com.blog.user.entity.qo.user.UserQo;
import com.blog.user.service.TokenService;
import com.blog.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

/**
 * Created by Dell on 2018/7/18.
 */
@Service
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserDao userDao;

    @Reference
    private JedisClient jedisClient;

    @Autowired
    private TokenService tokenService;

    /**
     * 用户注册
     * @param userQo
     * @return
     */
    @Override
    public Response register(UserQo userQo) {
        //1.验证邮箱是否被注册
        List<UserDto> users=this.userDao.queryUserBySelective(userQo);
        if(CollectionUtils.isNotEmpty(users)){
            return Response.dataError("该邮箱已被注册");
        }
        //2.验证两次输入密码是否一致
        if(!userQo.getPassword().equals(userQo.getRePassword())){
            return Response.dataError("两次输入密码不一致");
        }
        //3.验证验证码是否正确
        String verifyCode=TypeConvertUtils.toString(jedisClient.get(Constants.REGISTER_VERIFYCODE+userQo.getEmail())) ;
        if(StringUtils.isEmpty(verifyCode)||!verifyCode.equals(userQo.getVerifyCode())){
            return Response.dataError("验证码不正确");
        }
        int flag=this.userDao.saveUser(userQo);
        if(flag>0){
            return Response.success();
        }
        return Response.error("注册失败，请重试");
    }

    /**
     * 用户登录
     * @param userQo
     * @return
     */
    @Override
    public Response login(UserQo userQo) {
        List<UserDto> users=userDao.queryUserBySelective(userQo);
        if(CollectionUtils.isNotEmpty(users)){
            UserDto user=users.get(0);
            if(!EncryptUtils.MD5Encode(userQo.getPassword()).equals(user.getPassword())){
                return Response.dataError("邮箱或密码不正确");
            }
            String token= UUID.randomUUID().toString();
            user.setPassword(null);
            jedisClient.setAndExpire(Constants.TOKEN+token,JsonConverter.convertObjToJson(user),60*60*15L);
            user.setToken(token);
            return Response.successResult(user);
        }
        return Response.dataError("邮箱不存在");
    }

    @Override
    public Response logout(UserQo userQo) {
        jedisClient.delete(userQo.getToken());
        return Response.success();
    }

    @Override
    public Response updateUserInfo(UserQo userQo) {
        int flag=this.userDao.updateUser(userQo);
        return Response.successResult(flag);
    }
}
