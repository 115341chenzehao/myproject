package com.czhhhh.demo.demos.springboot.springtask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling //启动springtask定时任务,貌似必须加在启动类上才行
public class UseSpringtaskConfig {
}
