package com.yaorange.jk.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * @author coach tam
 * @date 2017/12/21
 */
public class Module extends BaseEntity {
    private String id;
    private String parentId;
    private String parentName;
    private String name;
    private Long layerNum;
    private Long isLeaf;
    private String ico;
    private String cpermission;
    private String curl;
    private Long ctype;
    private Long state;
    private String belong;
    private String cwhich;
    private Long quoteNum;
    private String remark;
    private Long orderNo;
    @JSONField(serialize = false)
    private Set<Role> roleSet = new HashSet<>();//拥有该模块的角色

    private Set<Module> children=new HashSet<>();//子模块

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLayerNum() {
        return layerNum;
    }

    public void setLayerNum(Long layerNum) {
        this.layerNum = layerNum;
    }

    public Long getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Long isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getCpermission() {
        return cpermission;
    }

    public void setCpermission(String cpermission) {
        this.cpermission = cpermission;
    }

    public String getCurl() {
        return curl;
    }

    public void setCurl(String curl) {
        this.curl = curl;
    }

    public Long getCtype() {
        return ctype;
    }

    public void setCtype(Long ctype) {
        this.ctype = ctype;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getCwhich() {
        return cwhich;
    }

    public void setCwhich(String cwhich) {
        this.cwhich = cwhich;
    }

    public Long getQuoteNum() {
        return quoteNum;
    }

    public void setQuoteNum(Long quoteNum) {
        this.quoteNum = quoteNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Set<Module> getChildren() {
        return children;
    }

    public void setChildren(Set<Module> children) {
        this.children = children;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Module module = (Module) o;

        if (!id.equals(module.id)) return false;
        if (cwhich != null ? !cwhich.equals(module.cwhich) : module.cwhich != null) return false;
        if (quoteNum != null ? !quoteNum.equals(module.quoteNum) : module.quoteNum != null) return false;
        if (remark != null ? !remark.equals(module.remark) : module.remark != null) return false;
        if (orderNo != null ? !orderNo.equals(module.orderNo) : module.orderNo != null) return false;
        if (roleSet != null ? !roleSet.equals(module.roleSet) : module.roleSet != null) return false;
        return children != null ? children.equals(module.children) : module.children == null;

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
