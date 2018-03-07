package com.yaorange.jk.web.controller;

import com.yaorange.jk.entity.User;
import com.yaorange.jk.entity.vo.ApiResponse;
import com.yaorange.jk.utils.ResponseCode;
import com.yaorange.jk.utils.SysConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller("userinfo")
@RequestMapping("/user")
public class UserCtrl {
    @RequestMapping("/info")
    public @ResponseBody ApiResponse info(String token,HttpSession session){
        User user = (User) session.getAttribute(token);

        if(user!=null)
        {
           return new ApiResponse(user);
        }else
        {
            return new ApiResponse(SysConstant.API_MESSAGE_PARAM_ERROR,"500",ResponseCode.API_CODE_CALL_FAIL);
        }

//        User user = new User();
//        user.setId("1");
//        user.setUid("1");
//        user.setName("admin");
//        user.setIntroduction("im super pig");
//        user.setRole(new String[]{"admin"});
//        user.setToken(UUID.randomUUID().toString());
//        return new ApiResponse(user);
    }
}
