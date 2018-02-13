package com.yaorange.jk.web.controller;

import com.yaorange.jk.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloCtrl {
    @RequestMapping("/index")
    public @ResponseBody User index(){
        User user = new User();
        user.setId("1");
        return user;
    }
}
