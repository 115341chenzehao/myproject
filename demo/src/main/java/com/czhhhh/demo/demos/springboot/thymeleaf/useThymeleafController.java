package com.czhhhh.demo.demos.springboot.thymeleaf;


import com.czhhhh.demo.demos.springboot.jpa.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/use")
public class useThymeleafController {

    @Autowired
    private UserService useService;

    /**
     * Model 接口可以承载数据库里查到的数据，前端可以从 model 中取出来。
     * @param model
     * @return
     */
    @RequestMapping("/all")
    public String all(Model model){
        model.addAttribute("users",useService.findall());
        return "all"; // resource/templates目录下对应的all.html文件
    }

}
