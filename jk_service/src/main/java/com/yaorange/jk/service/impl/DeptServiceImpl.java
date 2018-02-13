package com.yaorange.jk.service.impl;

import com.yaorange.jk.dao.BaseDao;
import com.yaorange.jk.entity.Dept;
import com.yaorange.jk.service.DeptService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Service("deptService")
@Transactional
public class DeptServiceImpl implements DeptService {

    @Autowired
    private BaseDao<Dept,String> deptDao;
    @Override
    @Transactional(readOnly = true)
    public Pagination findByPage(Pagination page) {
        return deptDao.pageByHql("from Dept",page.getPageNo(),page.getPageSize());
    }

    @Override
    public List<Dept> findAll() {
        return deptDao.getListByHQL("from Dept");
    }

    @Override
    public void save(Dept model) {
        deptDao.save(model);
    }

    @Override
    @Transactional(readOnly = true)
    public Dept findById(String id) {
        return deptDao.get(Dept.class,id);
    }

    @Override
    public void update(Dept model) {
        deptDao.update(model);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id :ids)
        {
            deptDao.deleteById(Dept.class,id);
        }
    }
}
