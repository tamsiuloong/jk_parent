package com.yaorange.jk.web.controller;

import com.yaorange.jk.entity.Role;
import com.yaorange.jk.entity.User;
import com.yaorange.jk.entity.vo.ApiResponse;
import com.yaorange.jk.utils.ResponseCode;
import com.yaorange.jk.utils.SysConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/login")
public class LoginCtrl {
    @RequestMapping("/loginbyemail")
    public ApiResponse loginbyemail(String username, String password, HttpSession session){

        String message = SysConstant.API_MESSAGE_SUCCESS;
        String code = ResponseCode.API_CODE_CALL_SUCCESS;
        //1,如果没有输入用户名密码，直接跳登录页面
        if(username==null||username.isEmpty())
        {
            return null;
        }
        else
        {
            try{

//                Subject subject = SecurityUtils.getSubject();
//                //将表单数据封装到token对象中
//                //token-->令牌
//                UsernamePasswordToken token = new UsernamePasswordToken(username,password);
//                subject.login(token);
//
//                User user = (User)subject.getPrincipal();
//
////                user.setIntroduction("im super pig");
////                user.setRole(new String[]{"test"});
//                List<String> permissions  = new ArrayList<>();
//                Set<Role> roleSet = user.getRoleSet();
//                roleSet.forEach(role -> {
//                    role.getModuleSet().forEach(module -> {
//                        permissions.add(module.getName());
//                    });
//                });
//
//                permissions.add("/");
//                user.setPermissions(permissions);
//                user.setToken(UUID.randomUUID().toString());



                User user = new User();
                user.setId("1");
                user.setUid("1");
                user.setName("admin");
                user.setIntroduction("im super pig");
                List<String>  permissions = new ArrayList<>();
                permissions.add("admin");

                user.setPermissions(permissions);
                user.setToken(UUID.randomUUID().toString());
                //将用户信息保存到session
                session.setAttribute(user.getToken(),user);

                ApiResponse apiResponse = new ApiResponse(user, message, code);
                return apiResponse;

            }
            catch(Exception e)
            {
                message=SysConstant.API_MESSAGE_PASSWORD_ERROR;
                code=ResponseCode.API_CODE_PASSWORD_ERROR;
                return new ApiResponse(null, message, code);
            }
        }
    }


    @RequestMapping("/logout")
    public  User logout(){
        User user = new User();
        user.setId("1");
        user.setUid("1");
        user.setName("admin");
        user.setIntroduction("im super pig");
//        user.setRole(new String[]{"admin"});
        user.setToken(UUID.randomUUID().toString());
        return user;
    }
}
