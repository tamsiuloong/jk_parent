package com.yaorange.jk.service.impl;

import com.yaorange.jk.dao.BaseDao;
import com.yaorange.jk.entity.Module;
import com.yaorange.jk.entity.Role;
import com.yaorange.jk.service.ModuleService;
import com.yaorange.jk.service.RoleService;
import com.yaorange.jk.utils.Pagination;
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
    public void update(Role model) {
        Role role = roleDao.get(Role.class,model.getId());

        role.setName(model.getName());
        role.setRemark(model.getRemark());
        role.setUpdateTime(new Date());

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
