package com.infoshareacademy.servlet.login;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeCallbackServlet;
import com.google.api.client.http.GenericUrl;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;
import com.infoshareacademy.context.ContextHolder;
import com.infoshareacademy.domain.request.UserRequest;
import com.infoshareacademy.domain.view.UserView;
import com.infoshareacademy.mapper.GoogleUserMapper;
import com.infoshareacademy.oauth.OAuthBuilder;
import com.infoshareacademy.oauth.OAuthManager;
import com.infoshareacademy.service.UserService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/oauth2callback")
public class CallbackServlet extends AbstractAuthorizationCodeCallbackServlet {

    @EJB
    OAuthBuilder oAuthBuilder;

    @EJB
    OAuthManager oAuthManager;

    @Inject
    GoogleUserMapper googleUserMapper;

    @EJB
    UserService userService;

    @Override
    protected void onSuccess(HttpServletRequest req, HttpServletResponse resp, Credential credential) throws ServletException, IOException {
        Oauth2 oauth2 = oAuthBuilder.buildOauth(credential);
        Userinfoplus user = oauth2.userinfo().get().execute();
        UserRequest userRequest = googleUserMapper.mapGoogleResponseToUserRequest(user);
        UserView userView = userService.login(userRequest);
        new ContextHolder(req.getSession(), userView);
        resp.sendRedirect("/");
    }

    @Override
    protected AuthorizationCodeFlow initializeFlow() throws ServletException, IOException {
        return oAuthManager.buildFlow();
    }

    @Override
    protected String getRedirectUri(HttpServletRequest httpServletRequest) throws ServletException, IOException {
        GenericUrl url = new GenericUrl(httpServletRequest.getRequestURL().toString());
        url.setRawPath("/oauth2callback");
        return url.build();
    }

    @Override
    protected String getUserId(HttpServletRequest httpServletRequest) throws ServletException, IOException {
        return UUID.randomUUID().toString();
    }
}
