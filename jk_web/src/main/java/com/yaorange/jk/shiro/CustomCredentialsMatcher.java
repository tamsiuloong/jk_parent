package com.yaorange.jk.shiro;

import com.yaorange.jk.utils.Encrypt;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @author coach tam
 * @date 2017/12/22
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //向下转型(父-->子)
        UsernamePasswordToken upToken = (UsernamePasswordToken)token;

        //将表单明文密码加密成密文密码
        //123456-->skdfjalkdjfklasjxcvxklvjksjdf
        String password = Encrypt.md5(new String(upToken.getPassword()),upToken.getUsername());

        //获取数据库密文密码
        SimpleAuthenticationInfo sInfo = (SimpleAuthenticationInfo)info;
        String dbPassword = (String) sInfo.getCredentials();
        //比较表单的密码和数据的密码是否一致
        return password.equalsIgnoreCase(dbPassword);
    }
}
