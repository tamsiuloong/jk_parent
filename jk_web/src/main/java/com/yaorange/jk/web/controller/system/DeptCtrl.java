package com.yaorange.jk.web.controller.system;

import com.yaorange.jk.entity.Dept;
import com.yaorange.jk.entity.User;
import com.yaorange.jk.service.DeptService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by coach-tam on 2018/2/24.
 */
//@Controller
@RequestMapping("/system")
@RestController
public class DeptCtrl {

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/dept",method = RequestMethod.GET)
    public Pagination list(Pagination page)
    {
        Pagination result = deptService.findByPage(page);
        return result;
    }

    @RequestMapping(value = "/dept",method = RequestMethod.DELETE)
    public String delete(String[] ids)
    {
        deptService.deleteByIds(ids);
        return "1";
    }


    @RequestMapping(value = "/dept",method = RequestMethod.PUT)
    public String update(@RequestBody Dept dept)
    {
        deptService.update(dept);
        return "1";
    }

    @RequestMapping(value = "/dept",method = RequestMethod.POST)
    public Dept add(@RequestBody Dept dept)
    {
        deptService.save(dept);
        return dept;
    }
}
