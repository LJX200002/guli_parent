package com.atguigu.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author Miracle Luna
 * @Date 2022/4/2 16:00
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan(value = "com.atguigu")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
