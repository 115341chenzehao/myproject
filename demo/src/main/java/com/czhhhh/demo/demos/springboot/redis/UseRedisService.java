package com.czhhhh.demo.demos.springboot.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UseRedisService {
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    public void useRedisConn(){
        /**
         * 1、使用RedisTemplate配置
         */
        //1.增加
        redisTemplate.opsForValue().set("key02","赵二");
        redisTemplate.opsForValue().set("key03","张三");
        //2.查询
        System.out.println("查询key为key02的value"+redisTemplate.opsForValue().get("key02"));
        //3.删除
        Boolean delFlag01 = redisTemplate.delete("key02");
        if ((delFlag01 != null)&&(delFlag01 == true)){
            System.out.println("删除key为key02的value--成功");
        } else {
            System.out.println("删除key为key02的value--失败");
        }
        //4.更新
        redisTemplate.opsForValue().set("key03","张三更新了");
        System.out.println(redisTemplate.opsForValue().get("key03"));

        /**
         * 2、使用RedisTemplate配置
         */
        //1.增加
        stringRedisTemplate.opsForValue().set("name","小张三");
        stringRedisTemplate.opsForValue().set("name02","小赵四");
        //2.查询
        System.out.println(stringRedisTemplate.opsForValue().get("name"));
        //3.删除
        Boolean delFlag02 = stringRedisTemplate.delete("name");
        if ((delFlag02 != null)&&(delFlag02 == true)){
            System.out.println("删除key为name的value--成功");
        } else {
            System.out.println("删除key为name的value--失败");
        }
        //4.更新
        stringRedisTemplate.opsForValue().set("name02","小张三更新了");
        System.out.println("更新内容"+stringRedisTemplate.opsForValue().get("name02"));
    }
}
