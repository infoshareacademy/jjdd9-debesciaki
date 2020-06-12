package com.infoshareacademy.service;

import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Set;

@Stateless
public class MailService {
    private static final Logger STDLOG = LoggerFactory.getLogger(MailService.class.getName());

    public void sendMailOnDeletion(Event event) {
        final String username = "debesciaki.mailing@gmail.com";
        final String password = "Debesciaki1!";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Set<User> users = event.getUsers();

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        for (User u : users) {
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("debesciaki.mailing@gmail.com"));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(u.getMail())
                );
                message.setSubject("Usunięto wydarzenie \"" + event.getName() + "\"");
                message.setText("Drogi użytkowniku,"
                        + "\n\n Team debeściaki chciałby Cię poinformować o usunięciu/odwołaniu wydarzenia, które znajdowało się wśród Twoich ulubionych: " + event.getName() + "." +
                        "\n\n Z wyrazami szacunku," +
                        "\n Team Debeściaki");

                Transport.send(message);


            } catch (MessagingException e) {
                STDLOG.info("Fail in sending email to {} \n error message:   ", u.getMail(), e.getMessage());
            }
        }
        STDLOG.info("Succes in sending emails to all of the addressed");
    }

}
