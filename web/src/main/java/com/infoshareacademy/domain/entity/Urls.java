package com.infoshareacademy.domain.entity;

import javax.persistence.*;
@NamedQueries({
        @NamedQuery(
                name = "Urls.findByEventId",
                query = "SELECT u FROM Urls u WHERE u.event.id=:eventId"
        )
})
@Entity
@Table(name = "urls")
public class Urls {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "www")
    private String www;

    @Column(name = "tickets")
    private String tickets;

    @Column(name = "fb")
    private String fb;

    @OneToOne(mappedBy = "urls", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Event event;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
