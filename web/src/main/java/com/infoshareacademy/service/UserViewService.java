package com.infoshareacademy.service;

import com.infoshareacademy.domain.entity.User;
import com.infoshareacademy.domain.view.UserView;
import com.infoshareacademy.repository.RoleDao;
import com.infoshareacademy.repository.UserDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserViewService {

    @Inject
    private UserDao userDao;

    @Inject
    private RoleDao roleDao;

    public List<UserView> prepareUsersToShow() {
        List<UserView> usersList = new ArrayList<>();

        for(User user : userDao.findAll()) {
            usersList.add(mapper(user));
        }

        return usersList;
    }

    public Integer sizeOfUsersList() {
        return prepareUsersToShow().size();
    }

    private UserView mapper(User user) {
        UserView userView = new UserView();
        userView.setEmail(user.getMail());
        userView.setRole(user.getRole().getName());
        return  userView;
    }

    public UserView findByEmail(String email) {
       return mapper(userDao.findByEmail(email).get());
    }

    public void changeRole(String email, String role) {
        UserView userView = findByEmail(email);
        User user = findFromDao(userView);
        user.setRole(roleDao.findByRoleType(role).get());
        userDao.update(user);
        userDao.save(user);
    }

    private User findFromDao(UserView userView) {
        return userDao.findByEmail(userView.getEmail()).get();
    }
 }
