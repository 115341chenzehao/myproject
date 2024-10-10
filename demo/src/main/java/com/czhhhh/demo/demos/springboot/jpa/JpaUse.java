package com.czhhhh.demo.demos.springboot.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 利用jpa，查询相关的service类
 */
@RestController
public class JpaUse {
    @Autowired
    private UserService userService;

    @RequestMapping("/selectAll")
    public List<User> selectAll(){
        List<User> findall = userService.findall();
        return findall;
    }
}
