package com.yaorange.jk.web.controller;

import com.yaorange.jk.entity.Role;
import com.yaorange.jk.entity.User;
import com.yaorange.jk.entity.vo.ApiResponse;
import com.yaorange.jk.utils.ResponseCode;
import com.yaorange.jk.utils.SysConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/login")
public class LoginCtrl {
//    @RequestMapping("/loginbyemail")
//    public ApiResponse loginbyemail(String username, String password, HttpSession session){
//
//        String message = SysConstant.API_MESSAGE_SUCCESS;
//        String code = ResponseCode.API_CODE_CALL_SUCCESS;
//        //1,如果没有输入用户名密码，直接跳登录页面
//        if(username==null||username.isEmpty())
//        {
//            return null;
//        }
//        else
//        {
//            try{
//
//                User user = new User();
//                user.setId("1");
//                user.setUid("1");
//                user.setName("admin");
//                user.setIntroduction("im super pig");
//                List<String>  permissions = new ArrayList<>();
//                permissions.add("admin");
//
//                user.setPermissions(permissions);
//                user.setToken(UUID.randomUUID().toString());
//                //将用户信息保存到session
//                session.setAttribute(user.getToken(),user);
//
//                ApiResponse apiResponse = new ApiResponse(user, message, code);
//                return apiResponse;
//
//            }
//            catch(Exception e)
//            {
//                message=SysConstant.API_MESSAGE_PASSWORD_ERROR;
//                code=ResponseCode.API_CODE_PASSWORD_ERROR;
//                return new ApiResponse(null, message, code);
//            }
//        }
//    }


    @RequestMapping("/logout")
    public  ApiResponse logout(HttpServletRequest request, HttpServletResponse response){
//        User user = new User();
//        user.setId("1");
//        user.setUid("1");
//        user.setName("admin");
//        user.setIntroduction("im super pig");
////        user.setRole(new String[]{"admin"});
//        user.setToken(UUID.randomUUID().toString());

//        OAuth2AccessToken token = UserDetailsManager.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return new ApiResponse(null, SysConstant.API_MESSAGE_USER_STATUS_NOT_LOGIN, ResponseCode.API_CODE_CALL_SUCCESS);
    }
}
