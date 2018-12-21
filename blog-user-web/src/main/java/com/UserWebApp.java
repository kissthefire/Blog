package com;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Dell on 2018/7/29.
 */
@EnableDubboConfiguration
@SpringBootApplication
public class UserWebApp {
    public static void main(String[] args) {
        SpringApplication.run(UserWebApp.class,args);
    }
}
