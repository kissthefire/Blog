package com;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Dell on 2018/7/13.
 */
@SpringBootApplication
@EnableDubboConfiguration
@ComponentScan("com.blog.manage")
public class ManageServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ManageServiceApp.class,args);
    }
}
