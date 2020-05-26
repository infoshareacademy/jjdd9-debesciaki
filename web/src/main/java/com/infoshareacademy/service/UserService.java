package com.infoshareacademy.service;

import com.infoshareacademy.domain.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//TODO zmienić to w serwis i zrobić do niego beana w repository
@Stateless
public class UserService {
    @PersistenceContext
    private EntityManager entityManager;

    public void saveUser(User user) {
        entityManager.persist(user);
    }
}
