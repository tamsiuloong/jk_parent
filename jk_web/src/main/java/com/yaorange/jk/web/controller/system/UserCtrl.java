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
@RequestMapping("/system")
@RestController
public class UserCtrl {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public Pagination list(Pagination page)
    {
        Pagination result = userService.findByPage(page);
        return result;
    }

    @RequestMapping(value = "/user/getAll",method = RequestMethod.GET)
    public List<User> getAll()
    {
        List<User> userList = userService.findAll();

        return userList;
    }
    @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    public String delete(String[] ids)
    {
        userService.deleteByIds(ids);
        return "1";
    }


    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public String update(@RequestBody User user)
    {
        userService.update(user);
        return "1";
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public User add(@RequestBody User user)
    {
        userService.save(user);
        return user;
    }


}
