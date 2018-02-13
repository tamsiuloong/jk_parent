package com.yaorange.jk.entity.vo;

import java.io.Serializable;

/**
 * id:"101", pId:"1", name:"我的留言板", open:true,checked:true
 * @author coach tam
 * @date 2017/12/25
 */
public class ZtreeVO implements Serializable {

    private String id;
    private String pId;
    private String name;
    private Boolean open;//是否展开
    private Boolean checked=false;//是否选中

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
