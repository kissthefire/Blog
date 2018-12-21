package com.blog.user.interceptor;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.blog.common.constant.Constants;
import com.blog.common.converter.JsonConverter;
import com.blog.common.converter.TypeConvertUtils;
import com.blog.redis.service.JedisClient;
import com.blog.user.annotation.AuthIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by Dell on 2018/10/22.
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter{

    @Reference
    private JedisClient jedisClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuthIgnore authIgnore;
        if(handler instanceof HandlerMethod){
            authIgnore=((HandlerMethod)handler).getMethodAnnotation(AuthIgnore.class);
        }else{
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod) handler;
        Method method=handlerMethod.getMethod();
        String token=request.getHeader("token");
        //包含@AuthIgnore注解，放行
        if(method.getAnnotation(AuthIgnore.class)!=null){
            return true;
        }
        //验证token
        if(StringUtils.isEmpty(token)){
            throw new RuntimeException(Constants.MISS_TOKEN);
        }else{
            //token为不为null
            String userJson= TypeConvertUtils.toString(jedisClient.get(Constants.TOKEN+token));
            if(StringUtils.isNotEmpty(userJson)){
                return true;
            }
        }
        return super.preHandle(request, response, handler);
    }
}