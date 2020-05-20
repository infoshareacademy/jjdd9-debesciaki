package com.infoshareacademy.service;

import com.infoshareacademy.entity.User;
import com.infoshareacademy.servlet.LoggerCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserService {
//    private static final Logger LOGGER = LoggerFactory.getLogger("UserService");

    @PersistenceContext
    private EntityManager entityManager;

    public void saveUser() {
        User user = new User();
        user.setId(1L);
        user.setMail("user@gmail.com");
        user.setRoleId(1);
        entityManager.persist(user);
      //  LOGGER.info("User succesfully added Id: {} mail: {} role: {}",user.getId(),user.getMail(),user.getRoleId());
    }
}
