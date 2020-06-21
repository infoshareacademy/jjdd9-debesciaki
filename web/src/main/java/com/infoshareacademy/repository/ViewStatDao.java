package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.ViewStat;
import com.infoshareacademy.domain.stat.ClicksPerEvent;
import com.infoshareacademy.domain.stat.ClicksPerOrganizer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
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

    public List<ClicksPerEvent> findPeriodClicksPerEvent(LocalDateTime date1, LocalDateTime date2) {
        Query query = entityManager.createNamedQuery("ViewStat.periodClicksPerEvent");
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);
        return query.getResultList();
    }

    public List<ClicksPerOrganizer> findGlobalClicksPerOrganizer() {
        Query query = entityManager.createNamedQuery("ViewStat.globalClicksPerOrganizer");
        return query.getResultList();
    }

    public List<ClicksPerOrganizer> findPeriodClicksPerOrganizer(LocalDateTime date1, LocalDateTime date2) {
        Query query = entityManager.createNamedQuery("ViewStat.periodClicksPerOrganizer");
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);
        return query.getResultList();
    }

    public void delete(Long eventId) {
        List<ViewStat> viewStats = findAll();
        for (ViewStat el : viewStats) {
            if (el.getEvent().getId().equals(eventId)) {
                el.setEvent(null);
                el.setUser(null);
                el.setViewDate(null);
                entityManager.remove(el);
            }
        }
    }


}
