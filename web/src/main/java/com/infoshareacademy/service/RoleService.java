package com.infoshareacademy.service;

import com.infoshareacademy.entity.Role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

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
