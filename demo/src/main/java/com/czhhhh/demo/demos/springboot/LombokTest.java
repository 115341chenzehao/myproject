package com.czhhhh.demo.demos.springboot;




import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class LombokTest {
    @RequestMapping("/lombok")
    public User01 getUser(){
        System.out.println("张三，18");
        return new User01("张三",18);
    }
}

// lombok的注解
@Getter
@Setter
@ToString
class User01 {
    private String name;
    private Integer age;

    public User01() {
    }

    public User01(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

// Data注解包含Getter、Setter、ToString等
@Data
class User02 {
    private String name;
    private Integer age;
    public User02() {
    }

    public User02(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
