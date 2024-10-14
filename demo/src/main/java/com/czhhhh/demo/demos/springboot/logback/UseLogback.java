package com.czhhhh.demo.demos.springboot.logback;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这种方式只会打印在控制台；
 * 建议用resource/logback-spring.xml单独生成日志 -- springboot会自动扫描resource下的logback-spring.xml
 * 而且用logback-spring.xml可以区分生产、测试、开发环境
 *
 * Logback 的日志级别是这样的：（比如陪INFO，则级别低不显示）
 * TRACE < DEBUG < INFO < WARN < ERROR
 */
@RestController
@Slf4j //lombok用于日志的注解，替代了 static Logger logger = LoggerFactory.getLogger(UseLogback.class);
public class UseLogback {

    //用lombok的slf4j注解
    //static Logger logger = LoggerFactory.getLogger(UseLogback.class);

    @RequestMapping("/useLogback")
    public String useLogback() {
        /**
         这里是查询logback-spring.xml中${user.home}的值，查出来为C:\Users\chenzehao
         因此logback文件日志路径：C:\Users\chenzehao\myproject\demo\logs\myproject-study.log
         */
        System.out.println(System.getProperty("user.home"));
        //logger.info("czh use logback");
        log.info("czh use logback"); //log是lombok的，logger是非注解的形参；
        return "hello,this is logback";
    }

}
