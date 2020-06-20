package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.Category;
import com.infoshareacademy.domain.entity.Reservation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Stateless
public class ReservationDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Reservation reservation){
        entityManager.persist(reservation);
    }

    public Optional<Category> findById(long id) {
        try {
            return Optional.ofNullable(entityManager.find(Category.class, id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Category> findByApiId(long id) {
        Query query = entityManager.createNamedQuery("Category.findByApiId");
        query.setParameter("apiID", id);
        try {
            return Optional.ofNullable((Category) query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
