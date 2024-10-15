package com.czhhhh.demo.demos.springboot.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 各项配置和Swagger一致；
 * 使用的配置类也是同一个
 * 访问地址不同：http://localhost:8080/doc.html
 */
@Api(tags = "Knife4j测试")
@RestController
@RequestMapping("/knife4j")
public class Knife4jController {

    @ApiOperation(value = "测试",httpMethod = "GET")
    @RequestMapping("/text")
    public String text(){
        return "这是Knife4j的测试--Swagger的升级版";
    }


}
