package com.yaorange.jk.service.impl;

import com.yaorange.jk.dao.BaseDao;
import com.yaorange.jk.entity.Module;
import com.yaorange.jk.service.ModuleService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Service("moduleService")
@Transactional
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private BaseDao<Module,String> moduleDao;
    @Override
    @Transactional(readOnly = true)
    public Pagination findByPage(Pagination page) {
        return moduleDao.pageByHql("from Module",page.getPageNo(),page.getPageSize());
    }

    @Override
    public List<Module> findAll() {
        return moduleDao.getListByHQL("from Module");
    }

    @Override
    public void save(Module model) {

        //更新父模块
        //根据模块名字查询出对应权限id
        Module parentModule = moduleDao.getByHQL("from Module where name=?",model.getParentName());
        model.setParentId(parentModule.getId());
        model.setParentName(parentModule.getName());

        moduleDao.save(model);
    }

    @Override
    @Transactional(readOnly = true)
    public Module findById(String id) {
        return moduleDao.get(Module.class,id);
    }

    @Override
    public void update(Module model) {
        //查询老数据
        Module module = moduleDao.get(Module.class,model.getId());

        model.setCreateTime(module.getCreateTime());
        model.setUpdateTime(new Date());


        //更新父模块
        //根据模块名字查询出对应权限id
        Module parentModule = moduleDao.getByHQL("from Module where name=?",model.getParentName());
        model.setParentId(parentModule.getId());
        model.setParentName(parentModule.getName());

        moduleDao.evict(module);
        moduleDao.update(model);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id :ids)
        {
            moduleDao.deleteById(Module.class,id);
        }
    }

    @Override
    public List<Module> findByPid(String pid) {
        List<Module> result = null;
        String hql = "from Module where 1=1 ";
        if(pid!=null && (pid.isEmpty() || pid.equals("0")))
        {
            hql += " and parentId is null";
            result = moduleDao.getListByHQL(hql);;
        }else
        {
            hql += " and parentId = ?";
            result = moduleDao.getListByHQL(hql,pid);
        }
        return result;
    }

    @Override
    public List<Module> findByRoleId(String rid) {
        List<Module> result = null;
        String hql = "select distinct m from Module m  join fetch m.roleSet r where r.id = ?  ";

        result = moduleDao.getListByHQL(hql,rid);;

        return result;
    }
}
