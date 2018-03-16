package com.yaorange.jk.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.yaorange.jk.entity.User;
import com.yaorange.jk.entity.vo.ApiResponse;
import com.yaorange.jk.utils.ResponseCode;
import com.yaorange.jk.utils.SysConstant;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController("userinfo")
@RequestMapping("/user")
public class UserCtrl {
    @GetMapping("/info")
    public @ResponseBody ApiResponse info(String token,HttpSession session){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        User user = new User();
        user.setId("1");
        user.setUserName(userDetails.getUsername());

        List<String>  permissions = new ArrayList<>();
        userDetails.getAuthorities().forEach(a->{permissions.add(a.getAuthority());});

        permissions.add("admin");
        permissions.add("首页");
        permissions.add("欢迎");

        user.setPermissions(permissions);
        user.setToken(UUID.randomUUID().toString());


        return new ApiResponse(user);
    }
}
