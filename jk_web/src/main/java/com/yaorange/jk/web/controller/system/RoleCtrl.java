package com.yaorange.jk.web.controller.system;

import com.yaorange.jk.entity.Module;
import com.yaorange.jk.entity.Role;
import com.yaorange.jk.service.RoleService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by coach-tam on 2018/2/24.
 */
//@Controller
@RequestMapping("/system/role")
@RestController
public class RoleCtrl {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public Pagination list(Pagination page)
    {
        Pagination result = roleService.findByPage(page);
        return result;
    }

    @GetMapping("/getAll")
    public List<Role> getAll(Pagination page)
    {
        List<Role> roleList = roleService.findAll();
        return roleList;
    }

    @DeleteMapping
    public String delete(String[] ids)
    {
        roleService.deleteByIds(ids);
        return "1";
    }


    @PutMapping
    public String update(Role role,String moduleIds)
    {
        String[] aModuleIds = moduleIds.split(",");
        roleService.update(role,aModuleIds);
        return "1";
    }

    @PostMapping
    public Role add(Role role,String moduleIds)
    {
        //moduleIds --> moduleSet
        Set<Module> moduleSet = new HashSet<>();
        String[] aModuleIds = moduleIds.split(",");
        for (String mId:aModuleIds) {
            Module module = new Module();
            module.setId(mId);
            moduleSet.add(module);
        }

        role.setModuleSet(moduleSet);
        roleService.save(role);
        return role;
    }
}
