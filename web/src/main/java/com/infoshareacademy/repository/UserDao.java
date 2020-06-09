package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserDao {

    @PersistenceContext
    EntityManager entityManager;

    public void save(User user) {
        entityManager.persist(user);
    }

    public void update(User user) {
        entityManager.merge(user);
    }

    public Optional<User> findByEmail(String email) {
        Query query = entityManager.createNamedQuery("User.findByEmail");
        query.setParameter("email", email);
        return query.getResultList().stream().findFirst();
    }

    public List<User> findAll() {
        Query query = entityManager.createNamedQuery("User.findAll");
        return query.getResultList();
    }
}