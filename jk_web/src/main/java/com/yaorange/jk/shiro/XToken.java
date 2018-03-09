package com.yaorange.jk.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by coach-tam on 2018/3/9.
 */
public class XToken implements AuthenticationToken {
    private String xToken;

    public XToken(String xToken) {
        this.xToken = xToken;
    }

    @Override
    public Object getPrincipal() {
        return xToken;
    }

    @Override
    public Object getCredentials() {
        return xToken;
    }
}
