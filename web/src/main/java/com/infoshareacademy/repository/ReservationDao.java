package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.Reservation;
import com.infoshareacademy.domain.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Stateless
public class ReservationDao {
    private static final Logger STDLOG = LoggerFactory.getLogger(ReservationDao.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Reservation reservation) {
        entityManager.persist(reservation);
        STDLOG.info("Success in persisting reservation token: {}", reservation.getToken());
    }

    public void update(Reservation reservation) {
        entityManager.persist(reservation);
        STDLOG.info("Success in updating reservation token: {}", reservation.getToken());
    }

    public void delete(Reservation reservation) {
        entityManager.remove(reservation);
        STDLOG.info("Success in deleting reservation token: {} \n Probable cause : expired link", reservation.getToken());
    }

    public Optional<Reservation> findByToken(String token) {
        Query query = entityManager.createNamedQuery("Reservation.findByToken");
        query.setParameter("token", token);

        List result = query.getResultList();
        if (!result.isEmpty()) {
            STDLOG.info("Success in searching for reservation by token");
            return Optional.ofNullable((Reservation) result.get(0));
        } else {
            STDLOG.error("Problems during searching for reservation by token");
            return Optional.empty();
        }
    }

    public List<Reservation> findExpired() {
        Query query = entityManager.createNamedQuery("Reservation.findExpired");
        query.setParameter("now", LocalDateTime.now());
        return query.getResultList();
    }

    public List<Reservation> findByUser(User user) {
        Query query = entityManager.createNamedQuery("Reservation.findByUser");
        query.setParameter("user", user);
        return query.getResultList();
    }

}
