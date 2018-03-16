package com.yaorange.jk.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.yaorange.jk.utils.ViewVo;

import java.io.Serializable;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","parent"})
public class Dept implements Serializable{

    private String id;
    private String deptName;
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
