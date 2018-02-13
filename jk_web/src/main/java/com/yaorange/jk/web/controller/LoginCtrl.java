package com.yaorange.jk.web.controller;

import com.yaorange.jk.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequestMapping("/login")
public class LoginCtrl {
    @RequestMapping("/loginbyemail")
    public @ResponseBody User loginbyemail(){
        User user = new User();
        user.setId("1");
        user.setUid("1");
        user.setName("admin");
        user.setIntroduction("im super pig");
        user.setRole(new String[]{"admin"});
        user.setToken(UUID.randomUUID().toString());
        return user;
    }
}
