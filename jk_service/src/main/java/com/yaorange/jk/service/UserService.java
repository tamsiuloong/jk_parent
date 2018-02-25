package com.yaorange.jk.service;

import com.yaorange.jk.entity.User;
import com.yaorange.jk.utils.Pagination;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
public interface UserService {
    Pagination findByPage(Pagination page);

    List<User> findAll();

    void save(User model);

    User findById(String id);

    void update(User model);

    void deleteByIds(String[] ids);

    /**
     * 更新用户角色
     * @param id  用户id
     * @param roleIds 角色ids
     */
    void updateRoles(String id, String[] roleIds);

    /**
     * 根据用户名查询user对象
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 检测用户名是否存在
     * @param userName
     * @return true:存在 false:不存在
     */
    Boolean checkUserName(String userName);
}
