package com.infoshareacademy.servlet.login;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeCallbackServlet;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;
import com.infoshareacademy.context.ContextHolder;
import com.infoshareacademy.domain.request.UserRequest;
import com.infoshareacademy.domain.view.UserView;
import com.infoshareacademy.mapper.GoogleUserMapper;
import com.infoshareacademy.oauth.OAuthBuilder;
import com.infoshareacademy.service.UserService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/oauth2callback")
public class CallbackServlet extends AbstractAuthorizationCodeCallbackServlet {

    @EJB
    OAuthBuilder oAuthBuilder;

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
        return null;
    }

    @Override
    protected String getRedirectUri(HttpServletRequest httpServletRequest) throws ServletException, IOException {
        return null;
    }

    @Override
    protected String getUserId(HttpServletRequest httpServletRequest) throws ServletException, IOException {
        return null;
    }
}
