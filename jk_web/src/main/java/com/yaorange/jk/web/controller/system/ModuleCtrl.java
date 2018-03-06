package com.yaorange.jk.web.controller.system;

import com.yaorange.jk.entity.Module;
import com.yaorange.jk.entity.Module;
import com.yaorange.jk.entity.Role;
import com.yaorange.jk.entity.vo.IviewTreeVO;
import com.yaorange.jk.entity.vo.ZtreeVO;
import com.yaorange.jk.service.ModuleService;
import com.yaorange.jk.service.RoleService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by coach-tam on 2018/2/24.
 */
//@Controller
@RequestMapping("/system")
@RestController
public class ModuleCtrl {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/module",method = RequestMethod.GET)
    public Pagination list(Pagination page)
    {
        Pagination result = moduleService.findByPage(page);
        return result;
    }

    @RequestMapping(value = "/module/getAll",method = RequestMethod.GET)
    public List<Module> getAll(Pagination page)
    {
        List<Module> moduleList = moduleService.findAll();
        return moduleList;
    }


    @RequestMapping(value = "/module/getParent/{ctype}",method = RequestMethod.GET)
    public List<Module> getAll(@PathVariable("ctype") Integer ctype)
    {
        List<Module> moduleList = moduleService.findListByCtype(ctype);
        return moduleList;
    }
    @RequestMapping(value = "/module",method = RequestMethod.DELETE)
    public String delete(String[] ids)
    {
        moduleService.deleteByIds(ids);
        return "1";
    }


    @RequestMapping(value = "/module",method = RequestMethod.PUT)
    public String update(@RequestBody Module module)
    {
        moduleService.update(module);
        return "1";
    }

    @RequestMapping(value = "/module",method = RequestMethod.POST)
    public Module add(@RequestBody Module module)
    {
        moduleService.save(module);
        return module;
    }

    /**
     * 根据角色id获取所有模块
     * @param roleid
     * @return
     */
    @RequestMapping(value = "/module/getAllModules/{roleid}",method = RequestMethod.GET)
    public List<ZtreeVO> getAllModules(@PathVariable("roleid") String roleid)
    {
        //获取当前角色moduleSet
        Role role = roleService.findById(roleid);
        Set<Module> moduleSet = role.getModuleSet();
        //获取所有moduleList
        List<Module> moduleList =moduleService.findAll();

        //将moduleList-->ZtreeVOList
        List<ZtreeVO> ztreeVOList = new ArrayList<>();
        for (Module module:moduleList)
        {
            //module-->ztreeVo
            ZtreeVO ztreeVO = new ZtreeVO();
            ztreeVO.setId(module.getId());
            ztreeVO.setName(module.getName());
            ztreeVO.setPId(module.getParentId());
            ztreeVO.setOpen(true);
            if(moduleSet.contains(module))
            {
                ztreeVO.setChecked(true);
            }
            ztreeVOList.add(ztreeVO);
        }
        return ztreeVOList;
    }


    /**
     * 获取所有模块
     * @return
     */
    @RequestMapping(value = "/module/getAllModules",method = RequestMethod.GET)
    public List<ZtreeVO> getAllModules()
    {
        //获取所有moduleList
        List<Module> moduleList =moduleService.findAll();

        //将moduleList-->ZtreeVOList
        List<ZtreeVO> ztreeVOList = new ArrayList<>();
        for (Module module:moduleList)
        {
            //module-->ztreeVo
            ZtreeVO ztreeVO = new ZtreeVO();
            ztreeVO.setId(module.getId());
            ztreeVO.setName(module.getName());
            ztreeVO.setPId(module.getParentId());
            ztreeVO.setOpen(true);
            ztreeVOList.add(ztreeVO);
        }
        return ztreeVOList;
    }
    
}
