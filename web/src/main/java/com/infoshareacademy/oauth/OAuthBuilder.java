package com.infoshareacademy.oauth;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;

import javax.ejb.Stateless;

@Stateless
public class OAuthBuilder {

    public Oauth2 buildOauth(Credential credential) {
        GoogleCredential googleCredential = new GoogleCredential().setAccessToken(credential.getAccessToken());

        return new Oauth2.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), googleCredential)
                        .setApplicationName("jjdd9-debesciaki").build();
    }

}
