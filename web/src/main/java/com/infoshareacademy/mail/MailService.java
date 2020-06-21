package com.infoshareacademy.mail;

import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

@Stateless
public class MailService {
    private static final Logger STDLOG = LoggerFactory.getLogger(MailService.class.getName());

    public void sendMailsOnFinished(Event event) {
        Set<User> users = event.getUsers();

        for (User u : users) {
            sendEmail(new EventFinishedMail(event.getName()), u.getMail());
        }

        STDLOG.info("Succes in sending emails to all of the addressed");
    }

    public void sendEmail(MailCore emailMessageBuilder, String recipient) {
        final String username = getEmailProperty("username");
        final String password = getEmailProperty("password");

        Session session = Session.getInstance(getSessionProperties(),
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            Multipart multiPart = new MimeMultipart("alternative");
            message.setFrom(new InternetAddress(getEmailProperty("email")));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipient)
            );

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(emailMessageBuilder.buildContent(), "utf-8");
            multiPart.addBodyPart(textPart);
            try {
                message.setSubject(MimeUtility.encodeText(emailMessageBuilder.buildSubject(), "utf-8", "B"));
            } catch (UnsupportedEncodingException e) {
                STDLOG.info(String.format("Problem occurred when encoding the email title: %s", e.getMessage()));
            }
            message.setContent(multiPart);
            Transport.send(message);

        } catch (MessagingException e) {
            STDLOG.info(String.format("Problem occurred when sending an email: %s", e.getMessage()));
        }
    }

    private Properties getSessionProperties() {
        Properties properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(Thread.currentThread()
                    .getContextClassLoader().getResource("session.properties"))
                    .openStream());
        } catch (IOException e) {
            STDLOG.error(e.getMessage());
        }
        return properties;
    }

    public String getEmailProperty(String property) {
        Properties properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(Thread.currentThread()
                    .getContextClassLoader().getResource("mail.properties"))
                    .openStream());
        } catch (IOException e) {
            STDLOG.error(e.getMessage());
        }
        return properties.getProperty(property);
    }

}

