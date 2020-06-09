package com.infoshareacademy.service;

import com.infoshareacademy.domain.entity.User;
import com.infoshareacademy.domain.view.UserView;
import com.infoshareacademy.repository.UserDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserViewService {

    @Inject
    UserDao userDao;

    public List<UserView> prepareUsersToShow() {
        List<UserView> usersList = new ArrayList<>();

        for(User user : userDao.findAll()) {
            usersList.add(mapper(user));
        }

        return usersList;
    }

    private UserView mapper(User user) {
        UserView userView = new UserView();
        userView.setEmail(user.getMail());
        userView.setRole(user.getRole().getName().toString());
        return  userView;
    }
 }
