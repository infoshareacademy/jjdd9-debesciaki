package com.infoshareacademy.service;

import com.infoshareacademy.domain.entity.User;
import com.infoshareacademy.domain.request.UserRequest;
import com.infoshareacademy.domain.view.UserView;
import com.infoshareacademy.mapper.UserMapper;
import com.infoshareacademy.repository.RoleDao;
import com.infoshareacademy.repository.UserDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;


@Stateless
public class UserService {

    @EJB
    private UserDao userDao;

    @EJB
    private RoleDao roleDao;

    @Inject
    private UserMapper userMapper;


    public void save(User user) {
        userDao.save(user);
    }

    public User findByEmail(String email){
        return userDao.findByEmail(email).orElse(null);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User createUser(UserRequest userRequest) {
        User user = new User();
        user.setMail(userRequest.getEmail());
        user.setRole(roleDao.findByRoleType("USER").orElseThrow());
        save(user);
        return user;
    }

    public UserView login(UserRequest userRequest) {
        User user = userDao.findByEmail(userRequest.getEmail()).orElseGet(() -> createUser(userRequest));
        return userMapper.mapper(user);
    }
}
