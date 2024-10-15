package com.czhhhh.demo.demos.springboot.swagger;

import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Swagger验证：访问http://localhost:8080/swagger-ui/
/**
 * @Api注解，用在类上，该注解将控制器标注为一个 Swagger 资源。该注解有 3 个属性：
 * tags，具有相同标签的 API 会被归在一组内展示
 * value，如果 tags 没有定义，value 将作为 API 的 tags 使用。
 */
@Api(tags="Swagger测试")
@RestController
@RequestMapping("/swagger")
public class SwaggerController {

    /**
     * @ApiOperation 注解，用在方法上，描述这个方法是做什么用的。该注解有 4 个属性：
     * value 操作的简单说明，长度为120个字母，60个汉字。
     * notes 对操作的详细说明。
     * httpMethod HTTP请求的动作名，可选值有："GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS" and "PATCH"。
     * code 默认为200，有效值必须符合标准的HTTP Status Code Definitions。
     * @return
     */
    @RequestMapping("/test")
    @ApiOperation(value = "测试",httpMethod = "GET")
    public String test(){
        return "这是一个swagger测试";
    }

}
