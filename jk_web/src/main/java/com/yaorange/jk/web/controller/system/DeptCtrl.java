package com.yaorange.jk.web.controller.system;

import com.fasterxml.jackson.annotation.JsonView;
import com.yaorange.jk.entity.Dept;
import com.yaorange.jk.entity.User;
import com.yaorange.jk.service.DeptService;
import com.yaorange.jk.utils.Pagination;
import com.yaorange.jk.utils.ViewVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by coach-tam on 2018/2/24.
 */
//@Controller
@RequestMapping("/system/dept")
@RestController
public class DeptCtrl {

    @Autowired
    private DeptService deptService;

    @GetMapping
    @JsonView(ViewVo.Simple.class)
    public Pagination list(Pagination page)
    {
        Pagination result = deptService.findByPage(page);
        return result;
    }


    @GetMapping("getAll")
    @JsonView(ViewVo.Simple.class)
    public List<Dept> getAll(Pagination page)
    {
        List<Dept> deptList = deptService.findAll();
        return deptList;
    }

    @DeleteMapping
    public String delete(String[] ids)
    {
        deptService.deleteByIds(ids);
        return "1";
    }


    @PutMapping
    public String update(@RequestBody Dept dept)
    {
        deptService.update(dept);
        return "1";
    }

    @PostMapping
    public Dept add(@RequestBody Dept dept)
    {
        deptService.save(dept);
        return dept;
    }
}
