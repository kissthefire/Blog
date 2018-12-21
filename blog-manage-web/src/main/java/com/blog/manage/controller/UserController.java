package com.blog.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.blog.manage.entity.bean.User;
import com.blog.manage.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dell on 2018/7/13.
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Reference
    private UserService userService;

    @RequestMapping("/get")
    public Map getUser(@RequestBody User u){
        User user=this.userService.querUserById(u.getId());
        Map map=new HashMap();
        map.put("code","100");
        map.put("data",user);
        map.put("msg","ok");
        return map;
    }

    @RequestMapping("/save")
    public Map saveUser(@RequestBody User user){
        int i=this.userService.saveUser(user);
        Map map=new HashMap();
        map.put("code","100");
        map.put("data","成功");
        map.put("msg","ok");
        return map;
    }

}
