package com.infoshareacademy.repository.writer;

import com.infoshareacademy.domain.entity.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.List;

@Stateless
public class CategoryPersisterBean {
    @PersistenceContext
    EntityManager entityManager;

    public void persistEntityList(List<Category> list) throws IOException {
        for (Category o : list) {
            entityManager.merge(o);
        }
    }
}
