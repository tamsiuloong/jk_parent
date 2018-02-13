package com.yaorange.jk.service;

import com.yaorange.jk.entity.Dept;
import com.yaorange.jk.utils.Pagination;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
public interface DeptService {
    Pagination findByPage(Pagination page);

    List<Dept> findAll();

    void save(Dept model);

    Dept findById(String id);

    void update(Dept model);

    void deleteByIds(String[] ids);
}
