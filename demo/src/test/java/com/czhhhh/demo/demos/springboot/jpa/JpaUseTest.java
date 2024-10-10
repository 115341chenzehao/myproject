package com.czhhhh.demo.demos.springboot.jpa;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
public class JpaUseTest {
    @Resource
    private UserService  userService;

    @Test
    void  contextLoads() {
        //查询所有
        userService.findall().stream().forEach(user -> log.info("查询所有{}",user));
    }

}
