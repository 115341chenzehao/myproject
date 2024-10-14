package com.czhhhh.demo.demos.springboot.interceptor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/useMyInterceptor")
public class useMyInterceptorController{

    @RequestMapping("/hello")
    public String hello(){
        return "good boy，this is use MyInterceptor!!!";
    }
}
