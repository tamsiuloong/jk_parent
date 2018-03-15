package com.yaorange.jk.security;

import com.yaorange.jk.entity.Role;
import com.yaorange.jk.service.RoleService;
import com.yaorange.jk.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by coach-tam on 2018/3/12.
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.yaorange.jk.entity.User userEntity = userService.findByUsername(username);
        if(userEntity == null) {
            throw new UsernameNotFoundException("用户名："+ username + "不存在！");
        }
        String password = userEntity.getPassword();
        log.info(password);


        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
        Set<Role> roleSet =  userEntity.getRoleSet();
        roleSet.forEach(role->{role.getModuleSet().forEach(module -> {
            collection.add(new SimpleGrantedAuthority(module.getCpermission()));
        });});


		/*return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));*/

        return new User(username, password, collection);
    }


}
