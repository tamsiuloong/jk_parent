package com.yaorange.jk.entity.vo;

import java.io.Serializable;

/**
 * @author coach tam
 * @date 2018/1/4
 */
public class PieChartVO implements Serializable{
    private String value;
    private String name;

    public PieChartVO() {
    }

    public PieChartVO(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
