package com.project.ccc_shop.common;

import java.util.Date;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

public class SendLetter {
    public static void main(String[] args) {

        String user = "";
        String pwd = "";
        String toAddress = "";
        String host = "smtp.gmail.com";
        String subject = "subject";
        String message = "message";
        try {
            sendPlainTextEmail(toAddress, message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void sendPlainTextEmail(String toAddress, String message) throws MessagingException {

        String host = "smtp.gmail.com";
        String port = "587";
        String userName = "cccshop2022@gmail.com";
        String password = "CCCshop2022";
        String subject = "CCC Shop 購物訂單";

        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.user", userName);

        // creates a new session, no Authenticator (will connect() later)
        Session session = Session.getDefaultInstance(properties);

        // creates a new e-mail message
        Message mailMessage = new MimeMessage(session);
        mailMessage.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
        mailMessage.setRecipients(Message.RecipientType.TO, toAddresses);
        mailMessage.setSubject(subject);
        mailMessage.setSentDate(new Date());

        Multipart multi = new MimeMultipart();
        BodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setContent(message, "text/html; charset=utf-8");
        multi.addBodyPart(textBodyPart);

        // set plain text message
        mailMessage.setContent(multi);
        mailMessage.saveChanges();

        // sends the e-mail
        Transport t = session.getTransport("smtp");
        t.connect(userName, password);
        t.sendMessage(mailMessage, mailMessage.getAllRecipients());
        t.close();

    }
}
