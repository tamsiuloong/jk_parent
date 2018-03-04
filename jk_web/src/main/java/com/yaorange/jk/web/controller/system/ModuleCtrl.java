package com.yaorange.jk.web.controller.system;

import com.yaorange.jk.entity.Module;
import com.yaorange.jk.entity.vo.IviewTreeVO;
import com.yaorange.jk.service.ModuleService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coach-tam on 2018/2/24.
 */
//@Controller
@RequestMapping("/system")
@RestController
public class ModuleCtrl {

    @Autowired
    private ModuleService moduleService;


    @RequestMapping(value = "/module/getChildren/{pid}",method = RequestMethod.GET)
    public List<IviewTreeVO> getChildren(@PathVariable("pid") String pid)
    {
        //查询所有权限
        List<Module> moduleList = moduleService.findByPid(pid);

        List<IviewTreeVO> result = new ArrayList<>(0);

        //modulelist  -->  iviewTreeList

        for (Module module:moduleList) {
            //转换子节点
            List<IviewTreeVO> children = new ArrayList<>();
            module.getChildren().forEach(each -> {

                IviewTreeVO child = new IviewTreeVO(each.getId(), each.getName(), false,false, null);


                children.add(child);
            });

            //转换父节点
            IviewTreeVO iviewTreeVO = new IviewTreeVO(module.getId(), module.getName(), false,false, children);

            result.add(iviewTreeVO);
        }
        return result;
    }

    /**
     * 获取所有模块，并标识出该角色的权限
     * @param pid
     * @param rid
     * @return
     */
    @RequestMapping(value = "/module/getChildren/{pid}/{rid}",method = RequestMethod.GET)
    public List<IviewTreeVO> getAllModulesByRoleId(@PathVariable("pid") String pid,@PathVariable("rid") String rid)
    {
        //查询所有权限
        List<Module> moduleList = moduleService.findByPid(pid);

        //查询该角色的权限
        List<Module> roleModuleList = moduleService.findByRoleId(rid);
        List<IviewTreeVO> result = new ArrayList<>(0);

        //modulelist  -->  iviewTreeList

        for (Module module:moduleList) {
            //转换子节点
            List<IviewTreeVO> children = new ArrayList<>();
            module.getChildren().forEach(each -> {

                IviewTreeVO child = new IviewTreeVO(each.getId(), each.getName(), false,false, null);
                //判断该角色是否拥有该权限，是-->选中
                if(rid!=null && roleModuleList.contains(each))
                {
                    child.setChecked(true);
                }

                children.add(child);
            });

            //转换父节点
            IviewTreeVO iviewTreeVO = new IviewTreeVO(module.getId(), module.getName(), false,false, children);
            //判断该角色是否拥有该权限，是-->选中
            if(rid!=null && roleModuleList.contains(module))
            {
                iviewTreeVO.setChecked(true);
            }
            result.add(iviewTreeVO);
        }
        return result;
    }


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
}
