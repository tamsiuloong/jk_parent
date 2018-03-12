package com.yaorange.jk.web.controller;

import com.yaorange.jk.entity.User;
import com.yaorange.jk.entity.vo.ApiResponse;
import com.yaorange.jk.utils.ResponseCode;
import com.yaorange.jk.utils.SysConstant;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller("userinfo")
@RequestMapping("/user")
public class UserCtrl {
    @RequestMapping("/info")
    public @ResponseBody ApiResponse info(String token,HttpSession session){
//        User user = (User) session.getAttribute(token);
//
//
//        if(user!=null)
//        {
//           return new ApiResponse(user);
//        }else
//        {
//            return new ApiResponse(SysConstant.API_MESSAGE_PARAM_ERROR,"500",ResponseCode.API_CODE_CALL_FAIL);
//        }
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        User user = new User();
        user.setId("1");
        user.setUid("1");
        user.setName(userDetails.getUsername());
        user.setIntroduction("im super pig");
        List<String>  permissions = new ArrayList<>();
        userDetails.getAuthorities().forEach(a->{permissions.add(a.getAuthority());});
        permissions.add("admin");

        user.setPermissions(permissions);
        user.setToken(UUID.randomUUID().toString());
        return new ApiResponse(user);
    }
}
