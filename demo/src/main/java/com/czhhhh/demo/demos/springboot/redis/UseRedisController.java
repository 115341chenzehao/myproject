package com.czhhhh.demo.demos.springboot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UseRedisController{
    @Autowired
    private UseRedisService useRedisService;

    @RequestMapping("/useRedis")
    public void useRedis(){
        useRedisService.useRedisConn();
    }
}
