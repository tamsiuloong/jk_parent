package com.yaorange.jk.service;

import com.yaorange.jk.entity.Module;
import com.yaorange.jk.utils.Pagination;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
public interface ModuleService {
    Pagination findByPage(Pagination page);

    List<Module> findAll();

    void save(Module model);

    Module findById(String id);

    void update(Module model);

    void deleteByIds(String[] ids);
}
