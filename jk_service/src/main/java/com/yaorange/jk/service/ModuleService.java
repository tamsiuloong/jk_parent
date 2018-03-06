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

    List<Module> findByPid(String pid);

    List<Module> findByRoleId(String rid);

    /**
     * 根据选中菜单类型 查询对应父菜单
     * @param ctype
     * @return
     */
    List<Module> findListByCtype(Integer ctype);
}
