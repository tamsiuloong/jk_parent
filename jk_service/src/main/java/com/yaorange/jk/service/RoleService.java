package com.yaorange.jk.service;

import com.yaorange.jk.entity.Role;
import com.yaorange.jk.utils.Pagination;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
public interface RoleService {
    Pagination findByPage(Pagination page);

    List<Role> findAll();

    void save(Role model);

    Role findById(String id);

    void update(Role model);

    void deleteByIds(String[] ids);

    /**
     * 根据角色id更新角色模块
     * @param id
     * @param moduleIds
     */
    void updateModule(String id, String moduleIds);
}
