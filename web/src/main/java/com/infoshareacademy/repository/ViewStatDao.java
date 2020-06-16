package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.ViewStat;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ViewStatDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(ViewStat viewStat) {
        entityManager.persist(viewStat);
    }

    public List<ViewStat> findAll() {
        Query query = entityManager.createNamedQuery("ViewStat.findAll");
        return query.getResultList();
    }
}
