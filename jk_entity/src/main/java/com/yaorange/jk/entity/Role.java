package com.yaorange.jk.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

/**
 * @author coach tam
 * @date 2017/12/21
 */

@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","moduleSet"})
public class Role extends BaseEntity {

    private String id;
    private String name;
    private String remark;
    private Integer orderNo;//用于排序

    private Set<Module> moduleSet;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Set<Module> getModuleSet() {
        return moduleSet;
    }

    public void setModuleSet(Set<Module> moduleSet) {
        this.moduleSet = moduleSet;
    }
}
