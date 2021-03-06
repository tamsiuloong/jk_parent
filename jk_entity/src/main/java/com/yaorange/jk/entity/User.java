package com.yaorange.jk.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author coach tam
 * @date 2017/12/19
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class User extends BaseEntity {

    public interface  Info{}
    @JsonView(Info.class)
    private String id;
    @JsonView(Info.class)
    private String userName;
    private Integer state;
    private String password;

    private Dept dept;//所属部门
    private UserInfo userInfo;//扩展信息
    private Set<Role> roleSet=new HashSet<>();


    //view object (因为fastjson的死循环检测机制，不会转换以下数据，因此得自己转换)
    private String deptId;
    private String managerId;

    @JsonView(Info.class)
    private String token;
    @JsonView(Info.class)
    private List<String> permissions;

    public User() {
    }

    public User(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    public String getDeptId() {
        if(dept!=null)
        {
            deptId = this.getDept().getId();
        }

        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getManagerId() {
        if(userInfo!=null&&userInfo.getManager()!=null)
        {
            managerId = this.getUserInfo().getManager().getId();
        }
        return managerId;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
}
