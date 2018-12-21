package com.blog.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.blog.common.result.Response;
import com.blog.user.entity.bean.User;
import com.blog.user.entity.qo.user.UserQo;
import com.blog.user.service.UserService;
import com.blog.user.validate.Validator;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dell on 2018/7/19.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    /**
     * 用户注册接口
     * @param userQo
     * @param request
     * @param result
     * @return
     */
    @RequestMapping("/register")
    public Response register(@RequestBody @Valid UserQo userQo, HttpServletRequest request, BindingResult result){
        if(result.hasErrors()){
            return Response.error(Validator.validate(result));
        }
        userQo.setLoginIp(request.getRemoteUser());

        return this.userService.register(userQo);
    }

    /**
     * 用户登录
     * @param userQo
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public Response login(@RequestBody UserQo userQo,HttpServletRequest request){
         String host=request.getRemoteHost();
         userQo.setLoginIp(host);
         return this.userService.login(userQo);
    }

    /**
     * 注销
     * @param userQo
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public Response logout(@RequestBody UserQo userQo,HttpServletRequest request){
        String token=request.getHeader("token");
        userQo.setToken(token);
        return this.userService.logout(userQo);
    }

    /**
     * 修改用户信息
     * @param userQo
     * @param result
     * @return
     */
    @RequestMapping("updateInfo")
    public Response updateUserInfo(@RequestBody @Valid UserQo userQo,BindingResult result){
        if(result.hasErrors()){
            return Response.error(Validator.validate(result));
        }
        return this.userService.updateUserInfo(userQo);
    }

}
