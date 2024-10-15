package com.czhhhh.demo.demos.springboot.springtask;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class UseCronTask {
    @Scheduled(cron = "0 0/1 * ? * ?") //定时执行---每分钟打印一次日志
    public void printLog(){
        log.info("定时任务执行中。。。"+System.currentTimeMillis());
    }
}
