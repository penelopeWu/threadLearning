package com.learn.javamail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {
    public static void main(String[] args) {
        String to = "15837113467@163.com";//收件人
        String from = "2516815643@qq.com";//发件人
        String host = "localhost";
        Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.host","localhost");
        Session session = Session.getDefaultInstance(properties);
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            mimeMessage.setSubject("This is the Subject Line");
            mimeMessage.setText("This is actual message");
            //mimeMessage.setContent("<h1>This is actual message</h1>","text/html" );
            Transport.send(mimeMessage);
            System.out.println("Send message successfully...");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
