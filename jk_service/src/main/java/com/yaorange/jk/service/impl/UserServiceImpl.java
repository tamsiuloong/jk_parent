package com.yaorange.jk.service.impl;

import com.yaorange.jk.dao.BaseDao;
import com.yaorange.jk.entity.Role;
import com.yaorange.jk.entity.User;
import com.yaorange.jk.service.RoleService;
import com.yaorange.jk.service.UserService;
import com.yaorange.jk.utils.Encrypt;
import com.yaorange.jk.utils.JavaMailUtils;
import com.yaorange.jk.utils.Pagination;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.java2d.pipe.SpanShapeRenderer;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private BaseDao<User,String> userDao;

    @Autowired
    private RoleService roleService;


    @Autowired
    private JavaMailUtils javaMailUtils;
    @Override
    @Transactional(readOnly = true)
    public Pagination findByPage(Pagination page, User user) {
        String hql = "from User where 1=1";
        if(user.getUserInfo().getDegree()==2)
        {
            hql +=" and createDept = '"+user.getDept().getDeptName()+"'";
        }
        else if(user.getUserInfo().getDegree()==3)
        {
            hql += " and createBy = '"+user.getUserName()+"'";
        }

        return userDao.pageByHql(hql,page.getPageNo(),page.getPageSize());
    }

    @Override
    public List<User> findAll() {
        return userDao.getListByHQL("from User");
    }

    @Override
    public void save(User model) {
        //一对一，需要双向设置关系
        model.getUserInfo().setUser(model);
        //初始化密码
        String password = "123456";
        //发送邮件
        if(null!=model.getUserInfo().getEmail()||!model.getUserInfo().getEmail().isEmpty())
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean isOK = false;
                    int count = 0;

                        while (!isOK&&count < 3)
                        {
                            try
                            {
                                javaMailUtils.sendMail(model.getUserInfo().getEmail(),"欢迎你加入本公司","你好:"+model.getUserInfo().getName()+",你的登录账号/密码是:"+model.getUserName()+"/"+password);
                                isOK = true;
                            }
                            catch (Exception e)
                            {
                                count++;
                                logger.error("发送第"+count+"次失败!5秒过后再次尝试。");
                                try {
                                    Thread.sleep(5000);
                                } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }



                }
            }).start();

        }


        model.setPassword(Encrypt.md5(password,model.getUserName()));


        userDao.save(model);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(String id) {
        return userDao.get(User.class,id);
    }

    @Override
    public void update(User model) {
        //根据id查询出数据库的老数据
        User user = userDao.get(User.class,model.getId());
        user.setDept(model.getDept());
        user.setUserName(model.getUserName());
        user.setState(model.getState());
        user.setUpdateTime(new Date());

        userDao.update(user);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id :ids)
        {
            userDao.deleteById(User.class,id);
        }
    }

    @Override
    public void updateRoles(String id, String[] roleIds) {
        //通过id找到用户
        User user = userDao.get(User.class,id);

        //roleIds-->roleSet
        Set<Role> roleSet = new HashSet<>();
        for (String roleId:roleIds) {
            Role role = roleService.findById(roleId);
            roleSet.add(role);
        }


        //设置用户和角色的关系
        user.setRoleSet(roleSet);
        //更新用户
        userDao.update(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.getByHQL("from User where userName=?",username);
    }

    @Override
    public Boolean checkUserName(String userName) {
        User user = findByUsername(userName);
        return user==null?false:true;
    }
}
