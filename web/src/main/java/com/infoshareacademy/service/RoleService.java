package com.infoshareacademy.service;

import com.infoshareacademy.entityDomain.Role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
//TODO zmienić to w serwis i zrobić do niego beana w repository
@Stateless
public class RoleService {
    @PersistenceContext
    private EntityManager entityManager;

    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    public Optional<Role> findRoleById(Long id) {
        return Optional.ofNullable(entityManager.find(Role.class, id));
    }
}
