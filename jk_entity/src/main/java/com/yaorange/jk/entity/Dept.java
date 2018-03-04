package com.yaorange.jk.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * @author coach tam
 * @date 2017/12/20
 */
public class Dept implements Serializable{
    private String id;
    private String deptName;
    @JSONField(serialize = false)
    private Dept parent;//many2one
    private Long state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Dept getParent() {
        return parent;
    }

    public void setParent(Dept parent) {
        this.parent = parent;
    }
}
