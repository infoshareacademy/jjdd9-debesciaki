package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Stateless
public class CategoryRepositoryBean {
    @PersistenceContext
    EntityManager entityManager;

    public Optional<Category> findById(long id) {
        return Optional.ofNullable(entityManager.find(Category.class, id));
    }
}
