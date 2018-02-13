package com.yaorange.jk.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

/**
 * @author coach tam
 * @date 2017/12/26
 */
@Component("javaMailUtils")
public class JavaMailUtils {

    @Autowired
    private SimpleMailMessage mailMessage;

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String to, String title, String text){
        mailMessage.setTo(to);
        mailMessage.setSubject(title);
        mailMessage.setText(text);

        mailSender.send(mailMessage);
    }

    public void sendHtmlMail(String to, String title, String text){
//        mailMessage.setTo(to);
//        mailMessage.setSubject(title);
//        mailMessage.setText(text);

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(mailMessage.getFrom());
            helper.setTo(to);
            helper.setSubject(title);
            helper.setText(text,true);


            //添加图片
            FileSystemResource resource = new FileSystemResource(new File("D:\\用户目录\\下载\\美女.jpg"));
            helper.addInline("image", resource);



            //发送时带附件
            FileSystemResource zipResource = new FileSystemResource(new File("d:/sqlnet.ora"));
            helper.addAttachment("高级教程.mv", zipResource);


            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }


//    public static void sendMail(String to, String title, String text)  {
//
//        try
//        {
//            Properties props = new Properties();
//            props.put("mail.smtp.host","smtp.sina.com");
//            props.put("mail.smtp.auth","true");
//
//            //新建session对象
//            Session session = Session.getInstance(props);
//
//            //构建消息体
//            Message message = new MimeMessage(session);
//
//            //设置发件人
//            Address sendAddress = new InternetAddress("yaorange_mail@sina.com");
//            message.setFrom(sendAddress);
//            //设置收件人
//            Address toAdddress = new InternetAddress(to);
//            message.setRecipient(Message.RecipientType.TO,toAdddress);
//
//
//            //设置标题
//            message.setSubject(title);
//
//            //设置正文
//            message.setText(text);
//
//
//            //通过transport对象发送邮件
//            Transport transport =session.getTransport("smtp");
//            transport.connect("smtp.sina.com","yaorange_mail@sina.com","yaorange201703");
//
//            transport.sendMessage(message,message.getAllRecipients());
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//    }
}
