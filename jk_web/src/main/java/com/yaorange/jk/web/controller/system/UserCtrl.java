package com.yaorange.jk.web.controller.system;

import com.yaorange.jk.entity.User;
import com.yaorange.jk.service.UserService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by coach-tam on 2018/2/24.
 */
//@Controller
@RequestMapping("/system/user")
@RestController
public class UserCtrl {

    @Autowired
    private UserService userService;

    @GetMapping
    public Pagination list(Pagination page)
    {
        Pagination result = userService.findByPage(page);
        return result;
    }
    @GetMapping("/getAll")
    public List<User> getAll()
    {
        List<User> userList = userService.findAll();

        return userList;
    }
    @DeleteMapping
    public String delete(String[] ids)
    {
        userService.deleteByIds(ids);
        return "1";
    }

    @PutMapping
    public String update(@RequestBody User user,@RequestParam("managerId") String managerId)
    {
        //这个地方的是Userinfo中忽略了manager字段的json转换，因此客户端虽然传了userInfo.manager.id过来，但spring mvc没有转换这个属性。因此单独传一个managerid过来
        User manager = new User();
        manager.setId(managerId);
        user.getUserInfo().setManager(manager);

        userService.update(user);
        return "1";
    }

    @PutMapping("/role")
    public String update(String id,@RequestParam("roleIds[]") String[] roleIds)
    {
        userService.updateRoles(id,roleIds);
        return "1";
    }
    @PostMapping
    public User add(@RequestBody User user)
    {
        userService.save(user);
        return user;
    }


}
