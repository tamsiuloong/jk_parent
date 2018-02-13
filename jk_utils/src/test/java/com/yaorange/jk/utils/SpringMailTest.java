package com.yaorange.jk.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author coach tam
 * @date 2017/12/26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-mail.xml")
public class SpringMailTest {

    @Autowired
    private JavaMailUtils javaMailUtils;
    @Test
    public void test1(){
        javaMailUtils.sendHtmlMail("yaorange_mail@sina.com","test",
                "<html><head></head><body><h1>hello kg </h1><a>点吧</a> " +
                        "<img src='https://f10.baidu.com/it/u=3415973664,1054904274&fm=72'/></body></html>");
    }
}
