package com.learn.javamail;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * QQ邮箱开启授权码，发送邮件
 */
public class SendMail2 {
    public static void main(String[] args) throws GeneralSecurityException, MessagingException {

        String to = "15837113467@163.com";//收件人
        String from = "2516815643@qq.com";//发件人
        String host = "smtp.qq.com";//发送邮件的主机：QQ邮箱服务器

        Properties prop = System.getProperties();//获取系统属性

        prop.setProperty("mail.smtp.host",host);//设置邮件服务器

        // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可
        prop.put("mail.smtp.auth",true);
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);


        //获取默认session对象
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //发件人邮件用户名，密码
                return new PasswordAuthentication("2516815643@qq.com","sxgjgzeknqjoebgh");
            }
        });

            //创建默认的MimeMessage对象
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));//set from 头部头字段
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));//set to 头部头字段
            message.setSubject("This is the subject line...");//邮件主题
            message.setText("This is actual message...");//邮件正文
            Transport.send(message);//发送邮件
            System.out.println("send message successfully...form runoob.com");

    }
}





