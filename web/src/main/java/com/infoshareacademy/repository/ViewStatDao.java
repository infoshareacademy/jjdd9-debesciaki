package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.ViewStat;
import com.infoshareacademy.domain.view.stat.chart.ClicksPerEvent;

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

    public List<ClicksPerEvent> findGlobalClicksPerEvent() {
        Query query = entityManager.createNamedQuery("ViewStat.globalClicksPerEvent");
        return query.getResultList();
    }
}
