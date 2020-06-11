package com.infoshareacademy.oauth;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Stateless
public class OAuthManager {
    private static final List<String> SCOPE = List.of("profile", "openid", "email");
    private static final Logger logger = LoggerFactory.getLogger(OAuthManager.class.getName());

    public GoogleAuthorizationCodeFlow buildFlow() {
        return new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(), getProperty("client.id"),
                getProperty("client.secret"), SCOPE)
                .setAccessType("online")
                .build();
    }

    private String getProperty(String property) {
        Properties properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(Thread.currentThread()
                    .getContextClassLoader().getResource("oauth.properties"))
                    .openStream());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return properties.getProperty(property);
    }
}
