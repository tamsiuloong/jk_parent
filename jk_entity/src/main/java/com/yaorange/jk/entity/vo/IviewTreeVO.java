package com.yaorange.jk.entity.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/25
 */
public class IviewTreeVO implements Serializable {


    private String id;
    private String title;
    private Boolean expand=false;
    private Boolean selected=false;
    private Boolean loading=false;
    private Boolean checked=false;
    private List<IviewTreeVO> children;

    public IviewTreeVO() {
    }

    public IviewTreeVO(String id,String title, Boolean loading, Boolean expand,List<IviewTreeVO> children) {
        this.id = id;
        this.expand = expand;
        this.title = title;
        this.loading = loading;
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getExpand() {
        return expand;
    }

    public void setExpand(Boolean expand) {
        this.expand = expand;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Boolean getLoading() {
        return loading;
    }

    public void setLoading(Boolean loading) {
        this.loading = loading;
    }

    public List<IviewTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<IviewTreeVO> children) {
        this.children = children;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
