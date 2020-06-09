package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.Role;
import com.infoshareacademy.domain.view.RoleEnum;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Stateless
public class RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Role> findByRoleType(RoleEnum role) {
        Query query = entityManager.createNamedQuery("Role.findByRole");
        query.setParameter("role", role);
        return query.getResultList().stream().findFirst();
    }
}