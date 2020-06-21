package com.infoshareacademy.domain.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
@NamedQueries({
        @NamedQuery(
                name = "Reservation.findByToken",
                query = "SELECT r FROM Reservation r WHERE r.token = :token"
        ),
        @NamedQuery(
                name = "Reservation.findExpired",
                query = "SELECT r FROM Reservation r WHERE (r.expirationDate < :now AND r.confirmed = FALSE)"
        ),
})
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Boolean confirmed;

    @Column(name = "price_is_full")
    private Boolean isFull;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime endDate) {
        this.expirationDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Boolean getFull() {
        return isFull;
    }

    public void setFull(Boolean full) {
        isFull = full;
    }
}