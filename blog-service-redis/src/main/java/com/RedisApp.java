package com;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Dell on 2018/10/7.
 */
@EnableDubboConfiguration
@SpringBootApplication
@ComponentScan("com.blog.redis.service.impl")
public class RedisApp {
    public static void main(String[] args) {
        SpringApplication.run(RedisApp.class,args);
    }
}
