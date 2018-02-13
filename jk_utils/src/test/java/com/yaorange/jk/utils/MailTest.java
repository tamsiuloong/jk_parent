package com.yaorange.jk.utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author coach tam
 * @date 2017/12/26
 */
public class MailTest {

    public static void main(String[] args) throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.host","smtp.sina.com");
        props.put("mail.smtp.auth","true");

        //新建session对象
        Session session = Session.getInstance(props);

        //构建消息体
        Message message = new MimeMessage(session);

        //设置发件人
        Address sendAddress = new InternetAddress("yaorange_mail@sina.com");
        message.setFrom(sendAddress);
        //设置收件人
        Address toAdddress = new InternetAddress("yaorange_mail@sina.com");
        message.setRecipient(Message.RecipientType.TO,toAdddress);


        //设置标题
        message.setSubject("hello  mail");

        //设置正文
        message.setText("你好，我们正在做java mail测试！");


        //通过transport对象发送邮件
        Transport transport =session.getTransport("smtp");
        transport.connect("smtp.sina.com","yaorange_mail@sina.com","yaorange201703");

        transport.sendMessage(message,message.getAllRecipients());

    }
}
