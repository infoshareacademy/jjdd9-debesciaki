package com.infoshareacademy.mapper;

import com.google.api.services.oauth2.model.Userinfoplus;
import com.infoshareacademy.domain.request.UserRequest;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class GoogleUserMapper {
    public UserRequest mapGoogleResponseToUserRequest(Userinfoplus user) {
        UserRequest userRequest = new UserRequest();
        userRequest.setName(user.getName());
        userRequest.setEmail(user.getEmail());
        return userRequest;
    }
}
