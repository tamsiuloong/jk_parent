package com.yaorange.jk.service.impl;

import com.yaorange.jk.dao.BaseDao;
import com.yaorange.jk.entity.Module;
import com.yaorange.jk.entity.Role;
import com.yaorange.jk.service.ModuleService;
import com.yaorange.jk.service.RoleService;
import com.yaorange.jk.utils.Pagination;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private BaseDao<Role,String> roleDao;

    @Autowired
    private ModuleService moduleService;
    @Override
    @Transactional(readOnly = true)
    public Pagination findByPage(Pagination page) {
        return roleDao.pageByHql("from Role",page.getPageNo(),page.getPageSize());
    }

    @Override
    public List<Role> findAll() {
        return roleDao.getListByHQL("from Role");
    }

    @Override
    public void save(Role model) {
        roleDao.save(model);
    }

    @Override
    @Transactional(readOnly = true)
    public Role findById(String id) {
        return roleDao.get(Role.class,id);
    }

    @Override
    public void update(Role model,String[] moduleIds) {
        Role role = roleDao.get(Role.class,model.getId());





        role.setName(model.getName());
        role.setRemark(model.getRemark());

        role.setUpdateTime(new Date());

        //moduleIds --> moduleSet
        Set<Module> moduleSet = new HashSet<>();

        for (String mId:moduleIds) {


            //由于ivew 的tree在选中A下的子节点时，只要没有把A下子节点 选中，那么A不会被选中。
            //这导致一个问题，父权限如果都没有，子权限就无效。所以怎么办？1，该用ztree   2，手动选中父节点(不行)

            Module module = moduleService.findById(mId);
            moduleSet.add(module);

            if(module.getParentId()!=null&&!module.getParentId().isEmpty())
            {
                Module parent = new Module();
                parent.setId(module.getParentId());
                moduleSet.add(parent);
            }
            moduleSet.add(module);
        }

        role.setModuleSet(moduleSet);
        roleDao.update(role);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id :ids)
        {
            roleDao.deleteById(Role.class,id);
        }
    }

    @Override
    public void updateModule(String id, String moduleIds) {
        //查询出需要更新角色
        Role role = roleDao.get(Role.class,id);
        String[] a_moduleIds = moduleIds.split(",");

        //更新角色模块
        //moduleIds --> moduleSet
        Set<Module> moduleSet = new HashSet<>(moduleIds.split(",").length);
        if(moduleIds!=null && !moduleIds.isEmpty())
        {
            for (String moduleId:a_moduleIds ) {
                Module module = moduleService.findById(moduleId);
                moduleSet.add(module);
            }
        }
        role.setModuleSet(moduleSet);

        roleDao.update(role);
    }
}
