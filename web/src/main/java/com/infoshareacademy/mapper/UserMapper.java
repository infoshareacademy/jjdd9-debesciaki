package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.entity.User;
import com.infoshareacademy.domain.view.UserView;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UserMapper {
    public UserView mapper(User user) {
        UserView userView = new UserView();
        userView.setId(user.getId());
        userView.setEmail(user.getMail());
        userView.setRole(user.getRole().getName().toString());
        return userView;
    }
}
