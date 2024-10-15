package com.czhhhh.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.validation.constraints.Max;

@SpringBootApplication
@ServletComponentScan //1.Filter过滤器自动注入
//@EnableScheduling //2.启动springtask定时任务
@MapperScan //Mybytis自动扫描
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
