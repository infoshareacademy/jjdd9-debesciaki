package com.infoshareacademy.servlet.login;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import com.google.api.client.http.GenericUrl;
import com.infoshareacademy.oauth.OAuthManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/loginGoogle")
public class GoogleLoginServlet extends AbstractAuthorizationCodeServlet {

    @EJB
    OAuthManager oAuthManager;

    @Override
    protected AuthorizationCodeFlow initializeFlow() throws ServletException, IOException {
        return oAuthManager.buildFlow();
    }

    @Override
    protected String getRedirectUri(HttpServletRequest httpServletRequest) throws ServletException, IOException {
        GenericUrl genericUrl = new GenericUrl(httpServletRequest.getRequestURL().toString());
        genericUrl.setRawPath("/oauth2callback");

        return genericUrl.build();
    }

    @Override
    protected String getUserId(HttpServletRequest httpServletRequest) throws ServletException, IOException {
        return UUID.randomUUID().toString();
    }
}
