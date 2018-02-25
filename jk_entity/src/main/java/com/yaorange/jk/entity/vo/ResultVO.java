package com.yaorange.jk.entity.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/27.
 */
public class ResultVO implements Serializable{
    private Object data;
    private Boolean status=true;
    private String token ;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
